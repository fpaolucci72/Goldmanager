package it.goldmanager.view;

import it.goldmanager.business.ConfigurazioneB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Configurazione;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

@ManagedBean
@ViewScoped
public class ConfigurazioneV {

	private ArrayList<Configurazione> configurazioneList;

	@PostConstruct
	public void postConstruct() {
		ConfigurazioneB conf = new ConfigurazioneB();
		configurazioneList = (ArrayList<Configurazione>) conf.getConfigurazione();
	}

	public ArrayList<Configurazione> getConfigurazioneList() {
		return configurazioneList;
	}

	public void setConfigurazioneList(ArrayList<Configurazione> configurazioneList) {
		this.configurazioneList = configurazioneList;
	}

	public void onEdit(RowEditEvent event) {
		GoldmanagerLogger.debug(ConfigurazioneV.class, "onEdit", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		try {
			ConfigurazioneB confB = new ConfigurazioneB();
			confB.updateConfigurazione((Configurazione) event.getObject());
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.modifica.configurazione"), null));
	}
}
