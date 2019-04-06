package sample;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    Button play, pause, choseFile, connect, reload;
    Label textPath;
    MP3Player player = new MP3Player();
    Communication communication = new Communication(player);
    boolean connection=false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        play=new Button();
        pause=new Button();
        choseFile=new Button();
        connect = new Button();
        reload = new Button();
        textPath=new Label();
        play.setText("Start");
        pause.setText("Pause");
        choseFile.setText("Open file");
        connect.setText("Connect");
        reload.setText("Reload");
        textPath.setText("PATH");
        GridPane sp = new GridPane();
        //sp.getChildren().add(play);
        //sp.getChildren().add(pause);
        sp.add(play, 1, 0);
        sp.add(pause, 2, 0);
        sp.add(reload, 1, 1);
        sp.add(choseFile, 1, 2);
        sp.add(connect, 2, 1);
        sp.add(textPath, 2, 2);
        primaryStage.setTitle("First MP3 Player");
        primaryStage.setScene(new Scene(sp, 400, 100));
        primaryStage.show();

        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.play();
                textPath.setText(player.getPath());
            }
        });
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean tmp = player.pause();
               if(tmp)
                   pause.setText("Play");
               else
                   pause.setText("Pause");
            }
        });
        reload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.reload();
            }
        });
        choseFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.setPath();
            }
        });
        connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!connection){
                    communication.start();
                    connection=true;
                }
            }
        });

        //SLIDER
       /* Slider slider = new Slider();
        HBox.setHgrow(slider, Priority.ALWAYS);
        slider.setMinSize(300, 50);

        slider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (slider.isValueChanging()) {
// multiply duration by percentage calculated by slider position
                    if (duration != null) {
                        mediaPlayer.seek(duration.multiply(slider.getValue() / 100.0));
                    }
                    updateValues();

                }
            }
        });*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
