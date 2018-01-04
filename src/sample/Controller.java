package sample;

public class Controller implements Observer_Interface{
    //wzorzec MVC controller jako obserwator jest zarejestrowany u podmiotu(view), jezeli tam sie cos wydarzy to odpowiednio na to reaguje
    //wzorzec obserwator, controller jako obserwator implementuje Observer_interface, przy ktorego pomocy bedzie powiadamiany o zmianach w podmiocie
    //pro tip, obiekt obserowany to podmiot(tutaj view), obietk obserwujacy to obserwator(tutaj controller)
    ApplicationView view;
    Model_Interface model ;
    //przechowuje dostepne polecenia
    CommandMenager_Interface commMgr = new CommandMenager();
    //flaga do oznaczenia czy zarejstrowalismy sie jako obserwator u raspberry
    private boolean registerd = false;

    public Controller(Model_Interface model , ApplicationView view){
        this.model=model;
        this.view=view;
        commMgr.registerCommand("PLAY",new PLAY_Command(this));
        commMgr.registerCommand("PAUSE",new PAUSE_Command(this));
        commMgr.registerCommand("QUEUE",new QUEUE_Command(this));
        commMgr.registerCommand("LENGTH",new LENGTH_Command(this));//to jeszcze nie skonczone
        Server.setController(this);
    }

    //reagujemy na klikniecia w przyciski na panelu uzytkownika
    @Override
    public void onClick_play() {
        setDisablePlay(true);
        setDisablePause(false);
        sendMessage("PLAY");
    }

    @Override
    public void onClick_pause() {
        setDisablePlay(false);
        setDisablePause(true);
        sendMessage("PAUSE");
    }

    @Override
    public void onClick_scroll() {

    }

    @Override
    public void onClick_addMovie(String link) {
        view.linkTextField.setText("");
        String msg = "ADDMOVIE|"+link;
        sendMessage(msg);
    }

    @Override
    public void onClick_host(String host) {
       model.setHost(host);
    }

    @Override
    public void onClick_port(String port) {
        model.setPort(port);
    }

    void setDisablePlay(boolean change){
        view.playButton.setDisable(change);
    }
    void setDisablePause(boolean change){
        view.pauseButton.setDisable(change);
    }


    //wysylamy wiadomosci do raspberry
    private void sendMessage(String msg){
        try {
            if(registerd==false){
                registerd = true;
                String m = "REGISTER|"+model.getHost()+"|"+Server.getPort();
                System.out.println("wysylam zadnie o rejestracje" +m);
                Server.sendGetRequest(model.getHost(),model.getPort(),m);
            }
            Server.sendGetRequest(model.getHost(),model.getPort(),msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //otrzymujemy wiadomosci z raspberry, rozkladamy i wywolujemy konkretne polecenie
    public void handleRequest(String request){
        String req = request.split(" ")[1];
        String[] part = req.split("\\|");

        if(part.length==1){
            commMgr.executeCommand(part[1]);
            //System.out.println("handelRequest bez argumentow" +part[0]);
        }else if(part.length==3){
            //System.out.println("handelRequest z argumentami " +part[1]+part[2]);
            commMgr.executeCommand(part[1],part[2]);

        }
    }
}
