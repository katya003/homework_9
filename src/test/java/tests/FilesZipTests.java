package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FilesZipTests {
    private ClassLoader cl = JsonTests.class.getClassLoader();
    PDF pdfFile = null;
    XLS excelFile = null;
    CSVReader csvFile = null;

    @Test
    void pdfCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    pdfFile = new PDF(zipFile);
                    break;
                }
            }

            assertThat(pdfFile.numberOfPages).isEqualTo(5);
        }
    }

    @Test
    void csvCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    csvFile = new CSVReader(new InputStreamReader(zipFile));
                    break;
                }
            }

            List<String[]> csvData = csvFile.readAll();

            assertThat(csvData.get(0)).contains("роза");
            assertThat(csvData.get(1)).contains("ромашка");
        }
    }

    @Test
    void xlsCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("files.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null) {
                if (entry.getName().equals("test.xls")) {
                    excelFile = new XLS(zipFile);
                    break;
                }
            }

            String firstActualValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            assertThat(firstActualValue).contains("двери");
            String secondActualValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
            assertThat(secondActualValue).contains("стулья");
        }
    }
}
