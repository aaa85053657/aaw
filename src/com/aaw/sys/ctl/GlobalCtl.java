package com.aaw.sys.ctl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.RedirectView;

import test.ReportURLHelper;
import cn.molos.common.SessionConstant;

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.UpmsAccount;
import com.aaw.bean.Workstation;
import com.aaw.sys.service.IUpmsAccountService;
import com.aaw.sys.service.IUpmsAssetsService;
import com.aaw.sys.service.IWorkstationService;

@Controller
public class GlobalCtl extends BaseCtl<Object> {

	@Resource
	private IUpmsAssetsService service;

	/**
	 * 初始化权限系统的方法
	 * 
	 * @return
	 */
	// @RequestMapping(value = { "initSystemAAW" })
	public void init() {
		service.init();
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "login", "/" })
	public ModelAndView loginView() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = { "app" })
	public ModelAndView appView(HttpServletRequest request) {
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		if (npv.isNull(wks)) {// 未配置工作台的处理
			return new ModelAndView("noCfgApp");
		} else {
			return new ModelAndView("app");
		}
	}

	@RequestMapping(value = { "app3" })
	public ModelAndView appView3(HttpServletRequest request) {
		String ip = getIpAddr(request);
		Map<String, Object> cc = new HashMap<String, Object>();
		cc.put("parameter", ip);
		List<Workstation> wks = workstationService.list(cc);
		if (npv.isNull(wks)) {// 未配置工作台的处理
			return new ModelAndView("noCfgApp");
		} else {
			return new ModelAndView("app3");
		}
	}

	/**
	 * 跳转至后台控制主菜单
	 * 
	 * @return
	 */
	@RequestMapping("main")
	public ModelAndView mainView(HttpSession se, UpmsAccount account,
			RedirectAttributes attr, String brower,
			@RequestParam(defaultValue = "") String veryCode,
			@RequestParam(defaultValue = "1") Integer type) {
		String code = (String) se.getAttribute(SessionConstant.VALIDATECODE);
		Integer clickCode = Integer.valueOf(ReportURLHelper.get2("clickCode"));
		if (clickCode == 0) {
			if ((se.getAttribute(SessionConstant.LOGIN_USER) == null)
					&& (code == null || !code.equalsIgnoreCase(veryCode))) {
				attr.addFlashAttribute("error", "验证码错误");
				attr.addFlashAttribute("oldacc", account.getName());
				attr.addFlashAttribute("oldpwd", account.getPazzwd());
				return new ModelAndView(new RedirectView("login"));
			}
		}
		return accountService.doLogin(se, account, attr, type);
	}

	/**
	 * 跳转至后台控制主菜单
	 * 
	 * @return
	 */
	@RequestMapping("goOut")
	public ModelAndView goOut(HttpSession se) {
		return accountService.goOut(se);
	}

	/**
	 * 加载菜单
	 * 
	 * @param se
	 * @return
	 */
	@RequestMapping(value = "menus", method = RequestMethod.POST)
	@ResponseBody
	public Object menus(HttpServletRequest se) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menus", assetsService.getMenus(se));
		return map;
	}

	@RequestMapping(value = "lang", method = RequestMethod.POST)
	@ResponseBody
	public Object lang(HttpServletRequest se) {
		return new RequestContext(se).getLocale().getLanguage();
	}

	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public Object upload(
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
			HttpServletRequest request) {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			String rootPath = request.getSession().getServletContext()
					.getRealPath("/");
			String suffix = uploadFile.getOriginalFilename();
			suffix = suffix.substring(suffix.indexOf("."));
			long c = System.currentTimeMillis();
			String fileName = c + suffix;
			String tmp = rootPath + "/upload/tmp/";
			File dir = new File(tmp);
			if (!dir.exists() || !dir.isDirectory()) {
				dir.mkdirs();
			}
			File targetFile = new File(tmp + fileName.toLowerCase());
			uploadFile.transferTo(targetFile);
			m.put("status", 1);
			m.put("src", "upload/tmp/" + fileName);
		} catch (Exception e) {
			log.error("上传失败", e);
			m.put("status", 0);
		}
		return m;
	}

	@RequestMapping("/download/{fileName}")
	public ModelAndView download(@PathVariable("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "\\" + "images\\";
		String downLoadPath = ctxPath + fileName;
		try {
			long fileLength = new File(downLoadPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	@RequestMapping(value = { "test" })
	public ModelAndView test() {
		return new ModelAndView("sys/test");
	}

	@Resource
	private IUpmsAssetsService assetsService;
	@Resource
	private IUpmsAccountService accountService;
	@Resource
	private IWorkstationService workstationService;
}
