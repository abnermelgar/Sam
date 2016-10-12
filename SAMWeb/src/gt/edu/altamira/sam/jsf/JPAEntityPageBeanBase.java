package gt.edu.altamira.sam.jsf;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import gt.edu.altamira.ejb.LogManagerLocal;
import gt.edu.altamira.ejb.MailManagerLocal;
import gt.edu.altamira.ejb.ReportManagerLocal;
import gt.edu.altamira.ejb.UtilsManagerLocal;
import gt.edu.altamira.jsf.JPAEntityPageBean;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.model.ReportFile;



/**
 * 
 * @author amelgar
 * 
 * @param <E>
 */
public class JPAEntityPageBeanBase<E> extends JPAEntityPageBean<E> {
	private static final long serialVersionUID = 2008934723958216262L;

	private static Logger logger = Logger
			.getLogger(JPAEntityPageBeanBase.class);

	public JPAEntityPageBeanBase() {
		persistedMsg = "{0} guardado.";
		updatedMsg = "{0} actualizado.";
		deletedMsg = "{0} borrado.";
	}

	// private static Logger logger = Logger.getLogger(MenuBean.class);

	protected SimpleDateFormat sdf = new SimpleDateFormat(format);
	protected SimpleDateFormat sdf2 = new SimpleDateFormat(format2);

	protected SimpleDateFormat sdfFull = new SimpleDateFormat(fullFormat);

	protected SimpleDateFormat sdfReport = new SimpleDateFormat(reportFormat);

	protected SimpleDateFormat sdfCalendar = new SimpleDateFormat(
			formatCalendar);

	protected static final long UPDATE_EVENT_ID = 20;
	protected static final long INSERT_EVENT_ID = 10;
	protected static final long STATUS_CONFIRM = 41L;
	protected static final long STATUS_DENIED = 21L;
	protected static final long STATUS_CANCEL = 5L;
	protected static final long STATUS_NOT_ASSIST = 3L;
	protected static final long STATUS_NOT_ENTRY = 51L;
	protected static final long STATUS_CLOSE = 4L;
	protected static final long STATUS_LATE = 7L;
	protected static final long ID_CF_COMPANY = 1L;
	protected static final long ACTIVE_STATUS = 1;
	protected static final String REPORT_EXT = "pdf";
	public static final long DELETE_STATUS = 9l;
	public static final long INITIAL_ESTATUS = 4l;
	public static final long COMPLETE_ESTATUS = 5l;

	// protected Object oldObject;
	protected String oldObjectJason;
	protected long newObjectId;

	protected String tableHeader;

	@PersistenceContext(unitName = "SAMSchema")
	protected EntityManager entityManager;

	// @EJB
	// protected UserManagerLocal userManager;

	

	@EJB
	protected ReportManagerLocal report;

	@EJB
	protected LogManagerLocal logDB;

	@EJB
	protected MailManagerLocal mailManager;
	
	@EJB
	protected UtilsManagerLocal utilsManager;

	protected void clear() {

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	protected ReportFile generateReport(long idReport, Map<String, String> map)
			throws Exception {
		return report.getReport(idReport, map);
	}

	@Override
	protected void afterSave(EntityManager em) {

		try {

			logDB.log(user.getUserAlias(), isNewEntity(), logDB
					.objectToJason(entity), oldObjectJason, entity.getClass()
					.getSimpleName(), userLogin, getId());

		} catch (Exception e) {
			logger.error("Error en afterSave : ", e);
		}

		super.afterSave(em);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTableHeader() {
		return tableHeader;
	}

	public void setTableHeader(String tableHeader) {
		this.tableHeader = tableHeader;
	}

}
