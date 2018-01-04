package sample;

public class PAUSE_Command implements Command_Interface {
    private Controller controller;

    PAUSE_Command(Controller controller){
        this.controller =controller;
    }

    @Override
    public void execute() {
        controller.setDisablePlay(false);
        controller.setDisablePause(true);
    }

    @Override
    public void execute(String args) {

    }
}
