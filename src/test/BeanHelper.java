package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import molos.plugins.tool.ToolFactory;
import molos.plugins.tool.common.IMsgTool;

public class BeanHelper {

	public static String toString(Object instance)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Class clzz = instance.getClass();
		IMsgTool t = ToolFactory.getMsgTool();
		StringBuffer sb = new StringBuffer();
		Field ff[] = clzz.getDeclaredFields();
		for (Field f : ff) {
			String n = f.getName();
			Object val = clzz.getDeclaredMethod("get" + t.upFirst(n)).invoke(
					instance);
			sb.append(n + ":" + val + "\r\n");
		}
		return sb.toString();
	}

	 
}
