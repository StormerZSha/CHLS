package entity;

import utils.StringDateTransformUtils;
import java.util.Date;
//借阅实体类
public class Borrow {
    private int bno;      //图书编号
    private Book book;    //借阅的图书信息
    private int borno;    //借阅号
    private String place; //馆藏地
    private int status;   //图书状态0可借1已预约2已借出3不可借
    private int sno;      //学生学号
    private Student student;//借阅的学生信息
    private Date optime;         //借书/预约时间
    private Date backtime;       //还书时间
    private String backplace;     //还书点
    private String statusStr;
    private String backtimeStr;
    private String optimeStr;


    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getBorno() {
        return borno;
    }

    public void setBorno(int borno) {
        this.borno = borno;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getOptime(){
        return optime;
    }

    public void setOptime(Date optime){
        this.optime=optime;
    }

    public Date getBacktime() {
        return backtime;
    }

    public void setBacktime(Date backtime) {
        this.backtime = backtime;
    }

    public String getBackplace() {
        return backplace;
    }

    public void setBackplace(String backplace) {
        this.backplace = backplace;
    }

    public String getStatusStr() {
        if (status==0){
            statusStr="可借阅";
        }else if (status==1){
            statusStr="已预约";
        }else if (status==2){
            statusStr="已借阅";
        }else {
            statusStr="不可借";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getBacktimeStr() {
        if (backtime!=null){
            StringDateTransformUtils utils=new StringDateTransformUtils();
            backtimeStr=utils.DateToString(backtime);
        }
        return backtimeStr;
    }

    public void setBacktimeStr(String backtimeStr) {
        this.backtimeStr = backtimeStr;
    }

    public String getOptimeStr() {
        if (optime!=null){
            StringDateTransformUtils utils=new StringDateTransformUtils();
            optimeStr=utils.DateToString(optime);
        }
        return optimeStr;
    }

    public void setOptimeStr(String optimeStr) {
        this.optimeStr = optimeStr;
    }



    @Override
    public String toString() {
        return "Borrow{" +
                "bno=" + bno + '\'' +
                ", book=" + book + '\'' +
                ", borno=" + borno + '\'' +
                ", place=" + place + '\'' +
                ", status=" + status + '\'' +
                ", sno=" + sno + '\'' +
                ", student=" + student + '\'' +
                ", optime='" + optime + '\'' +
                ", backtime='" + backtime + '\'' +
                ", backplace='" + backplace + '\'' +
                ", statusStr='" + statusStr + '\'' +
                ", backtimeStr='" + backtimeStr + '\'' +
                ", optimeStr='" + optimeStr + '\'' +
                '}';
    }
}
