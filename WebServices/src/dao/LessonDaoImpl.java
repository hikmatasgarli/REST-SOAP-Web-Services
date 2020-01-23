package dao;


import dao.LessonDao;
import model.Lesson;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {
    @Override
    public List<Lesson> getLessonList() throws Exception {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Connection c = null; // baza ile elaqe yaradir.
        PreparedStatement ps = null; // sql - i run etmek ucun hazirlayir
        ResultSet rs = null; // sql -den qayidan neticeni ozunde saxlayir
        String sql = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    //lesson.setR(rs.getLong("r"));
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonTime(rs.getString("LESSON_TIME"));
                    lesson.setLessonPrice(rs.getString("LESSON_PRICE"));
                    lessonList.add(lesson);

                }
            } else {
                System.out.println("Connection is null!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return lessonList;
    }

    @Override
    public boolean addLesson(Lesson lesson) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sorgu = "INSERT INTO LESSON(LESSON_NAME,LESSON_TIME,LESSON_PRICE) VALUE (?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, lesson.getLessonName());
                ps.setString(2, lesson.getLessonTime());
                ps.setString(3, lesson.getLessonPrice());
                ps.execute();
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, null);
        }
        return result;
    }

    @Override
    public Lesson getLessonById(Long lessonId) throws Exception {
        Lesson lesson = new Lesson();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sorgu = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON WHERE ACTIVE = 1 AND ID =?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, lessonId);
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonTime(rs.getString("LESSON_TIME"));
                    lesson.setLessonPrice(rs.getString("LESSON_PRICE"));

                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return lesson;
    }

    @Override
    public boolean updateLesson(Lesson lesson, Long lessonId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE LESSON SET LESSON_NAME = ?,LESSON_TIME = ?,LESSON_PRICE = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, lesson.getLessonName());
                ps.setString(2, lesson.getLessonTime());
                ps.setString(3, lesson.getLessonPrice());
                ps.setLong(4,lessonId);
                ps.execute();   // Resultset yoxdu die promoy execute istifade edirem.
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //c.commit();   //  DML emeliyyatlarndan sonra commit etmek lazimdir.
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean deleteLesson(Long lessonId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE LESSON SET ACTIVE = 0 " +
                "WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1,lessonId);
                ps.execute();   // Resultset yoxdu die promoy execute istifade edirem.
                result = true;
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //c.commit();   //  DML emeliyyatlarndan sonra commit etmek lazimdir.
            JdbcUtility.close(c, ps, null);
        }

        return result;
    }

    @Override
    public List<Lesson> searchLessonData(String keyword) throws Exception {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        Connection c = null;            // Baza ile elaqe yaradir.
        PreparedStatement ps = null;    // sql - i run etmek ucun hazirlayir.
        ResultSet rs = null;            // sql - den qaidan neticeni ozunde saxlayir.
        String sorgu = "SELECT ID,LESSON_NAME,LESSON_TIME,LESSON_PRICE FROM LESSON WHERE ACTIVE = 1\n" +
                "AND (LOWER(LESSON_NAME) LIKE LOWER(?) OR LOWER(LESSON_TIME) LIKE LOWER(?) OR LOWER(LESSON_PRICE) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1,"%"+keyword+"%");
                ps.setString(2,"%"+keyword+"%");
                ps.setString(3,"%"+keyword+"%");
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    Lesson lesson = new Lesson();
                    lesson.setId(rs.getLong("ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    lesson.setLessonTime(rs.getString("LESSON_TIME"));
                    lesson.setLessonPrice(rs.getString("LESSON_PRICE"));

                    lessonList.add(lesson);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return lessonList;
    }
}
