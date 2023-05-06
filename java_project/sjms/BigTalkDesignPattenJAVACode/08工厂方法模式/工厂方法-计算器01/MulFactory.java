package code.chapter8.calculator1;

//乘法工厂
public class MulFactory implements IFactory {

    public Operation createOperation(){
        return new Mul();
    }
    
}
