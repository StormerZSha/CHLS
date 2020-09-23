package dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import dao.intf.IAdminDao;
import entity.Admin;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.util.List;

public class AdminDao implements IAdminDao {
    //查询所有用户信息
    @Override
    public List<Admin> findAll() throws Exception{
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        List<Admin> result = queryRunner.query("select * from admin",new BeanListHandler<Admin>(Admin.class));
        return result;
    }
    //根据用户名删除用户信息
    @Override
    public void deleteByUsername(String username) throws Exception {
        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
        Object[] param={username};
        queryRunner.update("delete from admin where username=?",param);
    }
    //根据用户名用户修改密码
//    @Override
//    public void updateByUsername(String username,String password) throws Exception {
//        QueryRunner queryRunner=new QueryRunner(JDBCUtils.getDataSource());
//        Object[] param={username,password};
//        queryRunner.update("update admin set password=？where username=?",param);
//    }
    //根据用户名查询单个用户信息
    @Override
    public Admin findAdminByUsername(String username) throws Exception {
        QueryRunner queryRunner = new QueryRunner();
        DruidPooledConnection connection = JDBCUtils.getDataSource().getConnection();
        Object[] param={username};
        Admin result = queryRunner.query(connection, "select * from admin where username=?", new BeanHandler<Admin>(Admin.class), param);
        connection.close();
        return result;
    }
    //插入一条用户数据
    @Override
    public int insert(Admin admin) throws Exception {
        QueryRunner queryRunner=new QueryRunner();
        DruidPooledConnection connection = JDBCUtils.getDataSource().getConnection();
        Object[] param={admin.getUsername(), admin.getPassword(), admin.getType(), admin.getEmail()};
        int result = queryRunner.update(connection, "insert into admin(username,password,type,email) values(?,?,?,?)", param);
        connection.close();
        return result;
    }

    /**
     * 模糊查询
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Admin> findLike(String keyword) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {keyword};
        List<Admin> result = queryRunner.query("select * from admin where username like ?", new BeanListHandler<Admin>(Admin.class), param);
        return result;
    }

    /**
     * 授权
     * @param type
     * @throws Exception
     */
    @Override
    public void updateGrant(String name,int type) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {type,name};
        queryRunner.update("update admin set type=? where username=?", param);
    }


}
