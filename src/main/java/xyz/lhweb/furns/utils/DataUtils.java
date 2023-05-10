package xyz.lhweb.furns.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 处理数据的工具类
 *
 * @author 罗汉
 * @date 2023/04/02
 */
public class DataUtils {

	private static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 将字符串转成整数,否则返回默认值
	 *
	 * @param val        瓦尔
	 * @param defaultVal 默认瓦尔
	 * @return int
	 */
	public static int parseInt(String val, int defaultVal) {
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			// System.out.println(val + " 格式不正确...");
		}
		return defaultVal;
	}

	// 字符串转换为java.util.Date类型日期时间
	public static java.util.Date strDateToUtilDate(String strDate) {
		try {
			return SIMPLEDATEFORMAT.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	// java.util.Date类型日期时间转换为java.sql.Date类型日期时间
	public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
		// long date.getTime():返回自 1970 年 1 月 1 日 00:00:00 GMT以来此 Date对象表示的毫秒数
		return new java.sql.Date(date.getTime());
	}

	// java.util.Date类转换为字符串类型
	public static String utilDateToString(java.util.Date date) {
		return SIMPLEDATEFORMAT.format(date);
	}

	// 设置中文字符集
	public static void utf8(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
	}

	/**
	 * 随机生成id
	 * 
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * 生成随机码
	 * 
	 * @return
	 */
	public static String getCode() {
		return getId();
	}

	/**
	 * 得到年月日
	 *
	 * @return {@link String}
	 */
	public static String getYearMonthDay() {
		// 如何得到当前的日期-> java基础 日期 三代类
		LocalDateTime ldt = LocalDateTime.now();
		int year = ldt.getYear();
		int monthValue = ldt.getMonthValue();
		int dayOfMonth = ldt.getDayOfMonth();
		String yearMonthDay = "/"+year + "/" + monthValue + "/" + dayOfMonth + "/";
		return yearMonthDay;
	}

	/**
	 * 文件上传处理
	 *
	 * @param request  请求
	 * @param fileItem 文件项
	 * @throws Exception 异常
	 */
	private static Boolean fileUploadProcessing(HttpServletRequest request, FileItem fileItem) throws Exception {
		String name = fileItem.getName();
		System.out.println("false：fileItem.getName:--" + name);
		// 用一个方法
		// 把这个上传到 服务器的 temp下的文件保存到你指定的目录
		// 1.指定一个目录 , 就是我们网站工作目录下
		// F:\JavaWorksparce\javaweb\out\artifacts\fileupdown_war_exploded\`upload\
		String filePath = "/upload/";
		String fileRealPath = request.getServletContext().getRealPath(filePath);
		System.out.println(fileRealPath);
		// 写一个工具类，可以返回/2024/11/11
		// 2创建这个上传的文件目录=> io基础
		File file = new File(fileRealPath + DataUtils.getYearMonthDay());
		if (!file.exists()) {// 不存在
			if (file.mkdirs()) {
				System.out.println("创建目录成功:" + fileRealPath);
			} else {
				System.out.println("创建目录失败");
			}
		}
		// 3 将我们的文件拷贝到这个目录 fileRealPath,FileFullname 文件上传的完整路径
		// 对上传的文件名进行处理，前面增加一个前缀 保证文件名唯一
		name = UUID.randomUUID().toString() + "_" + name;
		String FileFullname = file + "/" + name;
		System.out.println("FileFullname:" + FileFullname);
		fileItem.write(new File(FileFullname));
		return true;
	}
	//将方法，封装到静态方法，方便使用
	public static <T> T copyParamToBean(Map value, T bean) {
		try {
			BeanUtils.populate(bean, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}


}