public class WashingWashingMachine implements WashingMachineActions {

    @Override
    public void turnOn() {
        System.out.println("WashingMachine is ON");
    }

    @Override
    public void turnOff() {
        System.out.println("WashingMachine is OFF");
    }

    @Override
    public void wash() {
        System.out.println("WashingMachine is washing clothes.");
    }
}
