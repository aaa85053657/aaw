package com.aaw.sys.ctl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.PropTool;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;
import com.aaw.sys.service.IProductAttributeService;
import com.aaw.sys.service.IProductElementService;

@Controller
@RequestMapping("productAttribute")
public class ProductAttributeCtl extends BaseCtl<ProductAttribute> {

	@Resource
	private IProductAttributeService service;

	private static String rootPath = PropTool.get("rootpath");

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/attribute/attrs");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductAttributeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=ElementList")
	@ResponseBody
	public Object listProfile(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.listElement(id);
		} catch (Exception e) {
			log.error("ProductAttributeCtl查询相关AttributeElement列表信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductAttribute bean, HttpServletRequest request) {
		String path = rootPath + bean.getPath();
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			file.mkdirs();
		}
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductAttributeCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductAttribute bean, HttpServletRequest request) {
		ProductAttribute attribute = service.query(bean.getId());
		if (!bean.getPath().equals(attribute.getPath())) {
			String oldpath = rootPath + attribute.getPath();
			String newpath = rootPath + bean.getPath();
			File oldfileDir = new File(oldpath);
			File newfileDir = new File(newpath);
			if (!newfileDir.exists() || !newfileDir.isDirectory()) {
				newfileDir.mkdirs();
			}
			String[] filePath = oldfileDir.list();
			if (filePath != null && filePath.length > 0) {
				for (int i = 0; i < filePath.length; i++) {
					new MovePic(oldpath, newpath, filePath[i]).start();
				}
			}
			oldfileDir.delete();

		}
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("ProductAttributeCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (service.deleteAndCheck(id)) {
				return delSuc(request);
			} else {
				return delRef(request);
			}
		} catch (Exception e) {
			log.error("ProductAttributeCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("ProductAttributeCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox() {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox();
		} catch (Exception e) {
			log.error("ProductAttributeCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=delElement")
	@ResponseBody
	public Object delElement(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			if (id > 0) {
				productElementService.delete(id);
				return delSuc(request);
			}
		} catch (Exception e) {
			log.error("MetaProcedureCtl通过config对象ID删除关联关系异常", e);
		}
		return delFail(request);
	}

	@Resource
	private IProductElementService productElementService;

	private class MovePic extends Thread {
		private String oldpath;
		private String newpath;
		private String fileName;

		public MovePic(String oldpath, String newpath, String fileName) {
			super();
			this.oldpath = oldpath;
			this.newpath = newpath;
			this.fileName = fileName;
		}

		@Override
		public void run() {
			File oldFile = new File(oldpath + "\\" + fileName);
			File newfile = new File(newpath + "\\" + fileName);
			FileInputStream in = null;
			FileOutputStream out = null;
			try {
				in = new FileInputStream(oldFile);
				out = new FileOutputStream(newfile);
				byte[] buffer = new byte[2097152];
				while ((in.read(buffer)) != -1) {
					out.write(buffer);
				}
				out.flush();
				out.close();
				in.close();
				oldFile.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<ProductElement> list = productElementService
					.findByPath(oldpath + "\\" + fileName);
			for (ProductElement p : list) {
				p.setSamplepath(newpath + "\\" + fileName);
				productElementService.update(p);
			}
		}

	}

}