package com.aaw.sys.ctl;

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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.ProductionException;
import com.aaw.sys.service.IProductionExceptionService;

@Controller
@RequestMapping("ProductionException")
public class ProductionExceptionCtl extends BaseCtl<ProductionException> {

	@RequestMapping(params = "act=view")
	public ModelAndView view() {
		return new ModelAndView("sys/ProductionException/ProductionException");
	}

	@RequestMapping(params = "act=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("ProductionExceptionCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=save")
	@ResponseBody
	public Object save(ProductionException bean) {
		try {
			service.save(bean);
			return suc("添加成功！");
		} catch (Exception e) {
			log.error("ProductionExceptionCtl保存对象异常", e);
		}
		return err("添加失败！");
	}

	@RequestMapping(params = "act=update")
	@ResponseBody
	public Object update(ProductionException bean) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("ProductionExceptionCtl修改对象异常", e);
		}
		return err("修改失败");
	}

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("ProductionExceptionCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@RequestMapping(params = "act=combobox")
	@ResponseBody
	public Object combobox() {
		try {
			List<Object[]> list = service.list4Props("id", "name");
			if (!npv.isNull(list)) {
				List<Map<Object, Object>> reList = new ArrayList<Map<Object, Object>>();
				for (Object[] objs : list) {
					Map<Object, Object> map = new HashMap<Object, Object>();
					map.put("id", objs[0]);
					map.put("text", objs[1]);
					reList.add(map);
				}
				return reList;
			}
		} catch (Exception e) {
			log.error("ProductionExceptionCtl用于获取候选信息异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=del")
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
			log.error("ProductionExceptionCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@Resource
	private IProductionExceptionService service;

}