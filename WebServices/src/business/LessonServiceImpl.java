package business;

import business.LessonService;
import dao.LessonDao;
import model.Lesson;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private LessonDao lessonDao;

    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public List<Lesson> getLessonList() throws Exception {
        return lessonDao.getLessonList();
    }

    @Override
    public boolean addLesson(Lesson lesson) throws Exception {
        return lessonDao.addLesson(lesson);
    }

    @Override
    public Lesson getLessonById(Long lessonId) throws Exception {
        return lessonDao.getLessonById(lessonId);
    }

    @Override
    public boolean updateLesson(Lesson lesson, Long lessonId) throws Exception {
        return lessonDao.updateLesson(lesson,lessonId);
    }

    @Override
    public boolean deleteLesson(Long lessonId) throws Exception {
        return lessonDao.deleteLesson(lessonId);
    }

    @Override
    public List<Lesson> searchLessonData(String keyword) throws Exception {
        return lessonDao.searchLessonData(keyword);
    }
}
