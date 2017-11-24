package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileTest {

	public static void main(String[] args) {
		String oldpath = "d:\\img\\test1";
		String newpath = "d:\\img\\test3";
		File oldfileDir = new File(oldpath);
		File newfileDir = new File(newpath);
		if (!newfileDir.exists() || !newfileDir.isDirectory()) {
			newfileDir.mkdirs();
		}
		System.out.println(oldfileDir);
		String[] filePath = oldfileDir.list();
		// for (int i = 0; i < filePath.length; i++) {
		// try {
		// File oldFile = new File(oldfileDir + "\\" + filePath[i]);
		// File newfile = new File(newpath + "\\" + filePath[i]);
		// FileInputStream in = new FileInputStream(oldFile);
		// FileOutputStream out = new FileOutputStream(newfile);
		// byte[] buffer = new byte[2097152];
		// while ((in.read(buffer)) != -1) {
		// out.write(buffer);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		oldfileDir.delete();

	}
}
