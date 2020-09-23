package dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import dao.intf.IBorrowDao;
import entity.Book;
import entity.Student;
import entity.Borrow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;
import java.util.Date;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDao implements IBorrowDao {
    /**
     * 查询所有借阅信息
     * @return
     * @throws SQLException
     */
    @Override
    public List<Borrow> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Borrow> results = queryRunner.query("select * from borrow",
                new BeanListHandler<Borrow>(Borrow.class));
        List<Borrow> result = new ArrayList<>();
        for (Borrow borrow : results){
            int borno = borrow.getBorno();
            Borrow borrowWithAll = findBorrow(borno);
            result.add(borrowWithAll);
        }
        return result;
    }

    //查询一条借阅信息，若已预约/借阅则包含学生信息和图书信息。
    @Override
    public Borrow findBorrow(int borno) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] param={borno};
        //查询借阅信息
        Borrow borrow = queryRunner.query("select * from borrow where borno=?", new BeanHandler<Borrow>(Borrow.class), param);
        //查询跟这条信息有关联的学生信息
        Student student = queryRunner.query(
                "select * from student where sno=(select sno from borrow where borno=?)", new BeanHandler<Student>(Student.class),param);
        //查询跟这条信息有关联的图书信息
        Book book = queryRunner.query(
                "select * from book where bno=(select bno from borrow where borno=?)", new BeanHandler<Book>(Book.class), param);
        //将关联信息全部分装为Borrow对象
        if (student!=null||book!=null){
            borrow.setStudent(student);
            borrow.setBook(book);
        }
        return borrow;
    }


    /**
     * //根据图书号查询有关的所有借阅信息。
     * @param bno
     * @return
     * @throws SQLException
     */
    @Override
    public List<Borrow> findBorrowbybno(int bno) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {bno};
        List<Borrow> result = queryRunner.query("select * from borrow where bno=?", new BeanListHandler<Borrow>(Borrow.class), param);
        return result;
    }

    /**
     * 添加一本新书
     * @param borrow
     * @throws SQLException
     */
    @Override
    public void insert(Borrow borrow) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borrow.getBno(),borrow.getBorno(),borrow.getPlace(),borrow.getStatus(),borrow.getBackplace()};
        queryRunner.update(
                "insert into borrow(bno,borno,place,status,backplace) values(?,?,?,?,?)", param);
    }

    /**
     * 下架一本旧书
     * @param borno
     * @throws SQLException
     */
    @Override
    public void deleteByBorno(int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borno};
        queryRunner.update("delete from borrow where borno =?", param);
    }


    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Borrow> findLike(String keyword) throws Exception{
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {keyword};
        List<Borrow> results = queryRunner.query("select a.borno,a.bno,b.name from borrow a inner join book b on a.bno=b.bno where b.name like ?", new BeanListHandler<Borrow>(Borrow.class), param);
        List<Borrow> result = new ArrayList<>();
        for (Borrow borrow : results){
            int borno = borrow.getBorno();
            Borrow borrowWithAll = findBorrow(borno);
            result.add(borrowWithAll);
        }
        return result;
        //select * from borrow where bno=(select bno from book where name like ?)
    }

    /**
     * 根据学号查询，某一个学生的所有借阅预约信息
     * @param sno
     * @return
     * @throws Exception
     */
    @Override
    public List<Borrow> findBorrowbysno(int sno) throws Exception{
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {sno};
        List<Borrow> results = queryRunner.query("select a.borno,a.bno,b.name from borrow a inner join book b on a.bno=b.bno where a.sno=?", new BeanListHandler<Borrow>(Borrow.class), param);
        List<Borrow> result = new ArrayList<>();
        for (Borrow borrow : results){
            int borno = borrow.getBorno();
            Borrow borrowWithAll = findBorrow(borno);
            result.add(borrowWithAll);
        }
        return result;
        //select * from borrow where bno=(select bno from book where name like ?)
    }

    /**
     * 取消预约,即修改操作人，操作时间为空，图书状态为可借阅
     * @param borno
     * @throws SQLException
     */
    @Override
    public void cancleOrderByBorno(int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borno};
        queryRunner.update(
                "update borrow set sno=null,optime=null,status=0 where borno=?",param);
    }

    /**
     * 预约,修改插入预约人，操作时间，图书状态为已预约
     * @param borrow
     * @param borno
     * @throws SQLException
     */
    @Override
    public void orderByBorno(Borrow borrow,int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borrow.getSno(),borrow.getOptime(),borno};
        queryRunner.update(
                "update borrow set sno=?,optime=?,status=1 where borno=?",param);
    }

    /**
     * 还书，即修改操作人,操作时间为空，图书状态为可借阅
     * @param borno
     * @throws SQLException
     */
    @Override
    public void backBookByBorno(int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borno};
        queryRunner.update(
                "update borrow set sno=null,optime=null,backtime=null,status=0 where borno=?",param);
    }

    /**
     * 借书,修改插入借阅人，操作时间，还书时间，图书状态为已借阅
     * @param borrow
     * @param borno
     * @throws SQLException
     */
    @Override
    public void lendBookByBorno(Borrow borrow,int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borrow.getSno(),borrow.getOptime(),borrow.getBacktime(),borno};
        queryRunner.update(
                "update borrow set sno=?,optime=?,backtime=?,status=2 where borno=?",param);
    }


    /**
     * 挂失删除，挂失后将borrow表中的数据删除添加到lost表
     * @param borno
     * @throws SQLException
     */
    @Override
    public void lostdeleteByBorno(int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {borno};
        queryRunner.update(
                "delete from borrow where borno=?",param);
    }
}
