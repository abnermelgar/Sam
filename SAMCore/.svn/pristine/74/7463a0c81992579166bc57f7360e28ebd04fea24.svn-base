package gt.edu.altamira.ejb;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

public class EjbManager {

	@PersistenceContext(unitName = "SAMSchema")
	protected EntityManager entityManager;

	protected static final long ACTIVE_STATUS = 1;
	protected static final long INACTIVE_STATUS = 2l;

	protected String format = "dd/MM/yyyy";
	protected String fullFormat = "dd/MM/yyyy hh:mm:ss";
	protected String formatCalendar = "yyyy-MM-dd";
	protected static final String SPLITTER = ";";

	protected SimpleDateFormat sdf = new SimpleDateFormat(format);

	protected SimpleDateFormat sdfFull = new SimpleDateFormat(fullFormat);

	protected SimpleDateFormat sdfCalendar = new SimpleDateFormat(
			formatCalendar);

	@Resource(mappedName = "jdbc/connSAM")
	// @Resource(mappedName = "jdbc/connUfit")
	// @Resource(mappedName = "jdbc/produccion")
	protected DataSource dbSAM;

	public boolean isEmpty(String text) {
		return text == null || text.trim().equals("");
	}

}
