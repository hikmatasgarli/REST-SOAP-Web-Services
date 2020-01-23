package business;

import model.Student;
import model.WebUser;

import java.util.List;

public interface StudentService {
    List<Student> getStudentList() throws Exception;
    boolean addStudent (Student student) throws Exception;
    Student getStudentById(Long studentId) throws Exception;
    boolean updateStudent(Student student,Long studentId) throws Exception;
    boolean deleteStudent(Long studentId) throws Exception;
    List<Student> searchStudentData(String keyword) throws Exception;
    WebUser login(String username, String password) throws Exception;
    boolean updateToken(String token, Long userId) throws Exception;
    boolean checkToken(String token) throws Exception;
    boolean updateTokenWithToken(String token) throws Exception;

}
