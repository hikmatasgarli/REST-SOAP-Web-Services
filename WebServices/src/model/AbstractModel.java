package model;
// Abstract modelde oxshar olan melumatlar saxlanir

import java.util.Date;

public abstract class AbstractModel {
    private Long id;
    private Date dataDate;
    private Integer active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "AbstractModel{" +
                "id=" + id +
                ", dataDate=" + dataDate +
                ", active=" + active +
                '}';
    }
}
