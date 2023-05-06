package BuilderPattern.Case1;

// 产品电脑类
public class Computer {
    private String name;
    private String CPU;
    private String GPU;
    private String memory;
    private String motherboard;
    private String hardDisk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public String toString() {
        return "you have a " + name + " computer:\n" +
                "\t CPU: " + CPU + "\n" +
                "\t GPU: " + GPU + "\n" +
                "\t memory: " + memory + "\n" +
                "\t motherboard: " + motherboard + "\n" +
                "\t hardDisk: " + hardDisk + "\n";
    }
}
