package entity;

import utils.StringDateTransformUtils;
import java.util.Date;
//挂失实体类
public class Lost {
    private int bno;           //图书编号
    private Book book;          //相关图书信息
    private int borno;          //借阅编号
    private String place;        //馆藏地
    private int sno;              //学生学号
    private Student student;      //相关学生信息
    private Date lostime;          //挂失时间
    private String lostimeStr;
    private Date optime;           //处理时间
    private String optimeStr;
    private int status;           //图书状态0未处理1已处理
    private String statusStr;

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

    public Date getOptime() {
        return optime;
    }

    public void setOptime(Date optime) {
        this.optime = optime;
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

    public Date getLostime() {
        return lostime;
    }

    public void setLostime(Date lostime) {
        this.lostime = lostime;
    }

    public String getLostimeStr() {
        if (lostime!=null){
            StringDateTransformUtils utils=new StringDateTransformUtils();
            lostimeStr=utils.DateToString(lostime);
        }
        return lostimeStr;
    }

    public void setLostimeStr(String lostimeStr) {
        this.lostimeStr = lostimeStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status==0){
            statusStr="未处理";
        }else {
            statusStr="已处理";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }


    @Override
    public String toString() {
        return "Lost{" +
                "bno=" + bno + '\'' +
                ", book=" + book + '\'' +
                ", borno=" + borno + '\'' +
                ", place=" + place + '\'' +
                ", sno=" + sno + '\'' +
                ", student=" + student + '\'' +
                ", optime='" + optime + '\'' +
                ", optimeStr='" + optimeStr + '\'' +
                ", lostime='" + lostime + '\'' +
                ", lostimeStr='" + lostimeStr + '\'' +
                '}';
    }
}
