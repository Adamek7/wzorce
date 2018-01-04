
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventListener;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;


public class PlayerView implements MediaPlayerEventListener{
    //tutaj jest po prostu wyswietlany film
    private Driver_Interface driver;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final JFrame frame;

    void setModel(Driver_Interface model){
        this.driver=model;
    }

    public PlayerView(){

        LibXUtil.initialise();

        frame = new JFrame("The best project");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        mediaPlayerComponent = new EmbeddedMediaPlayerComponent() {
            protected String[] onGetMediaPlayerFactoryArgs() {
                return new String[] {"--vout=xcb_x11","--aout=alsa"};
            }
        };
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        frame.setContentPane(contentPane);
        frame.setVisible(false);
        mediaPlayerComponent.getMediaPlayer().addMediaPlayerEventListener(this);
    }

    public void updateState_play(){
        mediaPlayerComponent.getMediaPlayer().play();
    }

    public void updateState_pause(){
        mediaPlayerComponent.getMediaPlayer().pause();
        System.out.print(" dlugosc "+mediaPlayerComponent.getMediaPlayer().getLength());
       // System.out.print(" dlugosc "+mediaPlayerComponent.getMediaPlayer().getVideoDescriptions().toString());
    }

      public void updateState_currentMovie(String currentMovie){
        if(currentMovie.equals("")){
            System.out.println("to jest pusty film");
            frame.setVisible(false);
        }else{
            frame.setVisible(true);
            mediaPlayerComponent.getMediaPlayer().setPlaySubItems(true);
            mediaPlayerComponent.getMediaPlayer().playMedia(currentMovie);
        }

    }

    @Override
    public void mediaChanged(MediaPlayer mediaPlayer, libvlc_media_t media, String mrl) {

    }

    @Override
    public void opening(MediaPlayer mediaPlayer) {

    }

    @Override
    public void buffering(MediaPlayer mediaPlayer, float newCache) {

    }

    @Override
    public void playing(MediaPlayer mediaPlayer) {
        //System.out.print(" dlugosc "+mediaPlayerComponent.getMediaPlayer().getLength());
        //System.out.println("playing");
        if(mediaPlayerComponent.getMediaPlayer().getLength()>0){
            driver.setTime(mediaPlayerComponent.getMediaPlayer().getLength());
        }
    }

    @Override
    public void paused(MediaPlayer mediaPlayer) {

          System.out.println("paused");
    }

    @Override
    public void stopped(MediaPlayer mediaPlayer) {

    }

    @Override
    public void forward(MediaPlayer mediaPlayer) {

    }

    @Override
    public void backward(MediaPlayer mediaPlayer) {

    }

    @Override
    public void finished(MediaPlayer mediaPlayer) {
        //System.out.println("finished");
        driver.finished();
    }

    @Override
    public void timeChanged(MediaPlayer mediaPlayer, long newTime) {

    }

    @Override
    public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {

    }

    @Override
    public void seekableChanged(MediaPlayer mediaPlayer, int newSeekable) {

    }

    @Override
    public void pausableChanged(MediaPlayer mediaPlayer, int newPausable) {

    }

    @Override
    public void titleChanged(MediaPlayer mediaPlayer, int newTitle) {

    }

    @Override
    public void snapshotTaken(MediaPlayer mediaPlayer, String filename) {

    }

    @Override
    public void lengthChanged(MediaPlayer mediaPlayer, long newLength) {

    }

    @Override
    public void videoOutput(MediaPlayer mediaPlayer, int newCount) {

    }

    @Override
    public void scrambledChanged(MediaPlayer mediaPlayer, int newScrambled) {

    }

    @Override
    public void elementaryStreamAdded(MediaPlayer mediaPlayer, int type, int id) {

    }

    @Override
    public void elementaryStreamDeleted(MediaPlayer mediaPlayer, int type, int id) {

    }

    @Override
    public void elementaryStreamSelected(MediaPlayer mediaPlayer, int type, int id) {

    }

    @Override
    public void error(MediaPlayer mediaPlayer) {

    }

    @Override
    public void mediaMetaChanged(MediaPlayer mediaPlayer, int metaType) {

    }

    @Override
    public void mediaSubItemAdded(MediaPlayer mediaPlayer, libvlc_media_t subItem) {

    }

    @Override
    public void mediaDurationChanged(MediaPlayer mediaPlayer, long newDuration) {

    }

    @Override
    public void mediaParsedChanged(MediaPlayer mediaPlayer, int newStatus) {

    }

    @Override
    public void mediaFreed(MediaPlayer mediaPlayer) {

    }

    @Override
    public void mediaStateChanged(MediaPlayer mediaPlayer, int newState) {

    }

    @Override
    public void newMedia(MediaPlayer mediaPlayer) {

    }

    @Override
    public void subItemPlayed(MediaPlayer mediaPlayer, int subItemIndex) {

    }

    @Override
    public void subItemFinished(MediaPlayer mediaPlayer, int subItemIndex) {

    }

    @Override
    public void endOfSubItems(MediaPlayer mediaPlayer) {

    }


  /*  public void updateState_scroll(){
        int time = model.getScroll();
        mediaPlayerComponent.getMediaPlayer().setTime(time*1000);
    }
  */
  /*public void updateState_changeQueue(){
        movieQueue = model.getMovieQueue();
        System.out.println("kolejka w playerze " +movieQueue.toString());
        if(movieQueue.isEmpty()==false){
            System.out.print("ustawiono ? "+mediaPlayerComponent.getMediaPlayer().playNextSubItem(movieQueue.peek()));
        }
    }
   */




}