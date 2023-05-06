package code.chapter8.calculator1;

//加法工厂
public class AddFactory implements IFactory {

    public Operation createOperation(){
        return new Add();
    }
    
}
