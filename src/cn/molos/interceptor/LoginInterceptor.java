package cn.molos.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import molos.plugins.listener.MolosSessionContext;
import molos.plugins.tool.ToolFactory;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final String NO_LOGIN_TIP = "NO_LOGIN_TIP";
	private static final String GREEN_URI = "GREEN_URI";
	private static final String ROOT_PAGE = "ROOT_PAGE";
	private static final String SESSION_ID = "SESSION_ID";
	private static final String CHARSET = "CHARSET";
	private static final String SID = "SID";
	private Map<Object, Object> map = null;

	public LoginInterceptor() {
		try {
			Map<Object, Object> map = ToolFactory.getFileTool().readProp(
					getClass().getResourceAsStream(
							"loginCheckDefault2.properties"), "UTF-8");
			InputStream in = getClass().getResourceAsStream(
					"/loginCheck.properties");
			if (in != null) {
				Map<Object, Object> map2 = ToolFactory.getFileTool().readProp(
						in, "UTF-8");
				if (map2.get("NO_LOGIN_TIP") != null) {
					map.put("NO_LOGIN_TIP", map2.get("NO_LOGIN_TIP"));
				}
				if (map2.get("GREEN_URI") != null) {
					map.put("GREEN_URI", map2.get("GREEN_URI"));
				}
				if (map2.get("ROOT_PAGE") != null) {
					map.put("ROOT_PAGE", map2.get("ROOT_PAGE"));
				}
				if (map2.get("CHARSET") != null) {
					map.put("CHARSET", map2.get("CHARSET"));
				}
				if (map2.get("SID") != null) {
					map.put("SID", map2.get("SID"));
				}
			}
			this.map = map;
		} catch (IOException e) {
			Logger.getLogger(getClass()).error("初始化配置信息异常！", e);
		}
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (rootReq(request, response, this.map.get("ROOT_PAGE").toString())) {
			return false;
		}
		String root = request.getContextPath();
		String uri = request.getRequestURI();
		String sid = request.getParameter(this.map.get("SID").toString());
		HttpSession session = ToolFactory.getNPV().isNull(sid) ? request
				.getSession() : MolosSessionContext.getSession(sid);
		if ((green(uri.substring(uri.indexOf(root) + root.length() + 1)))
				|| (session.getAttribute(this.map.get("SESSION_ID").toString()) != null)) {
			return super.preHandle(request, response, handler);
		}
		return noLoginTip(response);
	}

	private boolean green(String url) {
		String[] greenArr = this.map.get("GREEN_URI").toString().split(",");
		for (String s : greenArr) {
			if (s.equals(url)) {
				return true;
			}
		}
		return false;
	}

	private boolean noLoginTip(HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=" + this.map.get("CHARSET"));
		response.setCharacterEncoding(this.map.get("CHARSET").toString());
		PrintWriter out = response.getWriter();
		out.print(this.map.get("NO_LOGIN_TIP"));
		out.close();
		return false;
	}

	private boolean rootReq(HttpServletRequest request,
			HttpServletResponse response, String rootPage) throws IOException {
		String contentPath = request.getContextPath();
		String uri = request.getRequestURI();
		if ((uri.equals(contentPath)) || (uri.equals(contentPath + "/"))) {
			response.sendRedirect(rootPage);
			return true;
		}
		return false;
	}
}
