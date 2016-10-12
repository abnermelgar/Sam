package gt.edu.altamira.ejb;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

import gt.edu.altamira.sam.entity.sec.User;
import gt.edu.altamira.sam.entity.sec.UserLogin;



@Local
public interface RequestLoginManagerLocal {

	public UserLogin saveRequest(HttpServletRequest request, String action, User user);

}
