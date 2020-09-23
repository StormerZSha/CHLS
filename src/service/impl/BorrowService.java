package service.impl;

import dao.impl.BorrowDao;
import dao.intf.IBorrowDao;
import entity.Borrow;
import service.intf.IBorrowService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class BorrowService implements IBorrowService{
    IBorrowDao borrowDao=new BorrowDao();
    /**
     * 查询所有图书信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Borrow> findAll() throws Exception{
        List<Borrow> result=borrowDao.findAll();
        return result;
    }

    //查询一条借阅信息，若已预约/借阅则包含学生信息和图书信息。
    @Override
    public Borrow findBorrow(int borno) throws Exception{
        Borrow result=borrowDao.findBorrow(borno);
        return result;
    }

    /**
     * 根据图书号查询有关的所有借阅信息。
     *
     * @param bno
     * @throws Exception
     */
    @Override
    public List<Borrow> findBorrowbybno(int bno) throws Exception{
        List<Borrow> result=borrowDao.findBorrowbybno(bno);
        return result;
    }

    /**
     * 添加一本新书
     * @param borrow
     * @throws Exception
     */
    @Override
    public void insert(Borrow borrow) throws Exception{
        borrowDao.insert(borrow);
    }

    /**
     * 下架一本旧书
     * @param borno
     * @throws Exception
     */
    @Override
    public void deleteByBorno(int borno) throws Exception{
        borrowDao.deleteByBorno(borno);
    }

    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Borrow> findLike(String keyword) throws Exception{
        List<Borrow> result = borrowDao.findLike("%"+keyword+"%");
        return result;
    }

    /**
     * 根据学号查询，某一个学生的所有借阅预约信息
     * @param sno
     * @return
     * @throws Exception
     */
    @Override
    public List<Borrow> findBorrowbysno(int sno) throws Exception{
        List<Borrow> result=borrowDao.findBorrowbysno(sno);
        return result;
    }

    /**
     * 取消预约,即修改操作人，操作时间为空，图书状态为可借阅
     * @param borno
     * @throws Exception
     */
    @Override
    public void cancleOrderByBorno(int borno) throws Exception{
        borrowDao.cancleOrderByBorno(borno);
    }

    /**
     * 预约,修改插入预约人，操作时间，图书状态为已预约
     * @param borrow
     * @param borno
     * @throws Exception
     */
    @Override
    public void orderByBorno(Borrow borrow,int borno) throws Exception{
        borrowDao.orderByBorno(borrow,borno);
    }

    /**
     * 还书，即修改操作人,操作时间为空，图书状态为可借阅
     * @param borno
     * @throws Exception
     */
    @Override
    public void backBookByBorno(int borno) throws Exception{
        borrowDao.backBookByBorno(borno);
    }

    /**
     * 借书,修改插入借阅人，操作时间，还书时间，图书状态为已借阅
     * @param borrow
     * @throws Exception
     */
    @Override
    public void lendBookByBorno(Borrow borrow,int borno) throws Exception{
        borrowDao.lendBookByBorno(borrow,borno);
    }

    /**
     * 挂失删除，挂失后将borrow表中的数据删除添加到lost表
     * @param borno
     * @throws SQLException
     */
    @Override
    public void lostdeleteByBorno(int borno) throws SQLException{
        borrowDao.lostdeleteByBorno(borno);
    }
}
