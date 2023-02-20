package com.homework_and_exercise.chapter11.enum_;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/11
 */
public class TestEnum {
    public static void main(String[] args) {
//        System.out.println(Season.SPRING);
//        System.out.println("===所有星期的信息如下===");
//        Week[] week =  Week.values();
//        for (Week i: week) {
//            System.out.println(i);
//        }
//        System.out.println(Week.MONDAY.name());
        Color.RED.show();
    }
}

enum Color implements Interface {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    BLACK(0, 0, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0);
    private final int redValue;
    private final int blueValue;
    private final int blackValue;

    Color(int redValue, int blueValue, int blackValue) {
        this.redValue = redValue;
        this.blueValue = blueValue;
        this.blackValue = blackValue;
    }

    public int getRedValue() {
        return redValue;
    }

    public int getBlueValue() {
        return blueValue;
    }

    public int getBlackValue() {
        return blackValue;
    }

    public void show() {
        System.out.println(getRedValue() + "," + getBlueValue() + "," + getBlackValue());
    }

    @Override
    public String toString() {
        return redValue + ", " + blueValue + ", " + blackValue;
    }
}

enum Week {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    private final String name;

    Week(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

enum Season {
    SPRING("春天", "温暖"),
    SUMMER("夏天", "炎热"),
    AUTUMN("秋天", "凉爽"),
    WINTER("冬天", "寒冷");
    private final String name;
    private final String desc;

    Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

}

interface Interface {
    public void show();
}