package it.goldmanager.security;

import it.goldmanager.business.Login;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Utente;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class AuthenticationBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String AUTH_KEY = "goldmanager";
	private String username;
	private String password;

	public boolean isLoggedIn() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_KEY) != null;
	}

	public String login() {
		GoldmanagerLogger.debug(AuthenticationBean.class, "login init", null);
		Login lg = new Login();
		Utente utente = lg.getUtente(username, password);
		if (utente != null) {
			GoldmanagerSession gs = GoldmanagerSession.getCurrentInstance();
			gs.setUtente(utente);
		} else {
			GoldmanagerLogger.info(AuthenticationBean.class, "Data op: " + GoldmanagerUtility.today() + " utente non presente sul database username: " + username, null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.utente.novalid"), null));
			return "/index.xhtml";
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, username);
		GoldmanagerLogger.info(AuthenticationBean.class, "Data op: " + GoldmanagerUtility.today() + " utente nel sistema: " + username, null);
		return "goToHome";
	}

	public String logout() {
		GoldmanagerLogger.info(AuthenticationBean.class, "logout", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(AUTH_KEY);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
		} catch (IOException e) {
			GoldmanagerLogger.error(AuthenticationBean.class, "Data op: " + GoldmanagerUtility.today() + " errore nel logout", null);
			e.printStackTrace();
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}