package test;

import java.util.Map;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IFileTool;

public class ReportURLHelper {

	/**
	 * 获取系统定义的资源
	 * 
	 * @param key
	 *            必须使用URLKey接口定义的常量字段
	 * @return 资源映射具体内容
	 */
	public static String get(Object key) {
		try {
			IFileTool tool = ToolFactory.getFileTool();
			String path = ReportURLHelper.class.getResource("/").toURI()
					.getPath()
					+ "report.properties";
			Map<Object, Object> map = tool.readProp(path);
			return map.get(key).toString();
		} catch (Exception e) {

		}
		return null;
	}

	public static String get2(Object key) {
		try {
			IFileTool tool = ToolFactory.getFileTool();
			String path = ReportURLHelper.class.getResource("/").toURI()
					.getPath()
					+ "env.properties";
			Map<Object, Object> map = tool.readProp(path);
			return map.get(key).toString();
		} catch (Exception e) {

		}
		return null;
	}

}
