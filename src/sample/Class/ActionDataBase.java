package sample.Class;

import java.sql.SQLException;
import java.sql.Statement;

public class ActionDataBase {

    public String checkLogin(String user) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT PassWord FROM dbo.Customer where UserName = "+ "'" + user + "'";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        String a = null;
        while (resultSet.next()) {
            a = resultSet.getString("PassWord");
        }
        return  a;
    }

    public void insertTotalData(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("update Customer set Address = N'"
                + student.getAddress() + "'," + "Phone ='" + student.getPhone() +"',"
                + "FullName = N'"  + student.getFullName() + "',"
                + "Email = '"  + student.getEmail() + "'," + "Facebook ='" + student.getFacebook() +"',"
                +"DateOfBirth = '"  + student.getDateOfBirth() + "'," + "Age ='" + student.getAge() +"',"
                +"Gender = '"  + student.getGender() + "'," + "Edu =N'" + student.getEdu() +"'"
                + "where UserName =" + "'" + student.getUserName() + "'");
        System.out.println("Update total successful");
    }

    public void updatePass(String password,Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("update Customer set PassWord = '"
                + password +"'"
                + "where UserName =" + "'" + student.getUserName() + "'");
    }

    public boolean haveUserName(String user) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT UserName FROM dbo.Customer where UserName = "
                + "'" + user + "'";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        if(resultSet.next()){
            return true;
        }
        else{
            return false;
        }
    }

    public void setData(String user,Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT * FROM dbo.Customer where UserName = "+ "'" + user + "'";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        while (resultSet.next()) {
            student.setID(resultSet.getInt("ID"));
            student.setUserName(resultSet.getString("UserName"));
            student.setPassword(resultSet.getString("PassWord"));
            student.setFullName(resultSet.getString("FullName"));
            student.setAddress(resultSet.getString("Address"));
            student.setPhone(resultSet.getString("Phone"));
            student.setEmail(resultSet.getString("Email"));
            student.setFacebook(resultSet.getString("Facebook"));
            student.setDateOfBirth(resultSet.getDate("DateOfBirth"));
            student.setAge(resultSet.getInt("Age"));
            student.setGender(resultSet.getString("Gender"));
            student.setEdu(resultSet.getString("Edu"));
        }

    }
    public void insertData(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("insert into Customer(UserName,PassWord,FullName,Email) values ('"+
                student.getUserName()+"','"+student.getPassword()+"',N'"+student.getFullName()+"','"
                +student.getEmail()+ "')");
    }

    public void setSignUp(Student student,String userName,String password,String fullName,String email) throws Exception {
        /*this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;*/
        HashPass hashPass=new HashPass();
        student.setUserName(userName);
        student.setPassword(hashPass.hashPassword(password));
        student.setFullName(fullName);
        student.setEmail(email);
    }
    // update
    public void editHistory(float diem,Student student) throws SQLException {
        int[] nowStudent=student.getNow();
        student.setHistoies2(Integer.parseInt(student.getLevel())-1,student.getBaiso()-1,new History(student.getBaiso(), diem));
               // [Integer.parseInt(student.getLevel())-1][student.getBaiso()-1] = new History(student.getBaiso(), diem);
        nowStudent[Integer.parseInt(student.getLevel())-1] = nowStudent[Integer.parseInt(student.getLevel())-1] > student.getBaiso()? nowStudent[Integer.parseInt(student.getLevel())-1]: student.getBaiso();
        int a = nowStudent[Integer.parseInt(student.getLevel())-1];
        student.setNow(nowStudent);
        updateMaxValue(a,student);
    }
    public void updateMaxValue(int value,Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("update State set MaxValue = '"
                +  +value + "'"
                + "where ID ='"
                + student.getID() + "'and Level ='" + student.getLevel() + "';");
    }
    public void insertHistory(float point,Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("insert into History(CustomerID,Level,Lesson,Point) values ('"+student.getID()+"','"+student.getLevel()+"','"
                +student.getBaiso()+"','"+point
                + "')");
    }



    public float getPoint(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT Point FROM dbo.History  where CustomerID ='"
                + student.getID() + "'and Level ='" + student.getLevel() + "'and Lesson ='" + student.getBaiso() + "';";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        float a = (float) 0.0;
        while (resultSet.next()) {
            a = resultSet.getFloat("Point");
        }
        return a;
    }

    public void insertState(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT ID FROM dbo.State  where ID ='"
                + student.getID() + "';";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        if (!resultSet.next()) {
            subInsertState(student);
        }
        else{
            setNow(student);
        }
    }
    public void setNow(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT MaxValue FROM dbo.State  where ID ='"
                + student.getID() + "';";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        int i = 0;
        int[] nowStudent=student.getNow();
        while (resultSet.next()) {
            nowStudent[i] = resultSet.getInt("MaxValue");
            i++;
        }
        student.setNow(nowStudent);
    }
    public void subInsertState(Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        for(int i=0;i<3;i++){
            stm.executeUpdate("insert into State(ID,Level,MaxValue) values ('"+student.getID()+"','"+(i+1)+"','"
                    +1/*+"','"+point*/
                    + "')");
        }
    }

    public void updatePoint(float result,Student student) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        Statement stm = conn.createStatement();
        stm.executeUpdate("update History set Point = '"
                + result +"'"
                + "where CustomerID ='"
                + student.getID() + "'and Level ='" + student.getLevel() + "'and Lesson ='" + student.getBaiso() + "';");
    }
    public float getPoint(int iD,int Level,int Baiso) throws SQLException {
        var conn = MConnection.getInstance().getConnection();
        var sql = "SELECT Point FROM dbo.History  where CustomerID ='"
                + iD + "'and Level ='" + Level + "'and Lesson ='" + Baiso + "';";
        var result = conn.prepareStatement(sql);
        var resultSet = result.executeQuery();
        float a = (float) 0.0;
        while (resultSet.next()) {
            a = resultSet.getFloat("Point");
        }
        return a;
    }
    public void updatePoint(int level,int now,Student student) throws SQLException {
        for(int i= 0;i<now;i++){
            student.setHistoies2(level-1,i,new History(i+1,getPoint(student.getID(),level,i+1)));
            //histories[level-1][i] = new History(i+1,getPoint(student.getID(),level,i+1));
        }
    }
}
