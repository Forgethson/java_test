package BuilderPattern.Case1;

// 抽象电脑建造者
public abstract class ComputerBuilder {
    protected Computer computer = new Computer();

    public abstract void name();

    public abstract void CPU();

    public abstract void GPU();

    public abstract void memory();

    public abstract void motherboard();

    public abstract void hardDisk();

    public abstract Computer getComputer();
}