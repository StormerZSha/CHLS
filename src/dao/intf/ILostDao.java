package dao.intf;

import entity.Lost;

import java.sql.SQLException;
import java.util.List;

public interface ILostDao {
    //查找所有挂失记录
    List<Lost> findAll() throws SQLException;

    //查询一条挂失信息，包含学生信息和图书信息。
    Lost findLost(int borno) throws SQLException;

    //模糊查询
    List<Lost> findLike(String keyword) throws Exception;

    //根据学号查询，某一个学生的所有挂失信息
    List<Lost> findLostbysno(int sno) throws Exception;

    //挂失处理
    void treatLostByBorno(Lost lost, int borno) throws SQLException;

    //挂失
    void LostByBorno(Lost lost) throws SQLException;

}
