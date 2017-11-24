package com.aaw.sys.ctl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import cn.molos.common.SessionConstant;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.CommandeType;
import com.aaw.bean.FactoryStatus;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesCommandeRelation;
import com.aaw.bean.MainCommande;
import com.aaw.bean.SlaveCommande;
import com.aaw.bean.SlaveCommandeConfig;
import com.aaw.bean.SlaveCommandeFreeConfig;
import com.aaw.bean.Worksheet;
import com.aaw.sys.form.CommandeCfg;
import com.aaw.sys.form.MDConditions;
import com.aaw.sys.service.ICommandeTypeService;
import com.aaw.sys.service.IEmployeeService;
import com.aaw.sys.service.IFactoryStatusDetailService;
import com.aaw.sys.service.IFactoryStatusService;
import com.aaw.sys.service.IFranchisesCommandeService;
import com.aaw.sys.service.IFranchisesService;
import com.aaw.sys.service.IMainCommandeService;
import com.aaw.sys.service.ISlaveCommandeConfigService;
import com.aaw.sys.service.ISlaveCommandeFreeConfigService;
import com.aaw.sys.service.ISlaveCommandeService;
import com.aaw.sys.service.IWorksheetDetailService;
import com.aaw.sys.service.IWorksheetDetailTempService;
import com.aaw.sys.service.IWorksheetService;
import com.aaw.sys.service.IWorkstationService;

@Controller
@RequestMapping("mainCommande")
public class MainCommandeCtl extends BaseCtl<MainCommande> {

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/mainCommande/mainCommande2");
	}

	@RequestMapping(params = "goto=view2")
	public ModelAndView view2() {
		return new ModelAndView("sys/mainCommande/mainCommande3");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("MainCommandeCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=listMDC")
	@ResponseBody
	public Object listByMDConditions(
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows, MDConditions cc) {
		try {
			return service.map(page, rows, cc, 1);
		} catch (Exception e) {
			log.error("MainCommandeCtl列表根据条件查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=listMDC2")
	@ResponseBody
	public Object listByMDConditions2(HttpSession session,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows, MDConditions cc) {
		try {
			int type = (Integer) session
					.getAttribute(SessionConstant.LOGIN_USER_TYPE);
			if (type == 1) {
				return service.map(page, rows, cc, 1);
			} else {
				Franchises franchises = (Franchises) session
						.getAttribute(SessionConstant.LOGIN_USER);
				List<Franchises> temp = franchisesService
						.findByFran(franchises);
				return service.map2(page, rows, cc, temp);
			}

		} catch (Exception e) {
			log.error("MainCommandeCtl列表根据条件查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=listMDC3")
	@ResponseBody
	public Object listByMDConditions3(HttpSession session,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows, MDConditions cc) {
		try {
			int type = (Integer) session
					.getAttribute(SessionConstant.LOGIN_USER_TYPE);
			if (type == 1) {
				return service.map(page, rows, cc, 2);
			} else {
				Franchises franchises = (Franchises) session
						.getAttribute(SessionConstant.LOGIN_USER);
				List<Franchises> temp = franchisesService
						.findByFran(franchises);
				return service.map2(page, rows, cc, temp);
			}

		} catch (Exception e) {
			log.error("MainCommandeCtl列表根据条件查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(MainCommande bean, HttpServletRequest request) {
		try {
			int fid = 0;
			bean.setTimeCreation(new Date());
			bean.setOrderStatus(1);
			if (bean.getType().getId() == 2) {
				fid = bean.getSeller().getId();
				bean.setSeller(null);
			}
			int id = service.save(bean);
			CommandeType ct = typeService.query(bean.getType().getId());
			service.initStatusData2(id, ct.getCsg().getId());
			if (fid != 0) {
				FranchisesCommandeRelation fcr = new FranchisesCommandeRelation();
				Franchises franchises = new Franchises(fid);
				fcr.setCommande(bean);
				fcr.setFranchises(franchises);
				fcr.setCreateTime(new Date());
				fcr.setModificationTime(new Date());
				commandeService.save(fcr);
			}
			return saveSuc(request);
		} catch (Exception e) {
			log.error("MainCommandeCtl保存对象异常", e);
		}
		return saveFail(request);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "goto=hasSalve")
	@ResponseBody
	public Object query(int id) {
		try {
			if (service.hasSalve(id)) {
				return -1;
			}
			return 1;
		} catch (Exception e) {
			log.error("MainCommandeCtl查询异常", e);
		}
		return -1;
	}

	@RequestMapping(params = "goto=saveCfg")
	@ResponseBody
	public Object saveCfg(@RequestParam(defaultValue = "0") int id,
			CommandeCfg bean, HttpServletRequest request) {
		try {
			if (id > 0) {
				MainCommande commande = service.query(id);
				commande.setOrderStatus(2);
				service.update(commande);
				bean.getCommande().setMainCommande(new MainCommande(id));
				service.saveCfg(bean);
				return suc(new RequestContext(request)
						.getMessage("mCommande.cfg.saveSuc"));
			}
		} catch (Exception e) {
			log.error("MainCommandeCtl保存配置异常", e);
		}
		return err(new RequestContext(request)
				.getMessage("mCommande.cfg.saveFail"));
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(MainCommande bean, HttpServletRequest request) {
		try {
			int fid = 0;
			MainCommande mc = service.query(bean.getId());
			if (mc.getType().getId() == 2) {
				fid = bean.getSeller().getId();
				bean.setSeller(null);
			}
			bean.setTimeModification(new Date());
			bean.setType(mc.getType());
			service.update(bean);
			if (fid != 0) {
				FranchisesCommandeRelation fcr = commandeService
						.findByCommande(bean.getId());
				if (fcr != null) {
					Franchises franchises = new Franchises(fid);
					fcr.setFranchises(franchises);
					fcr.setModificationTime(new Date());
					commandeService.update(fcr);
				} else {
					fcr = new FranchisesCommandeRelation();
					Franchises franchises = new Franchises(fid);
					fcr.setFranchises(franchises);
					fcr.setCommande(bean);
					fcr.setFranchises(franchises);
					fcr.setCreateTime(new Date());
					fcr.setModificationTime(new Date());
					commandeService.save(fcr);
				}

			}
			return updateSuc(request);
		} catch (Exception e) {
			log.error("MainCommandeCtl修改对象异常", e);
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
				// return delRef(request);
				return err(new RequestContext(request)
						.getMessage("crud.del.cue"));

			}
		} catch (Exception e) {
			log.error("MainCommandeCtl通过ID删除对象异常", e);
		}
		return err(new RequestContext(request).getMessage("crud.del.cue"));
		// return delFail(request);
	}

	@RequestMapping(params = "goto=unique")
	@ResponseBody
	public Object unique(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("MainCommandeCtl验证是否唯一异常", e);
		}
		return 2;
	}

	@RequestMapping(params = "goto=unique2")
	@ResponseBody
	public Object unique2(String propName, String propVal,
			@RequestParam(defaultValue = "0") int id) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("MainCommandeCtl验证是否唯一异常", e);
		}
		return 2;
	}

	private String sixAdd3(int x) {
		String str = String.valueOf(x);
		int c = 8 - str.length();
		String r = "";
		for (int i = 0; i < c; i++) {
			r += "0";
		}
		r += str;
		return r;
	}

	@RequestMapping(params = "goto=scode")
	@ResponseBody
	public Object showCodeId() {
		try {
			String str = service.showCodeId();
			String start = str.substring(0, 5);
			String end = str.substring(5);
			String code = start + sixAdd3(Integer.valueOf(end) + 1);
			// service.updateCode(code);
			return code;
		} catch (Exception e) {
			log.error("MainCommandeCtl验证是否唯一异常", e);
		}
		return -1;
	}

	@RequestMapping(params = "goto=scode2")
	@ResponseBody
	public Object showCodeId2() {
		try {
			String str = service.showCodeId2();
			String start = str.substring(0, 5);
			String end = str.substring(5);
			String code = start + sixAdd3(Integer.valueOf(end) + 1);
			// service.updateCode2(code);
			return code;
		} catch (Exception e) {
			log.error("MainCommandeCtl验证是否唯一异常", e);
		}
		return -1;
	}

	@RequestMapping(params = "goto=cfgList")
	@ResponseBody
	public Object cfgList(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.cfgList(id);
		} catch (Exception e) {
			log.error("MainCommandeCtl查询相关profile列表信息异常", e);
		}
		return new HashMap<String, Object>();
	}

	@RequestMapping(params = "goto=stList")
	@ResponseBody
	public Object stList(@RequestParam(defaultValue = "0") int id) {
		try {
			return service.stList(id);
		} catch (Exception e) {
			log.error("MainCommandeCtl列表查询异常", e);
		}
		return null;
	}

	/**
	 * @param mid
	 *            订单号
	 * @param id
	 *            订单状态ID号
	 * @param st
	 *            订单状态值
	 * @return
	 */
	@RequestMapping(params = "goto=saveStChange")
	@ResponseBody
	public Object saveStChange(@RequestParam(defaultValue = "0") int mid,
			@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") int st, HttpServletRequest request) {
		try {
			String tip = "md.st.change.suc";
			if (service.saveStChange(mid, id, st,
					new RequestContext(request).getMessage("cst.save.specND"))) {
				tip = "md.st.change.suc2";
			}
			return suc(new RequestContext(request).getMessage(tip));
		} catch (Exception e) {
			log.error("MainCommandeCtl订单状态变更异常", e);
		}
		return err(new RequestContext(request).getMessage("md.st.change.fail"));
	}

	/**
	 * @param id
	 *            订单状态ID号
	 * @return
	 */
	@RequestMapping(params = "goto=initSt")
	@ResponseBody
	public Object initSt(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			MainCommande commande = service.query(id);
			if (commande.getOrderStatus() != 1) {
				commande.setOrderStatus(2);
			}
			service.update(commande);
			int wid = service.isInit(id);
			int sid = service.isInit2(id);
			if (wid != -1) {
				worksheetDetailService.endTask(wid);
				Worksheet worksheet = worksheetService.query(wid);
				worksheet.setTimeAbort(new Date());
				worksheet.setTimeDelete(new Date());
				worksheet.setSlaveCommande(null);
				worksheetService.update(worksheet);
				FactoryStatus status = factoryStatusService
						.findByWorksheet(worksheet.getId());
				status.setWorksheet(null);
				status.setSlaveCommande(null);
				status.setWorkStatus(9);
				status.setOptStatus(9);
				status.setStatus(0);
				factoryStatusService.update(status);
			} else if (wid == -1 && sid != -1) {
				FactoryStatus status = factoryStatusService.findBySlave(sid);
				if (status != null) {
					status.setWorksheet(null);
					status.setSlaveCommande(null);
					status.setWorkStatus(9);
					status.setOptStatus(9);
					status.setStatus(0);
					factoryStatusService.update(status);
				}
			}
			service.initSt(id);
			return suc(new RequestContext(request).getMessage("md.st.init.suc"));
		} catch (Exception e) {
			log.error("MainCommandeCtl订单状态初始化异常", e);
		}
		return err(new RequestContext(request).getMessage("md.st.init.fail"));
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox() {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		try {
			map = service.combobox();
		} catch (Exception e) {
			log.error("MainCommandeCtl查询候选列表异常", e);
		}
		return map;
	}

	@RequestMapping(params = "act=labelById")
	@ResponseBody
	public Object labelById(@RequestParam(defaultValue = "0") int id) {
		SlaveCommande commande = slaveCommandeService.query(id);
		List<SlaveCommandeConfig> clist = configService.findBySC(id);
		List<SlaveCommandeFreeConfig> cflist = freeconfigService
				.queryListByCommandeId(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("c", commande);
		map.put("clist", clist);
		map.put("cflist", cflist);
		return map;
	}

	@Resource
	private IMainCommandeService service;
	@Resource
	private ISlaveCommandeService slaveCommandeService;
	@Resource
	private ISlaveCommandeConfigService configService;
	@Resource
	private ISlaveCommandeFreeConfigService freeconfigService;
	@Resource
	private IWorksheetDetailService worksheetDetailService;
	@Resource
	private IEmployeeService employeeService;
	@Resource
	private IWorksheetDetailTempService detailTempService;
	@Resource
	private IWorkstationService workstationService;
	@Resource
	private IFactoryStatusService factoryStatusService;
	@Resource
	private IFactoryStatusDetailService factoryStatusDetailService;
	@Resource
	private IWorksheetService worksheetService;
	@Resource
	private IFranchisesCommandeService commandeService;
	@Resource
	private IFranchisesService franchisesService;
	@Resource
	private ICommandeTypeService typeService;

}