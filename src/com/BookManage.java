package com;


import entity.Book;
import entity.Borrow;
import service.impl.BookService;
import service.impl.BorrowService;
import service.intf.IBookService;
import service.intf.IBorrowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet("/BookManage")
public class BookManage extends HttpServlet {
    IBookService bookService = new BookService();
    IBorrowService borrowService=new BorrowService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int chls = Integer.parseInt(request.getParameter("chls"));
        //chls为0，遍历图书信息
        if (chls==0){
            try {
                List<Book> result = bookService.findAll();
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/book-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==1){ //chls为1，搜索图书信息
            String keyword = request.getParameter("keyword");
            try {
                List<Book> result = bookService.findLike(keyword);
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/book-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if (chls==2){ //chls为2，添加图书信息
            String bno = request.getParameter("bno");
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publish = request.getParameter("publish");
            String carrier = request.getParameter("carrier");
            String ISBN_price=request.getParameter("ISBN_price");
            String CLCN=request.getParameter("CLCN");
            String image=request.getParameter("image");//无法直接取到type=file值
            String imgurl=request.getParameter("url");
            try {
                Book book = new Book();
                if((!bno.equals(""))&&(!name.equals(""))&&(!ISBN_price.equals(""))&&(!CLCN.equals(""))){
                    book.setBno(Integer.parseInt(bno));
                    book.setName(name);
                    book.setAuthor(author);
                    book.setPublish(publish);
                    book.setCarrier(carrier);
                    book.setISBN_price(ISBN_price);
                    book.setCLCN(CLCN);
                    book.setImage(imgurl);
                    //查询数据库中是否存在当前图书信息
                    Book result = bookService.findBook(Integer.parseInt(bno));
                    if (result==null){
                        bookService.addBook(book);
                        request.getRequestDispatcher("/BookManage?chls=0").forward(request,response);
                    }else{
                        request.setAttribute("message","此图书已经存在，请勿重复添加！");
                        request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                    }
                }else{
                    request.setAttribute("message","图书信息不完整，请重新输入！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==3){ //删除一条图书信息
            String bno = request.getParameter("bookbno");
            try {
                bookService.deleteByBno(Integer.parseInt(bno));
                response.sendRedirect("/BookManage?chls=0");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==4){ //跳转修改
            try {
                String bookbno = request.getParameter("bookbno");
                Book book = bookService.findBook(Integer.parseInt(bookbno));
                request.setAttribute("book",book);
                request.getRequestDispatcher("/pages/book-update.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==5){ //提交修改
            int bno = Integer.parseInt(request.getParameter("bookbno"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String publish = request.getParameter("publish");
            String carrier = request.getParameter("carrier");
            String image=request.getParameter("image");
            String imgurl=request.getParameter("url");
            try {
                if (name!=""&&author!="") {
                    bookService.updateBook(name,author,publish,carrier,imgurl,bno);
                    response.sendRedirect("/BookManage?chls=0");
                }else {
                    request.setAttribute("message", "图书信息不完整，请重新输入！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//学生端操作
        else if (chls==6){//展示图书信息与借阅信息
            try {
                List<Book> result = bookService.findAll();
                //将借阅信息封装成list作为Book的一个属性，最后一起返回前台
                for (int i=0;i<result.size();i++){
                    int bno=result.get(i).getBno();
                    List<Borrow> borrows=borrowService.findBorrowbybno(bno);
                    result.get(i).setBorrows(borrows);
                }
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/searchaorder.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==7){//学生端查询
            String keyword = request.getParameter("keyword");
            try {
                List<Book> result = bookService.findLike(keyword);
                for (int i=0;i<result.size();i++){
                    int bno=result.get(i).getBno();
                    List<Borrow> borrows=borrowService.findBorrowbybno(bno);
                    result.get(i).setBorrows(borrows);
                }
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/searchaorder.jsp").forward(request,response);
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
