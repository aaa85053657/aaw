package com.aaw.base.ctl;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import molos.plugins.smvc.action.AbstractAct;
import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.validators.INPValidator;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.support.RequestContext;

import com.aaw.base.service.IBaseService;

public class BaseCtl<T> extends AbstractAct {
	protected INPValidator npv = ToolFactory.getNPV();

	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, dateDeal());
	}

	/**
	 * 通用协议，定制返回状态和信息
	 * 
	 * @param status
	 *            返回执行结果状态
	 * @param msg
	 *            提示内容
	 * @return 协议MAP
	 */
	protected Map<String, Object> tipMap(int status, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("suc", status);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 通用协议，定制返回状态和信息,默认状态为1
	 * 
	 * @param msg
	 *            提示内容
	 * @return 协议MAP
	 */
	protected Map<String, Object> suc(String msg) {
		return tipMap(1, msg);
	}

	/**
	 * 通用协议，定制返回状态和信息,默认状态为-1
	 * 
	 * @param msg
	 *            提示内容
	 * @return 协议MAP
	 */
	protected Map<String, Object> err(String msg) {
		return tipMap(-1, msg);
	}

	/**
	 * 组装异常详细信息
	 * 
	 * @param e
	 * @return
	 */
	protected String exStr(Exception e) {
		try {
			return ToolFactory.getMsgTool().ex2Str(e);
		} catch (IOException e1) {
			log.error("获取异常信息", e1);
		}
		return "获取异常信息错误！";
	}

	protected PropertyEditorSupport dateDeal() {
		return new PropertyEditorSupport() {
			public void setAsText(String value) {
				SimpleDateFormat sdf = null;
				if (value.length() > 10) {
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				} else {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				try {
					setValue(sdf.parse(value));
				} catch (ParseException e) {
					setValue(null);
				}
			}

			public String getAsText() {
				SimpleDateFormat sdf = null;
				if (getValue().toString().length() > 10) {
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				} else {
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				return sdf.format((Date) getValue());
			}
		};
	}

	protected int unique(IBaseService<T> service, String propName,
			String propVal, int id) {
		if (service.isUnique(propName, propVal, id)) {
			return 1;
		}
		return 2;
	}

	/**
	 * 保存数据成功
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> saveSuc(HttpServletRequest request) {
		return suc(new RequestContext(request).getMessage("crud.save.suc"));
	}

	/**
	 * 保存数据失败
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> saveFail(HttpServletRequest request) {
		return err(new RequestContext(request).getMessage("crud.save.fail"));
	}

	/**
	 * 修改数据成功
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> updateSuc(HttpServletRequest request) {
		return suc(new RequestContext(request).getMessage("crud.update.suc"));
	}

	/**
	 * 修改数据失败
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> updateFail(HttpServletRequest request) {
		return err(new RequestContext(request).getMessage("crud.update.fail"));
	}

	/**
	 * 删除数据成功
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> delSuc(HttpServletRequest request) {
		return suc(new RequestContext(request).getMessage("crud.del.suc"));
	}

	/**
	 * 删除数据异常
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> delFail(HttpServletRequest request) {
		return err(new RequestContext(request).getMessage("crud.del.fail"));
	}

	/**
	 * 删除数据存在引用
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> delRef(HttpServletRequest request) {
		return err(new RequestContext(request).getMessage("crud.del.ref"));
	}

	/**
	 * 删除数据存在引用
	 * 
	 * @param request
	 * @return
	 */
	protected Map<String, Object> delRef2(HttpServletRequest request) {
		return err(new RequestContext(request).getMessage("crud.del.ref"));
	}

}
