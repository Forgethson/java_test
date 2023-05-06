package code.chapter0.animal4;

public class Monkey extends Animal {

	public Monkey (){
		super();
	}
	public Monkey (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "吱";
	}
}
