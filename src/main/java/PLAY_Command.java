public class PLAY_Command implements Command_Interface {
    private Driver_Interface driver;

    PLAY_Command(Driver_Interface model){
        this.driver =model;
    }


    @Override
    public void execute() {
        driver.play();
    }

    @Override
    public void execute(String[] args) {

    }
}
