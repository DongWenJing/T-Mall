package com.tmall.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author R.Yu
 * @date 2022/4/1 23:01
 */

@WebFilter(filterName = "IllegalTextFilter", value = "/*")
public class IllegalTextFilter implements Filter {
    private final List<String> illegalTextList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        // 读取illegalText.txt文件
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(
                    new InputStreamReader(
                            IllegalTextFilter.class.getClassLoader().getResourceAsStream("illegalText.txt"), StandardCharsets.UTF_8));
            String illegalText = null;
            while ((illegalText = bufferReader.readLine()) != null) {
                illegalTextList.add(illegalText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferReader != null;
                bufferReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        // HttpServletRequest req = (HttpServletRequest) request;
        String commentText = request.getParameter("commentText");
        for (String s : illegalTextList) {
            if (commentText.contains(s)) {
                StringBuilder star = new StringBuilder();
                for (int i=0; i<s.length();i++) {
                    star.append("*");
                }
                commentText = commentText.replace(s, star.toString());
            }
        }
        System.out.println("commentText = " + commentText);
        request.setAttribute("commentText", commentText);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
