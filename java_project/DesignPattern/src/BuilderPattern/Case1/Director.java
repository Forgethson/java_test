package BuilderPattern.Case1;

// 组装电脑指挥者
public class Director {
    private final ComputerBuilder builder;

    public Director(ComputerBuilder builder) {
        this.builder = builder;
    }

    public Computer construct() {
        builder.name();
        builder.CPU();
        builder.GPU();
        builder.motherboard();
        builder.memory();
        builder.hardDisk();
        return builder.getComputer();
    }
}