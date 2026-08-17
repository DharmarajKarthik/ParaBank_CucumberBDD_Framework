package utilitity;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataReader {
    public static HashMap<String, String> storeValues = new HashMap();

    public static List<HashMap<String,String>> readData(String filepath, String sheetname) throws IOException {

        List<HashMap<String, String>> mydata = new ArrayList<>();

        FileInputStream file = new FileInputStream(filepath);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetname);

        int totalrows =  sheet.getLastRowNum();

        XSSFRow headerRow = sheet.getRow(0);

        for(int i = 1; i <= totalrows; i++){
            XSSFRow row = sheet.getRow(i);

            HashMap<String, String> currentHash = new HashMap<>();

            for(int j = 0; j < row.getLastCellNum(); j++){
                //XSSFCell cell = row.getCell(j);
                XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                currentHash.put(
                        headerRow.getCell(j).toString(),
                        cell.toString()
                );
            }
        mydata.add(currentHash);
        }
        file.close();
        return mydata;
    }
}
