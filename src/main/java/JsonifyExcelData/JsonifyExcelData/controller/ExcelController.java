package JsonifyExcelData.JsonifyExcelData.controller;
import JsonifyExcelData.JsonifyExcelData.service.ExcelToJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelToJsonService excelToJsonService;

    @Autowired
    public ExcelController(ExcelToJsonService excelToJsonService) {
        this.excelToJsonService = excelToJsonService;
    }
    @PostMapping("/upload")
    public List<String> uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        return excelToJsonService.parseExcelToJSONAndSaveToDatabase(file);
    }
}
