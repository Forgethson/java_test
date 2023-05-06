package code.chapter0.animal2;

public class Dog extends Animal {

	public Dog (){
		super();
	}
	public Dog (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "汪";
	}
}
