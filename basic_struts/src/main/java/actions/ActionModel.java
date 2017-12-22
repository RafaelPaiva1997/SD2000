package actions;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.media.sound.InvalidFormatException;
import exceptions.EmptyQueryException;
import exceptions.UserNotLoggedException;
import models.pessoas.Pessoa;
import org.apache.struts2.interceptor.SessionAware;
import rmi.RMI;

import java.rmi.RemoteException;
import java.util.EmptyStackException;
import java.util.Map;

public abstract class ActionModel extends ActionSupport implements SessionAware {

    protected Pessoa user;
    protected Map<String, Object> map;

    public Pessoa getUser() {
        return user;
    }

    protected void fetchUser() throws UserNotLoggedException {
        if (!map.containsKey("user"))
            throw new UserNotLoggedException();
        user = (Pessoa) map.get("user");
        try {
            user = (Pessoa) RMI.rmi.get("pessoas", "ID=" + user.getId());
        } catch (RemoteException | EmptyQueryException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    protected void putUser() throws UserNotLoggedException {
        if (user == null)
            throw new UserNotLoggedException();
        map.put("user", user);
    }

    public String validateAdmin() {
        try {
            fetchUser();
            if (!user.isAdmin())
                return "user-not-admin";
            return SUCCESS;
        } catch (UserNotLoggedException unle) {
            return "user-not-logged";
        }
    }

    public String validateUser() {
        try {
            fetchUser();
            return SUCCESS;
        } catch (UserNotLoggedException unle) {
            return "user-not-logged";
        }
    }

    public String voltar() {
        String validation;
        if (!(validation = validateAdmin()).equals("success"))
            return validation;

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.map = map;
    }
}
