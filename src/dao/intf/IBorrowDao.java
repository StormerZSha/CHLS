package dao.intf;

import entity.Borrow;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IBorrowDao {
    //查找所有图书记录
    List<Borrow> findAll() throws SQLException;

    //查询一条借阅信息，如已预约/借阅则包含学生信息和图书信息。
    Borrow findBorrow(int borno) throws SQLException;

    //根据图书号查询有关的所有借阅信息。
    List<Borrow> findBorrowbybno(int bno) throws SQLException;

    //添加一本新书
    void insert(Borrow borrow) throws SQLException;

    //下架一本旧书
    void deleteByBorno(int borno) throws SQLException;

    //根据书名模糊查询信息
    List<Borrow> findLike(String keyword) throws Exception;

    //根据学号查询，某一个学生的所有借阅预约信息
    List<Borrow> findBorrowbysno(int sno) throws Exception;

    //取消预约,即修改操作人，操作时间为空，图书状态为可借阅
    void cancleOrderByBorno(int borno) throws SQLException;

     // 预约,修改插入预约人，操作时间，图书状态为已预约
    void orderByBorno(Borrow borrow,int borno) throws SQLException;

    //还书，即修改操作人，操作时间为空，图书状态为可借阅
    void backBookByBorno(int borno) throws SQLException;

    //借书,修改插入借阅人，操作时间，还书时间，图书状态为已借阅
    void lendBookByBorno(Borrow borrow,int borno) throws SQLException;

    //挂失删除，挂失后将borrow表中的数据删除添加到lost表
    void lostdeleteByBorno(int borno) throws SQLException;
}
