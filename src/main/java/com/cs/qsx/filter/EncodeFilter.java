package com.cs.qsx.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 包名 com.cs.qsx.filter
 * 类名 EncodeFilter
 * 类描述 编码过滤器
 *
 * @author Lucifer
 * @version 1.0
 * 创建日期 2017/11/16
 * 时间 15:40
 */
@WebFilter(filterName = "EncodeFilter", urlPatterns = "/*")
public class EncodeFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取请求
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletRequest myRequest = new MyRequest(request);

        //处理响应乱码
        resp.setContentType("text/html;charset=utf-8");

        //放行
        chain.doFilter(myRequest, resp);

    }


    /**
     * 自定义处理请求
     */
    class MyRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;
        private boolean hasEncode = false;

        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String s) {
            //通过自己定义的已经处理过字符编码问题的方法获取map
            Map<String, String[]> parameterMap = getParameterMap();
            //根据map获取值,值是数组
            String[] values = parameterMap.get(s);
            if (values == null) {
                return null;
            }
            //取一个值,这个值就是数组的第0个元素
            return values[0];
        }

        @Override
        public String[] getParameterValues(String s) {
            Map<String, String[]> parameterMap = getParameterMap();
            return parameterMap.get(s);
        }


        //重写getParmeterMap方法,在内部把map中的中文乱码处理掉
        @Override
        public Map<String, String[]> getParameterMap() {
            //处理中文乱码,区别对待get和post
            String method = request.getMethod();
            if ("post".equalsIgnoreCase(method)) {
                //处理post乱码
                try {
                    request.setCharacterEncoding("utf-8");
                    return request.getParameterMap();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else if ("get".equalsIgnoreCase(method)) {
                //处理get乱码
                Map<String, String[]> parameterMap = request.getParameterMap();
                //对于每一个内容都要重新编码再解码
                /*
                说明:tomcat8之前的版本,默认编码格式是iso-8859-1
                在8之后,就是utf-8了,所以只需要对Tomcat8之前的版本进行处理即可
                 */
                String servletInfo = request.getServletContext().getServerInfo();
                if (!hasEncode) {
                    if (servletInfo.substring(servletInfo.lastIndexOf(File.separator) + 1).charAt(0) < 56) {
                        for (String parameterName : parameterMap.keySet()) {
                            //根据键拿到值,这个值并不确定是几个值
                            String[] values = parameterMap.get(parameterName);
                            if (values != null) {
                                for (int i = 0; i < values.length; i++) {
                                    try {
                                        values[i] = new String(values[i].getBytes("ISO-8859-1"), "UTF-8");
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        hasEncode = true;
                    }
                }
                return parameterMap;
            }
            return super.getParameterMap();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
