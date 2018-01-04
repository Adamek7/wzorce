public class PAUSE_Command implements Command_Interface {
    private Driver_Interface driver;

    PAUSE_Command(Driver_Interface model){
        this.driver =model;
    }

    @Override
    public void execute() {
        driver.pause();
    }
    @Override
    public void execute(String[] args) {

    }
}
