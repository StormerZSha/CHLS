package service.impl;

import dao.impl.AdminDao;
import dao.intf.IAdminDao;
import entity.Admin;
import service.intf.IAdminService;

import java.util.List;

public class AdminService implements IAdminService {
    IAdminDao adminDao=new AdminDao();

    /**查询所有管理员信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public List<Admin> findAll() throws Exception {
        List<Admin> result = adminDao.findAll();
        return result;
    }

    /**管理员登录
     *
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) throws Exception {
        Admin result = adminDao.findAdminByUsername(admin.getUsername());
        if (result!=null){
            String password = result.getPassword();
            if (admin.getPassword().equals(password)){
                return result;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }


    //添加一条管理员信息
    @Override
    public void add(Admin admin) throws Exception {
         adminDao.insert(admin);
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Admin> findLike(String keyword) throws Exception {
        List<Admin> result = adminDao.findLike("%" + keyword + "%");
        return result;
    }

    @Override
    public Admin findAdminByName(String name) throws Exception {
        Admin result = adminDao.findAdminByUsername(name);
        return result;
    }

    /**
     * 根据名称删除管理员信息
     * @param name
     * @throws Exception
     */
    @Override
    public void deleteAdminByName(String name) throws Exception {
        adminDao.deleteByUsername(name);
    }

    @Override
    public void updateGrant(String name, int type) throws Exception {
        adminDao.updateGrant(name,type);
    }

}
