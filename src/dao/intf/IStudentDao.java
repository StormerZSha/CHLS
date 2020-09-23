package dao.intf;

import entity.Student;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDao {
        //查找所有学生记录
        List<Student> findAll() throws SQLException;

        //根据sno查找一个学生记录
        Student findBySno(int sno) throws SQLException;

        //根据id删除一条学生记录
        void deleteById(int id) throws SQLException;

        //根据学生id修改该学生信息
        void updateById(int id, Student student) throws SQLException;

        //添加一条学生信息（主键字段自增长不写）
        void insert(Student student) throws SQLException;

        //查询一条学生信息
        Student findStudent(int id) throws SQLException;

        //模糊查询
        List<Student> findLike(String keyword) throws Exception;

        //更新学生信息
        void updateStudent(String name, String speciality,int booknum,String pass, int id) throws Exception;

        //修改密码
        void modfiyPass(String pass,int id) throws Exception;
}
