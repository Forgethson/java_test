//
//import java.util.Scanner;
//
//public class Test {
//
//	public static void main(String[] args){
//
//		Scanner sc = new Scanner(System.in);
//
//		System.out.println("请输入数字A：");
//		String A = sc.nextLine();
//		System.out.println("请选择运算符号(+、-、*、/)：");
//		String B = sc.nextLine();
//		System.out.println("请输入数字B：");
//		String C = sc.nextLine();
//		double D = 0d;
//
//		if (B.equals("+"))
//            D = Double.parseDouble(A) + Double.parseDouble(C);
//        if (B.equals("-"))
//            D = Double.parseDouble(A) - Double.parseDouble(C);
//        if (B.equals("*"))
//            D = Double.parseDouble(A) * Double.parseDouble(C);
//        if (B.equals("/"))
//            D = Double.parseDouble(A) / Double.parseDouble(C);
//
//		System.out.println("结果是："+D);
//	}
//}
//
//
//		try {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("请输入数字A：");
//			double numberA = Double.parseDouble(sc.nextLine());
//			System.out.println("请选择运算符号(+、-、*、/)：");
//			String strOperate = sc.nextLine();
//			System.out.println("请输入数字B：");
//			double numberB = Double.parseDouble(sc.nextLine());
//			double result = 0d;
//
//			switch(strOperate){
//				case "+":
//					result = numberA + numberB;
//					break;
//				case "-":
//					result = numberA - numberB;
//					break;
//				case "*":
//					result = numberA * numberB;
//					break;
//				case "/":
//					result = numberA / numberB;
//					break;
//			}
//
//			System.out.println("结果是："+result);
//		}
//		catch(Exception e){
//			System.out.println("您的输入有错："+e.toString());
//		}
//
//public class Operation {
//    public static double getResult(double numberA, double numberB, String operate) {
//        double result = 0d;
//        switch (operate) {
//            case "+":
//                result = numberA + numberB;
//                break;
//            case "-":
//                result = numberA - numberB;
//                break;
//            case "*":
//                result = numberA * numberB;
//                break;
//            case "/":
//                result = numberA / numberB;
//                break;
//            case "sqrt"
//            	result =
//        }
//        return result;
//    }
//}
//
//
//			Scanner sc = new Scanner(System.in);
//
//			System.out.println("请输入数字A：");
//			double numberA = Double.parseDouble(sc.nextLine());
//			System.out.println("请选择运算符号(+、-、*、/)：");
//			String strOperate = sc.nextLine();
//			System.out.println("请输入数字B：");
//			double numberB = Double.parseDouble(sc.nextLine());
//
//			double result = Operation.getResult(numberA,numberB,strOperate);
//
//			System.out.println("结果是："+result);
//
//
//		switch (operate) {
//            case "+":
//                result = numberA + numberB;
//                break;
//            case "-":
//                result = numberA - numberB;
//                break;
//            case "*":
//                result = numberA * numberB;
//                break;
//            case "/":
//                result = numberA / numberB;
//                break;
//            case "pow":
//                result= java.lang.Math.pow(numberA,numberB);
//                break;
//        }
//
//
//	if (员工是小菜) {
//	    salary = salary * 1.1;
//	}
//
//public abstract class Operation {
//
//    public double getResult(double numberA, double numberB){
//        return 0d;
//    }
//
//}
//
//public class Add extends Operation {
//    public double getResult(double numberA, double numberB){
//        return numberA + numberB;
//    }
//}
//
//public class Sub extends Operation {
//    public double getResult(double numberA, double numberB){
//        return numberA - numberB;
//    }
//}
//
//public class Mul extends Operation {
//    public double getResult(double numberA, double numberB){
//        return numberA * numberB;
//    }
//}
//
//public class Div extends Operation {
//    public double getResult(double numberA, double numberB){
//        if (numberB == 0){
//            System.out.println("除数不能为0");
//            throw new ArithmeticException();
//        }
//        return numberA / numberB;
//    }
//}
//
//public class OperationFactory {
//
//    public static Operation createOperate(String operate){
//        Operation oper = null;
//        switch (operate) {
//            case "+":
//                oper = new Add();
//                break;
//            case "-":
//                oper = new Sub();
//                break;
//            case "*":
//                oper = new Mul();
//                break;
//            case "/":
//                oper = new Div();
//                break;
//        }
//        return oper;
//    }
//}
//
//
//			Operation oper = OperationFactory.createOperate(strOperate);
//			double result = oper.getResult(numberA,numberB);
//
//
//
//abstract class Animal {
//
//}
//
//
//
//interface IFly {
//	void fly();
//}
//
//
//
//interface ILanguage {
//	void speak();
//}
//
//
//
//class Bird extends Animal {
//
//}
//
//
//
//class WideGoose implements IFly {
//
//}
//
//
//
//class Penguin extends Bird {
//
//	//在企鹅Penguin中，引用了气候Climate对象
//	private Climate climate;
//
//
//}
//
//
//class WideGooseAggregate {
//
//	//在雁群WideGooseAggregate类中有大雁数组对象arrayWideGoose
//	private WideGoose[] arrayWideGoose;
//
//
//}
//
//class Bird {
//	//在鸟Bird类中声明一个翅膀Wing对象wing
// 	private Wing wing;
//
//	public Bird() {
//		//初始化时，实例化翅膀Wing，它们之间同时生成
//		wing = new Wing();
//	}
//
//}
//
//abstract class Animal {
//
//	public Metabolism (Oxygen oxygen, Water water){
//
//	}
//
//}
//
//
//
//
//
//
//
