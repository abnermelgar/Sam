package gt.edu.altamira.ejb;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sun.xml.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;
import gt.edu.altamira.sam.entity.sec.UserRole;

/**
 * Session Bean implementation class UserManager
 */
@Stateless(mappedName = "ejb/UserManager")
@LocalBean
public class UserManager extends EjbManager implements UserManagerLocal {

	private static Logger logger = Logger.getLogger(UserManager.class);

	private static final String CTE_Cryptography_Key = "8Z64(yk;.L3=f[cC8@gmwKW7p?)+2#Gp";
	private static final String CTE_Cryptography_IV = "B1rM4AXz5aV=";

	@EJB
	private RequestLoginManagerLocal requestLogin;

	public UserManager() {
		// TODO Auto-generated constructor stub
	}

	private static final String QL_FIND_USER_BY_ALIAS = "Select u from User u where u.userAlias=:user and u.userStatus.id=:active";

	private static final String QL_FIND_USER_BY_EMAIL = "Select u from User u where u.email=:email and u.userStatus.id=:active";

	@Override
	public User getUser(String user) {
		try {

			try {

				User findUser = entityManager.createQuery(QL_FIND_USER_BY_ALIAS, User.class)
						.setParameter("active", ACTIVE_STATUS).setParameter("user", user).getSingleResult();

				return findUser;
			} catch (NoResultException ex) {
				User findUser = entityManager.createQuery(QL_FIND_USER_BY_EMAIL, User.class)
						.setParameter("active", ACTIVE_STATUS).setParameter("email", user).getSingleResult();

				return findUser;

			}
		} catch (Exception e) {
			logger.error("Error al obtener el usuario- " + e);
		}

		return null;
	}

	@Override
	public String encryptText(String plainText) throws Exception {
		try {

			byte[] plaintext = plainText.getBytes();
			byte[] tdesKeyData = BASE64DecoderStream.decode(CTE_Cryptography_Key.getBytes());
			byte[] myIV = BASE64DecoderStream.decode(CTE_Cryptography_IV.getBytes());

			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");

			SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
			IvParameterSpec ivspec = new IvParameterSpec(myIV);

			c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
			byte[] cipherText = c3des.doFinal(plaintext);

			return new String(BASE64EncoderStream.encode(cipherText));

		} catch (Exception e) {
			logger.error("Error encryptText password: " + e);

			throw e;
		}
	}

	@Override
	public String decryptText(String plainText) throws Exception {
		try {

			byte[] plaintext = plainText.getBytes();
			byte[] tdesKeyData = BASE64DecoderStream.decode(CTE_Cryptography_Key.getBytes());
			byte[] myIV = BASE64DecoderStream.decode(CTE_Cryptography_IV.getBytes());

			plaintext = BASE64DecoderStream.decode(plaintext);

			Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
			IvParameterSpec ivspec = new IvParameterSpec(myIV);

			c3des.init(Cipher.DECRYPT_MODE, myKey, ivspec);
			byte[] cipherText = c3des.doFinal(plaintext);

			return new String(cipherText);

		} catch (Exception e) {
			logger.error("Error decryptText password: " + e);

			throw e;
		}
	}

	@Override
	public UserLogin saveRequest(HttpServletRequest request, String action, User user) {
		return requestLogin.saveRequest(request, action, user);
	}

	private static final String QL_FIND_ROLE_BY_USER = "select ur from UserRole ur where ur.user=:user";

	@Override
	public UserRole getRole(User user) {
		try {

			return entityManager.createQuery(QL_FIND_ROLE_BY_USER, UserRole.class).setParameter("user", user)
					.getSingleResult();

		} catch (Exception e) {
			logger.error("Error obtniendo roles", e);
		}
		return null;
	}

	@Override
	public User getUser(long id) throws Exception {
		return entityManager.find(User.class, id);
	}

	private static final String QL_FIND_USER_ALIAS_EXISTS = "select count(1) from User u where u.userAlias = :userAlias";
	private static final String QL_FIN_USER_EMAIL_EXISTS = "select  count(1) from User u where u.email = :email";

	@Override
	public boolean existUser(String userAlias) {

		boolean value = false;
		try {

			long count = entityManager.createQuery(QL_FIND_USER_ALIAS_EXISTS, Long.class)
					.setParameter("userAlias", userAlias).getSingleResult();

			if (count > 0)
				value = true;

		} catch (Exception e) {
			logger.error("Error al validar el usuario ", e);
		}

		return value;
	}

	@Override
	public boolean existEmail(String email) {
		boolean value = false;
		try {

			long count = entityManager.createQuery(QL_FIN_USER_EMAIL_EXISTS, Long.class).setParameter("email", email)
					.getSingleResult();

			if (count > 0)
				value = true;

		} catch (Exception e) {
			logger.error("Error al validar el usuario ", e);
		}

		return value;
	}

}
