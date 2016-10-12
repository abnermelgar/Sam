package gt.edu.altamira.sam.jsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

import gt.edu.altamira.ejb.UserManagerLocal;
import gt.edu.altamira.sam.entity.sec.Option;
import gt.edu.altamira.sam.entity.sec.OptionRole;
import gt.edu.altamira.sam.entity.sec.Role;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserRole;

@Named("menuBean")
@ViewScoped
public class MenuBean extends JPAEntityPageBeanBase<Option> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1098415523558719492L;
	private static Logger logger = Logger.getLogger(MenuBean.class);

	public static final String PAGES_SESSION = "PAGES_SESSION";

	private String userName;

	private String userType;

	private Date date = new Date();

	private List<Option> options;

	@EJB
	private UserManagerLocal userManager;

	public MenuBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Option> getOptions() {

		if (options == null) {

			try {

				User user = (User) getSessionScope().get(LOGGED_USER);

				List<Role> roles = new ArrayList<Role>();

				for (UserRole ur : user.getUserRoles()) {
					roles.add(ur.getRole());
				}

				options = new ArrayList<Option>();

				List<Option> menus = new ArrayList<Option>();

				List<Option> hijos = new ArrayList<Option>();

				for (Role role : roles) {

					for (OptionRole optR : role.getOptionRoles()) {

						if (optR.getOption().getMenu()) {

							boolean hasEquals = false;

							for (Option op : menus) {
								if (op.equals(optR.getOption())) {
									hasEquals = true;
									break;
								} else
									continue;
							}

							if (!hasEquals)
								menus.add(optR.getOption());

						} else {

							boolean hasEquals = false;

							for (Option op : hijos) {
								if (op.equals(optR.getOption())) {
									hasEquals = true;
									break;
								} else
									continue;
							}

							if (!hasEquals)
								hijos.add(optR.getOption());

						}

					}
				}

				for (Option o : menus) {
					o.setOptions(null);
					o.setOptions(new ArrayList<Option>());

				}

				for (Option op : menus) {

					for (Option h : hijos) {
						if (op.getId() == h.getOption().getId()) {
							op.getOptions().add(h);
						}
					}

				}

				// ORDENAMIENTO HIJOS
				for (Option op : menus) {

					Option[] optionsArray = op.getOptions().toArray(new Option[op.getOptions().size()]);

					Arrays.sort(optionsArray, new Comparator<Option>() {

						@Override
						public int compare(Option o1, Option o2) {
							String op1 = o1.getOptionName().toUpperCase();
							String op2 = o2.getOptionName().toUpperCase();
							return op1.compareTo(op2);
						}
					});
					op.setOptions(Arrays.asList(optionsArray));
				}

				Option[] menuArray = menus.toArray(new Option[menus.size()]);

				// ORDENAMIENTO PADRES
				Arrays.sort(menuArray, new Comparator<Option>() {

					@Override
					public int compare(Option o1, Option o2) {

						Integer op1 = o1.getOptionOrder();
						Integer op2 = o2.getOptionOrder();
						return op1.compareTo(op2);
					}
				});

				menus = Arrays.asList(menuArray);
				options = menus;

			} catch (Exception e) {
				logger.error("Se ha ocurrido un error inesperado: ", e);
				options = new ArrayList<Option>();
			}

			List<String> permissionPages = new ArrayList<String>();
			permissionPages.add("index.jsf");

			updatePermissions(permissionPages, options);

			getSessionScope().put(PAGES_SESSION, permissionPages);

		}

		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	private List<String> updatePermissions(List<String> permissionPages, List<Option> opts) {

		for (Option o : opts) {

			permissionPages.add(o.getOptionView());
			if (o.getOptions() != null && !o.getOptions().isEmpty()) {
				updatePermissions(permissionPages, o.getOptions());
			}
		}

		return permissionPages;
	}

	@SuppressWarnings("unused")
	private List<Option> getDeleteOptions(List<Option> opts) {
		List<Option> res = new ArrayList<Option>();
		for (Option o : opts) {
			if (o.getOptions() != null && o.getOptions().isEmpty() && !o.getMenu()) {
				res.add(o);
			}
		}

		return res;
	}

	public String getUserName() {

		if (userName == null) {

			userName = user != null ? user.getUserNames() + " " + user.getLastNames() : "";
		}

		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUserType() {
		if (userType == null) {
			userType = user != null ? user.getUserType().getName() : "";
		}

		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
