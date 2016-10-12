package gt.edu.altamira.ejb;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import gt.edu.altamira.sam.entity.sec.AppEvent;
import gt.edu.altamira.sam.entity.sec.AppEventLog;
import gt.edu.altamira.sam.entity.sec.UserLogin;


/**
 * Session Bean implementation class LogManager
 */
@Stateless(mappedName = "ejb/LogManager")
@LocalBean
public class LogManager extends EjbManager implements LogManagerLocal {

	private static final long UPDATE_EVENT_ID = 20;
	private static final long INSERT_EVENT_ID = 10;
	private static final long DELETE_EVENT_ID = 30;

	private static Logger logger = Logger.getLogger(LogManager.class);

	@EJB
	private UserManagerLocal userManager;

	/**
	 * Default constructor.
	 */
	public LogManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void log(String user, boolean isNew, String newObj, String oldObj, String className, UserLogin userLogin,
			Object entityId) {
		try {

			AppEventLog log = new AppEventLog();

			if (isNew) {

				AppEvent ev = entityManager.find(AppEvent.class, INSERT_EVENT_ID);

				if (ev != null) {
					log.setEvent(ev);
					log.setCreatedBy(user);
					log.setDateCreated(new Date());
					log.setNewObject(userManager.encryptText(newObj));
					log.setEntityEvent(className);
					log.setUserLogin(userLogin);

					if (entityId != null)
						log.setEntityEventId(entityId.toString());

					entityManager.persist(log);
				}

			} else {

				AppEvent ev = entityManager.find(AppEvent.class, UPDATE_EVENT_ID);

				if (ev != null) {
					log.setEvent(ev);
					log.setCreatedBy(user);
					log.setDateCreated(new Date());
					log.setUserLogin(userLogin);
					log.setNewObject(userManager.encryptText(newObj));
					log.setOldObject(userManager.encryptText(oldObj));
					log.setEntityEvent(className);
					log.setEntityEventId(entityId.toString());

					entityManager.persist(log);

				}

			}
		} catch (Exception e) {
			logger.error("Error al grabar el log ", e);
		}

	}

	@Override
	public String objectToJason(Object obj) throws Exception {
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(obj);
	}

	@Override
	public void deleteEvent(String user, String oldObj, String className, UserLogin userLogin) {
		try {

			AppEventLog log = new AppEventLog();
			AppEvent ev = entityManager.find(AppEvent.class, DELETE_EVENT_ID);

			if (ev != null) {
				log.setEvent(ev);
				log.setCreatedBy(user);
				log.setDateCreated(new Date());
				log.setNewObject(userManager.encryptText(oldObj));
				log.setOldObject(userManager.encryptText(oldObj));
				log.setUserLogin(userLogin);

				entityManager.persist(log);
			}
		} catch (Exception e) {
			logger.error("Error al grabar el log ", e);
		}

	}

}
