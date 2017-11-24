package com.aaw.sys.ctl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.PropTool;
import cn.molos.common.SessionConstant;
import cn.molos.util.ExcelTool;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Franchises;
import com.aaw.bean.ProductAttribute;
import com.aaw.bean.ProductElement;
import com.aaw.sys.service.IProductAttributeService;
import com.aaw.sys.service.IProductElementService;

@Controller
@RequestMapping("productElement")
public class ProductElementCtl extends BaseCtl<ProductElement> {

	@Resource
	private IProductElementService service;
	@Resource
	private IProductAttributeService attributeService;

	private static String rootPath = PropTool.get("rootpath");

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/attrValue/attrVal");
	}

	@RequestMapping("upload")
	@ResponseBody
	public Object upload(
			@RequestParam(value = "sample", required = false) MultipartFile sample,
			HttpServletRequest request) {
		try {
			String path = rootPath + "temp";
			String suffix = sample.getOriginalFilename();
			File dir = new File(path);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdirs();
			}
			File targetFile = new File(dir, suffix.toLowerCase());
			sample.transferTo(targetFile);
			request.getSession().setAttribute(SessionConstant.IMG_ABSPATH,
					targetFile.getAbsolutePath());
			return 1;
		} catch (Exception e) {
			log.error("ProductElementCtl上传示例图片异常", e);
		}
		return -1;
	}

	@RequestMapping("show")
	public void show(HttpSession session, HttpServletResponse rsp) {
		try {
			try {
				File file = new File(session.getAttribute(
						SessionConstant.IMG_ABSPATH).toString());
				OutputStream os = rsp.getOutputStream();
				FileInputStream fips = new FileInputStream(file);
				byte[] btImg = readStream(fips);
				os.write(btImg);
				os.flush();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			log.error("ProductElementCtl显示示例图片异常", e);
		}
	}

	@RequestMapping("showById")
	public void showById(@RequestParam(defaultValue = "0") int id,
			HttpServletResponse rsp) {
		try {
			try {
				ProductElement ele = service.query(id);
				if (!npv.isNull(ele.getSamplepath())) {
					File file = new File(ele.getSamplepath());
					OutputStream os = rsp.getOutputStream();
					FileInputStream fips = new FileInputStream(file);
					byte[] btImg = readStream(fips);
					os.write(btImg);
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			log.error("ProductElementCtl显示示例图片异常", e);
		}
	}

	/**
	 * 读取管道中的流数据
	 */
	private byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			inStream.close();
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductElementCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(ProductElement bean, HttpServletRequest request) {
		try {
			Object obj = request.getSession().getAttribute(
					SessionConstant.IMG_ABSPATH);
			if (!npv.isNullObj(obj)) {
				String oldPath = obj.toString();
				File oldFile = new File(oldPath);
				ProductAttribute pa = attributeService.query(bean
						.getAttribute().getId());
				String newPath = rootPath + pa.getPath() + "/" + bean.getName()
						+ ".png";
				File newfile = new File(newPath);
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
					in.close();
					out.close();
					oldFile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				bean.setSamplepath(newPath);
			}
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("ProductElementCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(ProductElement bean, HttpServletRequest request) {
		try {
			Object obj = request.getSession().getAttribute(
					SessionConstant.IMG_ABSPATH);
			if (!npv.isNullObj(obj)) {
				String oldPath = obj.toString();
				ProductAttribute pa = attributeService.query(bean
						.getAttribute().getId());
				String newPath = rootPath + pa.getPath() + "/" + bean.getName()
						+ ".png";
				File oldFile = new File(oldPath);
				File newfile = new File(newPath);
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
					in.close();
					out.close();
					oldFile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				bean.setSamplepath(newPath);
			} else {
				ProductElement pe = service.query(bean.getId());
				bean.setSamplepath(pe.getSamplepath());
			}
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("ProductElementCtl修改对象异常", e);
		}
		return err("修改异常");
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
			log.error("ProductElementCtl通过ID删除对象异常", e);
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
			log.error("ProductElementCtl验证是否唯一异常", e);
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
			log.error("CountryCtl查询候选列表异常异常", e);
		}
		return map;
	}

	@RequestMapping(params = "goto=imgrefresh")
	@ResponseBody
	public Object imgrefresh() {
		List<ProductElement> list = service.list();
		int pic = 0;
		int picNull = 0;
		for (ProductElement p : list) {
			if (p.getSamplepath() == null || p.getSamplepath().equals("")
					|| !new File(p.getSamplepath()).exists()) {
				String path = rootPath + p.getAttribute().getPath() + "/"
						+ p.getName().trim() + ".png";
				System.out.println(path);
				File file = new File(path);
				if (file.exists() && !file.isDirectory()) {
					p.setSamplepath(path);
					service.update(p);
					pic += 1;
				} else {
					p.setSamplepath(null);
					service.update(p);
					picNull += 1;
				}
			} else {
				pic += 1;
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pic", pic);
		map.put("picNull", picNull);
		return map;
	}

	@RequestMapping(params = "goto=downloadEle")
	public void downloadEle(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-excel");
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		String fileName = System.currentTimeMillis() + ".xls";
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		try {
			OutputStream out = response.getOutputStream();
			List<ProductElement> temp = service.getPicNull();
			ArrayList<List<Object>> list = service.getList(temp);
			new ExcelTool(1).creatWorkBook(list, 3).write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class ShowPic extends Thread {
		private int id;
		private HttpServletResponse rsp;

		public ShowPic(int id, HttpServletResponse rsp) {
			this.id = id;
			this.rsp = rsp;
		}

		@Override
		public void run() {
			try {
				ProductElement ele = service.query(id);
				if (!npv.isNull(ele.getSamplepath())) {
					File file = new File(ele.getSamplepath());
					OutputStream os = rsp.getOutputStream();
					FileInputStream fips = new FileInputStream(file);
					byte[] btImg = readStream(fips);
					os.write(btImg);
					os.flush();
					os.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
