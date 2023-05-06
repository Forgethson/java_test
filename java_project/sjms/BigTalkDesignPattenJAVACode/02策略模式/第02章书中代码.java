
		double price = 0d; 		//商品单价
		int num = 0;			//商品购买数量
		double totalPrices = 0d;//当前商品合计费用
		double total = 0d;		//总计所有商品费用
		
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("请输入商品销售模式 1.原价 2.八折 3.七折：");	
			discount = Integer.parseInt(sc.nextLine());
			System.out.println("请输入商品单价：");	
			price = Double.parseDouble(sc.nextLine());
			System.out.println("请输入商品数量：");	
			num = Integer.parseInt(sc.nextLine());
			System.out.println();	

			if (price>0 && num>0){

				//通过单价*数量获得当前商品合计费用，
				//通过累加获得总计费用
				totalPrices = price * num;
				total = total + totalPrices;
				
				System.out.println();	
				System.out.println("单价："+ price + "元 数量："+ num +" 合计："+ totalPrices +"元");	
				System.out.println();
				System.out.println("总计："+ total+"元");	
				System.out.println();
			}
		}
		while(price>0 && num>0);


		int discount = 0; //商品销售模式 1.原价 2.八折 3.七折：
		
		......

		switch(discount){
			case 1:
				totalPrices = price * num; 		//正常收费
				break;
			case 2:
				totalPrices = price * num * 0.8;//打八折
				break;
			case 3:
				totalPrices = price * num * 0.7;//打七折
				break;
		}
		
		total = total + totalPrices;


//收费抽象类
public abstract class CashSuper {

	//收取费用的抽象方法，参数为单价和数量
    public abstract double acceptCash(double price,int num);
    
}

//正常收费
public class CashNormal extends CashSuper {

	//原价返回
    public double acceptCash(double price,int num){
        return price * num;
    }
    
}

//打折收费
public class CashRebate extends CashSuper {

	private double moneyRebate = 1d;
    //初始化时必需输入折扣率。八折就输入0.8
    public CashRebate(double moneyRebate){
        this.moneyRebate = moneyRebate;
    }

    //计算收费时需要在原价基础上乘以折扣率
    public double acceptCash(double price,int num){
        return price * num * this.moneyRebate;
    }
    
}

//返利收费
public class CashReturn extends CashSuper {

    private double moneyCondition = 0d; //返利条件
    private double moneyReturn = 0d;	//返利值

    //初始化时需要输入返利条件和返利值。
    //比如“满300返100”，就是moneyCondition=300,
    //moneyReturn=100
    public CashReturn(double moneyCondition,double moneyReturn
    	){
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    //计算收费时，当达到返利条件，就原价减去返利值
    public double acceptCash(double price,int num){
        double result = price * num;
        if (result >= moneyCondition){
            result = result - Math.floor(result / moneyCondition) * moneyReturn; 

        }
        return result;
    }    
}

//收费工厂
public class CashFactory {

    public static CashSuper createCashAccept(int cashType){
        CashSuper cs = null;
        switch (cashType) {
            case 1:
                cs = new CashNormal();      //正常收费
                break;
            case 2:
                cs = new CashRebate(0.8d);  //打八折
                break;
            case 3:
                cs = new CashRebate(0.7d);  //打七折
                break;
            case 4:
                cs = new CashReturn(300d,100d);//满300返100
                break;

        }
        return cs;
    }
    
}


				//简单工厂模式根据discount的数字选择合适的收费类生成实例
				CashSuper csuper = CashFactory.createCashAccept(discount);
				//通过多态，可以根据不同收费策略计算得到收费的结果
				totalPrices = csuper.acceptCash(price,num);
				
				total = total + totalPrices;


//抽象算法类
abstract class Strategy{
	//算法方法
	public abstract void algorithmInterface();

}

//具体算法A
class ConcreteStrategyA extends Strategy {
    //算法A实现方法
    public void algorithmInterface() {
        System.out.println("算法A实现");
    }
}

//具体算法B
class ConcreteStrategyB extends Strategy {
    //算法B实现方法
    public void algorithmInterface() {
        System.out.println("算法B实现");
    }
}

//具体算法C
class ConcreteStrategyC extends Strategy {
    //算法C实现方法
    public void algorithmInterface() {
        System.out.println("算法C实现");
    }
}

//上下文
class Context {
    Strategy strategy;
    //初始化时，传入具体的策略对象
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    //上下文接口
    public void contextInterface() {
    	//根据具体的策略对象，调用其算法的方法
        strategy.algorithmInterface();
    }
}

		Context context;

		//由于实例化不同的策略，所以最终在调用
		//context.contextInterface()时,所
		//获得的结果就不尽相同
	    context = new Context(new ConcreteStrategyA());
	    context.contextInterface();

	    context = new Context(new ConcreteStrategyB());
	    context.contextInterface();

	    context = new Context(new ConcreteStrategyC());
	    context.contextInterface();		



public class CashContext {

    private CashSuper cs;   //声明一个CashSuper对象

    //通过构造方法，传入具体的收费策略
    public CashContext(CashSuper csuper){
        this.cs=csuper;
    }

    public double getResult(double price,int num){
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price,num);
    }
    
}

				CashContext cc=null;

				//根据用户输入，将对应的策略对象作为参数传入CashContext对象中
				switch(discount){
					case 1:
						cc = new CashContext(new CashNormal());
						break;
					case 2:
						cc = new CashContext(new CashRebate(0.8d));
						break;
					case 3:
						cc = new CashContext(new CashRebate(0.7d));
						break;
					case 4:
						cc = new CashContext(new CashReturn(300d,100d));
						break;
				}
				
				//通过Context的getResult方法的调用，可以得到收取费用的结果
				//让具体算法与客户进行了隔离
				totalPrices = cc.getResult(price,num);
				
				total = total + totalPrices;

public class CashContext {

    private CashSuper cs;   //声明一个CashSuper对象

    //通过构造方法，传入具体的收费策略
    public CashContext(int cashType){
        switch(cashType){
            case 1:
                this.cs = new CashNormal();
                break;
            case 2:
                this.cs = new CashRebate(0.8d);
                break;
            case 3:
                this.cs = new CashRebate(0.7d);
                break;
            case 4:
                this.cs = new CashReturn(300d,100d);
                break;
        }
    }

    public double getResult(double price,int num){
        //根据收费策略的不同，获得计算结果
        return this.cs.acceptCash(price,num);
    }    
}


				//根据用户输入，将对应的策略对象作为参数传入CashContext对象中
				CashContext cc = new CashContext(discount);
				
				//通过Context的getResult方法的调用，可以得到收取费用的结果
				//让具体算法与客户进行了隔离
				totalPrices = cc.getResult(price,num);
				
				total = total + totalPrices;

				

				//简单工厂模式的用法
				CashSuper csuper = CashFactory.createCashAccept(discount);
				totalPrices = csuper.acceptCash(price,num);

				//策略模式与简单工厂结合的用法
				CashContext cc = new CashContext(discount);
				totalPrices = cc.getResult(price,num);

