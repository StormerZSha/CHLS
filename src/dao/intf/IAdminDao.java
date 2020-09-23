package dao.intf;
/*
管理员表（admin）的持久化层接口
 */
import entity.Admin;

import java.util.List;

public interface IAdminDao {
    //查询所有管理员信息
    List<Admin> findAll() throws Exception;
    //根据名称删除管理员信息
    void deleteByUsername(String username)throws Exception;
    //根据名称修改密码
    //void updateByUsername(String username, String password)throws Exception;
    //根据用户查询用户信息。
    Admin findAdminByUsername(String username) throws Exception;
    //插入一条用户信息
    int insert(Admin admin)throws Exception;
    //模糊查询
    List<Admin> findLike(String keyword) throws Exception;
    //授权
    void updateGrant(String name, int type) throws Exception;
}
