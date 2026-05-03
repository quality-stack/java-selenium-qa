package com.rideroundtrip.generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Reporter;
/**
 * Reads typed cell values from an Excel workbook used by the framework's test data layer.
 */
public class excelUtility
{
    /** Absolute or relative path to the workbook file on disk. */
    String filepath;

    /**
     * Creates a reader bound to the supplied workbook path.
     */
    public excelUtility(String filepath)
    {
        this.filepath = filepath;
    }
    /**
     * Reads a single cell value from the requested sheet, row, and column index.
     */
    public String readData(String sheetName, int row, int cell)
    {
        String value = null;
        try (FileInputStream fin = new FileInputStream(filepath);
             Workbook wb = WorkbookFactory.create(fin))
        {
            Sheet sheet = wb.getSheet(sheetName);
            Row selectedRow = sheet == null ? null : sheet.getRow(row);
            Cell cl = selectedRow == null ? null : selectedRow.getCell(cell);
            if (Objects.isNull(cl))
            {
                Reporter.log("Unable to read data from sheet=" + sheetName + ", row=" + row + ", cell=" + cell, true);
                return "";
            }

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
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
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
        catch(EncryptedDocumentException | IOException e)
        {
            throw new IllegalStateException("Unable to read Excel data from " + filepath, e);
        }
        return value == null ? "" : value;

    }

}
