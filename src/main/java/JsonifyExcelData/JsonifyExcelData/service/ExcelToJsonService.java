package JsonifyExcelData.JsonifyExcelData.service;
import JsonifyExcelData.JsonifyExcelData.entity.JsonDataEntity;
import JsonifyExcelData.JsonifyExcelData.repository.JsonDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
public class ExcelToJsonService {

    private final JsonDataRepository jsonDataRepository;

    @Autowired
    public ExcelToJsonService(JsonDataRepository jsonDataRepository) {
        this.jsonDataRepository = jsonDataRepository;
    }

    public List<String> parseExcelToJSONAndSaveToDatabase(MultipartFile file) throws IOException {
        List<String> jsonData = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                // create json data
                Map<String, Object> data = new HashMap<>();
                for (Cell cell : row) {
                    String cellValue = getCellValueAsString(cell);
                    data.put("column_" + cell.getColumnIndex(), cellValue);
                }

                // insert json data
                String jsonValue = new ObjectMapper().writeValueAsString(data);

                JsonDataEntity entity = new JsonDataEntity(jsonValue);
                entity.setJsonData(jsonValue);
                jsonDataRepository.save(entity);

                jsonData.add(jsonValue);
            }
        }
        return jsonData;
    }
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return "";
        }
    }
}
