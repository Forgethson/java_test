package code.chapter15.abstractfactory2;

//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser(){
        return new SqlserverUser();
    }
    
}

