package xyz.lhweb.furns.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Web工具类
 *
 * @author 罗汉
 * @date 2023/04/11
 */
public class WebUtils {
    //定义一个文件上传的路径   ctrl+shirt+u
    public static String FURN_IMG_DIRECTORY="assets/images/product-image";
    /**
     * 判断请求是不是ajax请求
     *
     * @param request 请求
     * @return boolean
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

}
