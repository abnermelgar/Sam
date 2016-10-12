package gt.edu.altamira.ejb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 * Session Bean implementation class RequestLoginManager
 */
@Stateless(mappedName = "ejb/RequestLoginManager")
@LocalBean
public class RequestLoginManager extends EjbManager implements RequestLoginManagerLocal {

	private static Logger logger = Logger.getLogger(RequestLoginManager.class);

	public RequestLoginManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserLogin saveRequest(HttpServletRequest request, String action, User user) {
		UserLogin userLog = new UserLogin();

		userLog.setUserAction(action);
		userLog.setUserAgent(request.getHeader("User-Agent"));
		userLog.setUserIp(getIpUsuario(request));
		userLog.setCreatedBy(user.getUserAlias());
		userLog.setDateCreated(new Date());
		userLog.setUser(user);

		// Get an UserAgentStringParser and analyze the requesting client
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
		userLog.setUserOs(agent.getOperatingSystem().getName());
		userLog.setUserDivice(agent.getDeviceCategory().getName());
		userLog.setUserBrowser(agent.getName());

		entityManager.persist(userLog);
		entityManager.flush();

		return userLog;
	}

	private String getIpUsuario(HttpServletRequest request) {
		String Ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip2 = addr.getHostAddress();
			byte[] ip3 = addr.getAddress();

			String ip3x = "";
			for (int i = 0; i <= ip3.length - 1; i++) {
				if (i > 0)
					ip3x += ".";
				ip3x += ip3[i] & 255;
			}

			logger.info("IP3X :" + ip3x);

			Ip = request.getRemoteAddr().trim().toString();

			int xx = Ip.split("\\.").length;

			// Validar que la IP tenga formato válido y que no sea localhost
			if (xx != 4 || Ip.equals("127.0.0.1"))
				return ip2;

		} catch (UnknownHostException e) {
			logger.error("error ", e);
		}

		return Ip;
	}

}
