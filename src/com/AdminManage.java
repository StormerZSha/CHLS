package com;

import entity.Admin;
import service.impl.AdminService;
import service.intf.IAdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminManage")
public class AdminManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IAdminService adminService = new AdminService();
        int chls = Integer.parseInt(request.getParameter("chls"));
        if (chls == 0) { //登录
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String code = request.getParameter("code");
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setEmail(email);
            HttpSession session = request.getSession();
            String drawCode = session.getAttribute("drawCode").toString();
            Boolean flag = drawCode.equalsIgnoreCase(code);//验证码
            if (flag){
                try {
                    Admin login = adminService.login(admin);
                    if (login!=null) {
                        //登录成功，重定向
                        session.setAttribute("type",login.getType());
                        session.setAttribute("username",login.getUsername());
                        response.sendRedirect("/pages/main.jsp");
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
        }else if (chls == 1) {//遍历所有管理员信息
            try {
                List<Admin> result = adminService.findAll();
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/admin-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (chls == 2) {//授权
            HttpSession session = request.getSession();
            int type= (int) session.getAttribute("type");
            String username=request.getParameter("username");
            if (type==1){//如果是超管，可以将普通管理员变为超管
                try {
                    adminService.updateGrant(username,1);
                    response.sendRedirect("/AdminManage?chls=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                request.setAttribute("message","非高级管理员，权限不足");
                request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
            }
        }else if (chls==3){//添加
                     String username=request.getParameter("username");
                     String password=request.getParameter("password");
                     String email=request.getParameter("email");
                     int type=Integer.parseInt(request.getParameter("type"));
                     try {
                         Admin admin = new Admin();
                         if ((!username.equals(""))&&(!password.equals(""))&&(!email.equals(""))) {
                            admin.setUsername(username);
                            admin.setPassword(password);
                            admin.setEmail(email);
                            admin.setType(type);
                            //查询数据库添加用户是否存在
                            Admin result = adminService.findAdminByName(username);
                            if (result == null) {
                                adminService.add(admin);
                                response.sendRedirect("/AdminManage?chls=1");
                            } else {
                                request.setAttribute("message", "添加用户已经存在，请重新输入");
                                request.getRequestDispatcher("/pages/fail.jsp").forward(request, response);
                            }
                        }else {
                            request.setAttribute("message","信息不完整，请重新输入！");
                            request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        }else if (chls == 4){ //模糊查询
            String keyword = request.getParameter("keyword");
            try {
                List<Admin> result = adminService.findLike(keyword);
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/admin-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==5){ //删除
            HttpSession session = request.getSession();
            int type= (int) session.getAttribute("type");
            String name = (String) session.getAttribute("username");
            String username=request.getParameter("username");
            if ((type==1)&&(!name.equals(username))) {//只有超管才能删除其他管理员且不能删除自己
                try {
                    adminService.deleteAdminByName(username);
                    response.sendRedirect("/AdminManage?chls=1");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                request.setAttribute("message","非高级管理员，权限不足");
                request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
            }
        } else if (chls==6){//退出
            try {
                HttpSession session = request.getSession();
                String sessionAdmin=(String)session.getAttribute("username");
                if (sessionAdmin!=null){
                    session.removeAttribute("username");
                    System.out.println("退出成功！");
                    request.getRequestDispatcher("/pages/login.jsp").forward(request,response);
                }else {
                    request.setAttribute("message","退出失败或者已经退出");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //if-end
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
