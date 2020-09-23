package service.impl;

import dao.impl.LostDao;
import dao.intf.ILostDao;
import entity.Lost;
import service.intf.ILostService;

import java.sql.SQLException;
import java.util.List;

public class LostService implements ILostService {
    ILostDao lostDao=new LostDao();
    /**
     * 查询所有挂失信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Lost> findAll() throws Exception{
        List<Lost> result=lostDao.findAll();
        return result;
    }

    //查询一条挂失信息，包含学生信息和图书信息。
    @Override
    public Lost findLost(int borno) throws Exception{
        Lost result=lostDao.findLost(borno);
        return result;
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Lost> findLike(String keyword) throws Exception{
        List<Lost> result = lostDao.findLike("%"+keyword+"%");
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
        List<Lost> result=lostDao.findLostbysno(sno);
        return result;
    }

    /**
     * 挂失处理,即添加处理时间，将状态改为已处理
     * @param borno
     * @param lost
     * @throws SQLException
     */
    @Override
    public void treatLostByBorno(Lost lost, int borno) throws SQLException{
        lostDao.treatLostByBorno(lost,borno);
    }


    /**
     * 挂失,添加一条数据且状态为未处理
     * @param lost
     * @throws SQLException
     */
    @Override
    public void LostByBorno(Lost lost) throws SQLException{
        lostDao.LostByBorno(lost);
    }
}
