package com.aaw.sys.ctl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.CommandeStatusTemp;
import com.aaw.sys.service.ICommandeStatusTempService;

@Controller
@RequestMapping("commandeStatusTemp")
public class CommandeStatusTempCtl extends BaseCtl<CommandeStatusTemp> {

	@Resource
	private ICommandeStatusTempService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view(@RequestParam(defaultValue = "0") Integer id) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("start", service.hasStartNode() ? 1 : 0);
		m.put("end", 1);
		m.put("groupId", id);
		return new ModelAndView("sys/commandeStatusTemp/commandeStatusTemp", m);
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "0") Integer gid) {
		try {
			return service.treeList(gid);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(CommandeStatusTemp bean, HttpServletRequest request) {
		try {
			int id = service.save(bean);
			if (bean.getParentId() > 0) {
				service.updateChidNum(bean.getParentId());
			}
			if (bean.getPreviousId() > 0) {
				service.updatePreNode(bean.getPreviousId(), id);
			}
			return saveSuc(request);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=saveSpec")
	@ResponseBody
	public Object saveSpec(int previousID, HttpServletRequest request, int gid) {
		try {
			CommandeStatusTemp bean = new CommandeStatusTemp();
			bean.setHasChildren(0);
			bean.setIsSendMail(1);
			bean.setName(new RequestContext(request)
					.getMessage("cst.save.specND"));
			bean.setPreviousId(previousID);
			bean.setParentId(0);
			bean.setGroupId(gid);
			int id = service.save(bean);
			service.updatePreNode(previousID, id);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(CommandeStatusTemp bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int cNum,
			HttpServletRequest request) {
		try {
			service.delChildren(id, cNum);
			return delSuc(request);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int gid) {
		try {
			return service.uniqueByGID(service, propName, propVal, id, gid);
		} catch (Exception e) {
			log.error("CommandeStatusTempCtl验证是否唯一异常", e);
		}
		return 2;
	}

}
