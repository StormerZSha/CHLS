package service.intf;

import entity.Student;

import java.util.List;

public interface IStudentService {
    /**
     * 获取所有学生信息
     * @return
     * @throws Exception
     */
    List<Student> findAll() throws Exception;

    /**
     * 根据学生关键字搜索学生信息
     * @return
     * @throws Exception
     */
    Student findStudentByName(String keyWord) throws Exception;

    /**
     * 添加学生信息
     * @param student
     * @throws Exception
     */
    void addStudent(Student student) throws Exception;

    /**
     * 根据学生ID删除学生信息
     * @param id
     * @throws Exception
     */
    void deleteById(int id) throws Exception;

    /**
     * 模糊查询
     * @return
     * @throws Exception
     */
    List<Student> findLike(String keyword) throws Exception;


    /**
     * 根据学生sno查询学生信息
     * @return
     * @throws Exception
     */
    Student findStudentBySno(int sno) throws Exception;


    /**
     * 查询一条学生信息
     * @param id
     * @return
     * @throws Exception
     */
    Student findStudent(int id) throws Exception;

    /**
     * 更新学生信息
     * @throws Exception
     */
    void updateStudent(String name,String speciality,int booknum,String pass,int id) throws Exception;

    /**学生登录
     *
     * @param student
     * @return
     */
    Student login(Student student) throws Exception;

    /**
     *  修改密码
     * @throws Exception
     */
    void modfiyPass(String pass,int id) throws Exception;
}


