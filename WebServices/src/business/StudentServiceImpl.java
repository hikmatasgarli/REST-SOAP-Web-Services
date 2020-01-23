package business;


import dao.StudentDao;
import model.Student;
import model.WebUser;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudentList() throws Exception {
        return studentDao.getStudentList();
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        return studentDao.addStudent(student);
    }

    @Override
    public Student getStudentById(Long studentId) throws Exception {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        return studentDao.updateStudent(student, studentId);
    }

    @Override
    public boolean deleteStudent(Long studentId) throws Exception {
        return studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudentData(String keyword) throws Exception {
        return studentDao.searchStudentData(keyword);
    }

    @Override
    public WebUser login(String username, String password) throws Exception {
        return studentDao.login(username,password);
    }

    @Override
    public boolean updateToken(String token, Long userId) throws Exception {
        return studentDao.updateToken(token,userId);
    }

    @Override
    public boolean checkToken(String token) throws Exception {
        return studentDao.checkToken(token);
    }

    @Override
    public boolean updateTokenWithToken(String token) throws Exception {
        return studentDao.updateTokenWithToken(token);
    }
}
