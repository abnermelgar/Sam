package gt.edu.altamira.ejb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import gt.edu.altamira.sam.entity.sec.DocumentUser;
import gt.edu.altamira.sam.entity.sec.Option;
import gt.edu.altamira.sam.entity.sec.Parameter;
import gt.edu.altamira.sam.entity.sec.Role;
import gt.edu.altamira.sam.entity.sec.Status;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserType;

/**
 * Session Bean implementation class UtilsManager
 */
@Stateless(mappedName = "ejb/UtilsManager")
@LocalBean
public class UtilsManager extends EjbManager implements UtilsManagerLocal {

	private static Logger logger = Logger.getLogger(UtilsManager.class);

	/**
	 * Default constructor.
	 */
	public UtilsManager() {
		// TODO Auto-generated constructor stub
	}

	private static final String QL_GET_OPTION_MENU = "Select e from Option e where e.option is null";

	@Override
	public List<Option> getOptionMenu() {
		List<Option> options = new ArrayList<Option>();

		try {
			options = entityManager.createQuery(QL_GET_OPTION_MENU, Option.class).getResultList();

		} catch (Exception e) {
			logger.error("Error al cargar opciones ", e);
		}

		return options;
	}

	private static final String QL_FIND_NO_ASSOCIATED_OPTIONS = "select o from Option o where  o.id not in ("
			+ "select opr.option.id from OptionRole opr where opr.role.id=:role) and o.publicView=true order by o.optionName ";

	@Override
	public List<Option> getOptions(long rolId) {
		List<Option> options = new ArrayList<Option>();
		try {
			options = entityManager.createQuery(QL_FIND_NO_ASSOCIATED_OPTIONS, Option.class).setParameter("role", rolId)
					.getResultList();

			Option[] optionsArray = options.toArray(new Option[options.size()]);

			Arrays.sort(optionsArray, new Comparator<Option>() {

				@Override
				public int compare(Option o1, Option o2) {
					String op1 = o1.getOptionName().toUpperCase();
					String op2 = o2.getOptionName().toUpperCase();
					return op1.compareTo(op2);
				}
			});

			options = Arrays.asList(optionsArray);

		} catch (Exception e) {
			logger.error("Error obteniendo las Opciones de menu " + e);
		}

		return options;
	}

	private static final String QL_FIND_NO_ASSOCIATED_ROLE = "select p from Role p where p.id not in ( "
			+ "select c.role.id from UserRole c where c.user = :user ) order by p.desRole";

	@Override
	public List<Role> getRoleNoUser(User user) {
		List<Role> roles = new ArrayList<Role>();
		try {

			roles = entityManager.createQuery(QL_FIND_NO_ASSOCIATED_ROLE, Role.class).setParameter("user", user)
					.getResultList();

		} catch (Exception e) {
			logger.error("Error obteniendo los roles del usuario ", e);
		}
		return roles;
	}

	private static final String QL_FIND_USER_TYPES = "select s from UserType s";

	@Override
	public List<UserType> getUserTypes() {
		List<UserType> types = new ArrayList<UserType>();

		try {
			types = entityManager.createQuery(QL_FIND_USER_TYPES, UserType.class).getResultList();

		} catch (Exception e) {
			logger.error("Error obteniendo los estados " + e);
		}

		return types;
	}

	private static final String QL_COUNT_USER_FILE_BY_ID = "select COUNT(u) from DocumentUser u where u.id=:idFile";

	private static final String QL_USER_FILE_BY_ID = "select u from DocumentUser u where u.id=:idFile";

	@Override
	public DocumentUser getUserFile(long idFile) {
		DocumentUser res = new DocumentUser();

		try {

			long count = entityManager.createQuery(QL_COUNT_USER_FILE_BY_ID, Long.class).setParameter("idFile", idFile)
					.getSingleResult();

			if (count > 0)

				res = entityManager.createQuery(QL_USER_FILE_BY_ID, DocumentUser.class).setParameter("idFile", idFile)
						.getSingleResult();
			else
				return null;

		} catch (Exception e) {
			logger.error("Error obteniendo los beneficios ", e);
		}

		return res;
	}

	private static final String QL_USER_FILE_BY_ID_AND_TYPE = "select u from DocumentUser u where u.user.id =:user and u.documentType.id=:type ";
	private static final String QL_COUNT_USER_FILE_BY_ID_AND_TYPE = "select count(u) from DocumentUser u where u.user.id =:user and u.documentType.id=:type ";

	@Override
	public DocumentUser getUserImage(long idUser, long idType) {
		try {

			long count = entityManager.createQuery(QL_COUNT_USER_FILE_BY_ID_AND_TYPE, Long.class)
					.setParameter("user", idUser).setParameter("type", idType).getSingleResult();

			if (count > 0)

				return entityManager.createQuery(QL_USER_FILE_BY_ID_AND_TYPE, DocumentUser.class)
						.setParameter("user", idUser).setParameter("type", idType).getSingleResult();

		} catch (Exception e) {
			logger.error("Error al cargar imagenes ", e);
		}
		return null;
	}

	private static final String QL_FIND_STATUS = "select s from Status s";

	@Override
	public List<Status> getStatus() {
		List<Status> status = new ArrayList<Status>();

		try {
			status = entityManager.createQuery(QL_FIND_STATUS, Status.class).getResultList();

		} catch (Exception e) {
			logger.error("Error obteniendo los estados " + e);
		}

		return status;
	}

	private static final String QL_GET_ALL_USER_ACTIVE_BY_USER_TYPE = "Select u from User u where u.userStatus.id=:active and u.userType.id=:type";

	@Override
	public List<User> getUsers(long idUserType) {
		List<User> users = new ArrayList<User>();
		try {

			users = entityManager.createQuery(QL_GET_ALL_USER_ACTIVE_BY_USER_TYPE, User.class)
					.setParameter("active", ACTIVE_STATUS).setParameter("type", idUserType).getResultList();

		} catch (Exception e) {
			logger.error("Error al obtener los usuarios ", e);
		}
		return users;
	}

	private static final String QL_GENERAL_STATUS = "select s from Status s where s.statusType.id=:generalStatus";
	private static final long GENERAL_STATUS = 1L;

	@Override
	public List<Status> getGeneralStatus() {
		List<Status> status = new ArrayList<Status>();

		try {
			status = entityManager.createQuery(QL_GENERAL_STATUS, Status.class)
					.setParameter("generalStatus", GENERAL_STATUS).getResultList();

		} catch (Exception e) {
			logger.error("Error obteniendo los estados " + e);
		}

		return status;
	}

	@Override
	public Parameter getParameter(long idParameter) {
		Parameter param = null;

		try {
			param = entityManager.find(Parameter.class, idParameter);
		} catch (Exception e) {
			logger.error("Error al obtener el parametro ", e);
		}

		return param;
	}

	private static final String QL_GET_PARAMETER_BY_NAME = "select p from Parameter p where p.name=:name";

	@Override
	public Parameter getParameter(String nameParameter) {
		Parameter param = null;
		try {
			param = entityManager.createQuery(QL_GET_PARAMETER_BY_NAME, Parameter.class)
					.setParameter("name", nameParameter).getSingleResult();
		} catch (Exception e) {
			logger.error("Error al obtener el parametro ", e);
		}
		return param;
	}

}
