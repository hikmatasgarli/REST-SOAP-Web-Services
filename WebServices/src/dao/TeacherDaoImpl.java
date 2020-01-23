package dao;

import dao.DbHelper;
import dao.TeacherDao;
import model.Teacher;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> getTeacherList() throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null; // SQL - i run etmek ucun hazirlayir.
        ResultSet rs = null; // Neticeni ozunde saxlayir
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM TEACHER " +
                "WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return teacherList;
    }

    @Override
    public boolean addTeacher(Teacher teacher) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sorgu = "INSERT INTO TEACHER(NAME,SURNAME,ADDRESS,DOB,PHONE) VALUES(?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getSurname());
                ps.setString(3, teacher.getAddress());
                ps.setDate(4, new java.sql.Date(teacher.getDob().getTime())); //DIQQET !!!
                ps.setString(5, teacher.getPhone());
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
    public Teacher getTeacherById(Long teacherId) throws Exception {
        Teacher teacher = new Teacher();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM TEACHER WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, teacherId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return teacher;
    }

    @Override
    public boolean updateTeacher(Teacher teacher, Long teacherId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sorgu = "UPDATE TEACHER SET NAME = ?,SURNAME = ?,ADDRESS = ?,DOB = ?,PHONE = ? WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getSurname());
                ps.setString(3, teacher.getAddress());
                ps.setDate(4, new java.sql.Date(teacher.getDob().getTime()));
                ps.setString(5, teacher.getPhone());
                ps.setLong(6, teacherId);
                ps.execute();   // Resultset yoxdu die promoy execute istifade edirem.
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
    public boolean deleteTeacher(Long teacherId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE TEACHER SET ACTIVE = 0 " +
                "WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1,teacherId);
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
    public List<Teacher> searchTeacherData(String keyword) throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null;            // Baza ile elaqe yaradir.
        PreparedStatement ps = null;    // sql - i run etmek ucun hazirlayir.
        ResultSet rs = null;            // sql - den qaidan neticeni ozunde saxlayir.
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM STUDENT WHERE ACTIVE = 1\n" +
                "AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?) OR LOWER(ADDRESS) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1,"%"+keyword+"%");
                ps.setString(2,"%"+keyword+"%");
                ps.setString(3,"%"+keyword+"%");
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));
                    teacher.setAddress(rs.getString("ADDRESS"));
                    teacher.setDob(rs.getDate("DOB"));
                    teacher.setPhone(rs.getString("PHONE"));
                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return teacherList;
    }

    @Override
    public List<Teacher> getTeacherListByLessonId(Long lessonId) throws Exception {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        Connection c = null;
        PreparedStatement ps = null; // SQL - i run etmek ucun hazirlayir.
        ResultSet rs = null; // Neticeni ozunde saxlayir
        String sorgu = "SELECT DISTINCT T.ID,T.NAME,T.SURNAME\n" +
                "    FROM PAYMENT P\n" +
                "    INNER JOIN TEACHER T\n" +
                "    ON P.T_ID = T.ID\n" +
                "    INNER JOIN LESSON L\n" +
                "    ON P.L_ID = L.ID\n" +
                "    WHERE T.ACTIVE = 1 AND L.ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1,lessonId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(rs.getLong("ID"));
                    teacher.setName(rs.getString("NAME"));
                    teacher.setSurname(rs.getString("SURNAME"));

                    teacherList.add(teacher);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return teacherList;
    }

}
