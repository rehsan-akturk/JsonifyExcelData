package JsonifyExcelData.JsonifyExcelData.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "json_data")
public class JsonDataEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "json_data", columnDefinition = "jsonb")
    private String jsonData;

    public JsonDataEntity(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
