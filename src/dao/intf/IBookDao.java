package dao.intf;

import entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDao {
    //查找所有图书记录
    List<Book> findAll() throws SQLException;

    //根据bno删除一本书
    void deleteByBno(int bno) throws SQLException;

    //根据bno修改图书信息
    void updateByBno(int bno, Book book) throws SQLException;

    //添加一本书
    void insert(Book book) throws SQLException;

    //查询一条图书信息
    Book findBook(int bno) throws SQLException;

    //模糊查询
    List<Book> findLike(String keyword) throws Exception;

    //更新图书信息
    void updateBook(String name,String author,String publish,String carrier,String image,int bno) throws Exception;
}
