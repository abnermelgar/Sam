package gt.edu.altamira.sam.tools;

import java.util.Map;

import org.apache.log4j.Logger;

import gt.edu.altamira.sam.dataload.ExcelDataLoader;



/**
 * 
 * @author amelgar
 * 
 */
public class MapedRecord {
	private static final Logger log = Logger.getLogger(ExcelDataLoader.class);
	public static final String XLS_SOURCE = "xls";

	// Mapeo de valores

	private Object[] values;
	private String type;
	private Map<String, Integer> map;

	/**
	 * 
	 * @param Arrays
	 *            of Objects read from row
	 * @param map
	 *            positions do extracted
	 */
	public MapedRecord(Object[] values, Map<String, Integer> map) {
		this.values = values;

		this.map = map;

	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean isA(String type) {
		return this.type.equalsIgnoreCase(type);
	}

	/**
	 * 
	 * @return
	 */
	private Object[] getValues() {
		return values;
	}

	/**
	 * 
	 * @return
	 */
	private Map<String, Integer> getMap() {
		return map;
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getValue(String key) {
		Object value = null;

		try {
			value = getValues()[getMap().get(key)];

		} catch (Exception e) {
			log.error("", e);
		}

		return value;
	}

}
