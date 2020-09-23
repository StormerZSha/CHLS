package service.intf;

import entity.Admin;

import java.util.List;

/**
管理员表（admin）的业务层接口
 */
public interface IAdminService {
    /**查询所有管理员信息
     *
     * @return
     * @throws Exception
     */
    List<Admin> findAll() throws Exception;

    /**管理员登录
     * @return
     * @param admin
     */
    Admin login(Admin admin) throws Exception;


    //添加一条管理员信息
    void add(Admin admin)throws Exception;

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    List<Admin> findLike(String keyword) throws Exception;

    /**
     * 通过名称查询管理员信息
     * @param name
     * @return
     * @throws Exception
     */
    Admin findAdminByName(String name) throws Exception;

    /**
     * 根据用名称删除管理员信息
     * @param name
     * @throws Exception
     */
    void deleteAdminByName(String name) throws Exception;

    /**
     * 授权和解除算法
     * @param type
     * @throws Exception
     */
    void updateGrant(String name, int type) throws Exception;
}
