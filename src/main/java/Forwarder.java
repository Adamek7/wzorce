
import java.util.ArrayList;


public class Forwarder implements Forwarder_Interface {
    //zaden wzorzec ta klasa jest troszke podobna to controllera ale nia nie jest
    //po prostu przyjmuje wiadomosci, parsuje i wywoluje konkretne polecenia
    //posiada tez zarejestrowanych obserwatorow (zewnetrznych klientow)

    public CommandMenager_Interface commMgr ;
    ArrayList<Observer> observers = new ArrayList<>();

    public Forwarder(){
        Server.setController(this);
    }

    public  void setCommandMenager(CommandMenager_Interface commMgr){
        this.commMgr=commMgr;
    }


    @Override
    public void handleRequest(String request){
        String req = request.split(" ")[1];
        String[] part = req.split("\\|");
        //System.out.println("przyszlo "+request+" wielkosc tablicy "+part.length);

        if(part.length==2){
            commMgr.executeCommand(part[1]);
            //System.out.println("handelRequest bez argumentow" +part[0]);
        }else if(part.length==3){
            //System.out.println("handelRequest z argumentami " +part[1]+part[2]);
            String[] args = new String[1];
            args[0]=part[2];
            commMgr.executeCommand(part[1],args);
        }else if(part.length==4){
            //System.out.println("zadnie o rejestracje ? "+part[1]);
            String[] args = new String[2];
            args[0]=part[2];
            args[1]=part[3];
            commMgr.executeCommand(part[1],args);

        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        //System.out.println("dodano nowego obserwatora! "+observers.toString());
    }

    @Override
    public void sendMessage(String msg) {
        try {
            for(Observer o : observers){
                Server.sendGetRequest(o.host,o.port,msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

