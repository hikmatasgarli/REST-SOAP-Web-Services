package webservices;

import request.ReqStudent;
import request.ReqToken;
import request.ReqUser;
import response.RespStatus;
import response.RespStudent;
import response.RespStudentList;
import response.RespUser;
import service.GeneralService;
import service.GeneralServiceImpl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService(serviceName = "SoapWebService")
public class SoapWebService {
    GeneralService generalService = new GeneralServiceImpl();

    @WebMethod(operationName = "loginUser")
    public RespUser userLogin(@WebParam(name = "reqUser") ReqUser reqUser) {
        return generalService.login(reqUser);
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello";
    }

    @WebMethod(operationName = "getStudentList")
    public RespStudentList getStudentList(@WebParam(name = "token") String token) {
        return generalService.getStudentList(token);
    }

    @WebMethod(operationName = "getStudentById")
    public RespStudent getStudentById(@WebParam(name = "studentId") Long studentId) {
        return generalService.getStudentById(studentId);
    }

    @WebMethod(operationName = "addStudent")
    public RespStatus addStudent(@WebParam(name = "reqStudent") ReqStudent reqStudent) {
        return generalService.addStudent(reqStudent);
    }

    @WebMethod(operationName = "logout")
    public RespStatus logout(@WebParam(name = "reqLogout") ReqToken reqToken) {
        return generalService.logout(reqToken);
    }

}