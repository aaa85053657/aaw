package cn.molos.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionStatus implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// System.out.println("第一次访问");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// ILogInoutsysService service = (ILogInoutsysService) SpringInit
		// .getApplicationContext().getBean("logInoutsysService");
		// service.save(new LogInoutsys());
		// System.out.println("登录超时！");
	}

}
