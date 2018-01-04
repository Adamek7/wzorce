package sample;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Model implements Model_Interface{
    //wzorzec MVC model nie wie nic o controllerze i view, on tylko przechowuje jakis stan, wykonuje jakaś robotę(algorytmy) cos liczy etc.
    private ArrayList<String> queueMovie = new ArrayList<>();
    private String host ="192.168.0.13";//wpisane na stale, w celu osobistego tesowania zmienic na odpowiednie wartosci, ew usunac i wpisywac recznie
    private int port = 8080; //wpisane na stale, w celu osobistego tesowania zmienic na odpowiednie wartosci, ew usunac i wpisywac recznie
    private long movieLength=0;

    @Override
    public ArrayList<String> getQueue() {
        return queueMovie;
    }
    @Override
    public void setQueue(Queue queue) {

        queueMovie.clear();
        queueMovie.addAll(queue);// = queue;
       // System.out.println("Ustanowiono nowa kolejke "+queueMovie.toString());
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public void setHost(String host) {
        this.host=host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(String port) {
        this.port=Integer.parseInt(port);
    }

    @Override
    public void setMovieLength(long time) {
        movieLength=time;
        //System.out.println("to jest dlugosc filmu "+movieLength);
    }

    @Override
    public long getMovieLength() {
        return movieLength;
    }
}
