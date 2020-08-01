package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Class.ActionDataBase;
import sample.Class.HashPass;
import sample.Class.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChangePass implements Initializable {
    @FXML
    private Text changeName;
    @FXML
    private PasswordField changePass,newPass,confirmPass;
    @FXML
    private Text baoLoi;


    public Student student;
    ActionDataBase action = new ActionDataBase();

    //private Student student;


    public boolean check(Student a) throws Exception {
        HashPass hashPass=new HashPass();
        if(hashPass.checkPassword(changePass.getText(),a.getPassword())==false){
            baoLoi.setFill(Color.RED);
            baoLoi.setText("Password is not incorrect.");
            return false;
        }
        else if(newPass.getText().equals("")||confirmPass.getText().equals("")){
            baoLoi.setFill(Color.RED);
            baoLoi.setText("Password mustn't null.");
            return false;
        }
        else if(!(newPass.getText()).equals(confirmPass.getText())){
            baoLoi.setFill(Color.RED);
            baoLoi.setText("Password does not match.");
            return false;
        }
        else {
            return true;
        }
    }
    public void setChangePass(ActionEvent event) throws Exception {
        Student a = student;
        if(check(a)){
            HashPass hashPass=new HashPass();

            action.updatePass(hashPass.hashPassword(newPass.getText()),a);
            Alert hi = new Alert(Alert.AlertType.INFORMATION);
            hi.setTitle("Update password");
            hi.setHeaderText("Log out");
            hi.setContentText("Update password successfully !");
            hi.showAndWait();
            logout(event);
        }
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

    public void logout(ActionEvent event) throws IOException {
        setSceneLogin(event);
        student = null;
    }

    public void BackAccounts(ActionEvent event) throws IOException{
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student = ControllerLogin.student;
        changeName.setText(student.getUserName());
    }
}
