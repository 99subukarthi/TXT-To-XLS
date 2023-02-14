package com.writetoxls;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TextToExcel {
    
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Acer\\Desktop\\karthi\\text1.txt"; // Path to text file
        String xlsFilePath = "C:\\Users\\Acer\\Desktop\\karthi\\text.xls"; // Path to xls file
        List<List<String>> data = readDataFromFile(filePath); // Read data from text file
        writeDataToXlsFile(xlsFilePath, data); // Write data to xls file
    }
    
    public static List<List<String>> readDataFromFile(String filePath) {
        List<List<String>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowValues = line.split(","); // Assuming data is comma-separated
                data.add(Arrays.asList(rowValues));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static void writeDataToXlsFile(String filePath, List<List<String>> data) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int rowNum = 0;
        for (List<String> rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (String cellData : rowData) {
                Cell cell = row.createCell(cellNum++);
                cell.setCellValue(cellData);
            }
        }
        try {
        	File f2=new File("C:\\Users\\Acer\\Desktop\\karthi\\test.xls");
        	FileOutputStream fos=new FileOutputStream(f2);
            workbook.write(fos);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
