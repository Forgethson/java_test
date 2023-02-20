import java.util.ArrayList;

//@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

        Test1 ex = new Test1();
        System.out.println(ex.str.hashCode());
        Test1.change(ex.str, ex.ch);
        System.out.println(ex.str + " and");
        System.out.println(ex.ch);

        String s1 = "abc";
        System.out.println(s1.hashCode());
        s1 = "abcd";
        System.out.println(s1.hashCode());

        System.out.println("abcd".compareTo("abcaef"));

        StringBuffer s2 = new StringBuffer("张三丰");
        s2.append("赵敏");
        System.out.println(s2);

        ArrayList<Integer> l1 = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            l1.add(i);
        }
        l1.add(10);
        l1.add(11);
        l1.add(12);
        l1.add(13);
        l1.add(14);
        l1.add(15);
        l1.add(16);


    }
}

class Test1 {
    String str = new String("hsp");
    final char[] ch = {'j', 'a', 'v', 'a'};

    public static void change(String str, char ch[]) {
        System.out.println(str.hashCode());
        str = "java";
        ch[0] = 'h';
    }
}

class im implements i2{
    @Override
    public void fun2() {

    }

    @Override
    public void fun1() {

    }
}

interface i1 {
    void fun1();
}

interface i2 extends i1 {
    void fun2();
}
