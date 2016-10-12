package gt.edu.altamira.sam.tools;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * Class used for reading a excel file
 * 
 * 
 */
public class ExcelReader {
	private static Logger log = Logger.getLogger(ExcelReader.class);
	private Object emptyValueCell;

	// Constructor
	/**
	 * <p>
	 * Create a reader and excel Sets are based on file entered
	 * </p>
	 * 
	 * @param fileName
	 *            , The name of the file to load
	 */
	public ExcelReader(String fileName) throws IOException {
		this(excelFromFilePath(fileName));
	}

	/**
	 * <p>
	 * Create a reader and sets the byte admitted excel
	 * </p>
	 * 
	 * @param bytes
	 *            , The bytes that will create the excel
	 * @throws IOException
	 */
	public ExcelReader(byte[] bytes) throws IOException {
		this(excelFromBytes(bytes));
	}

	/**
	 * <p>
	 * Create a reader based on the HSSFWorkbook entered with leaf 0 and zero in
	 *   * empty values
	 * </p>
	 * 
	 * @param excel
	 *            , The HSSFWorkbook of the reader to create
	 * 
	 */
	public ExcelReader(HSSFWorkbook excel) {
		this(excel, 0, null);
	}

	/**
	 * <p>
	 * Create a reader based on the HSSFWorkbook entered the leaf expesificada
	 * </p>
	 * 
	 * @param excel
	 *            , The HSSFWorkbook of the reader to create
	 * @param indexHoja
	 *            , The number of sheet refresh
	 * @param emptyValue
	 *            , The value to be placed in the cells when they are empty
	 */
	public ExcelReader(HSSFWorkbook book, int indexHoja, Object emptyValue) {
		this.book = book;
		this.sheet = book.getSheetAt(indexHoja);
		this.rowIndex = 0;
		this.emptyValueCell = emptyValue;
	}

	// Atributos's
	private HSSFSheet sheet;
	private HSSFWorkbook book;
	private int rowIndex;
	private Integer readingLimit;

	// Getter's && Setter's

	public HSSFSheet getSheet() {
		return sheet;
	}

	public HSSFWorkbook getBook() {
		return book;
	}

	/**
	 * <p>
	 * Gets the names of the columns in the worksheet how 'Column [Letter] '
	 * </p>
	 * 
	 * @param index
	 *            , Row index of the columns are counted
	 * @return List of String, de columnas en la hoja
	 */
	public List<String> getSheetColumns(int index) {
		HSSFRow fila0 = getSheet().getRow(index);
		int columnaIni = 0;
		int columnaFin = 0;
		List<String> listaCols = new ArrayList<String>();

		if (fila0 != null) {
			columnaIni = fila0.getFirstCellNum();
			columnaFin = fila0.getLastCellNum();
		} else
			return listaCols;

		for (int i = columnaIni; i < columnaFin; i++) {
			char id = (char) (65 + i);
			listaCols.add("Columna " + id);
		}
		return listaCols;
	}

	/**
	 * <p>
	 * Returns the index of the row to look for the method nextRow(), based on
	 * value 0
	 * </p>
	 * 
	 * @return the index of the current row
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * <p>
	 * Sets the value of the index of the row to get to the method nextRow()
	 * value based on 0
	 * </p>
	 * 
	 * @Param row Index to place
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * <p>
	 * Returns the row number will be read until the method nextRow(), based on
	 * value 0
	 * </p>
	 * 
	 * @Return The row number in which the reader will stop
	 */

	public Integer getReadingLimit() {
		return readingLimit;
	}

	/**
	 * <p>
	 * Sets the row number will be read until the method siguienteFila () Based
	 * on value 0
	 * </p>
	 * 
	 * @Param readingLimit The number of row that will stop the reader
	 */
	public void setReadingLimit(Integer readingLimit) {
		this.readingLimit = readingLimit;
	}

	// Metodos

	/**
	 * <p>
	 * Gets the values of a row based on value 0
	 * </p>
	 * 
	 * @Param rowNumber Number of the row values will be obtained
	 * @Return an array of objects with the values of the row
	 */
	public Object[] getRowValues(int rowNumber) {
		HSSFRow row = sheet.getRow(rowNumber);
		Object[] rowObjects = new Object[getSheetColumns(rowNumber).size()];

		for (int i = 0; i < getSheetColumns(rowNumber).size(); i++)
			rowObjects[i] = getCellObject(row.getCell(i));

		return rowObjects;
	}

	/**
	 * <p>
	 * Returns the values of a row and moves the index to the next row
	 * </p>
	 * 
	 * @Return The values in the current row
	 * @Throws IndexOutOfBoundsException
	 */
	public Object[] nextRow() throws IndexOutOfBoundsException {
		Object[] rowObjects = getRowValues(rowIndex);
		setRowIndex(getRowIndex() + 1);
		return rowObjects;
	}

	/**
	 * <p>
	 * Check if the index is in the last row or if already reached the Reading
	 * limit if it was specified
	 * </p>
	 * 
	 * @Return true if I already reached the last row
	 */
	public boolean lastRow() {

		if (rowIndex > sheet.getLastRowNum())
			return true;
		if (getReadingLimit() != null) {
			if (rowIndex > getReadingLimit().intValue())
				return true;
		}
		return false;
	}

	/**
	 * Gets an object as the value of a cell
	 * 
	 * @Param cell The cell to which the object will be obtained
	 * @Return The value of the cell
	 */
	private Object getCellObject(HSSFCell cell) {
		if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
			// log.debug("Celda vacia");
			return emptyValueCell;
		}

		int tipo = cell.getCellType();

		switch (tipo) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return Integer.valueOf(Double.valueOf(
						cell.getNumericCellValue()).intValue());
			}
		case HSSFCell.CELL_TYPE_STRING:
			return cell.getRichStringCellValue().getString().trim();
		}
		log.warn("Cell data type not supported" + cell.getCellType());
		return null;
	}

	/**
	 * Creates an HSSFWorkbook from a file
	 * 
	 * @Param filePath
	 * @Return The file HSSFWorkbook
	 * @Throws IOException Can not find file
	 */
	public static HSSFWorkbook excelFromFilePath(String filePath)
			throws IOException {
		HSSFWorkbook wb = null;
		FileInputStream s = new FileInputStream(filePath);
		try {
			wb = new HSSFWorkbook(s);
		} finally {
			s.close();
			s = null;
		}
		return wb;
	}

	/**
	 * Creates an HSSFWorkbook based on bytes admitted
	 * 
	 * @Param bytes The bytes which created the HSSFWorkbook
	 * @Return The created HSSFWorkbook
	 * @Throws IOException
	 */
	public static HSSFWorkbook excelFromBytes(byte[] bytes) throws IOException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		return new HSSFWorkbook(byteStream);
	}
}
