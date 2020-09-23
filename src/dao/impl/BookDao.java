package dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import dao.intf.IBookDao;
import entity.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {
    /**
     * 查询所有图书信息
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Book> results = queryRunner.query("select * from book",
                new BeanListHandler<Book>(Book.class));
        List<Book> result = new ArrayList<>();
        for (Book book : results) {
            int bno = book.getBno();
            Book bookWithAll = findBook(bno);
            result.add(bookWithAll);
        }
        return result;
    }

    /**
     * 根据图书编号删除图书信息
     *
     * @param bno
     * @throws SQLException
     */
    @Override
    public void deleteByBno(int bno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {bno};
        queryRunner.update("delete from book where bno =?", param);
    }

    /**
     * 根据编号修改信息
     *
     * @param bno
     * @param book
     * @throws SQLException
     */
    @Override
    public void updateByBno(int bno, Book book) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {book.getName(), book.getAuthor(), book.getPublish(),
                book.getCarrier(), book.getISBN_price(), book.getCLCN(),book.getImage(), bno};
        queryRunner.update(
                "update book set name=?,author=?,publish=?,carrier=?,ISBN_price=?,CLCN=?,image=? where bno=?", param);
    }

    /**
     * 插入一条图书信息
     *
     * @param book
     * @throws SQLException
     */
    @Override
    public void insert(Book book) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {book.getBno(),book.getName(), book.getAuthor(), book.getPublish(), book.getCarrier(),
                book.getISBN_price(), book.getCLCN(),book.getImage()};
        queryRunner.update(
                "insert into book(bno,name,author,publish,carrier,ISBN_price,CLCN,image) values(?,?,?,?,?,?,?,?)", param);
    }

    //根据查询一本书的信息
    @Override
    public Book findBook(int bno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DruidPooledConnection connection = JDBCUtils.getDataSource().getConnection();
        Object[] param = {bno};
        Book result = queryRunner.query(connection, "select * from book where bno=?", new BeanHandler<Book>(Book.class), param);
        connection.close();
        return result;
    }
    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Book> findLike(String keyword) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {keyword};
        List<Book> result = queryRunner.query("select * from book where name like ?", new BeanListHandler<Book>(Book.class), param);
        return result;
    }

    /**
     *  修改图书信息
     * @throws Exception
     */
    @Override
    public void updateBook(String name,String author,String publish,String carrier,String image,int bno) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param ={name,author,publish,carrier,image,bno};
        queryRunner.update("update book set name=?,author=?,publish=?,carrier=?,image=? where bno=?",param);
    }
}

