package dao.impl;


import dao.intf.ILostDao;
import entity.Lost;
import entity.Book;
import entity.Student;
import entity.Borrow;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LostDao implements ILostDao{

    /**
     * 查询所有丢失信息
     * @return
     * @throws SQLException
     */
    @Override
    public List<Lost> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Lost> results = queryRunner.query("select * from lost",
                new BeanListHandler<Lost>(Lost.class));
        List<Lost> result = new ArrayList<>();
        for (Lost lost : results){
            int borno = lost.getBorno();
            Lost lostWithAll = findLost(borno);
            result.add(lostWithAll);
        }
        return result;
    }

    //查询一条挂失信息，包含学生信息和图书信息。
    @Override
    public Lost findLost(int borno) throws SQLException {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] param={borno};
        //查询挂失信息
        Lost lost = queryRunner.query("select * from lost where borno=?", new BeanHandler<Lost>(Lost.class), param);
        //查询跟这条信息有关联的学生信息
        Student student = queryRunner.query(
                "select * from student where sno=(select sno from lost where borno=?)", new BeanHandler<Student>(Student.class),param);
        //查询跟这条信息有关联的图书信息
        Book book = queryRunner.query(
                "select * from book where bno=(select bno from lost where borno=?)", new BeanHandler<Book>(Book.class), param);
        //将关联信息全部分装为Borrow对象
        if (student!=null||book!=null){
            lost.setStudent(student);
            lost.setBook(book);
        }
        return lost;
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Lost> findLike(String keyword) throws Exception{
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {keyword};
        List<Lost> results = queryRunner.query("select a.borno,a.bno,b.name from lost a inner join book b on a.bno=b.bno where b.name like ?", new BeanListHandler<Lost>(Lost.class), param);
        List<Lost> result = new ArrayList<>();
        for (Lost lost : results){
            int borno = lost.getBorno();
            Lost lostWithAll = findLost(borno);
            result.add(lostWithAll);
        }
        return result;
    }

    /**
     * 根据学号查询，某一个学生的所有挂失信息
     * @param sno
     * @return
     * @throws Exception
     */
    @Override
    public List<Lost> findLostbysno(int sno) throws Exception{
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {sno};
        List<Lost> results = queryRunner.query("select a.borno,a.bno,b.name from lost a inner join book b on a.bno=b.bno where a.sno=?", new BeanListHandler<Lost>(Lost.class), param);
        List<Lost> result = new ArrayList<>();
        for (Lost lost : results){
            int borno = lost.getBorno();
            Lost lostWithAll = findLost(borno);
            result.add(lostWithAll);
        }
        return result;
        //select * from borrow where bno=(select bno from book where name like ?)
    }

    /**
     * 挂失处理,即添加处理时间，将状态改为已处理
     * @param borno
     * @param lost
     * @throws SQLException
     */
    @Override
    public void treatLostByBorno(Lost lost, int borno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {lost.getOptime(),borno};
        queryRunner.update(
                "update lost set optime=?,status=1 where borno=?",param);
    }

    /**
     * 挂失,添加一条数据且状态为未处理
     * @param lost
     * @throws SQLException
     */
    @Override
    public void LostByBorno(Lost lost) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {lost.getBorno(),lost.getBno(),lost.getPlace(),lost.getSno(),lost.getLostime()};
        queryRunner.update(
                "insert into lost(borno,bno,place,sno,lostime) values(?,?,?,?,?)",param);
    }
}
