package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.UpmsAssets;
import com.aaw.sys.service.IUpmsAssetsService;

@Controller
@RequestMapping("UpmsAssets")
public class UpmsAssetsCtl extends BaseCtl<UpmsAssets> {

	@RequestMapping(params = "act=view")
	public ModelAndView view() {
		return new ModelAndView("sys/upms/UpmsAssets");
	}

	@RequestMapping(params = "act=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("UpmsAssetsCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=listAll")
	@ResponseBody
	public Object listAll(HttpServletRequest request) {
		try {
			return service.listAll(request);
		} catch (Exception e) {
			log.error("UpmsAssetsCtl获取信息列表异常", e);
		}
		return new ArrayList<Map<String, Object>>();
	}

	@RequestMapping(params = "act=list4bind")
	@ResponseBody
	public Object list4Bind(@RequestParam(defaultValue = "0") int roleID) {
		try {
			return service.list4Bind(roleID);
		} catch (Exception e) {
			log.error("UpmsAssetsCtl获取绑定信息列表异常", e);
		}
		return new ArrayList<Map<String, Object>>();
	}

	@RequestMapping(params = "act=list4BindIDs")
	@ResponseBody
	public Object list4BindIDs(@RequestParam(defaultValue = "0") int roleID) {
		try {
			return service.list4BindIDs(roleID);
		} catch (Exception e) {
			log.error("UpmsAssetsCtl获取绑定信息列表异常", e);
		}
		return new ArrayList<Map<String, Object>>();
	}

	@RequestMapping(params = "act=save")
	@ResponseBody
	public Object save(UpmsAssets bean) {
		try {
			service.save(bean);
			return suc("添加成功！");
		} catch (Exception e) {
			log.error("UpmsAssetsCtl保存对象异常", e);
		}
		return err("添加失败！");
	}

	@RequestMapping(params = "act=update")
	@ResponseBody
	public Object update(UpmsAssets bean) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("UpmsAssetsCtl修改对象异常", e);
		}
		return err("修改失败");
	}

	@RequestMapping(params = "act=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id) {
		try {
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("UpmsAssetsCtl通过ID删除对象异常", e);
		}
		return err("删除失败");
	}

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("UpmsAssetsCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@Resource
	private IUpmsAssetsService service;

}