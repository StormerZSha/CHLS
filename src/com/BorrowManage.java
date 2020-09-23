package com;

import entity.Book;
import entity.Borrow;
import entity.Lost;
import service.impl.BookService;
import service.impl.BorrowService;
import service.impl.LostService;
import service.intf.IBookService;
import service.intf.IBorrowService;
import java.text.SimpleDateFormat;

import service.intf.ILostService;
import utils.StringDateTransformUtils;
import org.apache.commons.fileupload.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Date;

@WebServlet("/BorrowManage")
public class BorrowManage extends HttpServlet {
    IBorrowService borrowService = new BorrowService();
    IBookService bookService=new BookService();
    ILostService lostService=new LostService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int chls = Integer.parseInt(request.getParameter("chls"));
        //chls为0，遍历借阅信息
        if (chls==0){
            try {
                List<Borrow> result = borrowService.findAll();
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/borrow-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==1){ //chls为1，根据书名搜索借阅信息
            String keyword = request.getParameter("keyword");
            try {
                List<Borrow> result = borrowService.findLike(keyword);
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/borrow-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==2) { //chls为2，上架新书,返回图书信息
            try {
                List<Book> bookbno = bookService.findAll();
                request.setAttribute("bookbno", bookbno);
                request.getRequestDispatcher("/pages/borrow-add.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==3){//chls为3，上架新书,提交信息
            String bno = request.getParameter("bno");
            String borno=request.getParameter("borno");
            String place=request.getParameter("place");
            String status=request.getParameter("status");
            String backplace=request.getParameter("backplace");
             try{
                 Borrow borrow = new Borrow();
                 if((!bno.equals(""))&&(!borno.equals(""))&&(!status.equals(""))&&(!place.equals(""))){
                     borrow.setBno(Integer.parseInt(bno));
                     borrow.setBorno(Integer.parseInt(borno));
                     borrow.setPlace(place);
                     borrow.setStatus(Integer.parseInt(status));
                     borrow.setBackplace(backplace);
                     //查询数据库中是否存在当前条目
                     Borrow result = borrowService.findBorrow(Integer.parseInt(borno));
                     if (result==null){
                         borrowService.insert(borrow);
                         request.getRequestDispatcher("/BorrowManage?chls=0").forward(request,response);
                     }else{
                         request.setAttribute("message","当前条目已经存在，请重新输入！");
                         request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                     }
                 }else{
                     request.setAttribute("message","信息不完整，请重新输入！");
                     request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                 }
             }catch (Exception e){
                 e.printStackTrace();
               }
        } else if (chls==4) { //下架旧书
            String borno = request.getParameter("borno");
            try {
                //只有不是预约或者借阅的条目才能下架
                Borrow result=borrowService.findBorrow(Integer.parseInt(borno));
                if (result.getStatus()!=1&&result.getStatus()!=2){
                    borrowService.deleteByBorno(Integer.parseInt(borno));
                    response.sendRedirect("/BorrowManage?chls=0");
                }else {
                    request.setAttribute("message","当前图书已预约/借阅，无法下架！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (chls==5) { //取消预约
            try {
                String borno = request.getParameter("borno");
                Borrow result=borrowService.findBorrow(Integer.parseInt(borno));
                if (result.getStatus()==1) {
                    borrowService.cancleOrderByBorno(Integer.parseInt(borno));
                    response.sendRedirect("/BorrowManage?chls=0");
                }else {
                    request.setAttribute("message","当前图书不处于预约状态！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (chls==6) { //还书
            try {
                String borno = request.getParameter("borno");
                Borrow result=borrowService.findBorrow(Integer.parseInt(borno));
                if (result.getStatus()==2) {
                    borrowService.backBookByBorno(Integer.parseInt(borno));
                    response.sendRedirect("/BorrowManage?chls=0");
                }else {
                    request.setAttribute("message","当前图书不处于借阅状态！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (chls==7) { //借书跳转
            try {
                String borno = request.getParameter("borno");
                Borrow borrow=borrowService.findBorrow(Integer.parseInt(borno));
                request.setAttribute("borrow",borrow);
                request.getRequestDispatcher("/pages/borrow-lend.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (chls==8) { //借书提交
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String sno = request.getParameter("sno");
            int  borno=Integer.parseInt(request.getParameter("borno"));
            String optime=simpleDateFormat.format(date);
            String backtime=request.getParameter("backtime");
            try{
                Borrow borrow = new Borrow();
                if(!sno.equals("")&&!backtime.equals("")){
                    borrow.setSno(Integer.parseInt(sno));
                    borrow.setOptime(new StringDateTransformUtils().StringToDate(optime));
                    borrow.setBacktime(new StringDateTransformUtils().StringToDate(backtime));
                    //查看当前图书状态
                    Borrow result = borrowService.findBorrow(borno);
                    if (result.getStatus()==0){//如果处于可借阅状态
                        borrowService.lendBookByBorno(borrow,borno);
                        request.getRequestDispatcher("/BorrowManage?chls=0").forward(request,response);
                    }else if(result.getStatus()==1){//如果处于已预约状态
                        borrowService.lendBookByBorno(borrow,borno);
                        request.getRequestDispatcher("/BorrowManage?chls=0").forward(request,response);
                    } else{
                        request.setAttribute("message","当前图书不处于可借阅状态！");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }
                }else{
                    request.setAttribute("message","信息不完整，请重新输入！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
       ///学生操作
        else if (chls==9){//预约
            HttpSession session = request.getSession();
            String sessionname=(String)session.getAttribute("name");
            String sessionsno=session.getAttribute("sno").toString();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            int  borno=Integer.parseInt(request.getParameter("borno"));
            String optime=simpleDateFormat.format(date);
            try{
                    Borrow borrow = new Borrow();
                    borrow.setSno(Integer.parseInt(sessionsno));
                    borrow.setOptime(new StringDateTransformUtils().StringToDate(optime));
                    //查看当前图书状态
                    Borrow result = borrowService.findBorrow(borno);
                    if(result.getStatus()==0){//如果处于可借阅状态
                        borrowService.orderByBorno(borrow,borno);
                        request.setAttribute("message","操作成功！");
                        request.getRequestDispatcher("/pages/success.jsp").forward(request,response);
                    } else{
                        request.setAttribute("message","当前图书不处于可借阅状态！");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if (chls==10){//根据学号获取当前学生的借阅与预约信息,以及挂失信息
            try {
                HttpSession session = request.getSession();
                String sessionsno=session.getAttribute("sno").toString();
                List<Borrow> result = borrowService.findBorrowbysno(Integer.parseInt(sessionsno));
                request.setAttribute("result",result);
                List<Lost> result2=lostService.findLostbysno(Integer.parseInt(sessionsno));
                request.setAttribute("result2",result2);
                request.getRequestDispatcher("/pages/myborrow.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==11){//取消预约与管理端相同，改变重定向页面
            try {
                String borno = request.getParameter("borno");
                Borrow result=borrowService.findBorrow(Integer.parseInt(borno));
                if (result.getStatus()==1) {
                    borrowService.cancleOrderByBorno(Integer.parseInt(borno));
                    response.sendRedirect("/BorrowManage?chls=10");
                }else {
                    request.setAttribute("message","当前图书不处于预约状态！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
