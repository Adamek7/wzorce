

public class ADDMOVIE_Command implements Command_Interface {
    private Driver_Interface driver;

    ADDMOVIE_Command(Driver_Interface model){
        this.driver =model;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String[] args) {
        driver.addMovie(args[0]);
    }
}
