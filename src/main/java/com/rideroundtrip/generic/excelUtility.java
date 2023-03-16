package com.rideroundtrip.generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;
/**
 * 
 * @author sagar
 * created on 07/07/2019 at 12:10pm
 */
public class excelUtility
{
	String filepath;
	
	public excelUtility(String filepath)
	{
		this.filepath = filepath;
	}
	/**
	 * @author sagar
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 */
	public String readData(String sheetName, int row, int cell)
	{
		String value = null;
		try 
		{
			FileInputStream fin = new FileInputStream(filepath);
			Workbook wb = WorkbookFactory.create(fin);
			Cell cl = wb.getSheet(sheetName).getRow(row).getCell(cell);
			
			switch(cl.getCellType())
			{
				case STRING:
				{
					value = cl.getStringCellValue();
					break;
				}
				case NUMERIC:
				{
					if(DateUtil.isCellDateFormatted(cl)) 
					{
						SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
						value = sdf.format(cl.getDateCellValue());
					}
					else 
					{
						long longvalue = (long)cl.getNumericCellValue();
						value = Long.toString(longvalue);
//						value = String.valueOf(longvalue);
					}
					break;
				}
				case BOOLEAN:
				{
					value = Boolean.toString(cl.getBooleanCellValue());
					break;
				}
				default:
				{
					Reporter.log("Cell format is not matching");
				}
		}
	}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(EncryptedDocumentException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return value;
		
	}

}
