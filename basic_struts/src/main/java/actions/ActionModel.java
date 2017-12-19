package actions;

import com.opensymphony.xwork2.ActionSupport;
import exceptions.UserNotLoggedException;
import models.pessoas.Pessoa;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class ActionModel extends ActionSupport implements SessionAware {

    protected Pessoa user;
    protected Map<String, Object> map;

    protected void getUser() throws UserNotLoggedException {
        if (!map.containsKey("user"))
            throw new UserNotLoggedException();
        user = (Pessoa) map.get("user");
    }

    protected void putUser() throws UserNotLoggedException {
        if (user == null)
            throw new UserNotLoggedException();
        map.put("user", user);
    }

    public String validateUser() {
        try {
            getUser();
            if (!user.isAdmin())
                return "user-not-admin";
            return SUCCESS;
        } catch (UserNotLoggedException unle) {
            return "user-not-logged";
        }
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.map = map;
    }
}
