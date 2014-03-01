package it.goldmanager.view;

import it.goldmanager.business.AttovenditaB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AttovenditaSV {

	private List<Attovendita> listatto = new ArrayList<Attovendita>();
	private Attovendita selectedItem;

	@PostConstruct
	public void postConstruct() {
		Cliente cli = (Cliente) HttpJSFUtil.getRequestMap("cliente");
		if (cli != null) {
			listatto.addAll(cli.getAttovenditas());
		} else {
			AttovenditaB attoB = new AttovenditaB();
			listatto = attoB.getAllAttovendita();
		}
	}

	public void deleteAttovendita() {
		GoldmanagerLogger.debug(AttovenditaSV.class, "deleteAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		AttovenditaB attoB = new AttovenditaB();
		attoB.delAttovendita(selectedItem.getIdAttovendita());
		listatto.remove(selectedItem);
	}

	public String viewAttovendita() {
		GoldmanagerLogger.debug(AttovenditaSV.class, "viewAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		HttpJSFUtil.getRequest().setAttribute("id_Atto", String.valueOf(selectedItem.getIdAttovendita()));
		HttpJSFUtil.flashScope().put("idattovenditaflash", String.valueOf(selectedItem.getIdAttovendita()));
		return "goToStampaAtto";
	}

	public void noConfirm() {
		selectedItem = new Attovendita();
	}

	public List<Attovendita> getListatto() {
		return listatto;
	}

	public void setListatto(List<Attovendita> listatto) {
		this.listatto = listatto;
	}

	public Attovendita getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Attovendita selectedItem) {
		this.selectedItem = selectedItem;
	}
}
