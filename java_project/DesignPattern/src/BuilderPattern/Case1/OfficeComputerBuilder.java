package BuilderPattern.Case1;

// 办公电脑建造者
public class OfficeComputerBuilder extends ComputerBuilder {
    @Override
    public void name() {
        computer.setName("office");
    }

    @Override
    public void CPU() {
        computer.setCPU("i7-7700k");
    }

    @Override
    public void GPU() {
        computer.setGPU("GTX 1050 Ti");
    }

    @Override
    public void memory() {
        computer.setMemory("32GB");
    }

    @Override
    public void motherboard() {
        computer.setMotherboard("ASUS  B560M-PLUS");
    }

    @Override
    public void hardDisk() {
        computer.setHardDisk("1TB SSD");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
