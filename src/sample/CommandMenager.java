package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandMenager implements CommandMenager_Interface {
    private Map<String,Command_Interface> commands = new HashMap<>();
    @Override
    public void registerCommand(String name, Command_Interface comm) {
        commands.put(name,comm);
    }

    @Override
    public void executeCommand(String name, String args) {
        //System.out.println(" name "+name.toString());
        //commands.get("QUEUE").execute();
        commands.get(name).execute(args);
    }

    @Override
    public void executeCommand(String name) {
        commands.get(name).execute();
    }
}
