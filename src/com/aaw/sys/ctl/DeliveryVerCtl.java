package com.aaw.sys.ctl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

import com.aaw.base.ctl.BaseCtl;
import com.aaw.bean.DelliveryVer;
import com.aaw.bean.ParametresGeneralSite;
import com.aaw.sys.service.IDeliveryVerService;
import com.aaw.sys.service.IParamtresGeneralSiteService;

@Controller
@RequestMapping("deliveryVer")
public class DeliveryVerCtl extends BaseCtl<DelliveryVer> {

	@Resource
	private IDeliveryVerService service;

	@RequestMapping(params = "goto=view")
	public ModelAndView view() {
		return new ModelAndView("sys/deliveryVer/delivery");
	}

	@RequestMapping(params = "goto=list")
	@ResponseBody
	public Object list(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "20") int rows) {
		try {
			return service.map(page, rows);
		} catch (Exception e) {
			log.error("DeliveryAddressCtl列表查询异常", e);
		}
		return null;
	}

	/**
	 * 保存上传文件
	 */
	@RequestMapping(params = "act=uploadFile")
	@ResponseBody
	public Object studentgotoclazz(
			HttpSession se,
			@RequestParam(defaultValue = "0") int id,
			@RequestParam(value = "appFile", required = false) MultipartFile appFile) {
		try {
			ParametresGeneralSite generalSite = generalSiteService
					.findByPN("DeliveryInformationPath");
			String suffix = appFile.getOriginalFilename();
			String path = generalSite.getValueVarchar() + suffix;
			File file = new File(path);
			appFile.transferTo(file);
			DelliveryVer dv = service.query(id);
			dv.setFileInfo(path);
			service.update(dv);
			return suc("上传文件成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("上传文件失败：", e);
		}
		return err("上传文件失败!");
	}

	/**
	 * 文件下载
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(params = "act=downloadFile")
	public void download(@RequestParam(defaultValue = "0") Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DelliveryVer dv = service.query(id);
		String fileName = dv.getFileInfo().substring(
				dv.getFileInfo().lastIndexOf("/"));
		File f = new File(dv.getFileInfo());
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		java.net.URLEncoder.encode(fileName, "utf-8");
		response.addHeader("Content-Disposition", "attachment;" + "filename=\""
				+ URLEncoder.encode(fileName, "utf-8") + "\"");
		OutputStream os = null;
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(f));
		try {
			os = response.getOutputStream();
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				os.write(buff, 0, bytesRead);
			}
			bis.close();
			os.close();
		} catch (Exception e) {

		} finally {
			os.flush();
			os.close();
		}
		return;
	}

	@Resource
	private IParamtresGeneralSiteService generalSiteService;
}