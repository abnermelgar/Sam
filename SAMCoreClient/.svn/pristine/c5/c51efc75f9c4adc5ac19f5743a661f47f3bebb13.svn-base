package gt.edu.altamira.ejb;

import java.util.List;

import javax.ejb.Local;

import gt.edu.altamira.sam.entity.sec.DocumentUser;
import gt.edu.altamira.sam.entity.sec.Option;
import gt.edu.altamira.sam.entity.sec.Parameter;
import gt.edu.altamira.sam.entity.sec.Role;
import gt.edu.altamira.sam.entity.sec.Status;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserType;

@Local
public interface UtilsManagerLocal {
	
	/**
	 * 
	 * @return list of active {@link Option}
	 */
	public List<Option> getOptionMenu();

	/**
	 * 
	 * @param rolId
	 * @return list of active {@link Option} by rol id
	 */
	public List<Option> getOptions(long rolId);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<Role> getRoleNoUser(User user);

	/**
	 * 
	 * @return list of active {@link UserType}
	 */
	public List<UserType> getUserTypes();

	/**
	 * 
	 * @param idFile
	 * @return
	 */
	public DocumentUser getUserFile(long idFile);

	/**
	 * 
	 * @return
	 */

	/**
	 * 
	 * @param idUser
	 * @param idType
	 * @return
	 */
	public DocumentUser getUserImage(long idUser, long idType);

	/**
	 * 
	 * @return list of active {@link Status}
	 */
	public List<Status> getStatus();

	/**
	 * 
	 * @param idUserType
	 * @return
	 */
	public List<User> getUsers(long idUserType);

	/**
	 * 
	 * @return
	 */
	public List<Status> getGeneralStatus();

	/**
	 * 
	 * @param idParameter
	 * @return
	 */
	public Parameter getParameter(long idParameter);

	/**
	 * 
	 * @param namearameter
	 * @return
	 */

	public Parameter getParameter(String nameParameter);

	// INICIA METODOS PROPIOS DE UFIT

	

}
