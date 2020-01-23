package business;


import dao.TeacherDao;
import model.Teacher;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> getTeacherList() throws Exception {
        return teacherDao.getTeacherList();
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        return teacherDao.addTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) throws Exception {
        return teacherDao.getTeacherById(teacherId);
    }

    @Override
    public boolean updateTeacher(Teacher teacher, Long teacherId) throws Exception {
        return teacherDao.updateTeacher(teacher,teacherId);
    }

    @Override
    public boolean deleteTeacher(Long teacherId) throws Exception {
        return teacherDao.deleteTeacher(teacherId);
    }

    @Override
    public List<Teacher> searchTeacherData(String keyword) throws Exception {
        return teacherDao.searchTeacherData(keyword);
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {
        return teacherDao.getTeacherListByLessonId(lessonId);
    }
}
