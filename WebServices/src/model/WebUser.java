package model;

import java.util.Date;

public class WebUser {
    private Long idWebUser;
    private String username;
    private String password;
    private String companyName;
    private String token;
    private Date dataDate;
    private Integer active;

    public Long getIdWebUser() {
        return idWebUser;
    }

    public void setIdWebUser(Long idWebUser) {
        this.idWebUser = idWebUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "WebUser{" +
                "idWebUser=" + idWebUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", companyName='" + companyName + '\'' +
                ", token='" + token + '\'' +
                ", dataDate=" + dataDate +
                ", active=" + active +
                '}';
    }
}
