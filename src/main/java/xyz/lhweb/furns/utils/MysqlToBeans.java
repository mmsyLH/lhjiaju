package xyz.lhweb.furns.utils;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * mysql to bean
 *
 * @author 罗汉
 * @date 2023/03/27
 */
public class MysqlToBeans {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/home_furnishing?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true";
	static String user = "root";
	static String password = "root";
	static String toUrl="F:\\JavaWorksparce\\ecilpseWorkspace\\lhjiaju\\src\\main\\java\\xyz\\lhweb\\furns\\bean\\";

	static StringBuffer sb = new StringBuffer();

	private static Statement stmt = null;
	private static Connection conn = null;
	private static OutputStreamWriter osw = null;
	private static BufferedWriter bw = null;
	private static FileOutputStream fos = null;



	/**
	 * 写入数据
	 *
	 * @param message   消息
	 * @param className 类名
	 */
	private static void writeData(String message, String className) {
		// F:\JavaWorksparce\ecilpseWorkspace\01_10_luohan_03\src\main\java\bean
		String file = toUrl + className + ".java";
		//添加一个  如果表已经存在则跳过，
		File javaFile = new File(file);
		if (javaFile.exists()) {
			System.out.println(className + "类已存在，跳过这个类的生成。");
			return;
		}

		try {
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			bw.write(message);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    /**
     * 根据表名查到字段 名称类型
     *
     * @param tablename 表
     * @param jbname    jbname
     */

    public static void getTableDetatils(String tablename, String jbname) {
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			// 执行sql语句
			stmt = conn.createStatement();
			rs = stmt.executeQuery("desc " + tablename + ";");
			ResultSet rs2 = rs;
			List<HashMap> list = new ArrayList<HashMap>();
			while (rs.next()) {
				HashMap h = new HashMap();
				h.put("Field", rs.getString("Field"));
				h.put("Type", rs.getString("Type"));
				list.add(h);
			}

			for (int x = 0; x < list.size(); x++) {
				String field = (String) list.get(x).get("Field");// 字段名
				String type = (String) list.get(x).get("Type");
				// 表名转换：遇到_a变为A
				String[] ss = field.split("_");
				String jba = "";
				for (int i = 0; i < ss.length; i++) {
					String s = ss[i];
					if (i > 0)
						s = s.substring(0, 1).toUpperCase() + s.substring(1);
					jba += s;
				}
				// System.out.println(jbname);
				sb.append("\r \t private ");
				if (type.contains("int")) {
					sb.append("Integer " + jba + ";");
				} else if (type.contains("char") || type.contains("text")) {
					sb.append("String " + jba + ";");
				} else if (type.contains("time") || type.contains("date")) {
					sb.append("Date " + jba + ";");
				} else if (type.contains("float") || type.contains("decimal") || type.contains("double")) {
					sb.append("Double " + jba + ";");
				} else {
					sb.append("Unknow " + jba + ";");
				}

				// sb.append("\r \t private String " + jba + ";");

				// System.out.println(jba+": "+type);
			}

			sb.append("\r \t public " + jbname + "() {");
			sb.append("\n \t \t super();\r");
			sb.append("\t}\n");

			for (int x = 0; x < list.size(); x++) {
				String field = (String) list.get(x).get("Field");// 字段名
				String type = (String) list.get(x).get("Type");
				// 表名转换：遇到_a变为A
				String[] ss = field.split("_");
				String jba = "";
				String jba2 = "";
				for (int i = 0; i < ss.length; i++) {
					String s = ss[i];
					String s2 = ss[i];
					s = s.substring(0, 1).toUpperCase() + s.substring(1);
					if (i > 0)
						s2 = s2.substring(0, 1).toUpperCase() + s2.substring(1);
					jba += s;
					jba2 += s2;
				}

				// sb.append("\r \t private ");
				// sb.append("\n");
				if (type.contains("int")) {
					sb.append("\r \t public void set" + jba + "(Integer " + jba2 + "){");
					sb.append("\r \t\t this." + jba2 + "=" + jba2 + ";");
					sb.append("\r \t }");
					sb.append("\r \t public Integer get" + jba + "(){");
					sb.append("\r \t\t return this." + jba2 + ";");
					sb.append("\r \t }");
				} else if (type.contains("char") || type.contains("text")) {
					sb.append("\r \t public void set" + jba + "(String " + jba2 + "){");
					sb.append("\r \t\t this." + jba2 + "=" + jba2 + ";");
					sb.append("\r \t }");
					sb.append("\r \t public String get" + jba + "(){");
					sb.append("\r \t\t return this." + jba2 + ";");
					sb.append("\r \t }");
				} else if (type.contains("time") || type.contains("date")) {
					sb.append("\r \t public void set" + jba + "(Date " + jba2 + "){");
					sb.append("\r \t\t this." + jba2 + "=" + jba2 + ";");
					sb.append("\r \t }");
					sb.append("\r \t public Date get" + jba + "(){");
					sb.append("\r \t\t return this." + jba2 + ";");
					sb.append("\r \t }");
				} else if (type.contains("float") || type.contains("decimal") || type.contains("double")) {
					sb.append("\r \t public void set" + jba + "(Double " + jba2 + "){");
					sb.append("\r \t\t this." + jba2 + "=" + jba2 + ";");
					sb.append("\r \t }");
					sb.append("\r \t public Double get" + jba + "(){");
					sb.append("\r \t\t return this." + jba2 + ";");
					sb.append("\r \t }");
				} else {
					sb.append("\r \t public void set" + jba + "(Unknow " + jba2 + "){");
					sb.append("\r \t\t this." + jba2 + "=" + jba2 + ";");
					sb.append("\r \t }");
					sb.append("\r \t public Unknow get" + jba + "(){");
					sb.append("\r \t\t return this." + jba2 + ";");
					sb.append("\r \t }");
				}

				// sb.append("\r \t private String " + jba + ";");

				// System.out.println(jba+": "+type);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 1、建立与mysql的链接
		ResultSet rs = null;

		List<String> jbnames = new ArrayList<String>();// java类中的所有表名称
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			// 执行sql语句
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show tables;"); // 查询所有的表名，保存在结果集中
			// System.out.println(rs);
			// 测试表名
			// System.out.println("-----------------");
			// System.out.println("执行结果如下所示:");
			// System.out.println("-----------------");

			while (rs.next()) {
				sb = new StringBuffer();
				String tablename = rs.getString(1);
				// 表名转换：第一个字母大写，遇到_a变为A
				String[] ss = tablename.split("_");
				String jbname = "";
				for (int i = 0; i < ss.length; i++) {
					String s = ss[i];
					s = s.substring(0, 1).toUpperCase() + s.substring(1);
					jbname += s;
				}
				// System.out.println(jbname);
				// sb.append("package bean;\n\n");
				sb.append("package xyz.lhweb.furns.bean;\n\n");
				// sb.append("import java.util.Date;\n\n");

				sb.append("public class " + jbname + "{");

				getTableDetatils(tablename, jbname);

				sb.append("\n}");

				// System.out.println(sb.toString());
				writeData(sb.toString(), jbname);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
