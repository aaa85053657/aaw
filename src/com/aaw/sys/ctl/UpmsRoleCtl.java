package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.UpmsRole;
import com.aaw.sys.service.IRoleAssetsConfigService;
import com.aaw.sys.service.IUpmsAccountService;
import com.aaw.sys.service.IUpmsRoleService;

@Controller
@RequestMapping("UpmsRole")
public class UpmsRoleCtl extends BaseCtl<UpmsRole> {

	@RequestMapping(params = "act=view")
	public ModelAndView view() {
		return new ModelAndView("sys/upms/UpmsRole");
	}

	@RequestMapping(params = "act=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("UpmsRoleCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "act=save")
	@ResponseBody
	public Object save(UpmsRole bean) {
		try {
			service.save(bean);
			return suc("添加成功！");
		} catch (Exception e) {
			log.error("UpmsRoleCtl保存对象异常", e);
		}
		return err("添加失败！");
	}

	@RequestMapping(params = "act=update")
	@ResponseBody
	public Object update(UpmsRole bean) {
		try {
			service.update(bean);
			return suc("修改成功");
		} catch (Exception e) {
			log.error("UpmsRoleCtl修改对象异常", e);
		}
		return err("修改失败");
	}

	@RequestMapping(params = "act=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int b) {
		try {
			if (b == 2 || b == 3) {// 删除绑定资源信息
				roleAssetsConfigService.unbind(id);
			}
			if (b == 1 || b == 3) {
				accountService.updateByPropAndPropVal("upmsRoleId", null,
						"upmsRoleId", id);
			}
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("UpmsRoleCtl通过ID删除对象异常", e);
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
			log.error("UpmsRoleCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	@RequestMapping(params = "act=refEmp")
	@ResponseBody
	public Object refrencedEmp(@RequestParam(defaultValue = "0") int id) {
		try {
			return accountService.refEmp(id);
		} catch (Exception e) {
			log.error("UpmsRoleCtl检查角色被引用异常", e);
		}
		return -1;
	}

	@RequestMapping(params = "act=bind")
	@ResponseBody
	public Object bind(@RequestParam(value = "rids[]") Integer[] rids,
			@RequestParam(defaultValue = "0") int id) {
		try {
			service.bind(id, rids);
			return suc("分配资源成功！");
		} catch (Exception e) {
			log.error("UpmsRoleCtl分配角色资源异常", e);
		}
		return err("分配资源失败！");
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
			log.error("UpmsRoleCtl用于获取候选信息异常", e);
		}
		return null;
	}

	@Resource
	private IUpmsRoleService service;

	@Resource
	private IRoleAssetsConfigService roleAssetsConfigService;

	@Resource
	private IUpmsAccountService accountService;

}