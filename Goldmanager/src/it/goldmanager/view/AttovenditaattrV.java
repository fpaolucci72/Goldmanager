package it.goldmanager.view;

import it.goldmanager.business.TipometalloB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Attovenditaattr;
import it.goldmanager.databean.Tipometallo;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AttovenditaattrV {

	private ArrayList<Attovenditaattr> attoList = new ArrayList<Attovenditaattr>();
	private Attovenditaattr av = new Attovenditaattr();
	private Attovenditaattr selectedItem;
	@ManagedProperty(value = "#{attovenditaV}")
	private AttovenditaV attovenditaV;

	public Attovenditaattr addAttr() {
		GoldmanagerLogger.debug(AttovenditaattrV.class, "addAttr init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String carati = (String) HttpJSFUtil.getRequestParameter("addattr:carati_input");
		String prezzoRif = (String) HttpJSFUtil.getRequestParameter("addattr:prezzoRiferimento");
		String totale = (String) HttpJSFUtil.getRequestParameter("addattr:totale");
		TipometalloB tmb = new TipometalloB();
		Tipometallo tm = tmb.getTipometalloId(carati);
		Attovenditaattr attov = (Attovenditaattr) HttpJSFUtil.getRequestMap("attovenditaattr");
		attov.setTotale(totale);
		attov.setPrezzoRiferimento(prezzoRif);
		attov.setTipometallo(tm);
		BigDecimal bd = new BigDecimal(attov.getPeso());
		bd = bd.subtract(new BigDecimal(attov.getScarto()));
		attov.setPesoNetto(String.valueOf(bd));
		attoList.add(attov);
		av = new Attovenditaattr();
		HttpJSFUtil.setManagedBean("attovenditaattr", av);
		attovenditaV.setPrezzoTotale("");
		attovenditaV.setPesoNetto("");
		attovenditaV.setPrezzoRiferimento("");
		return av;
	}

	public String delRowAttr() {
		GoldmanagerLogger.debug(AttovenditaattrV.class, "delRowAttr init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		attoList.remove(selectedItem);
		return null;
	}

	public AttovenditaV getAttovenditaV() {
		return attovenditaV;
	}

	public void setAttovenditaV(AttovenditaV attovenditaV) {
		this.attovenditaV = attovenditaV;
	}

	public ArrayList<Attovenditaattr> getAttolist() {
		return attoList;
	}

	public ArrayList<Attovenditaattr> getAttoList() {
		return attoList;
	}

	public void setAttoList(ArrayList<Attovenditaattr> attoList) {
		this.attoList = attoList;
	}

	public Attovenditaattr getAv() {
		return av;
	}

	public void setAv(Attovenditaattr av) {
		this.av = av;
	}

	public Attovenditaattr getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Attovenditaattr selectedItem) {
		this.selectedItem = selectedItem;
	}
}
