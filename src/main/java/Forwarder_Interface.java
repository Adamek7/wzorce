
public interface Forwarder_Interface {

  void handleRequest(String request);
  void registerObserver(Observer o);
  void sendMessage(String msg);
  void setCommandMenager(CommandMenager_Interface commMgr);
}
