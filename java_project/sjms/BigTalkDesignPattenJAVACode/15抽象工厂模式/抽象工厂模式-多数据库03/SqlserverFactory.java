package code.chapter15.abstractfactory3;

//Sqlserver工厂
public class SqlserverFactory implements IFactory {

    public IUser createUser(){
        return new SqlserverUser();
    }
    
    public IDepartment createDepartment(){
        return new SqlserverDepartment();
    }
    
}

