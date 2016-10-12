package gt.edu.altamira.ejb;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;
import gt.edu.altamira.sam.entity.sec.UserRole;

/**
 * 
 * @author amelgar
 *
 */
@Local
public interface UserManagerLocal {
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User getUser(String user);

	/**
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public String encryptText(String plainText) throws Exception;

	/**
	 * 
	 * @param plainText
	 * @return
	 * @throws Exception
	 */
	public String decryptText(String plainText) throws Exception;

	/**
	 * 
	 * @param request
	 * @param action
	 * @param user
	 * @return
	 */
	public UserLogin saveRequest(HttpServletRequest request, String action, User user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public UserRole getRole(User user);

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUser(long id) throws Exception;

	/**
	 * 
	 * @param userAlias
	 * @return
	 */
	public boolean existUser(String userAlias);

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean existEmail(String email);

}
