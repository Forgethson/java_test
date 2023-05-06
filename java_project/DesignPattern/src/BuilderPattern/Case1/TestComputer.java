package BuilderPattern.Case1;

public class TestComputer {
    public static void main(String[] args) {
        Director director1 = new Director(new GameComputerBuilder());
        Director director2 = new Director(new OfficeComputerBuilder());
        Computer computer1 = director1.construct();
        Computer computer2 = director2.construct();
        System.out.println(computer1.toString());
        System.out.println(computer2.toString());
    }
}
