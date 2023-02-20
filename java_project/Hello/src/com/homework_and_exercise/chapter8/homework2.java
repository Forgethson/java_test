package com.homework_and_exercise.chapter8;

/**
 * 编写教师类
 */
public class homework2 {
    public static void main(String[] args) {
    professor p1 = new professor("Wang", 25, "A+", 15000.0, 5);
    p1.introduce();
    }
}

class professor extends teacher {
    private int grade;
    public professor (String name, int age, String post, double salary, int grade) {
        super(name, age, post, salary);
        this.grade = grade;
    }
    public void introduce(){
        System.out.println("教授" + "name=" + getName() + " age=" + getAge() + " post=" + getPost() + " salary=" + getSalary() + " grade=" + grade);
    }
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

class teacher {
    private String name;
    private int age;
    private String post;
    private double salary;

    public teacher(String name, int age, String post, double salary) {
        this.name = name;
        this.age = age;
        this.post = post;
        this.salary = salary;
    }

    public void introduce() {
        System.out.println("name=" + name + " age=" + age + " post=" + post + " salary=" + salary);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPost() {
        return post;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}