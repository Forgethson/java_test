package code.chapter0.animal3;

public class Sheep extends Animal {

	public Sheep (){
		super();
	}
	public Sheep (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "咩";
	}
}
