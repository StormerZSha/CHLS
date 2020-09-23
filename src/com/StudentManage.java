package com;


import entity.Student;
import service.impl.StudentService;
import service.intf.IStudentService;
import utils.StringDateTransformUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentManage")
public class StudentManage extends HttpServlet {
    IStudentService studentService = new StudentService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int chls = Integer.parseInt(request.getParameter("chls"));
        //chls为0，遍历学生信息
        if (chls==0){
            try {
                List<Student> result = studentService.findAll();
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/student-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==1){ //chls为1，搜索学生信息
            String keyword = request.getParameter("keyword");
            try {
                List<Student> result = studentService.findLike(keyword);
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/student-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (chls==2){ //chls为2，添加学生信息
            String sno = request.getParameter("sno");
            String name = request.getParameter("name");
            String sex = request.getParameter("sex");
            String birth = request.getParameter("birth");
            String speciality = request.getParameter("speciality");
            String booknum=request.getParameter("booknum");
            String pass=request.getParameter("pass");
            try {
                Student student = new Student();
                if((!sno.equals(""))&&(!name.equals(""))&&(!sex.equals(""))&&(!birth.equals(""))&&(!speciality.equals(""))&&(!booknum.equals(""))&&(!pass.equals(""))){
                    student.setSno(Integer.parseInt(sno));
                    student.setName(name);
                    student.setSex(Integer.parseInt(sex));
                    student.setBirthday(new StringDateTransformUtils().StringToDate(birth));
                    student.setSpeciality(speciality);
                    student.setBooknum(Integer.parseInt(booknum));
                    student.setPass(pass);
                    //查询数据库中是否存在当前学生信息
                    Student result = studentService.findStudentBySno(Integer.parseInt(sno));
                    if (result==null){
                        studentService.addStudent(student);
                        request.getRequestDispatcher("/StudentManage?chls=0").forward(request,response);
                    }else{
                        request.setAttribute("message","此学生已经存在，请勿重复添加！");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }
                }else{
                    request.setAttribute("message","学生信息不完整，请重新输入！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==3){ //删除一条学生信息
            String studentid = request.getParameter("studentid");
            try {
                studentService.deleteById(Integer.parseInt(studentid));
                response.sendRedirect("/StudentManage?chls=0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==4){ //跳转修改
                try {
                String studentid = request.getParameter("studentid");
                Student student = studentService.findStudent(Integer.parseInt(studentid));
                request.setAttribute("student",student);
                request.getRequestDispatcher("/pages/student-update.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==5){ //提交修改
            int studentid = Integer.parseInt(request.getParameter("studentid"));
            String studentname=request.getParameter("name");
            String studentspe=request.getParameter("speciality");
            int booknum=Integer.parseInt(request.getParameter("booknum"));
            String studentpass=request.getParameter("pass");
            try {
                if ((!studentname.equals(""))&&(!studentspe.equals(""))&&(!studentpass.equals(""))) {
                    studentService.updateStudent(studentname, studentspe,booknum,studentpass,studentid);
                    response.sendRedirect("/StudentManage?chls=0");
                }else {
                    request.setAttribute("message", "学生信息不完整，请重新输入！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//以下为学生端操作
        else if (chls==6){//学生登录
            String sno = request.getParameter("sno");
            String pass = request.getParameter("pass");
            String code = request.getParameter("code");
            Student student = new Student();
            student.setSno(Integer.parseInt(sno));
            student.setPass(pass);
            HttpSession session = request.getSession();
            String drawCode = session.getAttribute("drawCode").toString();
            Boolean flag = drawCode.equalsIgnoreCase(code);//验证码
            if (flag){
                try {
                    Student login = studentService.login(student);
                    if (login!=null) {
                        //登录成功
                        session.setAttribute("sno",login.getSno());
                        session.setAttribute("name",login.getName());
                        response.sendRedirect("/pages/main-student.jsp");
                    } else {
                        //登录失败,跳转到失败页面
                        request.setAttribute("message", "登录失败，请检查用户名密码是否输入正确");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                //验证码错误
                request.setAttribute("message", "验证码错误");
                request.getRequestDispatcher("/pages/fail.jsp").forward(request, response);
            }
        }else if (chls==7){//退出
            try {
                HttpSession session = request.getSession();
                String sessionStudent=(String)session.getAttribute("name");
                if (sessionStudent!=null){
                    session.removeAttribute("name");
                    System.out.println("退出成功！");
                    request.getRequestDispatcher("/pages/login-student.jsp").forward(request,response);
                }else {
                    request.setAttribute("message","退出失败或者已经退出");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (chls==8){//展示个人信息
            try {
                HttpSession session = request.getSession();
                String sessionname=(String)session.getAttribute("name");
                String sessionsno=session.getAttribute("sno").toString();
                Student student = studentService.findStudentBySno(Integer.parseInt(sessionsno));
                request.setAttribute("student",student);
                request.getRequestDispatcher("/pages/mymessage.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==9){//修改密码
            String oldpass = request.getParameter("oldpass");
            String newpass = request.getParameter("newpass");
            String doublepass=request.getParameter("doublepass");
            String code = request.getParameter("code");
            HttpSession session = request.getSession();
            String drawCode = session.getAttribute("drawCode").toString();
            Boolean flag = drawCode.equalsIgnoreCase(code);//验证码
            if (flag){
                try{
                    String sessionsno=session.getAttribute("sno").toString();
                    Student student = studentService.findStudentBySno(Integer.parseInt(sessionsno));
                    //旧密码输入要正确且新密码与重复密码要相同且新密码不能为空
                    if ((oldpass.equals(student.getPass()))&&(newpass.equals(doublepass))&&(!newpass.equals(""))){
                        studentService.modfiyPass(doublepass,student.getId());
                      //response.sendRedirect("/StudentManage?chls=8");
                        request.setAttribute("message","修改成功");
                        request.getRequestDispatcher("/StudentManage?chls=8").forward(request,response);
                    }else if (!(oldpass.equals(student.getPass()))){
                        request.setAttribute("message","旧密码输入错误");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }else if (!(newpass.equals(doublepass))){
                        request.setAttribute("message","确认密码错误");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }else if ((newpass==null)||(doublepass==null)){
                        request.setAttribute("message","新密码和确认密码都不能为空");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }else {
                        request.setAttribute("message","操作失败");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                request.setAttribute("message","验证码错误");
                request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
