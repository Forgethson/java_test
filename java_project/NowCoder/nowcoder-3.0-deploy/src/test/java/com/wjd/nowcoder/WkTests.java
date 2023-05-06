package com.wjd.nowcoder;

import java.io.IOException;

public class WkTests {

    public static void main(String[] args) {
        String cmd = "e:/wkhtmltopdf/bin/wkhtmltoimage --quality 75 https://www.baidu.com e:/work/data/wk-images/3.png";
//        String cmd = "e:/wkhtmltopdf/bin/wkhtmltopdf https://www.bilibili.com/ e:/work/data/wk-pdf/3.pdf";
        try {
            Runtime.getRuntime().exec(cmd);
            System.out.println("ok.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
