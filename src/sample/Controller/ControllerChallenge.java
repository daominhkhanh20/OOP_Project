package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Class.Student;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerChallenge implements Initializable {
    @FXML
    private Button bt1,bt2,bt3;

    @FXML
    private Text name;

    @FXML
    private Text textChallenge;

    @FXML
    private GridPane gridPane;
    @FXML
    private Button setButton;
    static int lesson=0;
    static int level;
    public final static int MAX = 10;

    private Student student;

    private Hyperlink[] hl = new Hyperlink[MAX];

    public void setHyperLink(){
        for(int i=0;i<MAX;i++){
            hl[i] = new Hyperlink();
            gridPane.add(hl[i],0,i);
        }
    }
    public void undo(ActionEvent event) throws IOException {
        setSceneHome(event);
    }
    public void setSceneHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/home.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
        window.setTitle("English Application");
        window.setScene(scene);
        window.show();
    }
    public void temp(String a) throws IOException {
        Scanner is = new Scanner(Paths.get(a),
                "UTF-8");
        for (int i =0;i<MAX;i++){
            hl[i].setText(is.nextLine());
            hl[i].setFont(Font.font("system", FontPosture.REGULAR,14));
        }
        is.close();
    }

    public void setOnButton(String a) throws IOException {
        this.textChallenge.setText("Level " + a);
        temp("src\\sample\\video\\Path\\lv" + a + ".txt");
        student.setLevel(a);
        setAction("Level " + a,"DataLevel" + a);
    }

    public void setTextChallenge1(ActionEvent event) throws IOException {
        setOnButton("1");
    }
    public void setTextChallenge2(ActionEvent event) throws IOException {
        setOnButton("2");
    }
    public void setTextChallenge3(ActionEvent event) throws IOException {
        setOnButton("3");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student = ControllerLogin.student;
        name.setText(student.getFullName());
        setHyperLink();
        try{
            String a = student.getLevel();
            if(a!=null){
                switch (a){
                    case "1" : setOnButton("1");break;
                    case "2" : setOnButton("2");break;
                    case "3" : setOnButton("3");break;
                    default:
                        System.out.println("Choose level");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(String a,String b){
        for(int i=0;i<MAX;i++){
            int finalI = i;
            hl[i].setOnAction(event -> {
                try {
                    student.setBaiso(finalI +1);
                    //System.out.println(Main.students[Main.dem].getBaiso());
                    openAudio(event,a,b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void logout(ActionEvent event) throws IOException {
        setSceneLogin(event);
        student = null;
    }
    public void setSceneLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/loginDemo.fxml"));
        Stage primaryStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        primaryStage.setTitle("English Application");
        Scene scene = new Scene(root, 700, 500);
       // scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

        Image image = new Image(getClass().getResourceAsStream("../book.png"));
        primaryStage.getIcons().add(image);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void getPath(ActionEvent event,String a,String b){
        ControllerPlayVideo.path = "src\\sample\\video\\" + a + "\\";
        ControllerPlayVideo.fileAnswer="src/sample/video/"+b+"/";
        Hyperlink hyperlink = (Hyperlink) event.getSource();
        for (int i =0;i<MAX;i++){
            if(hyperlink.equals(hl[i])){
                ControllerPlayVideo.path += hl[i].getText();
                lesson=i+1;
                ControllerPlayVideo.fileAnswer+=hl[i].getText().substring(0,hl[i].getText().length()-3)+"txt";
                break;
            }
        }
    }

    public boolean checkLession(int level){
        int temp[]= student.getNow();
        System.out.println("temp = " + temp[level-1]);
        System.out.println("Lesson = " + lesson);
        if(lesson>temp[level-1]+1){
            return false;
        }
        return true;
    }
    public void openAudio(ActionEvent event,String a,String b) throws IOException {
        getPath(event,a,b);
        String temp= textChallenge.getText();
        if(temp.toLowerCase().equals("level 1")){
            level=1;
        }
        else if(temp.toLowerCase().equals("level 2")){
            level=2;
        }
        else{
            level=3;
        }

        System.out.println(lesson);
        if(checkLession(level)){
            setSceneAudio(event);
        }
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warring");

            alert.setHeaderText("Results:");
            alert.setContentText("Sorry, You can't study this lesson because in recent\n" +
                    "because at this time you haven't completed a few previous lessons" +
                    "So please complete the previous lesson before study this lesson");
            alert.showAndWait();
        }
    }
    public void setSceneAudio(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/play.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
        window.setTitle("English Application");
        window.setScene(scene);
        window.show();
    }
}
