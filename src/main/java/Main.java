

public class Main{
    public static void main(String[] args){
        Server serv = new Server();
        serv.setPort(8080);
        Thread thread = new Thread(serv);
        thread.start();

        PlayerView player = new PlayerView();
        Forwarder_Interface forwarder = new Forwarder();
        Driver_Interface driver = new Driver(player, forwarder);
        player.setModel(driver);

        CommandMenager_Interface commMgr = new CommandMenager();
        commMgr.registerCommand("PLAY",new PLAY_Command(driver));
        commMgr.registerCommand("PAUSE",new PAUSE_Command(driver));
        commMgr.registerCommand("ADDMOVIE",new ADDMOVIE_Command(driver));
        commMgr.registerCommand("REGISTER", new REGISTER_Command(forwarder));
        forwarder.setCommandMenager(commMgr);


    }
}
