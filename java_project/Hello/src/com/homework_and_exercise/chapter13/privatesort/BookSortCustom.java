package com.homework_and_exercise.chapter13.privatesort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Jianda Wang
 * @version 1.0
 * 2022/12/12
 */
public class BookSortCustom {

    public static void bubbleSortCustom2(Book[] book, Comparator c) {
        double temp = 0;
        for (int i = 0; i < book.length - 1; i++) {
            for (int j = 0; j < book.length - 1 - i; j++) {
                if (c.compare(book[j].getPrice(), book[j + 1].getPrice()) > 0) {
                    temp = book[j].getPrice();
                    book[j].setPrice(book[j + 1].getPrice());
                    book[j + 1].setPrice(temp);
                }
            }
        }
    }

    public static void main(String[] args) {
        Book[] book = new Book[5];
        book[0] = new Book("wang", 18);
        book[1] = new Book("wangd", 1);
        book[2] = new Book("wanag", 24);
        book[3] = new Book("wanfg", 13);
        book[4] = new Book("waang", 6);
        bubbleSortCustom2(book, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                double i1 = (Double) o1;
                double i2 = (Double) o2;
                if (i1 > i2) {
                    return 1;
                } else if (i1 < i2) {
                    return -1;
                } else return 0;
            }
        });

        System.out.println("==排序后的情况==");
        System.out.println(Arrays.toString(book));
    }

}


class Book {
    private String name;
    private double price;

    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{price=" + price +
                '}';
    }
}
