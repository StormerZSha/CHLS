package com;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@javax.servlet.annotation.WebServlet("/Check")
public class Check extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //创建图片指定宽度和高度以及颜色类型。
        BufferedImage bufferedImage=new BufferedImage(200,50, BufferedImage.TYPE_INT_RGB);
        //美化图片。
        Graphics graphics=bufferedImage.getGraphics(); //获取画笔对象。
        graphics.setColor(Color.pink);//设置画笔的颜色。
        graphics.fillRect(0,0,200,50);//填充颜色从0,0。
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,200-1,50-1);//画一个边框。
        graphics.setColor(Color.black);
        String src="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Font font=new Font("楷体",Font.BOLD+Font.ITALIC,30);//设置字体风格以及大小。
        //随机生成。
        Random random=new Random();
        int index1 = random.nextInt(src.length());//在0到src.length之间生成一个随机数。
        int index2 = random.nextInt(src.length());
        int index3 = random.nextInt(src.length());
        int index4 = random.nextInt(src.length());
        char a1 = src.charAt(index1);
        char a2 = src.charAt(index2);
        char a3 = src.charAt(index3);
        char a4 = src.charAt(index4);
        HttpSession session = request.getSession();
        char[] chars = {a1,a2,a3,a4};
        session.setAttribute("drawCode",new String(chars));
        graphics.setFont(font);
        graphics.drawString(a1+"",25,35);//写字符。
        graphics.drawString(a2+"",75,35);
        graphics.drawString(a3+"",125,35);
        graphics.drawString(a4+"",175,35);
        //讲图片放在图片流中输出。
        //画干扰线
        graphics.setColor(Color.green);
        //随机生成坐标点。
        for(int i=1;i<=7;i++){
            int x1 = random.nextInt(200);
            int y1 = random.nextInt(50);
            int x2=random.nextInt(200);
            int y2=random.nextInt(50);
            graphics.drawLine(x1,y1,x2,y2);
        }
        ImageIO.write(bufferedImage,"jpg",response.getOutputStream());
    }
    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
}
