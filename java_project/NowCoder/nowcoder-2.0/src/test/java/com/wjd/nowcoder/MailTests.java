package com.wjd.nowcoder;

import com.wjd.nowcoder.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NowcoderApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("649343453@qq.com", "TEST", "Welcome.");
    }

    @Test
    public void testHtmlMail() {
        // 由于脱离了MVC场景，手动创建一个Context对象
        Context context = new Context();
        // 设置变量到context
        context.setVariable("username", "sunday");

        // content就是经过Thymeleaf渲染后的html代码
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("649343453@qq.com", "HTML", content);
    }

}
