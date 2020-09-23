package entity;

import utils.StringDateTransformUtils;

import java.util.Date;

/*
管理员实体类
 */
public class Admin {
    private String username;    //用户名
    private String email;       //电子邮件
    private String password;    //密码
    private Date time;          //注册时间
    private int type;           //用户类型 0普通管理员 1超级管理员
    private String typeStr;
    private String timeStr;

    public String getTypeStr() {
        if (type==0){
            typeStr="管理员";
        }else
            { typeStr="超级管理员";}
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Admin() {
    }

    public String getTimeStr() {
        if (time!=null){
            StringDateTransformUtils utils=new StringDateTransformUtils();
            timeStr=utils.DateToString(time);
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Admin(String username, String email, String password, Date time, int type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
