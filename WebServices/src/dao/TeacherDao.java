package dao;

import model.Teacher;

import java.util.List;

public interface TeacherDao {
    List<Teacher> getTeacherList() throws Exception;
    boolean addTeacher(Teacher teacher) throws Exception;
    Teacher getTeacherById(Long teacherId) throws Exception;
    boolean updateTeacher(Teacher teacher,Long teacherId) throws Exception;
    boolean deleteTeacher(Long teacherId) throws Exception;
    List<Teacher> searchTeacherData(String keyword) throws Exception;
    List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception;
}
