public class REGISTER_Command implements Command_Interface {
    private Forwarder_Interface forwarder;

    REGISTER_Command(Forwarder_Interface controller){
        this.forwarder = controller;
    }


    @Override
    public void execute() {

    }

    @Override
    public void execute(String[] args) {
        forwarder.registerObserver(new Observer(args[0],Integer.parseInt(args[1])));
    }


}
