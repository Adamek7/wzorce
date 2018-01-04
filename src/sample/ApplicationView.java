package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Queue;
//to jest nasze view, posiada model i obserwatora w postaci controllera

public class ApplicationView extends Application {


    private Model_Interface model = new Model();
    private Observer_Interface observer = new Controller(model,this);



    private TextField hostTextField;
    private TextField portTextField;
    private TextArea queueTextField;
    private ProgressBar movieProgressBar;
    TextField linkTextField;
    Button pauseButton;
    Button playButton;

    public void setCurrentTime(double length){
        movieProgressBar.setProgress(length);
    }

    public void setQueueMovies(){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> queue = model.getQueue();
        for(String s: queue){
            sb.append(s);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        queueTextField.setText(sb.toString());
    }

    @Override
    public void start(Stage primaryStage) {

        VBox siatkaPionowaVBox = new VBox(10);
        siatkaPionowaVBox.setPadding(new Insets(5));
        HBox hostAndPort = new HBox(10);

            Label hostLabel = new Label();
            hostLabel.setText("HOST: ");
            hostTextField = new TextField("192.168.0.13");
            hostTextField.setPrefSize(150, 5);
            hostTextField.textProperty().addListener((obs, oldText, newText) -> {
                    observer.onClick_host(hostTextField.getText());
            });

            Label portLabel = new Label();
            portLabel.setText("PORT: ");
            portTextField = new TextField("8080");
            portTextField.setPrefSize(80,5);
            portTextField.textProperty().addListener((obs, oldText, newText) -> {
                observer.onClick_port(portTextField.getText());
            });

        hostAndPort.getChildren().addAll(hostLabel, hostTextField, portLabel, portTextField );
        siatkaPionowaVBox.getChildren().add(hostAndPort);

        HBox linkHBox = new HBox(10);
            Label linkLabel = new Label();
            linkLabel.setText("LINK: ");
            linkTextField = new TextField();
            linkTextField.setPrefSize(500,10);
            Button sendButton = new Button("Add");
            sendButton.setOnAction(new EventHandler<ActionEvent>() {
                //jezeli kliknelismy w ten przycisk to informujemy naszego obserwatora, ze sie cos zmienilo//////////////
                @Override
                public void handle(ActionEvent event) {
                    observer.onClick_addMovie(linkTextField.getText());
                }
            });
        linkHBox.getChildren().addAll(linkLabel,linkTextField,sendButton);
        siatkaPionowaVBox.getChildren().add(linkHBox);

        HBox pauseAndPlayHBox= new HBox(10);
            pauseButton = new Button("pause");
            pauseButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    observer.onClick_pause();
                }
            });
            playButton = new Button("play");
            playButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    observer.onClick_play();
                }
            });
            queueTextField = new TextArea();
            queueTextField.setPrefSize(300,500);
        pauseAndPlayHBox.getChildren().addAll(pauseButton,playButton,queueTextField);
        siatkaPionowaVBox.getChildren().add(pauseAndPlayHBox);

        HBox exitHBox = new HBox(50);
        Button exitButton = new Button("Zakończ");
        movieProgressBar = new ProgressBar(0.30);
        movieProgressBar.setPrefSize(400,10);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });

        exitHBox.getChildren().addAll(exitButton,movieProgressBar);
        siatkaPionowaVBox.getChildren().add(exitHBox);
        Scene scene = new Scene(siatkaPionowaVBox, 700, 250);
        primaryStage.setTitle("Client panel");
        primaryStage.setScene(scene);
        primaryStage.show();


    }



}
