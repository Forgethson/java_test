package com.homework_and_exercise.chapter15.homework01;

import java.util.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/15
 * P772 泛型的作业
 * 定义个泛型类DAO<T>，在其中定义一个Map成员变量，Map的键为String类型，值为T类型。
 * 分别创建以下方法：
 * （1）public void save（String id，T entity）：保存T类型的对象到Map成员变量中
 * （2）public T get（String id）：从map中获取id对应的对象
 * （3）public void update（String id，T entity）：替换map中key为id的内容，改为entity对像
 * （4）public List-<T>list0：返回map中存放的所有T对象
 * （5）public void delete（String id）：删除指定id对象
 * <p>
 * 定义一个User类：
 * 该类包含：private成员变量（int类型）id，age；（String类型）name。
 * <p>
 * 创建DAO类的对像，分别调用其save、get、update、Iist、delete方法来操作User对象，
 * 使用Junit单元测试类进行测试。
 */
public class homework01 {
    public static void main(String[] args) {
        DAO<User> userDAO = new DAO<>();
        userDAO.save("0", new User(0, 24, "jack"));
        userDAO.save("1", new User(1, 25, "smith"));
        userDAO.save("2", new User(2, 27, "mary"));

        System.out.println(userDAO);
        System.out.println(userDAO.get("1"));
        userDAO.update("1", new User(1, 26, "Wang"));
        System.out.println(userDAO);
        userDAO.delete("2");
        System.out.println(userDAO.list());
    }
}

class DAO<T> {
    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity) {
        map.put(id, entity);
    }

    public T get(String id) {
        return map.get(id);
    }

    public void update(String id, T entity) {
        map.put(id, entity);
    }

    public List<T> list() {
        List<T> list = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            list.add(map.get(key));
        }
        return list;
    }

    public void delete(String id) {
        map.remove(id);
    }

    @Override
    public String toString() {
        return "key" + map;
    }
}

class User {
    private int id;
    private int age;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}