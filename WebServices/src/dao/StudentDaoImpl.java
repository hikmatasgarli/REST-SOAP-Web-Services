package dao;

import model.Student;
import model.WebUser;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getStudentList() throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;            // Baza ile elaqe yaradir.
        PreparedStatement ps = null;    // sql - i run etmek ucun hazirlayir.
        ResultSet rs = null;            // sql - den qaidan neticeni ozunde saxlayir.
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM STUDENT " +
                "WHERE ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return studentList;
    }

    @Override
    public boolean addStudent(Student student) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "INSERT INTO STUDENT(NAME,SURNAME,ADDRESS,DOB,PHONE) VALUES (?,?,?,?,?)";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getAddress());
                ps.setDate(4, new java.sql.Date(student.getDob().getTime()));
                ps.setString(5, student.getPhone());
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
    public Student getStudentById(Long studentId) throws Exception {
        Student student = new Student();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM STUDENT WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, studentId);
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return student;
    }

    @Override
    public boolean updateStudent(Student student, Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE STUDENT SET NAME = ?,SURNAME = ?,ADDRESS = ?,DOB = ?,PHONE = ? " +
                "WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setString(3, student.getAddress());
                ps.setDate(4, new java.sql.Date(student.getDob().getTime()));
                ps.setString(5, student.getPhone());
                ps.setLong(6, studentId);
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
    public boolean deleteStudent(Long studentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE STUDENT SET ACTIVE = 0 " +
                "WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, studentId);
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
    public List<Student> searchStudentData(String keyword) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        Connection c = null;            // Baza ile elaqe yaradir.
        PreparedStatement ps = null;    // sql - i run etmek ucun hazirlayir.
        ResultSet rs = null;            // sql - den qaidan neticeni ozunde saxlayir.
        String sorgu = "SELECT ID,NAME,SURNAME,ADDRESS,DOB,PHONE FROM STUDENT WHERE ACTIVE = 1\n" +
                "AND (LOWER(NAME) LIKE LOWER(?) OR LOWER(SURNAME) LIKE LOWER(?) OR LOWER(ADDRESS) LIKE LOWER(?))";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, "%" + keyword + "%");
                ps.setString(2, "%" + keyword + "%");
                ps.setString(3, "%" + keyword + "%");
                rs = ps.executeQuery(); // SQL - den qaidan neticeni goturur.
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getLong("ID"));
                    student.setName(rs.getString("NAME"));
                    student.setSurname(rs.getString("SURNAME"));
                    student.setAddress(rs.getString("ADDRESS"));
                    student.setDob(rs.getDate("DOB"));
                    student.setPhone(rs.getString("PHONE"));
                    studentList.add(student);
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return studentList;
    }

    @Override
    public WebUser login(String username, String password) throws Exception {
        WebUser webUser = new WebUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sorgu = "SELECT * FROM WEB_USER WHERE ACTIVE = 1 AND USERNAME = ? AND PASSWORD = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    webUser.setIdWebUser(rs.getLong("ID_WEB_USER"));
                    webUser.setCompanyName(rs.getString("COMPANY_NAME"));
                    webUser.setUsername(username);
                    webUser.setToken(rs.getString("TOKEN"));
                } else {
                    webUser = null;
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return webUser;
    }

    @Override
    public boolean updateToken(String token, Long userId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "UPDATE WEB_USER SET TOKEN = ? WHERE ID_WEB_USER = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                ps.setLong(2, userId);
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
    public boolean checkToken(String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM WEB_USER WU WHERE WU.TOKEN = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, token);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            } else {
                System.out.println("Connection is null!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return result;
    }

    @Override
    public boolean updateTokenWithToken(String token) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sorgu = "UPDATE WEB_USER SET TOKEN = ? WHERE TOKEN = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setString(1, null);
                ps.setString(2, token);
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

}
