package sample;

public interface CommandMenager_Interface {
    //wzorzec polecenie, tutaj posiadamy mape polecen. Polecenia wywolujemy metoda executeCommand
    void registerCommand(String name, Command_Interface comm);
    void executeCommand(String name, String args);
    void executeCommand(String name);

}
