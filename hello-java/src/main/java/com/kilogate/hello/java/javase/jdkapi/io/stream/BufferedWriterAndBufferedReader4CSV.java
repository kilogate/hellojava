package com.kilogate.hello.java.javase.jdkapi.io.stream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV：逗号分隔值（Comma Separated Values）
 * BufferedWriter and BufferedReader
 */
public class BufferedWriterAndBufferedReader4CSV {
    /**
     * 将字符串列表导出为 CSV 文件
     */
    public static boolean exportCsv(String file, List<String> dataList) {
        boolean isSucess = false;

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK")); // Microsoft Excel 默认使用 ANSI 解码，不支持 UTF-8 编码的中文字符
            if (dataList != null && !dataList.isEmpty()) {
                for (String data : dataList) {
                    writer.append(data).append("\r");
                }
            }
            writer.flush();
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    writer = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 将 CSV 文件导入为字符串列表
     */
    public static List<String> importCsv(String file) {
        List<String> dataList = new ArrayList<String>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                dataList.add(line);
            }
        } catch (Exception e) {
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                    reader = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }

    public static void main(String[] args) {
        // 导出测试
        List<String> dataList = new ArrayList<String>();
        dataList.add("姓名,手机号,身份证号,还款状态,逾期状态,资格审核结果,命中同盾规则,命中百融规则");
        dataList.add("张三,13838383838,'100000199901011000,还款中,正常还款,资格审核拒绝,多平台借贷申请检测,");
        boolean isSuccess = BufferedWriterAndBufferedReader4CSV.exportCsv("/Users/kilogate/Desktop/tst.csv", dataList);
        System.out.println(isSuccess);

        // 导入测试
//        List<String> dataList = BufferedWriterAndBufferedReader4CSV.importCsv("/Users/kilogate/Desktop/tst.csv");
//        if(dataList!=null && !dataList.isEmpty()){
//            for(String data : dataList){
//                System.out.println(data);
//            }
//        }
    }
}
