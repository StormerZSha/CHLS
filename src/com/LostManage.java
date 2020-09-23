package com;

import entity.Borrow;
import entity.Lost;
import service.impl.BorrowService;
import service.impl.LostService;
import service.intf.IBorrowService;
import service.intf.ILostService;
import utils.StringDateTransformUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/LostManage")
public class LostManage extends HttpServlet {
    ILostService lostService=new LostService();
    IBorrowService borrowService=new BorrowService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int chls = Integer.parseInt(request.getParameter("chls"));
        //chls为0，遍历借阅信息
        if (chls == 0) {
            try {
                List<Lost> result = lostService.findAll();
                request.setAttribute("result", result);
                request.getRequestDispatcher("/pages/lost-list.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==1){ //chls为1，根据书名搜索丢失信息
            String keyword = request.getParameter("keyword");
            try {
                List<Lost> result = lostService.findLike(keyword);
                request.setAttribute("result",result);
                request.getRequestDispatcher("/pages/lost-list.jsp").forward(request,response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (chls==2){ //挂失处理
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            int  borno=Integer.parseInt(request.getParameter("borno"));
            String optime=simpleDateFormat.format(date);
            try{
                Lost lost = new Lost();
                lost.setOptime(new StringDateTransformUtils().StringToDate(optime));
                Lost result = lostService.findLost(borno);
                if (result.getStatus()==0){//如果书处于未处理状态
                    lostService.treatLostByBorno(lost,borno);
                    response.sendRedirect("/LostManage?chls=0");
                }else {
                    request.setAttribute("message","当前图书已处理，请勿重复操作！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //学生端操作
        else if (chls==3){//挂失
            String borno = request.getParameter("borno");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            Date date = new Date();
            String lostime=simpleDateFormat.format(date);
            try {
                Borrow result = borrowService.findBorrow(Integer.parseInt(borno));//先查出要挂失的borrow信息
                if (result.getStatus()==2) {
                    Lost lost = new Lost();
                    lost.setBorno(result.getBorno());
                    lost.setBno(result.getBno());
                    lost.setPlace(result.getPlace());
                    lost.setSno(result.getSno());
                    lost.setLostime(new StringDateTransformUtils().StringToDate(lostime));
                    lostService.LostByBorno(lost);//再将挂失数据添加到lost
                    borrowService.lostdeleteByBorno(Integer.parseInt(borno));//最后删除原本borrow数据
                    request.getRequestDispatcher("/BorrowManage?chls=10").forward(request,response);
                }else{
                    request.setAttribute("message","当前图书未借阅！");
                    request.getRequestDispatcher("/pages/fail.jsp").forward(request,response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
