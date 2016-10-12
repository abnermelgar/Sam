package gt.edu.altamira.ejb;

import javax.ejb.Local;

import gt.edu.altamira.sam.entity.sec.UserLogin;



@Local
public interface LogManagerLocal {

	/**
	 * 
	 * @param user
	 * @param isNew
	 * @param newObj
	 * @param oldObj
	 * @param className
	 * @param login
	 * @param entityId
	 */
	public void log(String user, boolean isNew, String newObj, String oldObj, String className, UserLogin userLogin,
			Object entityId);

	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String objectToJason(Object obj) throws Exception;

	/**
	 * 
	 * @param user
	 * @param newObj
	 * @param oldObj
	 * @param className
	 * @param userLogin
	 */
	public void deleteEvent(String user, String oldObj, String className, UserLogin userLogin);

}
