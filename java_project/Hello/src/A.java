public class A {
    public static void main(String[] args) {

        Student1 student1 = new Student1();
        Student1 student2 = new Student1();

        {
            int x = 1;
        }
        AA a = new B();
        a.fun1();
        B b = new B();
        b.print();

        String s = "好的，明天见";
        String s1 = new String("好的，明天见");
        System.out.println(s.length());
        System.out.println(s1.length());

        System.out.println(s.getBytes().length);
        while (true) {
            int x = 1;
        }
    }
}

class AA {
    private void print() {
        System.out.println("A");
    }

    protected void fun1() {
        print();
    }
}

class B extends AA {
    public void print() {
        System.out.println("B");
    }

    public void fun1() {
        print();
    }
}

class Student1 {
    private String name;
    private int ID;
}
