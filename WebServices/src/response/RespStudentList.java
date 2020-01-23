package response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RespStudentList {

    private List<RespStudent> studentList;
    private RespStatus status;

    public List<RespStudent> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<RespStudent> studentList) {
        this.studentList = studentList;
    }

    public RespStatus getStatus() {
        return status;
    }

    public void setStatus(RespStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RespStudentList{" +
                "studentList=" + studentList +
                ", status=" + status +
                '}';
    }
}
