package test;

import com.aaw.sys.form.MDConditions;
import com.aaw.sys.service.IMainCommandeService;

public class TestCri {
	public static void main(String[] args) {
		SpringHelper h = new SpringHelper();
		IMainCommandeService service = (IMainCommandeService) h
				.getBean("mainCommandeService");
		MDConditions cc = new MDConditions();
		// cc.setSt1("1");
		// cc.setSt2("3");
		cc.setSymbol(">");
		cc.setThird("test");
		cc.setName("ab");
		cc.setLineID(1);
		service.map(1, 20, cc, 1);
		// service = (BgBaseCityService) h
		// .getBean("bgBaseCityServiceImp");
		// List<BgBaseCity> list = service.list();

	}
}
