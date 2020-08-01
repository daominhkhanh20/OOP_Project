package sample.Controller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Class.ActionDataBase;
import sample.Class.Exercise;
import sample.Class.Student;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class ControllerPlayVideo implements Initializable {
    @FXML
    private MediaView movie;

    @FXML
    private Text guideText;

    @FXML
    private Button TimeCurrent;

    @FXML
    private Slider time;


    @FXML
    private TextField check;

    @FXML
    private Button TotalTime;

    @FXML
    private Button reset;

    @FXML
    private Button Play;

    @FXML
    private Slider sliderVolume;

    @FXML
    private Text hourClock;

    @FXML
    private Text minuteClock;

    @FXML
    private Text secondClock;

    @FXML
    private TextArea guideAnswer;

    @FXML
    private Text countSuggestion;

    public static String path;

    public Student student;
    ActionDataBase action = new ActionDataBase();

    public static String fileAnswer;
    Exercise lesson=new Exercise(fileAnswer);
    private String data =lesson.getData();
    private String data1[]=data.split(" ");
    private String data2[]=Arrays.stream(data1).filter(values->values!=null&&values.length()>0).toArray(size->new String[size]);
    private String arrTemp[] = data.replaceAll("[\\p{Punct}&&[^']]+", " ").split(" ");
    private String arr[]= Arrays.stream(arrTemp).filter(values->values!=null&&values.length()>0).toArray(size->new String[size]);
    private String[] guide=lesson.getGuide();
    private int line=lesson.getLine();
    private int sizeForOneLine[]=lesson.getIndexFirstForEachLine();
    int level=ControllerChallenge.level;
    private int temp=0;
    private static int i = 0;
    String video=new File(path).getAbsolutePath();
    Media media=new Media(new File(video).toURI().toString());
    MediaPlayer mediaPlayer=new MediaPlayer(media);
    private float result=0;

    int countClickGuide=0;

    int second =0;
    int minutes=0;
    int hour=0;
    Timer myTimer =new Timer();
    TimerTask timeRun=new TimerTask() {
        @Override
        public void run() {
            second++;
            if(second>=60){
                minutes++;
                second=second%60;
            }
            if(minutes>=60){
                hour++;
                minutes=minutes%60;
            }
            secondClock.setText(Integer.toString(second));
            minuteClock.setText(Integer.toString(minutes)+":");
            hourClock.setText(Integer.toString(hour)+":");
        }
    };


    public ControllerPlayVideo() throws IOException {
    }



    public void startTimeCLock(){
        myTimer.scheduleAtFixedRate(timeRun,1000,1000);
    }

    public void undo(ActionEvent event) throws IOException {
        i=0;
        countClickGuide=0;
        setSceneChallenge(event);
        mediaPlayer.pause();
        myTimer.cancel();
    }
    public void setSceneChallenge(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/challenge.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
        window.setTitle("English Application");
        window.setScene(scene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student = ControllerLogin.student;

        if(line<4){
            guideAnswer.setText("A: "+guide[0]+"\nB: ");
        }
        else{
            guideAnswer.setText("A: "+guide[0]+"\n"+"B: "+guide[1]+"\nA: ");
        }


       guideAnswer.setEditable(false);
        this.startTimeCLock();
        Play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MediaPlayer.Status status=mediaPlayer.getStatus();
                if(status== MediaPlayer.Status.PLAYING){
                    if(mediaPlayer.getCurrentTime().greaterThanOrEqualTo(mediaPlayer.getTotalDuration())){
                        mediaPlayer.seek(mediaPlayer.getStartTime());
                        mediaPlayer.play();
                    }
                    else{
                        mediaPlayer.pause();
                        File input=new File("src\\sample\\photo\\pause.png");
                        try {
                            String url=input.toURI().toURL().toString();
                            ImageView pauseIcon=new ImageView(url);
                            pauseIcon.setFitHeight(25);
                            pauseIcon.setFitWidth(31);
                            Play.setGraphic(pauseIcon);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if(status== MediaPlayer.Status.PAUSED||status== MediaPlayer.Status.STOPPED){
                    mediaPlayer.play();
                    File input=new File("src\\sample\\photo\\play2.png");
                    try {
                        String url=input.toURI().toURL().toString();
                        ImageView playIcon=new ImageView(url);
                        playIcon.setFitHeight(25);
                        playIcon.setFitWidth(31);
                        Play.setGraphic(playIcon);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        // replay video
        File resetPhoto=new File("src\\sample\\photo\\replay.png");
        try{
            String url1=resetPhoto.toURI().toURL().toString();
            ImageView resetIcon=new ImageView(url1);
            resetIcon.setFitWidth(31);
            resetIcon.setFitHeight(25);
            reset.setGraphic(resetIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(0));

            }
        });


        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                updateValues();
                int a=(int)Math.round(mediaPlayer.getTotalDuration().toSeconds());
                int b=a/60;
                TotalTime.textProperty().set(Integer.toString(b)+":"+Integer.toString(a-60*b));
            }
        });




        //set default volume
        sliderVolume.setMin(0);
        sliderVolume.setMax(100);
        sliderVolume.setValue(100);//the value default
        //change volume audio
        time.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(time.isPressed()) {
                    mediaPlayer.seek(mediaPlayer.getMedia().getDuration().multiply(time.getValue() / 100));

                }
            }
        });
        sliderVolume.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(sliderVolume.isPressed()){
                    mediaPlayer.setVolume(sliderVolume.getValue()/100);
                }
            }
        });
        movie.setMediaPlayer(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
    }

    public void clickActionGuide(ActionEvent event){
        if(countClickGuide>=6){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("English Application");
            alert.setHeaderText("WARRING");
            alert.setContentText("You have exceed 6 times permission to use suggestions");
            alert.showAndWait();
            return;
        }
        guideText.setText(arr[i]);
        countClickGuide++;
        countSuggestion.setText(String.valueOf(countClickGuide));

    }


    public void updateValues(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                double a=mediaPlayer.getCurrentTime().toMillis()/mediaPlayer.getTotalDuration().toMillis()*100;
                time.setValue(a);
                int totalSecond=(int)Math.round(mediaPlayer.getCurrentTime().toSeconds());
                int minute=totalSecond/60;
                int second=totalSecond-minute*60;
                TimeCurrent.textProperty().set(Integer.toString(minute)+":"+Integer.toString(second));
            }
        });
    }

    private int temporary=0;
    public void checkAnswer() throws IOException, SQLException {
        if(temporary==0 &&i==0){
            System.out.println(arr[i]);
            temporary++;
        }
        try {
            int a=(int)(arr[i].charAt(0));
            if(check.getText().length()==1&&a>=65&&a<=90){

                char headChar=check.getText().charAt(0);
                if((int)headChar-32==a){
                    int caretPos=check.getCaretPosition();
                    check.setText(Character.toString(arr[i].charAt(0)));
                    check.positionCaret(caretPos);
                    if(arr[i].length()==1){
                        check.clear();
                        if(i==sizeForOneLine[temp])
                            guideAnswer.setText(guideAnswer.getText()+data2[i]);
                        else{
                            guideAnswer.setText(guideAnswer.getText()+" "+data2[i]);
                        }
                        i++;
                        System.out.println(arr[i]);
                    }
                    if(check.getText().length()==arr[i].length()) {
                        i++;
                        System.out.println(arr[i]);
                    }
                }
                else if((int)headChar==a){
                    if(arr[i].length()==1){
                        check.clear();
                        guideAnswer.setText(guideAnswer.getText()+data2[i]);
                        i++;
                        System.out.println(arr[i]);
                    }
                }
                else{
                    check.deletePreviousChar();
                    return;
                }

                return;
            }


            if (check.getText().charAt(check.getText().length() - 1) != arr[i].charAt(check.getText().length() - 1)) {
                check.deletePreviousChar();
            }
            if (check.getText().length() == arr[i].length()) {
                guideAnswer.setText(guideAnswer.getText() + " " + data2[i]);
                check.clear();
                i++;
                System.out.println(arr[i]);
            }

            if (i == sizeForOneLine[temp] && temp % 2 != 0) {
                guideAnswer.setText(guideAnswer.getText() + "\nA: ");
                temp++;
            } else if (i == sizeForOneLine[temp] && temp % 2 == 0) {
                guideAnswer.setText(guideAnswer.getText() + "\nB: ");
                temp++;
            }


        } catch(Exception e){}

        if(i >= arr.length){
            myTimer.cancel();
            mediaPlayer.pause();
            int time = Integer.parseInt(hourClock.getText().substring(0, hourClock.getText().length()-1))*3600 +
                    Integer.parseInt(minuteClock.getText().substring(0, minuteClock.getText().length()-1))*60 + Integer.parseInt(secondClock.getText());
            double videoduration = mediaPlayer.getTotalDuration().toSeconds();
            result = (float) ((10*(11-time/(4*videoduration))/11));
            
            result = Math.round(result*10);
            result=Math.round((float)(result/10-0.2*countClickGuide)*10);
            result = result/10;
            if(result<0)
                result=0;

            showResult();
            System.out.println(result);
            i = 0;
        }
    }


    public void updateLessonComplete() throws SQLException {

        int lesson=ControllerChallenge.lesson;
        if(student.getNow()[level-1]<level){
            student.getNow()[level-1]= lesson;
        }


        //Create function insert into database saved lesson is completed
        float oldPoint = action.getPoint(student);
        if(oldPoint == 0 ){
            action.insertHistory(result,student);
            //student.insertHistory(result);
        }
        else if (result > oldPoint){
            action.updatePoint(result,student);
           // student.updatePoint(result);
        }





        //insert result into database
        action.editHistory(result,student);
        //student.editHistory(result);
       /* Main.students[Main.dem].insertHistory(result);*/
    }

    public void showResult() throws IOException, SQLException {

        if(result>=7.5){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("App");

            alert.setHeaderText("Results:");
            alert.setContentText("Congratulation, your score is "+result);
            alert.showAndWait();

            updateLessonComplete();

            Parent root = FXMLLoader.load(getClass().getResource("../Fxml/challenge.fxml"));
            Stage window = (Stage)time.getScene().getWindow();
            Scene scene = new Scene(root,1083,690);
            window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
            window.setTitle("English Application");
            window.setScene(scene);
            window.show();
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("App");

            alert.setHeaderText("Results:");
            alert.setContentText("Your score is "+result+".You haven't overcome this challenge. ");
            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("../Fxml/play.fxml"));
            Stage window = (Stage)time.getScene().getWindow();
            Scene scene = new Scene(root,1083,690);
            window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
            window.setTitle("English Application");
            window.setScene(scene);
            window.show();
        }
    }
}