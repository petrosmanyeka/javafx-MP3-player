import java.io.File;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class Audus extends Application{

    Button button1 = new Button("play");
    Button button2 = new Button("pause");
    Button button3 = new Button("next");
    Button button4 = new Button("reset");
    Button button5 = new Button("previous");
    Button button6 = new Button("speed");

    GridPane gridPane = new GridPane();

    Slider slider = new Slider();
    VBox vBox = new VBox(10);
    HBox hBox = new HBox();
    Label label = new Label();
    SplitPane splitPane = new SplitPane();
    ProgressBar progressBar = new ProgressBar();
    private ComboBox <String> comboBox ;
    private File directory;
    private File[] files;
    private ArrayList<File> songs;
    private int songNumber;
    private int speed [] = {20,40,60,80,120};

    private Timer timer;
    private TimerTask timerTask;
    private Media media;
    private MediaPlayer mediaPlayer;
    
    @Override
    public void start(Stage stage){
       // label.setTextFill(Color.web("#0076a3"));

        songs = new ArrayList<File>();
        directory = new File("C:\\Users\\MANYEKA\\Documents\\JAVA\\music");
        files = directory.listFiles();

        if (files != null){
            for(File file : files){
                songs.add(file);
                //System.out.println(file);
            }
            
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        label.setText(songs.get(songNumber).getName());

        

        //************************************************** */
        button1.setOnAction(e-> plays());
        button2.setOnAction(e -> puases());
        button3.setOnAction(e -> nexts());
        button4.setOnAction(e -> resets());
        button5.setOnAction(e -> previ());

        hBox.getChildren().addAll(button1,button2,button3,button4,button5,button6);
         
        vBox.getChildren().addAll(hBox,slider);
        splitPane.getItems().addAll(label,progressBar,vBox);
        vBox.setAlignment(Pos.CENTER);
        splitPane.setOrientation(Orientation.VERTICAL);
        gridPane.add(splitPane, 0, 0);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane,300,300);
        stage.setScene(scene);
        stage.show();
    }

    private void plays(){
        mediaPlayer.play();
    }
    private void puases(){
        mediaPlayer.pause();
        
    }
    private void resets(){
        mediaPlayer.seek(Duration.seconds(0.0)); 
    }
    private void nexts(){
        if(songNumber < songs.size()-1){
            songNumber++;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
    
            label.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            volume();
        }
        else{
            songNumber = 0;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
    
            label.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
        }
    }
    private void previ(){
        if(songNumber > 0){
            songNumber--;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
    
            label.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            volume();
        }
        else{
            songNumber =songs.size()-1;
            mediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
    
            label.setText(songs.get(songNumber).getName());
            mediaPlayer.play();
            volume();        }
    }
    private void volume(){
        slider.setPrefWidth(200);
        slider.setMaxWidth(Region.USE_PREF_SIZE);
        slider.setMinWidth(30);
        slider.setValue(50);
        mediaPlayer.volumeProperty().bind(slider.valueProperty().divide(100));

    }
    private void beginTimer(){

    }
    private void endTimer(){
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
