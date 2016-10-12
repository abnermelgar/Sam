package gt.edu.altamira.sam.jsf.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import gt.edu.altamira.ejb.UserManagerLocal;
import gt.edu.altamira.sam.entity.sec.Role;
import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserRole;
import gt.edu.altamira.sam.entity.sec.UserRolePK;
import gt.edu.altamira.sam.entity.sec.UserStatus;
import gt.edu.altamira.sam.entity.sec.UserType;
import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;

@Named("userBean")
@ViewScoped
public class UserBean extends JPAEntityPageBeanBase<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1413222854509528012L;
	private static Logger logger = Logger.getLogger(UserBean.class);

	private long selectedStatus;
	private long selectedType;
	private long selectedRole;

	private UserRolePK rolePk;

	private List<SelectItem> userTypes;
	private List<SelectItem> roles;

	@EJB
	private UserManagerLocal userManager;

	public UserBean() {
		persistedMsg = "Se ha creado un nuevo usuario ";
		updatedMsg = "Se Ha actualizado el usuario ";
	}

	public void addRole() {

		try {
			UserRolePK pk = new UserRolePK();

			Role role = getEntityManager().find(Role.class, selectedRole);

			pk.setIdRole(role.getId());
			pk.setIdUser(entity.getId());

			UserRole userRole = new UserRole();

			userRole.setCreatedBy(user.getUserAlias());
			userRole.setDateCreated(new Date());
			userRole.setId(pk);
			userRole.setRole(role);
			userRole.setUser(entity);

			userTransaction.begin();
			getEntityManager().persist(userRole);
			userTransaction.commit();

			find();

			logDB.log(user.getUserAlias(), true, logDB.objectToJason(userRole), null, UserRole.class.getSimpleName(),
					userLogin, userRole.getId());

		} catch (Exception e) {
			logger.error("Error cargando las roles", e);
		}

	}

	@Override
	public void setId(Object entityId) throws Exception {
		if (entityId instanceof String)
			entityId = Long.parseLong(entityId.toString());
		super.setId(entityId);
	}

	@Override
	public String save() {

		if (super.save() != null) {
			wizardURL = "form:tooglePanelI1";
		}
		return null;
	}

	@Override
	protected boolean beforeSave(EntityManager em) {
		try {
			if (isNewEntity()) {

				if (userManager.existUser(entity.getUserAlias())) {
					error("El Alias elegido ya se encuentra registrado.");
					return false;
				}

				if (userManager.existEmail(entity.getEmail())) {
					error("El Email elegido ya se encuentra registrado.");
					return false;
				}

				entity.setDateCreated(new Date());
				entity.setCreatedBy(user.getUserAlias());
				entity.setPassword(userManager.encryptText(entity.getPassword()));

			} else {
				entity.setDateModification(new Date());
				entity.setModifiedBy(user.getUserAlias());
			}

			UserStatus status = em.find(UserStatus.class, selectedStatus);

			entity.setUserStatus(status);

			UserType type = em.find(UserType.class, selectedType);

			entity.setUserType(type);

		} catch (Exception e) {

			logger.error("Error al guardar el usaurio ");
			error("Error al guardar el usuario ");
			return false;
		}
		return super.beforeSave(em);
	}

	@Override
	public String addNew() throws Exception {
		selectedType = 0;
		selectedRole = 0;
		selectedStatus = 0;
		return super.addNew();
	}

	@Override
	protected void afterFind() throws Exception {

		oldObjectJason = logDB.objectToJason(entity);

		if (!isNewEntity()) {

			selectedStatus = entity.getUserStatus() != null ? entity.getUserStatus().getId() : 0;

			selectedType = entity.getUserType() != null ? entity.getUserType().getId() : 0;

		}
		super.afterFind();
	}

	public void removeRole() {

		try {
			UserRole userRole = getEntityManager().find(UserRole.class, rolePk);

			if (userRole != null) {
				userTransaction.begin();
				userRole = entityManager.merge(userRole);
				entityManager.remove(userRole);
				userTransaction.commit();
				find();

			} else {
				throw new Exception("Error buscando el role");
			}

		} catch (Exception e) {
			logger.error("Ha ocurrido un error inesperado al remover el rol ", e);
		}

	}

	public long getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(long selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public long getSelectedRole() {
		return selectedRole;
	}

	public void setSelectedRole(long selectedRole) {
		this.selectedRole = selectedRole;
	}

	public UserRolePK getRolePk() {
		return rolePk;
	}

	public void setRolePk(UserRolePK rolePk) {
		this.rolePk = rolePk;
	}

	public List<SelectItem> getUserTypes() {
		if (userTypes == null) {
			userTypes = entities2SelectItems(utilsManager.getUserTypes(), "getId", new String[] { "getName" });
			if (userTypes == null || userTypes.isEmpty()) {
				userTypes = new ArrayList<SelectItem>();
				userTypes.add(new SelectItem(null, "No hay opciones disponibles..."));
			}
		}
		return userTypes;
	}

	public void setUserTypes(List<SelectItem> userTypes) {
		this.userTypes = userTypes;
	}

	public List<SelectItem> getRoles() {

		roles = entities2SelectItems(utilsManager.getRoleNoUser(entity), "getId", new String[] { "getDesRole" });
		if (roles == null || roles.isEmpty()) {
			roles = new ArrayList<SelectItem>();
			roles.add(new SelectItem(null, "No hay opciones disponibles..."));
		}
		return roles;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}

	public long getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(long selectedType) {
		this.selectedType = selectedType;
	}

}
