package it.goldmanager.view;

import it.goldmanager.business.AttovenditaB;
import it.goldmanager.business.ClienteB;
import it.goldmanager.common.GoldmanagerDefine;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Attovenditaattr;
import it.goldmanager.databean.Cliente;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class AttovenditaBV {

	@ManagedProperty(value = "#{attovenditaattrV}")
	private AttovenditaattrV attovenditaattrV;
	@ManagedProperty(value = "#{attovenditaV}")
	private AttovenditaV attovenditaV;

	public String saveAttovenditaAttr() {
		GoldmanagerLogger.info(AttovenditaattrV.class, "saveAttovenditaAttr init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Set<Attovenditaattr> ava = new HashSet<Attovenditaattr>(attovenditaattrV.getAttoList());
		if (ava.size() < 1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.vendita.required"), null));
			return null;
		}
		Attovendita avendita = new Attovendita();
		String idhidden = (String) HttpJSFUtil.getRequestParameter("addattr:hiddenId");
		if (idhidden == null || idhidden.equals("")) {
			avendita.setCliente(attovenditaV.getCliente());
		} else {
			ClienteB cli = new ClienteB();
			Cliente cliente = cli.getCliente(idhidden);
			avendita.setCliente(cliente);
		}
		avendita.setInserito(new Date());
		avendita.setInseritoDa(GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BigDecimal bd = new BigDecimal(0);
		Set<Attovenditaattr> attovenditaF = new HashSet<Attovenditaattr>();
		String gruppo = "";
		for (Attovenditaattr avatt : ava) {
			if (!gruppo.equals("") && !gruppo.equals(avatt.getTipometallo().getGruppoTipometallo())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.tipometallo.diverso"), null));
				return null;
			}
			gruppo = avatt.getTipometallo().getGruppoTipometallo();
			avatt.setAttovendita(avendita);
			BigDecimal subtot = new BigDecimal(avatt.getTotale());
			bd = bd.add(subtot);
			attovenditaF.add(avatt);
		}
		if (gruppo.equals("1")) {
			avendita.setTipoMetallo(GoldmanagerDefine.ORO);
		} else {
			avendita.setTipoMetallo(GoldmanagerDefine.ARGENTO);
		}
		avendita.getAttovenditaattrs().addAll(attovenditaF);
		avendita.setTotaleVendita(String.valueOf(bd));
		AttovenditaB avb = new AttovenditaB();
		avb.saveAttovendita(avendita);
		GoldmanagerLogger.debug(AttovenditaattrV.class, "saveAttovenditaAttr salvato atto vendita e suoi attributi", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "goToHome";
	}

	public String savePrintAttovenditaAttr() {
		GoldmanagerLogger.info(AttovenditaattrV.class, "savePrintAttovenditaAttr init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Set<Attovenditaattr> ava = new HashSet<Attovenditaattr>(attovenditaattrV.getAttoList());
		if (ava.size() < 1) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.vendita.required"), null));
			return null;
		}		
		Attovendita avendita = new Attovendita();
		String idhidden = (String) HttpJSFUtil.getRequestParameter("addattr:hiddenId");	
		if (idhidden == null || idhidden.equals("")) {			
			avendita.setCliente(attovenditaV.getCliente());
		} else {			
			ClienteB cli = new ClienteB();
			Cliente cliente = cli.getCliente(idhidden);
			avendita.setCliente(cliente);
		}		
		avendita.setInserito(new Date());
		avendita.setInseritoDa(GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BigDecimal bd = new BigDecimal(0);
		Set<Attovenditaattr> attovenditaF = new HashSet<Attovenditaattr>();
		String gruppo = "";
		for (Attovenditaattr avatt : ava) {
			if (!gruppo.equals("") && !gruppo.equals(avatt.getTipometallo().getGruppoTipometallo())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.tipometallo.diverso"), null));
				return null;
			}
			gruppo = avatt.getTipometallo().getGruppoTipometallo();
			avatt.setAttovendita(avendita);
			BigDecimal subtot = new BigDecimal(avatt.getTotale());
			bd = bd.add(subtot);
			attovenditaF.add(avatt);
		}
		if (gruppo.equals("1")) {
			avendita.setTipoMetallo(GoldmanagerDefine.ORO);
		} else {
			avendita.setTipoMetallo(GoldmanagerDefine.ARGENTO);
		}
		avendita.getAttovenditaattrs().addAll(attovenditaF);
		avendita.setTotaleVendita(String.valueOf(bd));
		AttovenditaB avb = new AttovenditaB();
		int id_atto = avb.saveAttovendita(avendita);
		HttpJSFUtil.getRequest().setAttribute("id_Atto", String.valueOf(id_atto));
		HttpJSFUtil.flashScope().put("idattovenditaflash", id_atto);
		GoldmanagerLogger.debug(AttovenditaattrV.class, "savePrintAttovenditaAttr salvato atto vendita e suoi attributi", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "goToStampaAtto";
	}

	public AttovenditaattrV getAttovenditaattrV() {
		return attovenditaattrV;
	}

	public void setAttovenditaattrV(AttovenditaattrV attovenditaattrV) {
		this.attovenditaattrV = attovenditaattrV;
	}

	public AttovenditaV getAttovenditaV() {
		return attovenditaV;
	}

	public void setAttovenditaV(AttovenditaV attovenditaV) {
		this.attovenditaV = attovenditaV;
	}
}
