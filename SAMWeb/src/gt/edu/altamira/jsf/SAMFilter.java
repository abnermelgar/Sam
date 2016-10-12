package gt.edu.altamira.jsf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import gt.edu.altamira.sam.jsf.JPAEntityPageBeanBase;
import gt.edu.altamira.sam.jsf.MenuBean;


/**
 * <p>
 * Filra:
 * 
 * Si no tiene sesión válida se redirecciona a login.jsf.
 * </p>
 * 
 * @author Enlaces
 * 
 */
public class SAMFilter implements Filter {

	protected static String SPECIAL_PATHS = "SPECIAL_PATHS";
	protected static String LOGIN_PAGE = "LOGIN_PAGE";
	protected static String WELCOME_PAGE = "WELCOME_PAGE";
	protected static String PAGES_WITHOUT_LOGIN = "PAGES_WITHOUT_LOGIN";
	protected static String AVAILABLE_PAGES_VAR = MenuBean.PAGES_SESSION;
	protected static String PROPERTIES = "SAMFilterProperties.properties";

	protected String[] availablePagesNoLogin;
	protected String[] specialPaths;
	protected String loginPage;
	protected String welcomePage;
	protected String sessionUserVariable;
	protected String availablePages;

	protected Logger log = Logger.getLogger(SAMFilter.class);

	@Override
	public void destroy() {
	}

	protected static String HEXPIRES = "Expires";
	protected static String HLAST_MODIFIED = "Last-Modified";
	protected static String HCACHE_CONTROL = "Cache-Control";
	protected static String VCACHE_CONTROL = "no-store, no-cache, must-revalidate, post-check=0, pre-check=0";
	protected static String HPRAGMA = "Pragma";
	protected static String VPRAGMA = "no-cache";
	protected static String JSF = ".jsf";

	public void addHeaders(String destino, ServletResponse response) {
		if (!(response instanceof HttpServletResponse))
			return;
		if (destino == null || !destino.endsWith(JSF))
			return;
		HttpServletResponse hr = (HttpServletResponse) response;
		hr.setHeader(HEXPIRES, "-1");
		hr.setDateHeader(HLAST_MODIFIED, System.currentTimeMillis());
		hr.setHeader(HCACHE_CONTROL, VCACHE_CONTROL);
		hr.setHeader(HPRAGMA, VPRAGMA);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {

			HttpServletRequest hr = (HttpServletRequest) request;
			HttpSession session = hr.getSession(true);
			String uri = hr.getRequestURI();
			String ctxPath = hr.getContextPath();

			try {
				// logout
				if (request.getParameter("exit") != null) {
					String flag = request.getParameter("exit").toString();
					if (flag.equals("true")) {
						if (session != null) {
							Enumeration<String> attribs = session
									.getAttributeNames();
							while (attribs.hasMoreElements()) {
								String aString = attribs.nextElement();
								System.out.println(aString);
								session.removeAttribute(aString);
							}
							session.invalidate();
							((HttpServletRequest) request).logout();
							return;
						}
					}
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			// Path especial?

			for (String path : specialPaths) {
				if (uri.startsWith(ctxPath + path, 0)) {
					chain.doFilter(request, response);
					return;
				}
			}

			// Está firmado?

			// Si tenemos cuenta activa y la página es PAGINA_LOGIN
			// y no vamos a la página de inicio, redireccionemos a ella
			if (session != null
					&& session.getAttribute(sessionUserVariable) != null
					&& uri.endsWith(loginPage)) {
				log.info("CAMFIler.doFilter() URI redirecting to welcome page from: "
						+ uri);
				((HttpServletResponse) response).sendRedirect(ctxPath
						+ welcomePage);

				return;
			}

			// Página sin Login?
			for (String pagina : availablePagesNoLogin) {
				if (uri.endsWith(pagina)) {
					addHeaders(pagina, response);
					chain.doFilter(request, response);
					return;
				}
			}

			if (session == null
					|| session.getAttribute(sessionUserVariable) == null) {
				log.info("CAMFilter.doFilter() No login: " + uri);
				if (loginPage.startsWith("http://")
						|| loginPage.startsWith("https://"))

					((HttpServletResponse) response).sendRedirect(loginPage);
				else
					((HttpServletResponse) response).sendRedirect(ctxPath
							+ loginPage);
				return;
			}

			if (session.getAttribute(availablePages) == null) {
				log.info("CAMFiler.doFilter() No permissions for page " + uri
						+ " ./No avaible pages on session yet");

				if (!uri.endsWith(welcomePage)) {
					((HttpServletResponse) response).sendRedirect(ctxPath
							+ welcomePage);
					return;
				}
			} else {
				List<String> permissionPages = (List<String>) session
						.getAttribute(availablePages);

				boolean hasPermission = false;
				for (String page : permissionPages) {
					if (uri.endsWith(welcomePage) || uri.endsWith(page) || uri.endsWith("changepassword.jsf")) {
						hasPermission = true;
						log.debug("Passed");
						break;
					}
				}
				if (!hasPermission) {
					((HttpServletResponse) response).sendRedirect(ctxPath
							+ welcomePage);
					// ((HttpServletResponse)
					// response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					return;
				}
			}

		}
		if (((HttpServletResponse) response).getStatus() == HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
			System.out.println("Error, para redirect.");
		chain.doFilter(request, response);
	}

	public String obtenerValor(Properties props, String clave)
			throws ServletException {
		String s = props.getProperty(clave, "").trim();
		if (s.equals(""))
			throw new ServletException("CAMFilter:" + clave + " not found.");
		return s;
	}

	public void init(FilterConfig config) throws ServletException {
		Properties props = new Properties();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		try {
			InputStream in = cl.getResourceAsStream(PROPERTIES);
			try {
				props.load(in);
				specialPaths = obtenerValor(props, SPECIAL_PATHS).split(",");
				loginPage = obtenerValor(props, LOGIN_PAGE);
				availablePagesNoLogin = obtenerValor(props, PAGES_WITHOUT_LOGIN)
						.split(",");
				welcomePage = obtenerValor(props, WELCOME_PAGE);

				sessionUserVariable = JPAEntityPageBeanBase.LOGGED_USER;
				availablePages = AVAILABLE_PAGES_VAR;
			} finally {
				in.close();
			}
		} catch (Exception ex) {
			log.error("", ex);
			if (ex instanceof ServletException)
				throw (ServletException) ex;
			else
				throw new ServletException(ex);
		}
	}

}
