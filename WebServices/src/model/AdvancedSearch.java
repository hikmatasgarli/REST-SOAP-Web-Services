package model;

public class AdvancedSearch {
    private Long   advLessonCbmId;
    private Long   advTeacherCmbId;
    private String advMaxAmount;
    private String advMinAmount;
    private String advBeginDataId;
    private String advEndDateId;

    public Long getAdvLessonCbmId() {
        return advLessonCbmId;
    }

    public void setAdvLessonCbmId(Long advLessonCbmId) {
        this.advLessonCbmId = advLessonCbmId;
    }

    public Long getAdvTeacherCmbId() {
        return advTeacherCmbId;
    }

    public void setAdvTeacherCmbId(Long advTeacherCmbId) {
        this.advTeacherCmbId = advTeacherCmbId;
    }

    public String getAdvMaxAmount() {
        return advMaxAmount;
    }

    public void setAdvMaxAmount(String advMaxAmount) {
        this.advMaxAmount = advMaxAmount;
    }

    public String getAdvMinAmount() {
        return advMinAmount;
    }

    public void setAdvMinAmount(String advMinAmount) {
        this.advMinAmount = advMinAmount;
    }

    public String getAdvBeginDataId() {
        return advBeginDataId;
    }

    public void setAdvBeginDataId(String advBeginDataId) {
        this.advBeginDataId = advBeginDataId;
    }

    public String getAdvEndDateId() {
        return advEndDateId;
    }

    public void setAdvEndDateId(String advEndDateId) {
        this.advEndDateId = advEndDateId;
    }

    @Override
    public String toString() {
        return "AdvancedSearch{" +
                "advLessonCbmId=" + advLessonCbmId +
                ", advTeacherCmbId=" + advTeacherCmbId +
                ", advMaxAmount='" + advMaxAmount + '\'' +
                ", advMinAmount='" + advMinAmount + '\'' +
                ", advBeginDataId='" + advBeginDataId + '\'' +
                ", advEndDateId='" + advEndDateId + '\'' +
                '}';
    }
}
