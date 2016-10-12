package gt.edu.altamira.jsf;

import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;
import gt.edu.altamira.sam.tools.ExcelReader;




/**
 * 
 * @author amelgar
 * @version 1
 * 
 */
public class PageBean implements Serializable, Cloneable {
	private static final long serialVersionUID = 4280837610281659530L;
	private static Logger log = Logger.getLogger(PageBean.class);
	public static final String LOGGED_USER = "LoggedUser";
	public static final String LOGGED_USER_COUNTRY = "loggedUserCountry";
	public static final String LOGGED_ID = "LoggedId";
	protected String format = "dd/MM/yyyy";
	protected String format2 = "dd/MM/yyyy HH:mm:ss";
	protected String reportFormat = "ddMMyyyy";
	protected String fullFormat = "dd/MM/yyyy hh:mm:ss";
	protected String formatCalendar = "yyyy-MM-dd HH:mm:ss";
	public static final long FILE_TYPE_USER_PHOTOGRAFY = 1;
	public static final long FILE_TYPE_USER_SING_PHOTOGRAFY = 2;
	public static final long FILE_TYPE_PATIENT_PHOTOGRAFY = 4;
	public static final long FILE_TYPE_PATIENT_DOCUMENT = 3;
	private double random = 0;

	private long fileTypeSing = FILE_TYPE_USER_SING_PHOTOGRAFY;

	private long fileTypePhoto = FILE_TYPE_USER_PHOTOGRAFY;

	private long filePatientPhoto = FILE_TYPE_PATIENT_PHOTOGRAFY;

	protected User user = (User) getSessionScope().get(LOGGED_USER);

	protected UserLogin userLogin = (UserLogin) getSessionScope()
			.get(LOGGED_ID);

	/**
	 * Indica el tamanio que se mostrara en los jsf de los comandos utilizados
	 * 
	 * @return
	 */
	public int getLengthCommand() {
		return 50;
	}

	/**
	 * <p>
	 * Get concurrent Faces Context
	 * 
	 * @return FacesContext
	 */
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	public Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}

	public Map<String, String> getRequestParam() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
	}

	public Map<String, Object> getRequestScope() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap();
	}

	public Map<String, Object> getSessionScope() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getSessionMap();
	}

	public Map<String, Object> getApplicationScope() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap();
	}

	public Object getValue(String expression) {
		return getApplication().evaluateExpressionGet(getFacesContext(),
				expression, Object.class);
	}

	public Object getSessionValue(String value) {
		return getSessionScope().get(value);
	}

	protected void addUniqueMessage(String summary,
			FacesMessage.Severity severity) {
		for (Iterator<FacesMessage> i = getFacesContext().getMessages(); i
				.hasNext();) {
			FacesMessage m = i.next();
			String s = m.getSummary();
			if (s != null && s.equals(summary) && m.getSeverity() == severity)
				return;
		}
		getFacesContext().addMessage(null,
				new FacesMessage(severity, summary, null));
	}

	protected boolean hasMessages(FacesMessage.Severity severity) {
		for (Iterator<FacesMessage> i = getFacesContext().getMessages(); i
				.hasNext();) {
			FacesMessage m = i.next();
			if (m.getSeverity() == severity)
				return true;
		}
		return false;
	}

	public boolean isErrorMessages() {
		return hasMessages(FacesMessage.SEVERITY_ERROR);
	}

	public boolean isWarningMessages() {
		return hasMessages(FacesMessage.SEVERITY_WARN);
	}

	public boolean isErrorOrWarningMessages() {
		return hasMessages(FacesMessage.SEVERITY_ERROR)
				|| hasMessages(FacesMessage.SEVERITY_WARN);
	}

	public static UIComponent findComponent(UIComponent base, String id) {
		if (id.equals(base.getId())) {
			return base;
		}

		UIComponent kid = null;
		UIComponent result = null;
		Iterator<UIComponent> kids = base.getFacetsAndChildren();
		while (kids.hasNext() && (result == null)) {
			kid = kids.next();
			if (id.equals(kid.getId())) {
				result = kid;
				break;
			}
			result = findComponent(kid, id);
			if (result != null) {
				break;
			}
		}
		return result;
	}

	public static UIComponent findComponentInRoot(String id) {
		UIComponent ret = null;

		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext != null) {
			UIComponent root = facesContext.getViewRoot();
			ret = findComponent(root, id);
		}
		return ret;
	}

	protected void addMessage(String id, String summary,
			FacesMessage.Severity severity) {
		UIComponent component = findComponentInRoot(id);
		if (component == null) {
			return;
		}
		getFacesContext().addMessage(component.getClientId(getFacesContext()),
				new FacesMessage(severity, summary, null));
	}

	protected void info(String id, String summary) {
		addMessage(id, summary, FacesMessage.SEVERITY_INFO);
	}

	protected void warn(String id, String summary) {
		addMessage(id, summary, FacesMessage.SEVERITY_WARN);
	}

	protected void error(String id, String summary) {
		addMessage(id, summary, FacesMessage.SEVERITY_ERROR);
	}

	protected void info(String summary) {
		addUniqueMessage(summary, FacesMessage.SEVERITY_INFO);
	}

	protected void warn(String summary) {
		addUniqueMessage(summary, FacesMessage.SEVERITY_WARN);
	}

	protected void error(String summary) {
		addUniqueMessage(summary, FacesMessage.SEVERITY_ERROR);
	}

	protected void fatal(String summary) {
		addUniqueMessage(summary, FacesMessage.SEVERITY_FATAL);
	}

	@SuppressWarnings("el-syntax")
	protected String messageFromResourceBundle(String resourceKey) {
		return (String) getValue("#{" + resourceKey + "}");
	}

	protected String messageFromResourceBundle(String resourceKey,
			Object... arguments) {
		String pattern = (String) getValue("#{" + resourceKey + "}");
		if (pattern == null) {
			return "";
		}
		return MessageFormat.format(pattern, arguments);
	}

	protected String[] messagesFromResourceBundle(String resourceKey) {
		String value = (String) getValue("#{" + resourceKey + "}");
		String[] values = value.split("\\|");
		return values;
	}

	protected void processException(Logger log, Exception ex) {
		String message = ex.getLocalizedMessage();
		if (message == null || "".equals(message)) {
			message = ex.getClass().getName();
		}
		if (log != null && log.isEnabledFor(Level.ERROR)) {
			log.error("", ex);
		}
		// TODO: Specific exceptions
		error(message);
		return;
	}

	protected void processException(Exception ex) {
		processException(log, ex);
	}

	private String reRenderList;

	/**
	 * <p>
	 * Gets the "global" reRender list
	 * </p>
	 * 
	 * @return Comma separated of components to be reRender for the next render
	 *         response phase.
	 */
	public String getReRenderList() {
		return reRenderList;
	}

	/**
	 * <p>
	 * Sets the "global" reRender list
	 * </p>
	 * 
	 * @param reRenderList
	 *            Comma separated of components to be reRender for the next
	 *            render response phase.
	 */
	public void setReRenderList(String reRenderList) {
		this.reRenderList = reRenderList;
	}

	/**
	 * <p>
	 * Convert the sql's result set into SelectItem's list.
	 * </p>
	 * <p>
	 * Columns (ordinal position):
	 * </p>
	 * <ul>
	 * <li>Value
	 * <li>Label
	 * <li>Description (optional)
	 * </ul>
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */

	// Security

	public String getRemoteUser() {
		return getExternalContext().getRemoteUser();
	}

	public boolean isLoggedIn() {
		return getExternalContext().getRemoteUser() != null;
	}

	public String getContextPath() {
		return getExternalContext().getRequestContextPath();
	}

	public String getViewRootId() {
		return getFacesContext().getViewRoot().getViewId();
	}

	protected MethodExpression createMethodExpression(String expression) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		return facesContext
				.getApplication()
				.getExpressionFactory()
				.createMethodExpression(elContext, expression, null,
						new Class[] {});
	}

	protected ValueExpression createValueExpression(String expression) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		return facesContext.getApplication().getExpressionFactory()
				.createValueExpression(elContext, expression, Object.class);
	}

	protected void setValue(String expression, Object value) {
		ELContext elContext = getFacesContext().getELContext();
		createValueExpression(expression).setValue(elContext, value);
	}

	protected void invokeMethod(String expression, Object... args) {
		ELContext elContext = getFacesContext().getELContext();
		createMethodExpression(expression).invoke(elContext, args);
	}

	/**
	 * <p>
	 * method used to download files
	 * </p>
	 * 
	 * @param file
	 *            - Download File
	 * @param type
	 *            - MimeType
	 * @param name
	 *            - Name of File
	 * @throws Exception
	 */
	protected void prepareDownload(byte[] file, String type, String name)
			throws Exception {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();

		response.reset();
		response.setContentType(type);
		response.setContentLength(file.length);
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ name + "\"");
		response.getOutputStream().write(file);
		facesContext.responseComplete();
	}

	protected String formatDate(String format, Date date) {
		SimpleDateFormat sdfChartLend = new SimpleDateFormat(format);
		return sdfChartLend.format(date);
	}

	public boolean isOkCerrarEditar() {
		FacesMessage.Severity s = getFacesContext().getMaximumSeverity();
		return (s == null || s == FacesMessage.SEVERITY_INFO || s == FacesMessage.SEVERITY_WARN);
	}

	public ExcelReader createLector(InputStream file) throws Exception {

		try {
			HSSFWorkbook excel = new HSSFWorkbook(file);
			return new ExcelReader(excel);
		} finally {

		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}

	public Date set24Date(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public Date onlyDate(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String getFileExtension(String file) {
		String res = "";
		try {
			if (file.contains(".")) {
				int pos = file.lastIndexOf(".");
				res = file.substring(pos);
			} else {
				res = "NONE";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			res = "NONE";
		}
		return res;
	}

	public boolean isEmpty(String text) {
		return text == null || text.trim().equals("");
	}

	public long getFileTypeSing() {
		return fileTypeSing;
	}

	public void setFileTypeSing(long fileTypeSing) {
		this.fileTypeSing = fileTypeSing;
	}

	public long getFileTypePhoto() {
		return fileTypePhoto;
	}

	public void setFileTypePhoto(long fileTypePhoto) {
		this.fileTypePhoto = fileTypePhoto;
	}

	public double getRandom() {

		random = Math.random();
		return random;
	}

	public void setRandom(double random) {
		this.random = random;
	}

	public long getFilePatientPhoto() {
		return filePatientPhoto;
	}

	public void setFilePatientPhoto(long filePatientPhoto) {
		this.filePatientPhoto = filePatientPhoto;
	}

	public String getPath(String file) {
		try {
			ServletContext cl = (ServletContext) this.getExternalContext()
					.getContext();

			if (cl == null)
				return "";
			else {

				return cl.getRealPath(file);
			}
		} catch (Exception ex) {
			// si hay error no hace nada
		}
		return "";
	}

	public String getFilePath(String templante) throws Exception {

		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		String dirBaseWB = cl.getResource("/").getFile();
		dirBaseWB = dirBaseWB + "";
		dirBaseWB = dirBaseWB.replace("%20", " ");

		return dirBaseWB.concat(templante);
	}

}
