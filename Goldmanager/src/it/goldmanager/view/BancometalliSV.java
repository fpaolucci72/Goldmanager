package it.goldmanager.view;

import it.goldmanager.business.BancometalliB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Bancometalli;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class BancometalliSV {

	private List<Bancometalli> listBancometalli = new ArrayList<Bancometalli>();
	private Bancometalli selectedItem;

	@PostConstruct
	public void postConstruct() {
		BancometalliB bmb = new BancometalliB();
		listBancometalli = bmb.getBancometalli();
	}

	public void deleteBancometalli() {
		GoldmanagerLogger.debug(BancometalliSV.class, "deleteBancometalli init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BancometalliB bmb = new BancometalliB();
		bmb.delBancometalli(selectedItem.getIdBancometalli());
		listBancometalli.remove(selectedItem);
	}

	public String goToMod() {
		GoldmanagerLogger.debug(BancometalliSV.class, "goToMod init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		HttpJSFUtil.setManagedBean("bancometalli", selectedItem);
		return "goToBancometalliMod";
	}

	public void noConfirm() {
		selectedItem = new Bancometalli();
	}

	public List<Bancometalli> getListBancometalli() {
		return listBancometalli;
	}

	public void setListBancometalli(List<Bancometalli> listBancometalli) {
		this.listBancometalli = listBancometalli;
	}

	public Bancometalli getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Bancometalli selectedItem) {
		this.selectedItem = selectedItem;
	}
}
