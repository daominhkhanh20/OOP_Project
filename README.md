# ApplicationEnglish
OOP project
- Một số thư viện cần cài đặt để project có thể chạy :
+ JAVAFX link hướng dẫn cài đặt : https://openjfx.io/openjfx-docs/
+ thư viện mssql-jdbc để kết nối với database, link download : https://docs.microsoft.com/vi-vn/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15
+ thư viện jbcrypt-0.3m đã có sẵn trong project để tạo được chuỗi băm mật khẩu
Lưu ý : 
+ nếu bạn sử dụng Intellij Idea  bạn sẽ phải chỉnh sửa lại VM option để thư viện JavaFx có thể hoạt động, link hướng dẫn : https://openjfx.io/openjfx-docs/#install-javafx
ví dụ với VM option của mình : " --module-path "E:\Java\JavFx\javafx-sdk-14.0.1\lib" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.web "
+ vào class Mconnect của project để chỉnh lại tài khoản và mật khẩu của sql (ở đây bọn mình sử dụng Sql Server)
+ import file EnglishApp.bacpac vào cơ sở dữ liệu (file mình đã tải vào trong project)
- Cảm ơn các bạn, và chúc các bạn thành công
