package gt.edu.altamira.sam.jsf.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import gt.edu.altamira.sam.entity.sec.Option;
import gt.edu.altamira.sam.entity.sec.OptionRole;
import gt.edu.altamira.sam.entity.sec.OptionRolePK;
import gt.edu.altamira.sam.entity.sec.Role;
import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;

@Named("roleBean")
@ViewScoped
public class RoleBean extends JPAEntityPageBeanBase<Role> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144892363052922842L;

	private static Logger logger = Logger.getLogger(RoleBean.class);

	private List<SelectItem> options;
	private long selectedOption;
	private OptionRole deleteOption;

	public RoleBean() {
		persistedMsg = "Se ha agreado un nuevo Rol";
		updatedMsg = "Se actualizado el Rol";
	}

	public void addOptionrole() {

		if (selectedOption == 0) {
			error("listaOpciones", "Debe seleccionar una opción.");
		}

		logger.info("Entro a agregar opciones");

		try {

			Option opt = getEntityManager().find(Option.class, selectedOption);

			if (opt != null) {

				OptionRole optRole = new OptionRole();

				OptionRolePK pkOptRole = new OptionRolePK();

				pkOptRole.setIdRole(entity.getId());
				pkOptRole.setIdOption(opt.getId());

				optRole.setId(pkOptRole);
				optRole.setRole(entity);
				optRole.setOption(opt);
				optRole.setDateCreated(new Date());
				optRole.setCreatedBy(user.getUserAlias());

				userTransaction.begin();
				entityManager.persist(optRole);
				userTransaction.commit();

				setOptions(null);
				find();

			}

		} catch (Exception e) {
			logger.error("Error cargando las roles", e);
		}

	}

	public void deleteSelectedOption() {

		try {

			OptionRole optRole = deleteOption;

			userTransaction.begin();
			optRole = entityManager.merge(optRole);
			entityManager.remove(optRole);

			userTransaction.commit();

			setOptions(null);
			find();

		} catch (Exception e) {
			logger.error("Error - Ha ocurrido un error inesperado : " + e);
		}

	}

	@Override
	public String addNew() throws Exception {
		selectedOption = 0;
		return super.addNew();
	}

	@Override
	protected boolean beforeSave(EntityManager em) {

		if (isNewEntity()) {
			entity.setDateCreated(new Date());
			entity.setCreatedBy(user.getUserAlias());
		} else {
			entity.setDateModification(new Date());
			entity.setModifiedBy(user.getUserAlias());
		}

		return super.beforeSave(em);
	}

	@Override
	protected void clear() {
		this.entityList = null;
		this.entityCount = null;
		this.countQuery = null;
		this.currPage = 1;
		this.listQL = null;
		this.countQL = null;
		this.params = new HashMap<String, Object>();

		super.clear();
	}

	@Override
	public void setId(Object entityId) throws Exception {
		if (entityId instanceof String)
			entityId = Long.parseLong(entityId.toString());
		super.setId(entityId);
		clear();
	}

	@Override
	public String save() {

		if (super.save() != null) {
			wizardURL = "form:tooglePanelI1";

		}
		return null;
	}

	@Override
	protected void afterFind() throws Exception {
		oldObjectJason = logDB.objectToJason(entity);
		selectedOption = 0;
		super.afterFind();
	}

	public List<SelectItem> getOptions() {

		options = entities2SelectItems(utilsManager.getOptions(entity.getId()), "getId",
				new String[] { "getId", "getOptionDesc" });
		if (options == null || options.isEmpty()) {
			options = new ArrayList<SelectItem>();
			options.add(new SelectItem(0, "No hay opciones disponibles..."));
		}

		return options;
	}

	public void setOptions(List<SelectItem> options) {
		this.options = options;
	}

	public long getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(long selectedOption) {
		this.selectedOption = selectedOption;
	}

	public OptionRole getDeleteOption() {
		return deleteOption;
	}

	public void setDeleteOption(OptionRole deleteOption) {
		this.deleteOption = deleteOption;
	}

}
