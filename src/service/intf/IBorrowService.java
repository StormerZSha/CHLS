package service.intf;

import entity.Borrow;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface IBorrowService {

    /**
     * 查询所有图书信息
     *
     * @return
     * @throws Exception
     */
    List<Borrow> findAll() throws Exception;

    //查询一条借阅信息，若已预约/借阅则包含学生信息和图书信息。
    Borrow findBorrow(int borno) throws Exception;

    /**
     * 根据图书号查询有关的所有借阅信息。
     *
     * @param bno
     * @throws Exception
     */
    List<Borrow> findBorrowbybno(int bno) throws Exception;

    /**
     * 添加一本新书
     * @param borrow
     * @throws Exception
     */
    void insert(Borrow borrow) throws Exception;

    /**
     * 下架一本旧书
     * @param borno
     * @throws Exception
     */
    void deleteByBorno(int borno) throws Exception;

    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    List<Borrow> findLike(String keyword) throws Exception;

    /**
     * 根据学号查询，某一个学生的所有借阅预约信息
     * @param sno
     * @return
     * @throws Exception
     */
    List<Borrow> findBorrowbysno(int sno) throws Exception;

    /**
     * 取消预约,即修改操作人，操作时间为空，图书状态为可借阅
     * @param borno
     * @throws Exception
     */
    void cancleOrderByBorno(int borno) throws Exception;

    /**
     * 预约,即添加操作人，操作时间为空，图书状态改为为已预约
     * @param borrow
     * @param borno
     * @throws Exception
     */
    void orderByBorno(Borrow borrow,int borno) throws Exception;

    /**
     * 还书，即修改操作人,操作时间为空，图书状态为可借阅
     * @param borno
     * @throws Exception
     */
    void backBookByBorno(int borno) throws Exception;

    /**
     * 借书,修改插入借阅人，操作时间，还书时间，图书状态为已借阅
     * @param borrow
     * @param borno
     * @throws Exception
     */
    void lendBookByBorno(Borrow borrow,int borno) throws Exception;

    /**
     * 挂失删除，挂失后将borrow表中的数据删除添加到lost表
     * @param borno
     * @throws SQLException
     */
    void lostdeleteByBorno(int borno) throws SQLException;
}
