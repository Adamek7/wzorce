package sample;

import java.util.LinkedList;
import java.util.Queue;

public class QUEUE_Command implements Command_Interface {
    private Controller controller;

    QUEUE_Command(Controller controller){
        this.controller =controller;
    }

    @Override
    public void execute() {
        //System.out.println("queue commands bez arguentow");
    }

    @Override
    public void execute(String args) {
        args = args.replaceAll("\\[" ,"");
        args = args.replaceAll("]","");
        String[] list = args.split(",");
        Queue<String> queue = new LinkedList<>();

        for(String l : list){
            queue.add(l);
        }
        controller.model.setQueue(queue);
        controller.view.setQueueMovies();
    }
}
