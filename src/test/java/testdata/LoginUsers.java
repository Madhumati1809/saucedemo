package testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import keys.StaticKeys;

public class LoginUsers {

	@DataProvider(name="userLoginCredentials")
	public static String[][] xclDataProvider() throws EncryptedDocumentException, IOException {

		File f = new File(StaticKeys.USER_LOGIN_DATA);
		FileInputStream fis = new FileInputStream(f);
		Workbook wbk = WorkbookFactory.create(fis);
		Sheet sheet = wbk.getSheet(StaticKeys.SAUCEDEMO);
		int totalRows = sheet.getLastRowNum();
		Row row = sheet.getRow(0);
		int totalCells = row.getLastCellNum();

		DataFormatter format = new DataFormatter();
		String[][] data = new String[totalRows][totalCells];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCells; j++) {
				data[i - 1][j] = format.formatCellValue(sheet.getRow(i).getCell(j));
			}
		}
		return data;
	}

}