package sample.Class;

import java.sql.Date;

public class Student {
    private int ID;
    private String userName;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String facebook;
    private Date dateOfBirth = Date.valueOf("2000-01-01");
    private int age;
    private String gender;
    private String edu;
    private History[][] histories = new History[3][100];
    private int[] now = new int[3];
    private double[] data = new double[100];
    private int sum = 0;
    private String level;
    private int baiso;

    public int getBaiso() {
        return baiso;
    }

    public void setBaiso(int baiso) {
        this.baiso = baiso;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Student(){
        /*now[0] = 1;
        now[1] = 1;
        now[2] = 1;*/
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEdu() {
        return edu;
    }

    public void setEdu(String edu) {
        this.edu = edu;
    }

    public History[][] getHistories() {
        return histories;
    }

    public void setHistories(History[][] histories) {
        this.histories = histories;
    }
    public void setHistoies2(int Liv,int bai,History history){
        histories[Liv][bai] = history;
    }
    //public void setHistory(History history){this.}
    public int[] getNow() {
        return now;
    }

    public void setNow(int[] now) {
        this.now = now;
    }


}

