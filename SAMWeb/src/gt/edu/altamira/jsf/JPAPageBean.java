package gt.edu.altamira.jsf;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author amelgar
 * 
 */
public class JPAPageBean extends PageBean {
	private static final long serialVersionUID = 2045233655430903820L;

	private static Logger logger = Logger.getLogger(JPAPageBean.class);

	protected SimpleDateFormat sdf2 = new SimpleDateFormat(format2);
	protected SimpleDateFormat sdf1 = new SimpleDateFormat(format);
	protected Locale locale = Locale.getDefault();
	protected TimeZone timeZone = TimeZone.getDefault();

	/**
	 * <p>
	 * Converts a list of entities (or plain objects) to SelectItem list.
	 * </p>
	 * 
	 * @param entities
	 *            Entity list to convert.
	 * @param itemValue
	 *            Name of the entity's property for SelecItem.itemValue.
	 *            Required.
	 * @param itemLabels
	 *            Names of the entity's property for SelecItem.itemLabel. Each
	 *            value is separared by space. Required.
	 * @param itemDescription
	 *            Name of the entity's property for SelecItem.itemDescription.
	 *            Optional.
	 * @return
	 */
	public static List<SelectItem> entities2SelectItems(
			List<? extends Object> entities, String itemValue,
			String[] itemLabels, String itemDescription) {
		List<SelectItem> result = new ArrayList<SelectItem>();

		if (entities == null || entities.size() == 0 || itemValue == null
				|| itemValue.equals("") || itemLabels == null
				|| itemLabels.length == 0) {
			return result;
		}

		// Find first non null entry
		Object entity = null;
		for (Object e : entities) {
			if (e != null) {
				entity = e;
				break;
			}
		}
		if (entity == null) {
			return result;
		}

		// Find the getter methods
		Class<? extends Object> clazz = entity.getClass();
		Method getValue = null;
		Method[] getLabels = new Method[itemLabels.length];
		Method getDescription = null;
		try {
			getValue = clazz.getMethod(itemValue, (Class<?>[]) null);
			int i = 0;
			for (String l : itemLabels) {
				getLabels[i++] = clazz.getMethod(l, (Class<?>[]) null);
			}
			if (itemDescription != null && itemDescription.equals("")) {
				getDescription = clazz.getMethod(itemDescription,
						(Class<?>[]) null);
			}
		} catch (Exception ex) {
			logger.error("Obtaining getter methods.", ex);
			return result;
		}

		// Make the select items...
		try {
			for (Object e : entities) {
				if (e != null) {
					Object value = getValue.invoke(e, (Object[]) null);

					StringBuilder label = new StringBuilder();
					boolean first = true;
					for (Method m : getLabels) {
						Object l = m.invoke(e, (Object[]) null);
						if (first) {
							first = false;
						} else {
							label.append(' ');
						}
						label.append(l.toString());
					}

					Object description = null;
					if (getDescription != null) {
						description = getDescription.invoke(e, (Object[]) null);
					}
					SelectItem item = new SelectItem(value, label.toString());
					if (getDescription != null) {
						item.setDescription(description.toString());
					}
					result.add(item);
				}
			}
		} catch (Exception ex) {
			logger.error("Creating select items.", ex);
		}

		return result;
	}

	public static List<SelectItem> entities2SelectItems(
			List<? extends Object> entities, String itemValue,
			String[] itemLabels) {
		return entities2SelectItems(entities, itemValue, itemLabels, null);
	}

	public static List<SelectItem> entities2SelectItems(
			List<? extends Object> entities, String itemValue,
			String itemLabel, String itemDescription) {
		return entities2SelectItems(entities, itemValue,
				new String[] { itemLabel }, itemDescription);
	}

	public static List<SelectItem> entities2SelectItems(
			List<? extends Object> entities, String itemValue, String itemLabel) {
		return entities2SelectItems(entities, itemValue,
				new String[] { itemLabel }, null);
	}

	public static List<SelectItem> entities2SelectItems2(
			List<? extends Object> entities, String itemValue,
			String... itemLabels) {
		List<SelectItem> result = new ArrayList<SelectItem>();

		if (entities == null || entities.size() == 0 || itemValue == null
				|| itemValue.equals("") || itemLabels == null
				|| itemLabels.length == 0) {
			return result;
		}

		try {
			for (Object e : entities) {
				if (e == null) {
					continue;
				}
				Object value = PropertyUtils.getProperty(e, itemValue);
				if (value == null) {
					continue;
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < itemLabels.length; i++) {
					String name = itemLabels[i];
					try {
						Object label = PropertyUtils.getProperty(e, name);
						if (label != null) {
							sb.append(label.toString());
						}
					} catch (Exception ex) {
						sb.append(name);
					}
				}
				SelectItem item = new SelectItem(value, sb.toString());
				result.add(item);
			}
		} catch (Exception ex) {
			logger.error("Creating select items.", ex);
		}

		return result;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}
}
