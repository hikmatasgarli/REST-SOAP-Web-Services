package service;

import business.StudentService;
import business.StudentServiceImpl;
import dao.StudentDao;
import dao.StudentDaoImpl;
import model.Student;
import model.WebUser;
import request.ReqStudent;
import request.ReqStudentId;
import request.ReqToken;
import request.ReqUser;
import response.RespStatus;
import response.RespStudent;
import response.RespStudentList;
import response.RespUser;
import util.ExceptionConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GeneralServiceImpl implements GeneralService {
    StudentDao studentDao = new StudentDaoImpl();
    StudentService studentService = new StudentServiceImpl(studentDao);
    DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    public RespStudentList getStudentList(String token) {
        RespStudentList response = new RespStudentList();
        List<RespStudent> responseList = new ArrayList<>();

        try {
            if (token == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data"));
                return response;
            }
            boolean isCheck = studentService.checkToken(token);
            if (!isCheck) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid Token!"));
                return response;
            }
            List<Student> studentList = studentService.getStudentList();
            for (Student student : studentList) {
                RespStudent respStudent = new RespStudent();
                respStudent.setId(student.getId());
                respStudent.setName(student.getName());
                respStudent.setSurname(student.getSurname());
                respStudent.setAddress(student.getAddress());
                if (student.getDob() != null)
                    respStudent.setDob(date.format(student.getDob()));

                responseList.add(respStudent);
            }
            response.setStudentList(responseList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;

    }

    @Override
    public RespStudent getStudentById(Long studentId) {
        RespStudent response = new RespStudent();
        try {
            if (studentId == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data"));
                return response;
            }
            Student student = studentService.getStudentById(studentId);
            if (student == null) {
                response.setStatus(new RespStatus(ExceptionConstants.DATA_NOT_FOUND, "Data Not Found"));
                return response;
            }
            response.setName(student.getName());
            response.setSurname(student.getSurname());
            response.setAddress(student.getAddress());
            response.setDob(date.format(student.getDob()));
            response.setStatus(RespStatus.getSuccessMessage());

        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }

        return response;
    }

    @Override
    public RespStatus addStudent(ReqStudent reqStudent) {
        RespStatus response = null;
        if (reqStudent.getName() == null || reqStudent.getSurname() == null || reqStudent.getDob() == null || reqStudent.getToken() == null) {
            return new RespStatus(ExceptionConstants.DATA_NOT_FOUND, "Data Not Found");
        }
        try {
            boolean isCheck = studentService.checkToken(reqStudent.getToken());
            if (!isCheck) {
                response = new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid Token!");
                return response;
            }

            Student student = new Student();
            student.setName(reqStudent.getName());
            student.setSurname(reqStudent.getSurname());
            student.setAddress(reqStudent.getAddress());
            student.setDob(reqStudent.getDob());
            boolean isAdded = studentService.addStudent(student);
            if (!isAdded) {
                return new RespStatus(ExceptionConstants.ADD_STUDENT_FAIL, "ADD Student Fail");
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");

            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus updateStudent(ReqStudent reqStudent) {
        RespStatus response = null;
        if (reqStudent.getName() == null || reqStudent.getSurname() == null || reqStudent.getDob() == null) {
            return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request");
        }
        try {
            boolean isCheck = studentService.checkToken(reqStudent.getToken());
            if (!isCheck) {
                response = new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid Token!");
                return response;
            }
            Student student = new Student();
            student.setName(reqStudent.getName());
            student.setSurname(reqStudent.getSurname());
            student.setAddress(reqStudent.getAddress());
            student.setDob(reqStudent.getDob());
            boolean isUpdated = studentService.updateStudent(student, reqStudent.getStudentId());
            if (!isUpdated) {
                return new RespStatus(ExceptionConstants.UPDATE_STUDENT_FAIL, "Update Student Fail");
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");

            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus deleteStudent(ReqStudentId reqStudentId) {
        RespStatus response = null;
        if (reqStudentId.getStudentId() == null || reqStudentId.getToken() == null) {
            return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request");
        }
        try {
            boolean isCheck = studentService.checkToken(reqStudentId.getToken());
            if (!isCheck) {
                response = new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid Token!");
                return response;
            }
            boolean isDelete = studentService.deleteStudent(reqStudentId.getStudentId());
            if (!isDelete) {
                return new RespStatus(ExceptionConstants.DELETE_STUDENT_FAIL, "Delete Student Fail");
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");

            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespUser login(ReqUser reqUser) {
        RespUser response = new RespUser();
        try {
            if (reqUser.getUsername() == null || reqUser.getPassword() == null) {
                response.setStatus(new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request"));
                return response;
            }
            WebUser webUser = studentService.login(reqUser.getUsername(), reqUser.getPassword());
            if (webUser == null) {
                response.setStatus(new RespStatus(ExceptionConstants.USERNAME_OR_PASSWORD_IS_INVALID, "User or password is invalid!"));
                return response;
            }
            if (webUser.getToken() != null) {
                response.setStatus(new RespStatus(ExceptionConstants.SESSION_IS_ALREADY_EXIST, "Session is already exist!"));
                return response;
            }
            String token = UUID.randomUUID().toString();
            boolean isUpdate = studentService.updateToken(token, webUser.getIdWebUser());
            if (!isUpdate) {
                response.setStatus(new RespStatus(ExceptionConstants.LOGIN_FAIL, "Login failed!"));
                return response;
            }
            response.setUserId(webUser.getIdWebUser());
            response.setUsername(webUser.getUsername());
            response.setCompanyName(webUser.getCompanyName());
            response.setToken(token);
            response.setStatus(RespStatus.getSuccessMessage());


        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public RespStatus logout(ReqToken reqToken) {
        RespStatus response = null;
        if (reqToken == null) {
            return new RespStatus(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
        }
        try {
            boolean isCheck = studentService.checkToken(reqToken.getToken());
            if (!isCheck) {
                response = new RespStatus(ExceptionConstants.INVALID_TOKEN, "Invalid Token!");
                return response;
            }
            boolean isUpdate = studentService.updateTokenWithToken(reqToken.getToken());
            if(!isUpdate) {
                response = new RespStatus(ExceptionConstants.LOGOUT_IS_FAILED, "LogOut is Failed!");
                return response;
            }
            response = RespStatus.getSuccessMessage();
        } catch (Exception ex) {
            response = new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception");
            ex.printStackTrace();
        }

        return response;
    }

}
