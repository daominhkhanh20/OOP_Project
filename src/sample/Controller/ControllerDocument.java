package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Class.Student;
import sample.Main;

import java.applet.AppletContext;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDocument implements Initializable {
    private Student student;

    @FXML
    private Text name;

    @FXML
    private ImageView firstEbook;

    @FXML
    private ImageView secondEbook;

    @FXML
    private ImageView thirdEbook;

    @FXML
    private ImageView forthEbook;

    @FXML
    private ImageView firstLink;

    @FXML
    private ImageView secondLink;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student = ControllerLogin.student;
        name.setText(student.getFullName());
        Tooltip firstOne = new Tooltip("Đây là cuốn sách tự học TOEIC đầu tiên mà cô khuyên những bạn ở giai đoạn \n bắt đầu học TOEIC nên sử dụng. Thời điểm này các bạn chỉ cần chăm chỉ học Very Easy Toeic là đủ,\n không nên học quá nhiều sách cùng một lúc. Sách Very Easy TOEIC Second Edition được gói\n gọn cung cấp cho các bạn bắt đầu học kiến thức nền tảng về từ vựng, ngữ pháp\n và phát âm. Cuốn sách bao gồm 12 bài học lớn theo từng chủ đề.\n Mỗi bài học cung cấp cho các kiến thức tiếng Anh và bài tập hỗ trợ để bạn luyện\n tập và củng cố kiến thức vừa học.");
        firstEbook.setPickOnBounds(true);
        Tooltip.install(firstEbook, firstOne);

        Tooltip secondOne = new Tooltip("Về sách từ vựng TOEIC có 2 cuốn là 600 từ vựng TOEIC căn bản và 3420 từ vựng\n được sử dụng nhiều nhất. Với quan điểm của cô, các bạn trước tiên hãy nắm vững ghi nhớ 600\n từ vựng TOEIC sẽ tốt nhất, bởi các bạn học quá nhiều từ nhưng không biết cách vận dụng hay\n hiểu về từ đó, cũng như học trước quen sau thì cũng không có ích. Chính vì vậy\n cuốn sách 600 từ vựng TOEIC căn bản sẽ là lựa chọn phù hợp với các\n bạn tự học TOEIC.");
        secondEbook.setPickOnBounds(true);
        Tooltip.install(secondEbook, secondOne);

        Tooltip thirdOne = new Tooltip("Đây là cuốn sách luyện đề TOEIC sẽ giúp bạn hệ thống lại kiến thức đã học, cũng\n như củng cố vững chắc hơn về kiến thức TOEIC qua các bộ đề. Tất cả đề trong sách TOIEIC Analyst\n được đánh giá rất sát thực với đề thi TOEIC hiện nay, các bạn sẽ làm quen những dạng\n bài tập thường gặp trong bài thi, từ vựng, ngữ pháp hay xuất hiện trong từng Part\n và mẹo tránh bẫy, làm bài thi nhanh và hiệu quả hơn. TOEIC Analyst\n phù hợp với các bạn đang đặt mục tiêu TOEIC là 500-750 điểm.");
        thirdEbook.setPickOnBounds(true);
        Tooltip.install(thirdEbook, thirdOne);

        Tooltip forthOne = new Tooltip("Chắc chắn rồi Economy TOEIC sẽ là bộ tài liệu vô cùng quan trọng cho các bạn ôn\n thi TOEIC nâng cao trình độ của bản thân một cách tốt nhất. Hiện nay, sách đã có 5 cuốn, mỗi một\n tập Economy gồm 10 đề thi, vậy là các bạn có 50 đề thi TOEIC để luyện tập thật vững\n chắc trước khi bước vào kỳ thi TOEIC thực. Với bộ đề Economy TOEIC sẽ giúp bạn hiểu\n rõ được cấu trúc đề thi TOEIC như thế nào? các dạng bài tập nào\n sẽ hay xuất hiện trong bài thi và đặc biệt luyện tập nhiều các bộ đề\n trong Economy TOEIC sẽ giúp bạn hiểu được bẫy mà đề thi thường đưa ra nhằm đánh lừa thí sinh.");
        forthEbook.setPickOnBounds(true);
        Tooltip.install(forthEbook, forthOne);

        Tooltip fifthOne = new Tooltip("Đây là một tài liệu TOEIC rất căn bản sẽ giúp bạn tìm hiểu chi tiết cụ thể về đề thi TOEIC Listening.\n" +
                "\n" +
                "Tomato Toeic compact part 1&2: xoay quanh các chủ đề như mua sắm nhà hàng, giao thông, nơi làm việc,\n thời gian rảnh dỗi. Cuốn sách sẽ cung cấp cho bạn kiến thức, kinh nghiệm làm bài\n thi và cả mẹo tránh bẫy trong TOEIC Listening part 1, part 2 một cách hiệu quả nhất.\n" +
                "Tomato Toeic compact part 3&4: dùng để ôn tập TOEIC Listening part 3, part 4. Bạn sẽ được luyện tập\n kiến thức trong sách và bài test của hai part cuối với kiến thức mở rộng hơn so với\n cuốn Tomato Toeic compact part 1&2.\n" +
                "2 cuốn sách này phù hợp với các bạn có trình độ từ 350 Toeic trở lên nhé!");
        firstLink.setPickOnBounds(true);
        Tooltip.install(firstLink, fifthOne);

        Tooltip lastOne = new Tooltip("Tiếp theo là một cuốn sách TOEIC khác mà cô cũng rất yêu thích đó là Jim’s TOEIC\n 1000 Listening Comprehension gồm 10 đề thi TOEIC sát thực với đề thi TOEIC hiện\n nay về từ vựng, ngữ pháp, dạng đề thi.\n" +
                "Khi sử dụng cuốn sách này ôn luyện sẽ giúp các bạn có thể làm quen và rèn luyện\n kiến thức trước khi bước vào kỳ thi TOEIC của mình. Đây là cuốn sách có nội dung tương\n đối khó, vì vậy nó chỉ thích hợp cho các bạn luyện thi TOEIC trên 700 điểm trở lên thôi nhé.");
        secondLink.setPickOnBounds(true);
        Tooltip.install(secondLink, lastOne);
        student = ControllerLogin.student;
        name.setText(student.getFullName());

        firstEbook.setOnMouseClicked(e -> {
            ControllerDownloadDocument.link = "https://www.anhngumshoa.com/tin-tuc/tai-lieu-luyen-thi-toeic-sach-very-easy-toeic-34868.html";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        secondEbook.setOnMouseClicked(e -> {
            ControllerDownloadDocument.link = "https://drive.google.com/file/d/1_fBLexI6l4ArTGy-y1NS6chbDAX-gP1U/view";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        thirdEbook.setOnMouseClicked(e -> {
            ControllerDownloadDocument.link = "https://drive.google.com/file/d/1pTHL3DKFW3Pt9RYR9X4JaS92sD0RaPny/view";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        forthEbook.setOnMouseClicked(e -> {
            ControllerDownloadDocument.link = "https://www.anhngumshoa.com/tin-tuc/tron-bo-economy-toeic-vol-1-2-3-4-5-ban-dep-giai-chi-tiet-37050.html";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        firstLink.setOnMouseClicked(e ->{
            ControllerDownloadDocument.link = "https://drive.google.com/file/d/1C-N9jij3M_Wv9ctcR2ua4w-dyRL0N0OJ/view";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        secondLink.setOnMouseClicked(e->{
            ControllerDownloadDocument.link = "https://drive.google.com/file/d/0ByrOdmWCChm_TUcycHhPOEQ0eGc/view";
            try {
                setSceneDownload(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public void goToLink(){
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
        //  scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

        Image image = new Image(getClass().getResourceAsStream("../book.png"));
        primaryStage.getIcons().add(image);

        primaryStage.setScene(scene);
        primaryStage.show();
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

    public void setSceneDownload(MouseEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/downloadDocument.fxml"));
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1083,690);
        window.getIcons().add(new Image(getClass().getResourceAsStream("../book.png")));
        window.setTitle("English Application");
        window.setScene(scene);
        window.show();
    }
}
