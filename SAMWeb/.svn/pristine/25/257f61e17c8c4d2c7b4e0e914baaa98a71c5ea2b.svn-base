package gt.edu.altamira.sam.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gt.edu.altamira.ejb.UserManagerLocal;
import gt.edu.altamira.jsf.PageBean;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;


@Named("loginBean")
@ViewScoped
public class LoginBean extends PageBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3769737285177930153L;

	private static Logger logger = Logger.getLogger(LoginBean.class);

	@EJB
	private UserManagerLocal userManager;

	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	private String logUser;
	private String logPassword;

	public String login() {
		if (!isEmpty(logUser)) {

			try {

				User findUser = userManager.getUser(logUser.trim());

				if (findUser != null) {

					// Verificar password

					String cifrado = userManager.encryptText(logPassword);

					HttpServletRequest request = (HttpServletRequest) getExternalContext()
							.getRequest();

					if (!isEmpty(cifrado)
							&& findUser.getPassword().equals(cifrado)) {

						getSessionScope().put(
								JPAEntityPageBeanBase.LOGGED_USER, findUser);

						logger.info("Usuario válido " + findUser.getUserAlias());

						List<String> permissionPages = new ArrayList<String>();
						permissionPages.add("index.jsf");
						permissionPages.add("edit.jsf");

						getSessionScope().put(MenuBean.PAGES_SESSION,
								permissionPages);

						// Map<String, String> params = userManager
						// .getParameter(findUser.getUserCountries()
						// .get(0).getCountry());

						// if (params != null) {
						// for (Entry<String, String> e : params
						// .entrySet()) {
						// getSessionScope().put(e.getKey(),
						// e.getValue());
						// }
						// }

						UserLogin userlog = userManager.saveRequest(request,
								"LOGIN", findUser);

						if (userlog != null)
							getSessionScope().put(LOGGED_ID, userlog);

						return "index";

					} else {
						userManager.saveRequest(request, "FAIL_USER_PASSWORD",
								findUser);
						error("Usuario o contraseña no es válida.");
						logger.info("Error - El password no es valido "
								+ logPassword);
					}

				} else {

					error("Usuario o contraseña no es válida.");
					logger.info("Error - El usuarion ingresado no es valido "
							+ user);
				}

			} catch (Exception e) {
				error("Lo sentimos, ha ocurrido un error interno.  Por favor intenta más tarde.");
				logger.error("se ha producido un error inesperado: " + e);
				kill();
			}
		}

		// TODO retornar vista a ser redirigida

		return "";
	}

	public void kill() {

		HttpServletRequest request = (HttpServletRequest) getExternalContext()
				.getRequest();

		if (getSessionScope().get(LOGGED_USER) != null)
			userManager.saveRequest(request, "LOGOFF", (User) getSessionScope()
					.get(LOGGED_USER));

		getExternalContext().getSessionMap().clear();

		HttpSession session = (HttpSession) getExternalContext().getSession(
				false);
		String ingreso = getExternalContext().getRequestContextPath()
				+ "/login.jsf";

		if (session != null)
			session.invalidate();

		try {
			getExternalContext().redirect(ingreso);
		} catch (Exception ex) {
			logger.error("se ha producido un error inesperado: " + ex);
		}

	}

	public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	public String getLogPassword() {
		return logPassword;
	}

	public void setLogPassword(String logPassword) {
		this.logPassword = logPassword;
	}

}
