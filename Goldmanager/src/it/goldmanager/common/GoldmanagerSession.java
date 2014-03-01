package it.goldmanager.common;

import it.goldmanager.databean.Utente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GoldmanagerSession {

	private boolean administrator;
	private Utente utente;

	public static GoldmanagerSession getCurrentInstance() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		GoldmanagerSession goldmanagerSession = (GoldmanagerSession) facesContext.getExternalContext().getSessionMap().get("goldmanagerSession");
		if (goldmanagerSession == null) {
			GoldmanagerLogger.debug(GoldmanagerSession.class, "Session nuova", null);
			goldmanagerSession = new GoldmanagerSession();
			facesContext.getExternalContext().getSessionMap().put("goldmanagerSession", goldmanagerSession);
		}
		return goldmanagerSession;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
}
