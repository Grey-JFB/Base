package com.grey.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class StringUtils {

    public static void main(String[] args) {

    }

    /**
     * 批量替换文件夹中文件文本内容
     *
     * @param suffix     要处理的文本文件后缀
     * @param dealPath   要处理的问题目录
     * @param sourceStr  要替换的目标字符串
     * @param replaceStr 要替换的字符串
     */
    public static void batchReplace(String suffix, String dealPath, String sourceStr, String replaceStr) {
        String newPath = dealPath + "\\new\\";
        File newFilePath = new File(newPath);
        newFilePath.mkdir();
        File pathFile = new File(dealPath);
        if (pathFile.isDirectory()) {
            File[] files = pathFile.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(suffix)) {
                    String s = readFile(file);
                    String newStr = s.replaceAll(sourceStr, replaceStr);
                    try {
                        FileOutputStream outputStream = new FileOutputStream(newPath + file.getName());
                        outputStream.write(newStr.getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static String readFile(File file) {
        FileInputStream is = null;
        StringBuilder stringBuilder = null;
        try {
            if (file.length() != 0) {
                is = new FileInputStream(file);
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                is.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
