package sample;

public interface Command_Interface {
    //kazde polecenie posiada tylko funkcje execute, która wykonuje po wywolaniu tego polecenia
    void execute();
    void execute(String args);
}
