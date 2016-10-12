package gt.edu.altamira.sam.dataload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import gt.edu.altamira.sam.tools.ExcelReader;
import gt.edu.altamira.sam.tools.MapedRecord;





public class ExcelDataLoader implements FileDataLoader {

	private static final Logger log = Logger.getLogger(ExcelDataLoader.class);

	public ExcelDataLoader() {

	}

	@Override
	public void loadData(File file) throws Exception {
		
	}

//	@Override
	public void loadData(byte[] bytes) throws Throwable {
		log.info("Loading data from file :");
		ExcelReader lector = createLector(bytes);
		lector.setRowIndex(0);

		try {

			while (!lector.lastRow()) {
				Object[] values = lector.nextRow();
				MapedRecord record = new MapedRecord(values,null);

				if (record.isA(MapedRecord.XLS_SOURCE)) {

				}
			}

		} catch (Exception e) {

			log.error("Error - " + e);
			throw e;
		} finally {

		}

	}

	public ExcelReader createLector(File file) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		try {
			
			HSSFWorkbook excel = new HSSFWorkbook(fis);
			return new ExcelReader(excel);
		} finally {
			fis.close();
		}
	}
	
	public ExcelReader createLector(byte[] bytes) throws Exception {

		return new ExcelReader(bytes);

	}

	@Override
	public void loadData(InputStream file) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
