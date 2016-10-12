package gt.edu.altamira.sam.jsf.security;

import java.util.Date;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import gt.edu.altamira.sam.entity.sec.UserType;
import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;

@Named("userTypeBean")
@ViewScoped
public class UserTypeBean extends JPAEntityPageBeanBase<UserType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259340663980854383L;

	public UserTypeBean() {
		persistedMsg = "Se ha creado un nuevo tipo de usuario ";
		updatedMsg = "Se Ha actualizado el tipo de usuario ";
	}

	@Override
	protected boolean beforeSave(EntityManager em) {
		if (isNewEntity()) {
			entity.setCreatedBy(user.getUserAlias());
			entity.setDateCreated(new Date());
		} else {
			entity.setModifiedBy(user.getUserAlias());
			entity.setDateModification(new Date());
		}
		return super.beforeSave(em);
	}

	@Override
	public String addNew() throws Exception {

		return super.addNew();
	}

	@Override
	protected void afterFind() throws Exception {

		oldObjectJason = logDB.objectToJason(entity);

		super.afterFind();
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

}
