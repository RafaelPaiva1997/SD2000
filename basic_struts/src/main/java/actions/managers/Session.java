package actions.managers;

import actions.ActionModel;
import exceptions.PasswordException;
import exceptions.UserNotLoggedException;
import exceptions.UsernameException;
import models.pessoas.Pessoa;
import rmi.RMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Session extends ActionModel {

    private String username;
    private String password;
    private String usernameError;
    private String passwordError;

    public Session() {
        usernameError = "";
        passwordError = "";
    }

    public String index(Pessoa user) {
        if (user.isAdmin())
            return "admin-console";
        else
            return "voting-terminal";
    }

    public String login() {
        try {
            getUser();
            return index(user);
        } catch (UserNotLoggedException unle) {
            try {
                user = new Pessoa();
                if (!user.setUsername(username))
                    throw new UsernameException("Username Inválido!");

                RMI.connect();
                user = RMI.rmi.login(username, password);

                try {
                    putUser();
                } catch (UserNotLoggedException e) {
                    addActionError("error: rmi.login() returned null");
                    return "rmi-error";
                }

                return index(user);

            } catch (UsernameException ue) {
                usernameError = ue.getMessage();
                return INPUT;
            } catch (PasswordException pe) {
                passwordError = pe.getMessage();
                return INPUT;
            } catch (RemoteException | NotBoundException rmie) {
                addActionError(rmie.getMessage());
                return "rmi-error";
            }
        }
    }

    public String logout() {
        map.remove("user");
        return SUCCESS;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
