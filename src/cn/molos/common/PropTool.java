package cn.molos.common;

import java.util.Map;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IFileTool;

import org.apache.log4j.Logger;

/**
 * 资源配置加载工具类
 * 
 * @author molos
 *
 */
public class PropTool {

	private static final Logger log = Logger.getLogger(PropTool.class);

	public static String get(Object key) {
		try {
			IFileTool tool = ToolFactory.getFileTool();
			String path = PropTool.class.getResource("/").toURI().getPath()
					+ "env.properties";
			Map<Object, Object> map = tool.readProp(path);
			return map.get(key).toString();
		} catch (Exception e) {
			log.error("读取资源文件内容异常", e);
		}
		return null;
	}

	/**
	 * 获取开发模式
	 * 
	 * @return 开发模式：true,其他情况:false
	 */
	public static boolean isDev() {
		String dev = PropTool.get("devMode");
		if (dev != null && Integer.parseInt(dev) == 1) {
			return true;
		}
		return false;
	}
}
