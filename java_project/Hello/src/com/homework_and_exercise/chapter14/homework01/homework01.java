package com.homework_and_exercise.chapter14.homework01;

import java.util.ArrayList;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/14
 * 按要求实现：
 * （1）封装一个新闻类，包含标题和内容属性，提供get、set方法，重写toString方法，打印对象时只打印标题：
 * （2）只提供一个带参数的构造器，实例化对象时，只初始化标题；并且实例化两个对像：
 * 新闻一：新冠确诊病例超干万，数百万印度教信徒赴恒河“圣浴”引民众担忧新闻二：男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生
 * （3）将新闻对象添加到ArrayList集合中，并且进行倒序遍历；
 * （4）在遍历集合过程中，对新闻标题进行处理，超过15字的只保留前15个，然后在后边加“.”
 * （5）在控制台打印遍历出经过处理的新闻标题；
 */
@SuppressWarnings({"all"})
public class homework01 {

    public static String processTitle(String title) {
        if (title == null) {
            return "";
        }
        if (title.length() > 15) {
            return title.substring(0, 15) + "...";
        } else return title;
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new news("新冠病例超千万阿富汗傅海峰哈司法是覅哈是覅时发誓非"));
        arrayList.add(new news("男子突然想起发发发发发发付付付付付付付付付付付"));

        int size = arrayList.size();
        for (int i = size - 1; i >= 0; i--) {
            news n = (news)arrayList.get(i);
            System.out.println(processTitle(n.getTitle()));
        }
    }
}

class news {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public news(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
