package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {

	private ApplicationContext context;

	public SpringHelper() {
		this.context = new ClassPathXmlApplicationContext(this.getClass()
				.getResource("/beans.xml").toString());
	}

	public Object getBean(String id) {
		return context.getBean(id);
	}
}
