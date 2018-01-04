import java.util.LinkedList;
import java.util.Queue;

public class Driver implements Driver_Interface {
    PlayerView player;
    Forwarder_Interface forwarder;

    Driver(PlayerView view, Forwarder_Interface forwarder){
        this.player = view;
        this.forwarder = forwarder;
    }

    Queue<String> movieQueue = new LinkedList();
    private String currentMovie ="";
    private long movieLength;

    public void play(){
        player.updateState_play();
        forwarder.sendMessage("PLAY");
    }
    public void pause(){
        player.updateState_pause();
        forwarder.sendMessage("PAUSE");
    }
    public void addMovie(String movie) {

        movieQueue.add(movie);

        String que = movieQueue.toString();
        que = que.replaceAll(" ","");
        String msg = "QUEUE|"+que;
        forwarder.sendMessage(msg);
        if (currentMovie.equals("")) {
            setCurrentMovie();
        }

    }

   /* public void changeQueue(){
        notifyObserver("queue");
    }
    public void changedCurrentMovie(){
        notifyObserver("current");
    }

    public Queue getMovieQueue(){
        return movieQueue;
    }

*/
    private void setCurrentMovie() {
        if(movieQueue.isEmpty()==false){
            currentMovie = movieQueue.poll();
            System.out.println("ustawiam film " + currentMovie);
            player.updateState_currentMovie(currentMovie);
            System.out.println("i to wszystko");

            String que = movieQueue.toString();
            que = que.replaceAll(" ","");
            String msg = "QUEUE|"+que;
            forwarder.sendMessage(msg);
        }
        player.updateState_currentMovie(currentMovie);

    }


    private int kurwaNieMamPomysluDlaczegoToTakKurwaDziala = 2;
    public void finished(){
        kurwaNieMamPomysluDlaczegoToTakKurwaDziala--;
        if(kurwaNieMamPomysluDlaczegoToTakKurwaDziala==0){
            System.out.println("finished");
            currentMovie="";
            setCurrentMovie();
            kurwaNieMamPomysluDlaczegoToTakKurwaDziala=2;
        }
    }

    @Override
    public void setTime(long time) {
        String msg = "LENGTH|"+time;
        forwarder.sendMessage(msg);
    }
/*
    private void setScroll(int time){
        scroll = time;
    }
    public int getScroll(){return scroll;}
*/








}
