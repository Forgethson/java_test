//
//public class Test {
//
//	public static void main(String[] args){
//
//		System.out.println("喵");
//
//	}
//}
//
//public class Test {
//
//	public static void main(String[] args){
//
//		System.out.println(shout());
//
//	}
//
//	String shout(){
//		return "喵";
//	}
//}
//
//public class Cat {
//
//	public String shout(){
//		return "喵";
//	}
//
//}
//
//public class Test {
//
//	public static void main(String[] args){
//
//		Cat cat = new Cat();
//		System.out.println(cat.shout());
//
//	}
//}
//
//Cat cat;			//声明一个Cat对象，对象名为cat
//
//cat = new Cat();	//将此cat对象实例化
//
//public class Cat {
//
//	//声明Cat的私有字符串变量name
//	private String name = "";
//	//定义Cat类的构造方法，参数是输入一个字符串
//	public Cat(String name){
//		//将参数赋值给私有变量name
//		this.name = name;
//	}
//
//	public String shout(){
//		return "我的名字叫"+ name + " 喵";
//	}
//}
//
//public class Test {
//
//	public static void main(String[] args){
//
//		Cat cat = new Cat("咪咪");
//		System.out.println(cat.shout());
//
//	}
//}
//
//
//
//
//public class Cat {
//
//	private String name = "";
//	public Cat(String name){
//		this.name = name;
//	}
//
//	//将构造方法重载
//	public Cat(){
//		this.name = "无名";
//	}
//
//	public String shout(){
//		return "我的名字叫"+ name + " 喵";
//	}
//}
//
//public class Cat {
//
//	//声明一个内部字段，注意是private，默认叫的次数为3
//	private int shoutNum = 3;
//	//表示外界可以给内部的shoutNum赋值
//	public void setShoutNum(int value){
//		this.shoutNum=value;
//	}
//	//表示外界调用时可以得到shoutNum的值
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//}
//
//
//public class Cat {
//
//
//	private int shoutNum = 3;
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//	//去掉set,表示ShoutNum
//	//属性是只读的
//
//
//
//
//}
//
//
//
//
//public class Cat {
//
//	private int shoutNum = 3;
//	public void setShoutNum(int value){
//
//		//控制叫声次数，最多只能叫10声
//		if (value<=10)
//			this.shoutNum=value;
//		else
//			this.shoutNum = 10;
//
//	}
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//}
//
//	public String shout(){
//		String result="";
//		//用一个循环让小猫叫相应的次数
//		for(int i=0;i<this.shoutNum;i++){
//			result+= "喵 ";
//		}
//		return " 我的名字叫"+ name + " " + result;
//	}
//
//
//
//
//		Cat cat = new Cat("咪咪");
//
//		cat.setShoutNum(5);   //给属性赋值
//
//		System.out.println(cat.shout());
//
//
//
//
//		Dog dog = new Dog("旺财");
//
//		dog.setShoutNum(8);
//
//		System.out.println(dog.shout());
//
//
//
//
//public class Cat {
//
//	private String name = "";
//	public Cat(String name){
//		this.name = name;
//	}
//
//	public Cat(){
//		this.name="无名";
//	}
//
//	private int shoutNum = 3;
//	public void setShoutNum(int value){
//		this.shoutNum=value;
//	}
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result+= "喵 ";
//		}
//		return " 我的名字叫"+ name + " " + result;
//	}
//}
//
//public class Dog {
//
//	private String name = "";
//	public Dog(String name){
//		this.name = name;
//	}
//
//	public Dog(){
//		this.name="无名";
//	}
//
//	private int shoutNum = 3;
//	public void setShoutNum(int value){
//		this.shoutNum=value;
//	}
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result+= "汪 ";
//		}
//		return " 我的名字叫"+ name + " " + result;
//	}
//}
//
//public class Animal {
//
//	protected String name = "";
//	public Animal(String name){
//		this.name = name;
//	}
//
//	public Animal(){
//		this.name = "无名";
//	}
//
//	protected int shoutNum = 3;
//	public void setShoutNum(int value){
//		this.shoutNum = value;
//	}
//	public int getShoutNum(){
//		return this.shoutNum;
//	}
//
//	public String shout(){
//		return "";
//	}
//}
//
//public class Cat extends Animal {
//
//	public Cat(){
//		super();
//	}
//
//	public Cat(String name){
//		super(name);
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result += "喵 ";
//		}
//		return "我的名字叫"+ name + " " + result;
//	}
//}
//
//public class Dog extends Animal {
//
//	public Dog(){
//		super();
//	}
//
//	public Dog(String name){
//		super(name);
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result += "汪 ";
//		}
//		return "我的名字叫"+ name + " " + result;
//	}
//}
//
//public class Test {
//
//	public static void main(String[] args){
//
//		//有五个动物报名的资格
//		Animal[] arrayAnimal=new Animal[5];
//
//		//报名代码
//
//
//
//
//
//
//
//		//开始叫声比赛，遍历这个数组，让每个动物对象都shout
//        for(int i=0;i<5;i++){
//        	System.out.println(arrayAnimal[i].shout());
//        }
//	}
//}
//
//public class Animal {
//
//	......
//
//	public String shout(){
//		return "";
//	}
//}
//
//public class Test {
//
//	public static void main(String[] args){
//
//		//有五个动物报名的资格
//		Animal[] arrayAnimal=new Animal[5];
//
//		//报名代码
//		arrayAnimal[0] = new Cat("小花");
//        arrayAnimal[1] = new Dog("阿毛");
//        arrayAnimal[2] = new Dog("小黑");
//        arrayAnimal[3] = new Cat("娇娇");
//        arrayAnimal[4] = new Cat("咪咪");
//
//		//开始叫声比赛，遍历这个数组，让每个动物对象都shout
//        for(int i=0;i<5;i++){
//        	System.out.println(arrayAnimal[i].shout());
//        }
//	}
//}
//
//	动物 animal = new 猫();
//
//
//	猫 cat = new 猫();
//	动物 animal= cat;
//
//
//public class Cattle extends Animal {
//
//	public Cattle (){
//		super();
//	}
//
//	public Cattle (String name){
//		super(name);
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result+= "哞 ";
//		}
//		return "我的名字叫"+ name + " " + result;
//	}
//}
//
//
//public class Sheep extends Animal {
//
//	public Sheep (){
//		super();
//	}
//
//	public Sheep (String name){
//		super(name);
//	}
//
//	public String shout(){
//		String result="";
//		for(int i=0;i<this.shoutNum;i++){
//			result+= "咩 ";
//		}
//		return "我的名字叫"+ name + " " + result;
//	}
//}
//
//public class Animal {
//
//	......
//
//	public String shout(){
//
//		String result="";
//
//		for(int i=0;i<this.shoutNum;i++){
//			result+= getShoutSound()+", ";
//		}
//
//		return "我的名字叫"+ name + " " + result;
//
//	}
//
//	protected String getShoutSound(){
//		return "";
//	}
//
//}
//
//public class Cat extends Animal {
//
//	public Cat (){
//		super();
//	}
//	public Cat (String name){
//		super(name);
//	}
//
//	protected String getShoutSound(){
//		return "喵";
//	}
//}
//
//public class Dog extends Animal {
//
//	public Dog (){
//		super();
//	}
//	public Dog (String name){
//		super(name);
//	}
//
//	protected String getShoutSound(){
//		return "汪";
//	}
//}
//
//public class Sheep extends Animal {
//
//	public Sheep (){
//		super();
//	}
//	public Sheep (String name){
//		super(name);
//	}
//
//	protected String getShoutSound(){
//		return "咩";
//	}
//}
//
//public class Cattle extends Animal {
//
//	public Cattle (){
//		super();
//	}
//	public Cattle (String name){
//		super(name);
//	}
//
//	protected String getShoutSound(){
//		return "哞";
//	}
//}
//
////声明一个抽象类，在class前增加abstract关键字
//public abstract class Animal {
//
//	......
//
//	//声明一个抽象方法，在返回值类型前加abstract
//	//抽象方法没有方法体，直接在括号后加“；”
//	protected abstract String getShoutSound();
//
//}
//
////声明一个IChange接口
//public interface IChange {
//
//	//此接口有一个方法ChangeThing，
//	//参数是一个字符串变量，返回一字符
//	public String changeThing(String thing);
//
//}
//
////继承了Cat类，并实现了IChange接口
//public class MachineCat extends Cat implements IChange {
//
//	public MachineCat (){
//		super();
//	}
//	public MachineCat (String name){
//		super(name);
//	}
//
//	//实现了接口的方法
//	public String changeThing(String thing){
//		return super.shout()+ "，我有万能的口袋，我可变出" + thing;
//	}
//}
//
//	//创建两个类的实例
//    MachineCat mcat = new MachineCat("叮噹");
//    StoneMonkey wukong = new StoneMonkey("孙悟空");
//
//    //声明了一个接口数组，将两个类的实例引用给接口数组
//    IChange[] array = new IChange[2];
//    array[0] = mcat;
//    array[1] = wukong;
//
//    //利用多态性，实现不同的changeThing
//    System.out.println(array[0].changeThing("各种各样的东西！"));
//    System.out.println(array[1].changeThing("各种各样的东西！"));
//
//
////导入ArrayList所在的程序包
//import java.util.ArrayList;
//
//public class Test {
//
//	public static void main(String[] args){
//
//		//声明集合对象，并实例化对象
//		ArrayList arrayAnimal=new ArrayList();
//
//		//调用集合的add方法增加对象，参数是所有对象的抽象类Object,
//		//所以new Cat()或new Dog()都是可以的
//		arrayAnimal.add(new Cat("小花"));
//		arrayAnimal.add(new Dog("阿毛"));
//		arrayAnimal.add(new Dog("小黑"));
//		arrayAnimal.add(new Cat("娇娇"));
//		arrayAnimal.add(new Cat("咪咪"));
//
//		//遍历集合
//        for(Object item : arrayAnimal){
//        	Animal animal = (Animal)item;//此时需要强制将Object转化为Animal对象
//        	System.out.println(animal.shout());
//        }
//
//		System.out.println("动物个数："+arrayAnimal.size());
//
//	}
//}
//
//		arrayAnimal.add(new Cat("小花"));
//		arrayAnimal.add(new Dog("阿毛"));
//		arrayAnimal.add(new Dog("小黑"));
//		arrayAnimal.add(new Cat("娇娇"));
//		arrayAnimal.add(new Cat("咪咪"));
//
//		//阿毛和小黑两条狗要退出比赛，所以要移除它们
//		arrayAnimal.remove(1);
//		arrayAnimal.remove(2);
//
//		arrayAnimal.remove(1);
//		arrayAnimal.remove(1);
//
//	int i = 123;
//	Object o = (Object)i;  //装箱 boxing
//
//
//	o = 123;
//	i = (int)o;  //拆箱 unboxing
//
//
//import java.util.ArrayList;
//
//public class Test {
//
//	public static void main(String[] args){
//
//		//声明泛型集合变量，在<>中声明Animal，意味着此集合只接受Animal对象
//		ArrayList<Animal> arrayAnimal = new ArrayList<Animal>();
//
//		arrayAnimal.add(new Cat("小花"));
//		arrayAnimal.add(new Dog("阿毛"));
//		arrayAnimal.add(new Dog("小黑"));
//		arrayAnimal.add(new Cat("娇娇"));
//		arrayAnimal.add(new Cat("咪咪"));
//
//		//此时循环可以直接明确集合中都是Animal的item
//        for(Animal item : arrayAnimal){
//        	System.out.println(item.shout());
//        }
//
//		System.out.println("动物个数："+arrayAnimal.size());
//
//	}
//}
//
//
//
//
//
