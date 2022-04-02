package com.tmall.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 本类用于将非法词转换为*
 * @author R.Yu
 * @date 2022/4/1 22:45
 */
public class FilterText {

    private static final ArrayList<String> illegalTextList = new ArrayList<>();

    // 初始化illegalTextList
    static {
        InputStream in = FilterText.class.getClassLoader()
                                    .getResourceAsStream("illegalText.txt");
        BufferedReader br = null;
        try {
            assert in != null;
            br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8));
            String illegalText = null;
            while ((illegalText = br.readLine()) != null) {
                illegalTextList.add(illegalText);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String replaceText(String text) {
        for (String s : illegalTextList) {
            if (text.contains(s)) {
                StringBuilder star = new StringBuilder();
                for (int i=0;i<s.length();i++)
                    star.append("*");
                text = text.replace(s, star.toString());
            }
        }
        return text;
    }

}
