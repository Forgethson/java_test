package com.homework_and_exercise.chapter8.extend;

//输入ctrl + H 可以看到类的继承关系
public class Sub extends Base { //子类
    public int n1 = 1000;
    public String count = "Sub类的count";
    public Sub() {//无参构造器
        super();
        this.fun1();
        System.out.println("子类Sub()构造器被调用....");
    }
    public Sub(String name, int age) {
        super(name, age);
        System.out.println("子类Sub(String name, int age)构造器被调用....");
    }
    public int getN1() {
        return n1;
    }
    public String getCount() {
        return count;
    }
    public void fun1() {
        System.out.println("调用fun1函数");
    }

    public void sayOk() {//子类方法
        //非私有的属性和方法可以在子类直接访问
        //但是私有属性和方法不能在子类直接访问
        System.out.println(n1 + " " + n2 + " " + n3);
        test100();
        test200();
        test300();
        //test400();错误
        //要通过父类提供公共的方法去访问
        System.out.println("n4=" + getN4());
        callTest400();//
    }

}
