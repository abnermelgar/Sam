package gt.edu.altamira.jsf;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

/**
 * 
 * @author amelgar
 * 
 * @param <E>
 */
public class JPAEntityPageBean<E> extends JPAPageBean {
	private static final long serialVersionUID = -7391906242296555523L;

	private static Logger logger = Logger.getLogger(JPAEntityPageBean.class);

	protected Class<E> entityClass;
	protected List<E> entityList;
	protected E entity;
	protected String entityName;
	protected Object entityId;
	protected String where;
	protected String sortColumn;
	protected String sortOrder = "asc";
	protected String listQL;
	protected String countQL;
	protected Map<String, Object> params = new HashMap<String, Object>();
	protected int pageSize = 15;
	protected int pageSizeReport = 10000;
	protected int currPage = 1;
	protected Number entityCount;
	protected Query listQuery;
	protected Query countQuery;
	protected Object id;

	// Messages
	protected String persistedMsg = "{0} saved.";
	protected String updatedMsg = "{0} updated.";
	protected String deletedMsg = "{0} was succesfully deleted.";

	// Return values after update action
	protected String persisted = "persisted";
	protected String updated = "updated";
	protected String deleted = "deleted";
	protected String added = "new";
	protected Locale locale = Locale.getDefault();
	protected TimeZone timeZone = TimeZone.getDefault();

	// TogglePanel
	protected String wizardURL = "form:tooglePanelI1";

	protected String pageTitle = "";

	/**
	 * <p>
	 * Must be overriden by descendants.
	 * </p>
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return null;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
		listQL = null;
		countQL = null;
		clearList();
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		if (sortColumn.equals(this.sortColumn)) {
			if (isAsc()) {
				sortOrder = "desc";
			} else {
				sortOrder = "asc";
			}
		} else {
			sortOrder = "asc";
		}
		this.sortColumn = sortColumn;
		listQL = null;
		clearList();
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
		listQL = null;
		clearList();
	}

	protected boolean isAsc() {
		return sortOrder == null
				|| sortOrder.trim().toLowerCase().equals("asc");
	}

	public void clearList() {
		entityList = null;
		entityCount = null;
		currPage = 1;
	}

	/**
	 * <p>
	 * Returns the QL to get list of entities.
	 * </p>
	 * <p>
	 * Should be overriden if filter and other criteria is needed.
	 * </p>
	 * 
	 * @return
	 */
	protected String getListQL() {
		if (listQL == null) {
			listQL = "select e from " + getEntityClass().getSimpleName() + " e";
			if (where != null && !where.trim().equals("")) {
				listQL += where;
			}
			if (sortColumn != null && !sortColumn.trim().equals("")) {
				listQL += " order by " + sortColumn;
				if (sortOrder != null) {
					listQL += " " + sortOrder;
				}
			}
		}
		return listQL;
	}

	/**
	 * <p>
	 * Returns the query object to be used to get the entities.
	 * </p>
	 * <p>
	 * Should be overriden if filter and other criteria is needed.
	 * </p>
	 * 
	 * @param em
	 * 
	 * @return
	 */
	protected Query getListQuery(EntityManager em) {
		if (listQuery == null) {
			listQuery = em.createQuery(getListQL(), getEntityClass());
		}
		return listQuery;
	}

	/**
	 * <p>
	 * Gets the params to be user by the query.
	 * </p>
	 * 
	 * @return
	 */
	public Map<String, Object> getParams() {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setParam(String name, Object value) {
		getParams().put(name, value);
	}

	protected void assignParams(Query query) {
		for (Iterator<Entry<String, Object>> i = getParams().entrySet()
				.iterator(); i.hasNext();) {
			Map.Entry<String, Object> e = i.next();
			query.setParameter(e.getKey(), e.getValue());
		}
	}

	protected void beforeList() {
	}

	/**
	 * <p>
	 * Returns the entity list.
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> getList() {
		if (entityList == null) {
			try {
				beforeList();
				assignParams(getListQuery(getEntityManager()));
				entityList = getListQuery(getEntityManager()).getResultList();
				entityCount = entityList.size();
				getEntityManager().clear();
			} finally {
				listQuery = null;
				countQuery = null;
			}
		}
		return entityList;
	}

	/**
	 * <p>
	 * Simply clears the entity list.
	 * </p>
	 */
	public void refreshList() {
		clearList();
	}

	/**
	 * <p>
	 * Gets the current loaded entity.
	 * </p>
	 * 
	 * @return
	 */
	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	/**
	 * <p>
	 * Called before em.find().
	 * </p>
	 */
	protected void beforeFind() {
	}

	/**
	 * <p>
	 * Called after successful call to rm.find().
	 * </p>
	 * <p>
	 * Can be used to load lazy properties.
	 * </p>
	 */
	protected void afterFind() throws Exception {
	}

	/**
	 * <p>
	 * Finds the entity corresponding to entitytId property.
	 * </p>
	 */
	protected void find() throws Exception {
		beforeFind();
		entity = getEntityManager().find(getEntityClass(), getId());
		if (entity == null) {
			setId(null);
		} else {
			afterFind();
		}
	}

	public Object getId() {
		return entityId;
	}

	/**
	 * <p>
	 * Sets the entitity id to be loaded.
	 * </p>
	 * 
	 * @param entityId
	 */
	public void setId(Object entityId) throws Exception {
		this.entityId = entityId;

		if (entityId == null) {
			entity = null;
		} else {
			find();
		}
	}

	protected E newInstance() throws Exception {
		return getEntityClass().newInstance();
	}

	public String addNew() throws Exception {
		entity = newInstance();
		entityId = null;
		return added;
	}

	protected boolean beforeSave(EntityManager em) {
		return true;
	}

	protected void afterSave(EntityManager em) {
		entityList = null;
		entityCount = null;
	}

	public boolean isNewEntity() {
		return getId() == null;
	}

	protected boolean validateSave() {
		return true;
	}

	@Resource
	protected UserTransaction userTransaction;

	public String save() {
		try {
			boolean isNew = isNewEntity();
			if (validateSave() && beforeSave(getEntityManager())) {
				userTransaction.begin();
				try {
					if (getId() == null) {
						getEntityManager().persist(entity);
					} else {
						getEntityManager().merge(entity);
					}
					afterSave(getEntityManager());
					userTransaction.commit();
				} catch (RuntimeException ex) {
					userTransaction.rollback();
					throw ex;
				}
			} else {

				return null;

			}
			if (isNew) {

				info(MessageFormat.format(persistedMsg, getEntityName()));
				return persisted;
			} else {

				info(MessageFormat.format(updatedMsg, getEntityName()));
				return updated;
			}
		} catch (Exception ex) {
			processException(logger, ex);
			logger.error("Error on save", ex);

		}

		return null;
	}

	protected boolean beforeDelete(EntityManager em) {
		return true;
	}

	protected void afterDelete(EntityManager em) {
		entityList = null;
		entityCount = null;
		entity = null;
		entityId = null;
	}

	public String delete() {
		try {
			if (beforeDelete(getEntityManager())) {
				userTransaction.begin();
				try {
					getEntityManager().remove(
							getEntityManager().find(getEntityClass(), getId()));
					afterDelete(getEntityManager());
					userTransaction.commit();
				} catch (RuntimeException ex) {
					userTransaction.rollback();
					throw ex;
				}
			} else {
				return null;
			}

			// TODO: There is a rare condition where
			// user deletes all records of last page,
			// bean should go to previous page...

			info(MessageFormat.format(deletedMsg, getEntityName()));
			return deleted;
		} catch (Exception ex) {
			processException(logger, ex);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
			} else {
				logger.error("Could not guess entity class by reflection");
			}
		}
		return entityClass;
	}

	protected String getEntityName() {
		if (entityName == null) {
			String s = getEntityClass().getSimpleName();
			StringBuilder sb = new StringBuilder(128);
			boolean first = true;
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (Character.isUpperCase(c)) {
					if (first) {
						first = false;
					} else {
						sb.append(' ');
					}
				}
				sb.append(c);
			}
			entityName = sb.toString();
		}
		return entityName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		clearList();
		this.pageSize = pageSize;
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPageNo) {
		if (currPageNo < 0) {
			currPageNo = 1;
		} else if (currPageNo > getPageCount()) {
			currPageNo = getPageCount();
		}
		entityList = null;
		this.currPage = currPageNo;
	}

	public int getEntityCount() {
		if (entityCount == null) {
			entityCount = (Number) getCountQuery(getEntityManager())
					.getSingleResult();
		}
		return entityCount.intValue();
	}

	public int getPageCount() {
		int count = getEntityCount();
		if (count % pageSize == 0) {
			return count / pageSize;
		}
		return (count / pageSize) + 1;
	}

	public int getPageCountReport() {
		int count = getEntityCount();
		if (count % pageSizeReport == 0) {
			return count / pageSizeReport;
		}
		return (count / pageSizeReport) + 1;
	}

	// SESS - 20110315: EclipseLink doesn�t like count(*)
	protected String getCountQL() {
		if (countQL == null) {
			countQL = "select count(e) from "
					+ getEntityClass().getSimpleName() + " e";
			if (where != null && !where.trim().equals("")) {
				countQL += where;
			}
		}
		return countQL;
	}

	protected Query getCountQuery(EntityManager em) {
		if (countQuery == null) {
			countQuery = em.createQuery(getCountQL());
		}
		return countQuery;
	}

	@SuppressWarnings("unchecked")
	public List<E> getPaginatedList() {
		if (entityList == null) {
			try {
				beforeList();
				assignParams(getListQuery(getEntityManager()));
				assignParams(getCountQuery(getEntityManager()));

				entityCount = (Number) getCountQuery(getEntityManager())
						.getSingleResult();
				// int recordsCount = Integer.parseInt(entityCount.toString());
				int pageCount = getPageCount();
				if (pageCount == 0) {
					pageCount = 1;
				}
				entityList = getListQuery(getEntityManager())
						.setFirstResult(pageSize * (currPage - 1))
						.setMaxResults(pageSize).getResultList();

				afterList();

				getEntityManager().clear();
			} finally {
				listQuery = null;
				countQuery = null;
			}
		}
		return entityList;
	}

	protected void afterList() {

	}

	@SuppressWarnings("unchecked")
	public List<E> getPaginatedListReport() {
		if (entityList == null) {
			EntityManager em = getEntityManager();
			try {
				beforeList();
				assignParams(getListQuery(em));
				assignParams(getCountQuery(em));
				entityList = getListQuery(em)
						.setFirstResult(pageSizeReport * (currPage - 1))
						.setMaxResults(pageSizeReport).getResultList();
				entityCount = (Number) getCountQuery(em).getSingleResult();
				getEntityManager().clear();
			} finally {
				listQuery = null;
				countQuery = null;
			}
		}
		return entityList;
	}

	public String first() {
		setCurrPage(1);
		return null;
	}

	public String previous() {
		setCurrPage(--currPage);
		return null;
	}

	public String next() {
		setCurrPage(++currPage);
		return null;
	}

	public String last() {
		setCurrPage(getPageCount());
		return null;
	}

	public boolean getPreviousExists() {
		return currPage > 1;
	}

	public boolean getNextExists() {
		return currPage < getPageCount();
	}

	public String getWizardURL() {
		return wizardURL;
	}

	public void setWizardURL(String wizardURL) {
		this.wizardURL = wizardURL;
	}

	public void renaudarWizardURL(String renaudar) {
		setWizardURL(renaudar);
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

}
