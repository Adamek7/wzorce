package sample;

public class LENGTH_Command implements Command_Interface {
    private Controller controller;
    LENGTH_Command(Controller controller){
        this.controller=controller;
    }
    @Override
    public void execute() {

    }

    @Override
    public void execute(String args) {
        controller.model.setMovieLength(Long.parseLong(args));
    }
}
