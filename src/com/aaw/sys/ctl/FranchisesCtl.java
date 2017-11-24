package com.aaw.sys.ctl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.molos.common.SessionConstant;
import cn.molos.util.ExcelTool;
import cn.molos.util.ExcelTool2;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.Franchises;
import com.aaw.bean.FranchisesAccount;
import com.aaw.bean.FranchisesAccountAssets;
import com.aaw.bean.FranchisesAssets;
import com.aaw.sys.form.FrandAccConditions;
import com.aaw.sys.form.FrandConditions;
import com.aaw.sys.service.IFranchisesAccountAssetsService;
import com.aaw.sys.service.IFranchisesAccountService;
import com.aaw.sys.service.IFranchisesService;

@Controller
@RequestMapping("franchises")
public class FranchisesCtl extends BaseCtl<Franchises> {

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/franchises/franchises");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows, HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		try {
			if (type == 1) {
				return service.map(page, rows);
			} else {

			}
		} catch (Exception e) {
			log.error("CustomerInfoCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=map4EUI")
	@ResponseBody
	public Object map4EUI(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows, FrandConditions c,
			HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		try {
			if (type == 1) {
				return service.map4EUI(page, rows, c, 0);
			} else {
				Franchises franchises = (Franchises) session
						.getAttribute(SessionConstant.LOGIN_USER);
				return service.map4EUI(page, rows, c, franchises.getId());
			}
		} catch (Exception e) {
			log.error("CustomerInfoCtl列表查询异常", e);
		}
		return null;
	}

	@RequestMapping(params = "goto=levelList")
	@ResponseBody
	public Object levelList(HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		List<Integer> temp = new ArrayList<Integer>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (type == 1) {
			temp = service.findAllLevel(0);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			temp = service.findAllLevel(franchises.getId());
		}
		for (Integer i : temp) {
			Map<String, Object> map = getMap(i);
			list.add(map);
		}
		return list;
	}

	@RequestMapping(params = "goto=levelList2")
	@ResponseBody
	public Object levelList2(HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		List<Integer> temp = new ArrayList<Integer>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (type == 1) {
			temp = service.findAllLevel(0);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			int level = franchises.getFranchiseLevel();
			Map<String, Object> map2 = getMap(level);
			list.add(map2);
			temp = service.findAllLevel(franchises.getId());
		}
		for (Integer i : temp) {
			Map<String, Object> map = getMap(i);
			list.add(map);
		}
		return list;
	}

	private Map<String, Object> getMap(Integer i) {
		Map<String, Object> map = new HashMap<String, Object>();
		switch (i) {
		case 1:
			map.put("id", 1);
			map.put("name", "一级");
			break;
		case 2:
			map.put("id", 2);
			map.put("name", "二级");
			break;
		case 3:
			map.put("id", 3);
			map.put("name", "三级");
			break;
		case 4:
			map.put("id", 4);
			map.put("name", "四级");
			break;
		default:
			break;
		}
		return map;
	}

	@RequestMapping(params = "goto=save")
	@ResponseBody
	public Object save(Franchises bean, HttpServletRequest request,
			HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			bean.setParentId(0);
			bean.setFranchiseLevel(1);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			bean.setParentId(franchises.getId());
			bean.setFranchiseLevel(franchises.getFranchiseLevel() + 1);
		}
		try {
			bean.setCreateTime(new Date());
			bean.setModificationTime(new Date());
			bean.setIsCreateAcc(1);
			bean.setState(0);
			service.save(bean);
			return saveSuc(request);
		} catch (Exception e) {
			log.error("franchises保存对象异常", e);
		}
		return saveFail(request);
	}

	@RequestMapping(params = "goto=update")
	@ResponseBody
	public Object update(Franchises bean, HttpServletRequest request) {
		try {
			Franchises franchises = service.query(bean.getId());
			bean.setModificationTime(new Date());
			boolean flag = service.clickInput();
			if (!flag) {
				bean.setCode(franchises.getCode());
			}
			service.update(bean);
			return updateSuc(request);
		} catch (Exception e) {
			log.error("franchises修改对象异常", e);
		}
		return updateFail(request);
	}

	@RequestMapping(params = "goto=del")
	@ResponseBody
	public Object delete(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			service.delete(id);
			return delSuc(request);
		} catch (Exception e) {
			log.error("franchises通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=delete")
	@ResponseBody
	public Object deleteF(@RequestParam(defaultValue = "0") int id,
			HttpServletRequest request) {
		try {
			Franchises franchises = service.query(id);
			franchises.setState(9);
			franchises.setDeleteTime(new Date());
			service.update(franchises);
			FranchisesAccount account = accountService.findByFra(franchises);
			account.setStatus(1);
			accountService.update(account);
			return delSuc(request);
		} catch (Exception e) {
			log.error("CustomerInfoCtl通过ID删除对象异常", e);
		}
		return delFail(request);
	}

	@RequestMapping(params = "goto=clickAssets")
	@ResponseBody
	public Object clickAssets(@RequestParam(defaultValue = "0") int as,
			HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<String, Object>();
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			map.put("suc", 1);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			FranchisesAccount account = accountService.findByFra(franchises);
			if (service.clickAssets(account, as)) {
				map.put("suc", 1);
			} else {
				map.put("suc", 2);
				map.put("msg", "您暂无创建权限");
			}
		}
		return map;
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

	@RequestMapping(params = "goto=assets")
	@ResponseBody
	public Object assets(@RequestParam(defaultValue = "0") Integer fid) {
		List<FranchisesAssets> list = service.findAllAssets();
		List<FranchisesAccountAssets> temp = service.findAssetsByFran(fid);
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (FranchisesAssets f : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", f.getId());
			map.put("name", f.getDescribe());
			map.put("isclick", 1);
			if (temp != null) {
				for (FranchisesAccountAssets fa : temp) {
					if (fa.getAssets().getId() == f.getId()) {
						map.put("isclick", 2);
					}
				}
			}
			result.add(map);
		}
		return result;
	}

	@RequestMapping(params = "goto=saveAcc")
	@ResponseBody
	public Object saveAcc(FrandAccConditions fa, HttpServletRequest request,
			HttpSession session) {
		FranchisesAccount bean = new FranchisesAccount();
		bean.setAccount(fa.getAccount());
		bean.setPazzword(fa.getPazzword());
		bean.setCreateTime(new Date());
		bean.setModificationTime(new Date());
		Franchises franchises = service.query(fa.getFid());
		bean.setFranchises(franchises);
		bean.setStatus(fa.getStatus());
		accountService.save(bean);
		List<Integer> list = fa.getAssets();
		if (list != null) {
			for (Integer i : list) {
				FranchisesAccountAssets faa = new FranchisesAccountAssets();
				faa.setAccount(bean);
				faa.setCreateTime(new Date());
				faa.setModificationTime(new Date());
				FranchisesAssets fs = new FranchisesAssets();
				fs.setId(i);
				faa.setAssets(fs);
				assetsService.save(faa);
			}
		}
		if (fa.getStatus() == 1) {
			franchises.setState(0);
		} else {
			franchises.setState(1);
		}
		franchises.setIsCreateAcc(2);
		service.update(franchises);
		return saveSuc(request);
	}

	@RequestMapping(params = "goto=findAcc")
	@ResponseBody
	public Object findAcc(@RequestParam(defaultValue = "0") Integer fid,
			HttpServletRequest request, HttpSession session) {
		Franchises franchises = new Franchises(fid);
		FranchisesAccount account = accountService.findByFra(franchises);
		return account;
	}

	@RequestMapping(params = "goto=updateAcc")
	@ResponseBody
	public Object updateAcc(FrandAccConditions fa, HttpServletRequest request,
			HttpSession session) {
		FranchisesAccount bean = accountService.query(fa.getId());
		bean.setAccount(fa.getAccount());
		bean.setPazzword(fa.getPazzword());
		bean.setModificationTime(new Date());
		Franchises franchises = service.query(fa.getFid());
		bean.setStatus(fa.getStatus());
		accountService.update(bean);
		service.deleteAccAss(bean);
		List<Integer> list = fa.getAssets();
		if (list != null) {
			for (Integer i : list) {
				FranchisesAccountAssets faa = new FranchisesAccountAssets();
				faa.setAccount(bean);
				faa.setCreateTime(new Date());
				faa.setModificationTime(new Date());
				FranchisesAssets fs = new FranchisesAssets();
				fs.setId(i);
				faa.setAssets(fs);
				assetsService.save(faa);
			}
		}
		if (fa.getStatus() == 1) {
			franchises.setState(0);
		} else {
			franchises.setState(1);
		}
		service.update(franchises);
		return saveSuc(request);
	}

	@RequestMapping(params = "goto=combobox")
	@ResponseBody
	public Object combobox(HttpSession session,
			@RequestParam(defaultValue = "0") Integer level) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			map = service.combobox2(null, level);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			map = service.combobox2(franchises, level);
		}
		return map;

	}

	@RequestMapping(params = "act=unique")
	@ResponseBody
	public Object unique(@RequestParam(defaultValue = "0") int id,
			@RequestParam(defaultValue = "0") String propName,
			@RequestParam(defaultValue = "0") String propVal) {
		try {
			return unique(service, propName, propVal, id);
		} catch (Exception e) {
			log.error("UpmsAccountCtl通过唯一性查询异常", e);
		}
		return 3;
	}

	/**
	 * 导出明细报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "act=franchisesInfo")
	public void downloadAppl(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-excel");
		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding("UTF-8");
		String fileName = System.currentTimeMillis() + ".xls";
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		OutputStream out = response.getOutputStream();
		List<Franchises> temp = service.list();
		ArrayList<List<Object>> list = service.getList(temp);
		new ExcelTool(1).creatWorkBook(list, 1).write(out);
		out.flush();
		out.close();
	}

	/**
	 * 导出错误信息报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "act=downloadERR")
	public void downloadERR(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String str = (String) session.getAttribute(SessionConstant.ERR_MSG);
		String filepath = System.currentTimeMillis() + ".txt";
		response.reset();
		response.setContentType("application/octet-stream");
		String fileName = URLDecoder.decode(filepath, "utf-8");
		java.net.URLEncoder.encode(fileName, "utf-8");
		response.addHeader("Content-Disposition", "attachment;" + "filename=\""
				+ URLEncoder.encode(fileName, "utf-8") + "\"");
		StringBuilder sb = new StringBuilder(str);
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] byt = sb.toString().getBytes();
			os.write(byt);
		} catch (Exception e) {

		} finally {
			os.flush();
			os.close();
			if (input != null) {
				input.close();
			}
			if (output != null) {
				output.close();
			}
		}
		return;
	}

	/**
	 * 导入明细报表
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "act=intoFranchisesInfo")
	@ResponseBody
	public Object intoAppl(
			HttpSession session,
			@RequestParam(value = "appFile", required = false) MultipartFile appFile)
			throws InvalidFormatException, IOException {
		boolean flag = true;
		String errStr = "";
		List<Object> codes = service.findAllCode();
		String suffix = appFile.getOriginalFilename();
		suffix = suffix.substring(suffix.lastIndexOf("."));
		List<Object[]> list = new ArrayList<Object[]>();
		list = new ExcelTool2(1).read(appFile.getInputStream());
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = list.get(i);
			if (objs[0].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":加盟商编码为空\r\n";
				flag = false;
			} else {
				for (int j = (i + 1); j < list.size(); j++) {
					Object[] tempObjs = list.get(j);
					if (objs[0].toString().trim()
							.equals(tempObjs[0].toString().trim())) {
						errStr += "行数" + (i + 2) + "和行数" + (j + 2)
								+ "加盟商编码相同\r\n";
					}
				}
			}
			if (objs[1].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":加盟商名字为空\r\n";
				flag = false;
			} else {
				for (int j = (i + 1); j < list.size(); j++) {
					Object[] tempObjs = list.get(j);
					if (objs[1].toString().trim()
							.equals(tempObjs[1].toString().trim())) {
						errStr += "行数" + (i + 2) + "和行数" + (j + 2)
								+ "加盟商名字相同\r\n";
					}
				}
			}
			if (objs[6].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":邮箱为空\r\n";
				flag = false;
			} else {
				for (int j = (i + 1); j < list.size(); j++) {
					Object[] tempObjs = list.get(j);
					if (objs[6].toString().trim()
							.equals(tempObjs[6].toString().trim())) {
						errStr += "行数" + (i + 2) + "和行数" + (j + 2)
								+ "加盟商邮箱相同\r\n";
					}
				}
			}
			if (objs[7].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":状态为空\r\n";
				flag = false;
			}
			if (objs[8].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":等级为空\r\n";
				flag = false;
			}
			if (objs[10].toString().trim().equals("")) {
				errStr += "行数" + (i + 2) + ":父节点（加盟商编码）为空\r\n";
				flag = false;
			} else {
				boolean tempFlag = false;
				if (objs[10].toString().equals("0")) {
					tempFlag = true;
				} else {
					for (Object[] temp : list) {
						if (objs[10].toString().equals(temp[0].toString())) {
							tempFlag = true;
						}
					}
				}
				if (!tempFlag) {
					errStr += "行数" + (i + 2) + ":父节点（加盟商编码）不存在\r\n";
				}
			}
			if (!objs[7].toString().trim().equals("")
					&& objs[7].toString().trim().equals("1")) {
				if (objs[12].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":账号为空\r\n";
					flag = false;
				} else {
					for (int j = (i + 1); j < list.size(); j++) {
						Object[] tempObjs = list.get(j);
						if (objs[12].toString().trim()
								.equals(tempObjs[12].toString().trim())) {
							errStr += "行数" + (i + 2) + "和行数" + (j + 2)
									+ "加盟商账号相同\r\n";
						}

					}
				}

				if (objs[13].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":密码为空\r\n";
					flag = false;
				}
				if (objs[14].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":子加盟商创建权限为空\r\n";
					flag = false;
				}
				if (objs[15].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":子加盟商修改权限为空\r\n";
					flag = false;
				}
				if (objs[16].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":子加盟商删除权限为空\r\n";
					flag = false;
				}
				if (objs[17].toString().trim().equals("")) {
					errStr += "行数" + (i + 2) + ":订单创建权限为空\r\n";
					flag = false;
				}
			}
		}

		if (codes != null && !codes.isEmpty()) {
			for (Object obj : codes) {
				boolean codeFlag = false;
				for (Object[] objs : list) {
					if (obj.toString().equals(objs[0].toString())) {
						codeFlag = true;
					}
				}
				if (!codeFlag) {
					flag = false;
					errStr += obj.toString() + "在导入列表中没有存在\r\n";
				}
			}
		}

		List<Object[]> temp = new ArrayList<Object[]>();
		temp.addAll(list);
		Object[] ob = { "0" };
		temp = clickTree(temp, ob);
		if (!temp.isEmpty()) {
			for (Object[] objs : temp) {
				errStr += objs[0].toString() + "父编号错误或等级错误\r\n";
				flag = false;
			}
		}
		if (flag == true) {
			for (Object[] objs : list) {
				String code = objs[0].toString();
				Franchises f = service.findByCode(code);
				if (f != null) {
					f.setName(objs[1].toString());
					f.setDescription(objs[2].toString());
					f.setAddress(objs[3].toString());
					f.setContactName(objs[4].toString());
					f.setContactNumber(objs[5].toString());
					f.setContactEmail(objs[6].toString());
					f.setState(Integer.valueOf(objs[7].toString()));
					if (objs[7].toString().equals("9")) {
						f.setDeleteTime(new Date());
					}
					f.setFranchiseLevel(Integer.valueOf(objs[8].toString()));
					if (objs[9] != null
							&& !objs[9].toString().trim().equals(""))
						f.setCategory(Integer.valueOf(objs[9].toString()));
					f.setContactPhone(objs[11].toString());
					f.setModificationTime(new Date());
					service.update(f);
				} else {
					f = new Franchises();
					f.setCode(objs[0].toString());
					f.setName(objs[1].toString());
					f.setDescription(objs[2].toString());
					f.setAddress(objs[3].toString());
					f.setContactName(objs[4].toString());
					f.setContactNumber(objs[5].toString());
					f.setContactEmail(objs[6].toString());
					f.setState(Integer.valueOf(objs[7].toString()));
					if (objs[7].toString().equals("9")) {
						f.setDeleteTime(new Date());
					}
					f.setFranchiseLevel(Integer.valueOf(objs[8].toString()));
					if (objs[9] != null
							&& !objs[9].toString().trim().equals(""))
						f.setCategory(Integer.valueOf(objs[9].toString()));
					f.setContactPhone(objs[11].toString());
					f.setCreateTime(new Date());
					f.setModificationTime(new Date());
					f.setIsCreateAcc(1);
					service.save(f);
				}

				if (!objs[12].toString().trim().equals("")
						&& !objs[13].toString().trim().equals("")
						&& !objs[14].toString().trim().equals("")
						&& !objs[15].toString().trim().equals("")
						&& !objs[16].toString().trim().equals("")
						&& !objs[17].toString().trim().equals("")) {
					FranchisesAccount acc = accountService.findByFra(f);
					if (acc != null) {
						acc.setAccount(objs[12].toString());
						acc.setPazzword(objs[13].toString());
						if (objs[7].toString().equals("1")) {
							acc.setStatus(2);
						} else {
							acc.setStatus(1);
						}
						acc.setModificationTime(new Date());
						accountService.update(acc);
					} else {
						acc = new FranchisesAccount();
						acc.setAccount(objs[12].toString());
						acc.setPazzword(objs[13].toString());
						if (objs[7].toString().equals("1")) {
							acc.setStatus(2);
						} else {
							acc.setStatus(1);
						}
						acc.setCreateTime(new Date());
						acc.setModificationTime(new Date());
						acc.setFranchises(f);
						accountService.save(acc);
					}
					if (objs[14].toString().equals("1")) {
						FranchisesAssets fa = new FranchisesAssets();
						fa.setId(1);
						FranchisesAccountAssets faa = new FranchisesAccountAssets();
						faa.setAccount(acc);
						faa.setAssets(fa);
						faa.setCreateTime(new Date());
						faa.setModificationTime(new Date());
						assetsService.save(faa);
					}
					if (objs[15].toString().equals("1")) {
						FranchisesAssets fa = new FranchisesAssets();
						fa.setId(2);
						FranchisesAccountAssets faa = new FranchisesAccountAssets();
						faa.setAccount(acc);
						faa.setAssets(fa);
						faa.setCreateTime(new Date());
						faa.setModificationTime(new Date());
						assetsService.save(faa);
					}
					if (objs[16].toString().equals("1")) {
						FranchisesAssets fa = new FranchisesAssets();
						fa.setId(3);
						FranchisesAccountAssets faa = new FranchisesAccountAssets();
						faa.setAccount(acc);
						faa.setAssets(fa);
						faa.setCreateTime(new Date());
						faa.setModificationTime(new Date());
						assetsService.save(faa);
					}
					if (objs[17].toString().equals("1")) {
						FranchisesAssets fa = new FranchisesAssets();
						fa.setId(4);
						FranchisesAccountAssets faa = new FranchisesAccountAssets();
						faa.setAccount(acc);
						faa.setAssets(fa);
						faa.setCreateTime(new Date());
						faa.setModificationTime(new Date());
						assetsService.save(faa);
					}
				}
			}

			for (Object[] objs : list) {
				String code = objs[0].toString();
				Franchises f = service.findByCode(code);
				if (objs[10].toString().equals("0")) {
					f.setParentId(0);
				} else {
					Franchises tf = service.findByCode(objs[10].toString());
					f.setParentId(tf.getId());
				}
				service.update(f);
			}
			return "1";
		} else {
			session.setAttribute(SessionConstant.ERR_MSG, errStr);
			return "2";
		}
	}

	private List<Object[]> clickTree(List<Object[]> list, Object[] obj) {
		int oldNum = list.size();
		List<Object[]> stairfran = new ArrayList<Object[]>();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = list.get(i);
			if (objs[10].toString().trim().equals(obj[0].toString())
					&& (obj[0].toString().equals("0") || Integer.valueOf(obj[8]
							.toString()) < Integer.valueOf(objs[8].toString()))) {
				stairfran.add(objs);
			}
		}
		list.removeAll(stairfran);
		int newNum = list.size();
		if (oldNum == newNum) {
			return list;
		}
		for (Object[] objs : stairfran) {
			clickTree(list, objs);
		}
		return list;
	}

	@RequestMapping(params = "act=clickExport")
	@ResponseBody
	public Object clickExport(HttpSession session) {
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			return 1;
		}
		return 2;
	}

	@RequestMapping(params = "goto=clickInput")
	@ResponseBody
	public Object clickInput() {
		boolean flag = service.clickInput();
		if (flag) {
			return "1";
		}
		return "2";
	}

	@RequestMapping(params = "goto=getTree")
	@ResponseBody
	public Object getTree(HttpSession session) {
		List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();
		int type = (Integer) session
				.getAttribute(SessionConstant.LOGIN_USER_TYPE);
		if (type == 1) {
			map = service.getTree(null);
		} else {
			Franchises franchises = (Franchises) session
					.getAttribute(SessionConstant.LOGIN_USER);
			map = service.getTree(franchises);
		}
		return map;

	}

	@Resource
	private IFranchisesService service;
	@Resource
	private IFranchisesAccountService accountService;
	@Resource
	private IFranchisesAccountAssetsService assetsService;

}