package dao;

import model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getLessonList() throws Exception;
    boolean addLesson (Lesson lesson) throws Exception;
    Lesson getLessonById(Long lessonId) throws Exception;
    boolean updateLesson (Lesson lesson, Long lessonId) throws Exception;
    boolean deleteLesson(Long lessonId) throws Exception;
    List<Lesson> searchLessonData(String keyword) throws Exception;
}
