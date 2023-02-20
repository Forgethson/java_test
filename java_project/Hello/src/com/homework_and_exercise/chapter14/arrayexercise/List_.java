package com.homework_and_exercise.chapter14.arrayexercise;

import java.util.*;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/13
 * ArrayList 课后练习
 */
@SuppressWarnings("all")

public class List_ {
    public static void bubbleSortCustom(List arr, Comparator c) {
        Object temp;
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = 0; j < arr.size() - 1 - i; j++) {
                if (c.compare(arr.get(j), arr.get(j + 1)) > 0) {
                    temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args) {

        ArrayList BookList = new ArrayList();
//        LinkedList BookList = new LinkedList();
//        Vector BookList = new Vector();
        BookList.add(new Book("三国演义", 50, "罗贯中"));
        BookList.add(new Book("西游记", 24, "吴承恩"));
        BookList.add(new Book("水浒传", 16, "施耐庵"));
        BookList.add(new Book("红楼梦", 79, "曹雪芹"));
        BookList.add(new Book("三体", 21, "刘慈欣"));

        System.out.println(BookList.get(1).hashCode());

//        BookList.sort(new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                Book book1 = (Book) o1;
//                Book book2 = (Book) o2;
//                return book1.getPrice() - book2.getPrice();
//            }
//        });
        bubbleSortCustom(BookList, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book) o1;
                Book book2 = (Book) o2;
                return book1.getPrice() - book2.getPrice();
            }
        });

        System.out.println(BookList.toString());
    }
}

class Book {
    private String name;
    private int price;
    private String author;

    public Book(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                '}' + '\n';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, author);
    }
}