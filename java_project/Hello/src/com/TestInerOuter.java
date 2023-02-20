package com;

public class TestInerOuter {
    public static void main(String[] args) {
        Outer outer = new Outer(122);
        outer.usem1();

    }
}

class Outer {
    private int x1 = 100;

    public Outer(int x1) {
        this.x1 = x1;
    }

    private void fun1() {
        System.out.println("Outer fun1");
    }

    public void usem1() {
        m1();
    }

    public void m1() {
        class Inner {
            private int x1 = 800;

            private void fun1() {
                System.out.println("Inner fun1");
                System.out.println("Inner x1=" + x1 + " Outer x1=" + Outer.this.x1);
            }

            public Inner(int x1) {
                this.x1 = x1;
                Outer.this.fun1();  // 外部类的方法
                fun1(); // 内部类的方法
            }
        }
        // 在定义完一个内部类之后，在内部 new 新的对象（不能在这个m1外定义）
        Inner inner = new Inner(123);
        System.out.println(inner.x1);
    }
}
