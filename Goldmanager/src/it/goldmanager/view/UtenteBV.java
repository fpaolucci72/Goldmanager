package it.goldmanager.view;

import it.goldmanager.business.Login;
import it.goldmanager.common.CryptManager;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Utente;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class UtenteBV {

	private String oldpwd;
	private String newpwd;
	private String confirmpwd;

	public String changePwd() {
		GoldmanagerLogger.debug(UtenteBV.class, "changePwd", null);
		if (!newpwd.equals(confirmpwd)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cambiopwd.confirm.pwd"), null));
			return null;
		}
		GoldmanagerSession gms = GoldmanagerSession.getCurrentInstance();
		Utente utente = gms.getUtente();
		CryptManager crypt = new CryptManager("DES");
		String pwdLast = crypt.decryptHexString(utente.getPassword());
		if (!oldpwd.equals(pwdLast)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cambiopwd.old.pwd.required"), null));
			return null;
		}
		String encpwd = crypt.encryptHexString(newpwd);
		utente.setPassword(encpwd);
		Login l = new Login();
		l.updateUtente(utente);
		gms.setUtente(utente);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cambiopwd.pwd.cambiata"), null));
		return "goToHome";
	}

	public String getOldpwd() {
		return oldpwd;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
}
