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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.sys.service.ISlaveCommandeConfigService;

@Controller
@RequestMapping("slaveCommandeConfig")
public class SlaveCommandeConfigCtl extends BaseCtl<SlaveCommandeConfig> {

	@Resource
	private ISlaveCommandeConfigService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(SlaveCommandeConfig bean, HttpServletRequest request) {
		try {
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(SlaveCommandeConfig bean, HttpServletRequest request) {
		try {
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			service.delete(id);
			return suc("删除成功");
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl通过ID删除对象异常", e);
		}
		return err("删除异常");
	}

	/**
	 * @param id
	 *            副单ID号
	 * @return
	 */
	@RequestMapping(params = "goto=slaveDetail")
	@ResponseBody
	public Object slaveDetail(@RequestParam(defaultValue = "0") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id > 0) {
				map = service.slaveDetail(id);
			}
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl根据副单ID查询记录异常", e);
		}
		return map;
	}

	/**
	 * @param id
	 *            副单ID号
	 * @return
	 */
	@RequestMapping(params = "goto=slaveDetail2")
	@ResponseBody
	public Object slaveDetail2(@RequestParam(defaultValue = "0") int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id > 0) {
				map = service.slaveDetail2(id);
			}
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl根据副单ID查询记录异常", e);
		}
		return map;
	}

	/**
	 * @param id
	 *            型号ID
	 * @param cid
	 *            组件ID
	 * @param aid
	 *            属性ID
	 * @return
	 */
	@RequestMapping(params = "goto=modelElems")
	@ResponseBody
	public Object modelElems(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int cid,
			@RequestParam(defaultValue = "0") int aid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (id > 0) {
				map = service.modelElems(id, cid, aid);
			}
		} catch (Exception e) {
			log.error("SlaveCommandeConfigCtl根据副单ID查询记录异常", e);
		}
		return map;
	}

}