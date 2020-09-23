package service.intf;

import entity.Lost;

import java.sql.SQLException;
import java.util.List;

public interface ILostService {

    /**
     * 查询所有挂失信息
     *
     * @return
     * @throws Exception
     */
    List<Lost> findAll() throws Exception;

    //查询一条挂失信息，包含学生信息和图书信息。
    Lost findLost(int borno) throws Exception;

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    List<Lost> findLike(String keyword) throws Exception;

    /**
     * 根据学号查询，某一个学生的所有挂失信息
     * @param sno
     * @return
     * @throws Exception
     */
    List<Lost> findLostbysno(int sno) throws Exception;

    /**
     * 挂失处理,即添加处理时间，将状态改为已处理
     * @param borno
     * @param lost
     * @throws SQLException
     */
    void treatLostByBorno(Lost lost, int borno) throws SQLException;

    /**
     * 挂失,添加一条数据且状态为未处理
     * @param lost
     * @throws SQLException
     */
    void LostByBorno(Lost lost) throws SQLException;
}
