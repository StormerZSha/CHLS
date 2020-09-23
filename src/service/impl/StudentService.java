package service.impl;

import dao.impl.StudentDao;
import dao.intf.IStudentDao;
import entity.Student;
import service.intf.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {
    IStudentDao studentDao = new StudentDao();
    /**
     * 获取所有学生信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> findAll() throws Exception {//
        List<Student> result = studentDao.findAll();
        return result;
    }

    /**
     * 根据学生关键字查询学生信息
     * @param keyWord
     * @return
     * @throws Exception
     */
    @Override
    public Student findStudentByName(String keyWord) throws Exception {
        return null;
    }

    /**
     * 添加一条学生信息
     * @param student
     * @throws Exception
     */
    @Override
    public void addStudent(Student student) throws Exception {
        studentDao.insert(student);
    }

    /**
     * 根据学生ID删除学生信息
     * @param id
     * @throws Exception
     */
    @Override
    public void deleteById(int id) throws Exception {
        studentDao.deleteById(id);
    }

    @Override
    public List<Student> findLike(String keyword) throws Exception{
        List<Student> result = studentDao.findLike("%"+keyword+"%");
        return result;
    }


    /**
     * 根据学生sno查询学生信息
     * @return
     * @throws Exception
     */
    @Override
    public Student findStudentBySno(int sno) throws Exception {
        Student result = studentDao.findBySno(sno);
        return result;
    }

    /**
     *根据id查询一条学生信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Student findStudent(int id) throws Exception {
        Student result = studentDao.findStudent(id);
        return result;
    }

    /**
     * 学生信息更新
     * @throws Exception
     */
    @Override
    public void updateStudent(String name,String speciality,int booknum,String pass,int id) throws Exception {
        studentDao.updateStudent(name,speciality,booknum,pass,id);
    }

    /**学生登录
     *
     * @param student
     * @return
     */
    @Override
    public Student login(Student student) throws Exception {
        Student result = studentDao.findBySno(student.getSno());
        if (result!=null){
            String pass = result.getPass();
            if (student.getPass().equals(pass)){
                return result;
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

    /**
     *  修改密码
     * @throws Exception
     */
    @Override
    public void modfiyPass(String pass,int id) throws Exception {
        studentDao.modfiyPass(pass, id);
    }

}
