package webservices;

import request.ReqStudent;
import request.ReqStudentId;
import request.ReqToken;
import request.ReqUser;
import response.RespStatus;
import response.RespStudent;
import response.RespStudentList;
import response.RespUser;
import service.GeneralService;
import service.GeneralServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/webservices")
public class WebServices {

    GeneralService generalService = new GeneralServiceImpl();

    @GET
    @Path("/test")
    @Produces("text/html")
    public String test() {
        return "<html><body><h1>Hello Mamed!</h1></body></html>";
    }

    @GET
    @Path("/getStudentList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStudentList getStudentList(@QueryParam("token") String token) {
        return generalService.getStudentList(token);
    }

    @GET
    @Path("/getStudentById/{studentId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStudent getStudentById(@PathParam("studentId") Long studentId) {  //QueryParam ve PathParam
        return generalService.getStudentById(studentId);
    }

    @POST
    @Path("/addStudent")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus addStudent(ReqStudent reqStudent) {

        return generalService.addStudent(reqStudent);
    }

    @POST
    @Path("/updateStudent")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus updateStudent(ReqStudent reqStudent) {

        return generalService.updateStudent(reqStudent);
    }

    @PUT
    @Path("/deleteStudent")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus deleteStudent(ReqStudentId reqStudentId) {
        return generalService.deleteStudent(reqStudentId);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespUser login(ReqUser reqUser) {
        return generalService.login(reqUser);
    }

    @POST
    @Path("/logout")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public RespStatus logout(ReqToken token) {
        return generalService.logout(token);
    }

}
