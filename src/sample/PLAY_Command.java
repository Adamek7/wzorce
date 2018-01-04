package sample;

public class PLAY_Command implements Command_Interface {
    private Controller controller;

    PLAY_Command(Controller controller){
        this.controller =controller;
    }

    @Override
    public void execute() {
        controller.setDisablePlay(true);
        controller.setDisablePause(false);
    }

    @Override
    public void execute(String args) {

    }
}
