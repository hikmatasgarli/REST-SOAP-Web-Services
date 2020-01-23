package request;

public class ReqStudentId {
    private String token;
    private Long studentId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "ReqStudentId{" +
                "token='" + token + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}
