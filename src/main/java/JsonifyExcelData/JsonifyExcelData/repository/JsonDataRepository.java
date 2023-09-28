package JsonifyExcelData.JsonifyExcelData.repository;


import JsonifyExcelData.JsonifyExcelData.entity.JsonDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JsonDataRepository extends JpaRepository<JsonDataEntity,Long> {

}
