package sample;

import java.util.ArrayList;
import java.util.Queue;

public interface Model_Interface {
    ArrayList getQueue();
    void setQueue(Queue queue);
    String getHost();
    void setHost(String host);
    int getPort();
    void setPort(String port);
    void setMovieLength(long time);
    long getMovieLength();

}
