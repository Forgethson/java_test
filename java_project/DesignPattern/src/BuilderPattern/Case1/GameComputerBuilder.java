package BuilderPattern.Case1;

// 游戏电脑建造者
public class GameComputerBuilder extends ComputerBuilder {
    @Override
    public void name() {
        computer.setName("game");
    }

    @Override
    public void CPU() {
        computer.setCPU("i9-12900K");
    }

    @Override
    public void GPU() {
        computer.setGPU("RTX 3090 Ti");
    }

    @Override
    public void memory() {
        computer.setMemory("64GB");
    }

    @Override
    public void motherboard() {
        computer.setMotherboard("Z590 AORUS MASTER");
    }

    @Override
    public void hardDisk() {
        computer.setHardDisk("2TB SSD");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
