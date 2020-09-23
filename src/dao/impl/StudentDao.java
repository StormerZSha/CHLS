package dao.impl;

import com.alibaba.druid.pool.DruidPooledConnection;
import dao.intf.IStudentDao;
import entity.Student;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements IStudentDao {
    /**
     * 查询所有学生信息
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Student> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Student> results = queryRunner.query("select * from student",
                new BeanListHandler<Student>(Student.class));
        List<Student> result = new ArrayList<>();
        for (Student student : results) {
            int id = student.getId();
            Student studentWithAll = findStudent(id);
            result.add(studentWithAll);
        }
        return result;
    }

    /**
     * 根据学号查询学生信息
     *
     * @param sno
     * @return
     * @throws SQLException
     */
    @Override
    public Student findBySno(int sno) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        Object[] param = {sno};
        Connection connection = JDBCUtils.getDataSource().getConnection();
        Student result = queryRunner.query(connection, "select * from student where sno =?", new BeanHandler<Student>(Student.class), param);
        connection.close();
        return result;
    }



    /**
     * 根据学生ID删除学生信息
     *
     * @param id
     * @throws SQLException
     */
    @Override
    public void deleteById(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {id};
        queryRunner.update("delete from student where id =?", param);
    }

    /**
     * 根据id更新学生信息
     *
     * @param id
     * @param student
     * @throws SQLException
     */
    @Override
    public void updateById(int id, Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {student.getName(), student.getSex(), student.getBirthday(),
                student.getSpeciality(), student.getSno(), student.getBooknum(),student.getPass(), id};
        queryRunner.update(
                "update student set name=?,sex=?,birthday=?,speciality=?,sno=?,booknum=?,pass=? where id=?", param);
    }

    /**
     * 插入一条学生信息
     *
     * @param student
     * @throws SQLException
     */
    @Override
    public void insert(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {student.getName(), student.getSex(), student.getBirthday(), student.getSpeciality(),
                student.getSno(), student.getBooknum(),student.getPass()};
        queryRunner.update(
                "insert into student(name,sex,birthday,speciality,sno,booknum,pass) values(?,?,?,?,?,?,?)", param);
    }

    //根据查询一条学生信息
    @Override
    public Student findStudent(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        DruidPooledConnection connection = JDBCUtils.getDataSource().getConnection();
        Object[] param = {id};
        Student result = queryRunner.query(connection, "select * from student where id=?", new BeanHandler<Student>(Student.class), param);
        connection.close();
        return result;
    }
    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> findLike(String keyword) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param = {keyword};
        List<Student> result = queryRunner.query("select * from student where name like ?", new BeanListHandler<Student>(Student.class), param);
        return result;
    }

    /**
     *  更新学生信息
     * @throws Exception
     */
    @Override
    public void updateStudent(String name,String speciality,int booknum,String pass,int id) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param ={name,speciality,booknum,pass,id};
        queryRunner.update("update student set name=?,speciality=?,booknum=?,pass=? where id=?",param);
    }

    /**
     *  修改密码
     * @throws Exception
     */
    @Override
    public void modfiyPass(String pass,int id) throws Exception {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        Object[] param ={pass,id};
        queryRunner.update("update student set pass=? where id=?",param);
    }
}

