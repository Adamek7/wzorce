public interface CommandMenager_Interface {
    void registerCommand(String name, Command_Interface comm);
    void executeCommand(String name, String[] args);
    void executeCommand(String name);

}
