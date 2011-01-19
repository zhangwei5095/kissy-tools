package com.taobao.f2e;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;


public class FileUtils {
	public static String getFileContent(String path, String encoding) {
		StringBuffer sb = new StringBuffer();
		BufferedReader r = null;
		try {
			r = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding));
			String line;
			while ((line = r.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (r != null) r.close();
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}

	public static void outputContent(String content, String path, String encoding) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(path, encoding);
			pw.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	/**
	 * @param path
	 * @return
	 */
	public static String escapePath(String path) {
		return path.replaceAll("\\\\", "/");
	}

	/**
	 * 1.replace \ to /
	 * 2.event/./x to event/x
	 * 3.event/../x to x
	 *
	 * @param path
	 */
	public static String normPath(String path) {
		path = escapePath(path);
		String[] paths = path.split("/");
		ArrayList<String> re = new ArrayList<String>();
		for (String p : paths) {
			if (p.equals(".")) continue;
			else if (p.equals("..")) re.remove(re.size() - 1);
			else re.add(p);
		}
		return ArrayUtils.join(re.toArray(new String[re.size()]), "/");
	}


	public static void main(String[] args) {
		System.out.println(normPath("event/./../h/../s"));
	}
}
