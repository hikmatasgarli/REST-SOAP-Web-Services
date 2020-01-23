package dao;


import dao.DbHelper;
import dao.PaymentDao;
import model.*;
import util.JdbcUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public List<Payment> getPaymentList() throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID,\n"
                + "       S.ID STUDENT_ID,\n"
                + "       S.NAME STUDENT_NAME,\n"
                + "       S.SURNAME STUDENT_SURNAME,\n"
                + "       T.ID TEACHER_ID,\n"
                + "       T.NAME TEACHER_NAME,\n"
                + "       T.SURNAME TEACHER_SURNAME,\n"
                + "       L.ID LESSON_ID,\n"
                + "       L.LESSON_NAME LESSON_NAME,\n"
                + "       P.AMOUNT,\n"
                + "       P.PAY_DATE\n"
                + "  FROM PAYMENT P\n"
                + "       INNER JOIN STUDENT S\n"
                + "          ON P.S_ID = S.ID\n"
                + "       INNER JOIN TEACHER T\n"
                + "          ON P.T_ID = T.ID\n"
                + "       INNER JOIN LESSON L\n"
                + "          ON P.L_ID = L.ID\n"
                + " WHERE P.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    //payment.setR(rs.getLong("r"));
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    Teacher teacher = new Teacher();
                    Lesson lesson = new Lesson();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    payment.setStudent(student);
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    payment.setTeacher(teacher);
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        boolean isAdded = false;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sorgu = "INSERT INTO PAYMENT (S_ID,T_ID,L_ID,AMOUNT) VALUES(?,?,?,?)";

        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, payment.getStudent().getId());
                ps.setLong(2, payment.getTeacher().getId());
                ps.setLong(3, payment.getLesson().getId());
                ps.setDouble(4, payment.getAmount());
                ps.execute();
                isAdded = true;
            } else {
                System.out.println("Connection is null!");

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return isAdded;
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        Payment payment = new Payment();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID,S_ID,T_ID,L_ID,AMOUNT,PAY_DATE FROM PAYMENT WHERE ACTIVE = 1 AND ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setLong(1, paymentId);
                rs = ps.executeQuery();
                while (rs.next()) {

                    //payment.setR(rs.getLong("r"));
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    Teacher teacher = new Teacher();
                    Lesson lesson = new Lesson();
                    student.setId(rs.getLong("S_ID"));

                    payment.setStudent(student);
                    teacher.setId(rs.getLong("T_ID"));

                    payment.setTeacher(teacher);
                    lesson.setId(rs.getLong("L_ID"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return payment;
    }

    @Override
    public boolean updatePayment(Payment payment, Long paymentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        // ResultSet rs = null; Netice qaitmir die ehtiyyac yoxdur
        String sorgu = "UPDATE PAYMENT SET S_ID = ?,T_ID = ?,L_ID = ?,AMOUNT = ?" +
                "WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, payment.getStudent().getId());
                ps.setLong(2, payment.getTeacher().getId());
                ps.setLong(3, payment.getLesson().getId());
                ps.setDouble(4, payment.getAmount());
                ps.setLong(5, paymentId);
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
    public List<Payment> advancedSearchPaymentData(AdvancedSearch advancedSearch) throws Exception {
        List<Payment> paymentList = new ArrayList<Payment>();
        DateFormat df = new SimpleDateFormat("yy/mm/dd");
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT P.ID,S.ID STUDENT_ID,\n" +
                "                       S.NAME STUDENT_NAME,\n" +
                "                       S.SURNAME STUDENT_SURNAME,\n" +
                "                       T.ID TEACHER_ID,\n" +
                "                       T.NAME TEACHER_NAME,\n" +
                "                       T.SURNAME TEACHER_SURNAME,\n" +
                "                       L.ID LESSON_ID,\n" +
                "                       L.LESSON_NAME LESSON_NAME,\n" +
                "                       P.AMOUNT,\n" +
                "                       P.PAY_DATE\n" +
                "                  FROM PAYMENT P\n" +
                "                       INNER JOIN STUDENT S\n" +
                "                          ON P.S_ID = S.ID\n" +
                "                       INNER JOIN TEACHER T\n" +
                "                          ON P.T_ID = T.ID\n" +
                "                    INNER JOIN LESSON L\n" +
                "                      ON P.L_ID = L.ID\n" +
                "                 WHERE P.ACTIVE = 1 AND S.ACTIVE = 1 AND T.ACTIVE = 1";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                if (advancedSearch.getAdvLessonCbmId() != 0) {
                    sql += " AND L.ID = " + advancedSearch.getAdvLessonCbmId();
                }
                if (advancedSearch.getAdvTeacherCmbId() != 0) {
                    sql += " AND T.ID = " + advancedSearch.getAdvTeacherCmbId();
                }
                if (advancedSearch.getAdvMinAmount() != null &&
                        !advancedSearch.getAdvMinAmount().isEmpty()) {
                    sql += " AND P.AMOUNT >= " + Double.parseDouble(advancedSearch.getAdvMinAmount());
                }
                if (advancedSearch.getAdvMaxAmount() != null &&
                        !advancedSearch.getAdvMaxAmount().isEmpty()) {
                    sql += " AND P.AMOUNT <= " + Double.parseDouble(advancedSearch.getAdvMaxAmount());
                }
                if (advancedSearch.getAdvBeginDataId() != null &&
                        !advancedSearch.getAdvBeginDataId().isEmpty()) {
                    sql += " AND P.PAY_DATE >= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getAdvBeginDataId()).getTime()) + "','yy/mm/dd')";
                }
                if (advancedSearch.getAdvEndDateId() != null &&
                        !advancedSearch.getAdvEndDateId().isEmpty()) {
                    sql += " AND P.PAY_DATE <= TO_DATE('" + new java.sql.Date(df.parse(advancedSearch.getAdvEndDateId()).getTime()) + "','yy/mm/dd')";

                }
                ps = c.prepareStatement(sql + " ORDER BY P.ID ");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Payment payment = new Payment();
                    //payment.setR(rs.getLong("r"));
                    payment.setId(rs.getLong("ID"));
                    Student student = new Student();
                    Teacher teacher = new Teacher();
                    Lesson lesson = new Lesson();
                    student.setId(rs.getLong("STUDENT_ID"));
                    student.setName(rs.getString("STUDENT_NAME"));
                    student.setSurname(rs.getString("STUDENT_SURNAME"));
                    payment.setStudent(student);
                    teacher.setId(rs.getLong("TEACHER_ID"));
                    teacher.setName(rs.getString("TEACHER_NAME"));
                    teacher.setSurname(rs.getString("TEACHER_SURNAME"));
                    payment.setTeacher(teacher);
                    lesson.setId(rs.getLong("LESSON_ID"));
                    lesson.setLessonName(rs.getString("LESSON_NAME"));
                    payment.setLesson(lesson);
                    payment.setAmount(rs.getDouble("AMOUNT"));
                    payment.setPayDate(rs.getDate("PAY_DATE"));
                    paymentList.add(payment);
                }
            } else {
                System.out.println("Connection is null");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }
        return paymentList;
    }

    @Override
    public boolean deletePayment(Long paymentId) throws Exception {
        boolean result = false;
        Connection c = null;
        PreparedStatement ps = null;
        String sorgu = "UPDATE PAYMENT SET ACTIVE = 0 WHERE ID = ?";
        try {
            c = DbHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sorgu);
                ps.setLong(1, paymentId);
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


