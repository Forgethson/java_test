public class ArrayTest {
    public static void main(String[] args) {
        MyTools mt = new MyTools();
        int[] arr = {10, -1, 8, 0, 34};
        mt.bubble(arr);
        System.out.println("===排序后的arr===");
        for (int j : arr) {
            System.out.println(j);
        }
        
        /*等价于下面的循环
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        */
        Student stu1 = new Student("Jianda Wang", 12);
//      自动运行快捷键：Alt + R
    }
}

class MyTools {
    public void bubble(int[] arr) {
        //冒泡排序
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
class Student{
    String name;
    int age;

//  生成构造函数快捷键：Alt + Insert
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
