import java.util.List;

public class Metadata {
     String name;
     List Value;
     String Description;
     String Unit;
     Boolean Delete_for_anon;

    public Metadata(String name, List value, String description, String unit, Boolean delete_for_anon) {
        this.name = name;
        Value = value;
        Description = description;
        Unit = unit;
        Delete_for_anon = delete_for_anon;
    }

    public Metadata(List value, String description, String unit, Boolean delete_for_anon) {
        Value = value;
        Description = description;
        Unit = unit;
        Delete_for_anon = delete_for_anon;
    }

    public Metadata( List value) {
        Value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(List value) {
        Value = value;
    }

    public List getValue() {
        return Value;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public Boolean getDelete_for_anon() {
        return Delete_for_anon;
    }

    public void setDelete_for_anon(Boolean delete_for_anon) {
        Delete_for_anon = delete_for_anon;
    }

}
