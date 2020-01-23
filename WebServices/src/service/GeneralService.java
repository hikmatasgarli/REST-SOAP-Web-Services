package service;


import request.ReqStudent;
import request.ReqStudentId;
import request.ReqToken;
import request.ReqUser;
import response.RespStatus;
import response.RespStudent;
import response.RespStudentList;
import response.RespUser;



public interface GeneralService {

    RespStudentList getStudentList(String token);

    RespStudent getStudentById(Long studentId);

    RespStatus addStudent(ReqStudent reqStudent);

    RespStatus updateStudent(ReqStudent reqStudent);

    RespStatus deleteStudent(ReqStudentId reqStudentId);

    RespUser login(ReqUser reqUser);

    RespStatus logout(ReqToken reqToken);



}
