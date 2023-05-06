package code.chapter0.animal3;

public class Cattle extends Animal {

	public Cattle (){
		super();
	}
	public Cattle (String name){
		super(name);
	}

	protected String getShoutSound(){
		return "哞";
	}
}
