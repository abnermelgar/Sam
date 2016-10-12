package gt.edu.altamira.sam.jsf.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import gt.edu.altamira.sam.entity.sec.Option;
import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;

@Named("optionBean")
@ViewScoped
public class OptionBean extends JPAEntityPageBeanBase<Option> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9161078080962595214L;
	// private static Logger logger = Logger.getLogger(OptionBean.class);

	private long selectedOption;

	private List<SelectItem> options;

	public OptionBean() {
		persistedMsg = "Se ha creado un nuevo menú";
		updatedMsg = "Se ha actualizado el menú";
	}

	@Override
	protected void afterFind() throws Exception {
		oldObjectJason = logDB.objectToJason(entity);

		if (!isNewEntity()) {

			selectedOption = entity.getOption() != null ? entity.getOption().getId() : 0;
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

		Option op = em.find(Option.class, selectedOption);

		if (op != null) {
			entity.setOption(op);
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

	public long getSelectedOption() {

		return selectedOption;
	}

	public void setSelectedOption(long selectedOption) {
		this.selectedOption = selectedOption;
	}

	public List<SelectItem> getOptions() {

		options = entities2SelectItems(utilsManager.getOptionMenu(), "getId", "getOptionName");
		return options;
	}

	public void setOptions(List<SelectItem> options) {
		this.options = options;
	}

}
