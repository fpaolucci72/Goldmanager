package it.goldmanager.view;

import it.goldmanager.business.AttovenditaB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerReport;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Attovenditaattr;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@ViewScoped
public class AttovenditaStampaV {

	private Attovendita attovendita;
	private List<Attovenditaattr> listattoattr = new ArrayList<Attovenditaattr>();
	private String tipoPag;
	private String numAtto;

	@PostConstruct
	public void postConstruct() {
		
		String idAtto = (String) HttpJSFUtil.getRequest().getAttribute("id_Atto");
		AttovenditaB attoB = new AttovenditaB();
		attovendita = attoB.getAttovendita(idAtto);
		for (Attovenditaattr ava : attovendita.getAttovenditaattrs()) {
			Attovenditaattr avatt = new Attovenditaattr();
			avatt.setDescOggetto(ava.getDescOggetto());
			avatt.setPeso(ava.getPeso());
			avatt.setPesoNetto(ava.getPesoNetto());
			avatt.setPrezzoRiferimento(ava.getPrezzoRiferimento());
			avatt.setTipometallo(ava.getTipometallo());
			avatt.setTotale(ava.getTotale());
			avatt.setScarto(ava.getScarto());
			listattoattr.add(avatt);
		}
		String tp = attovendita.getTipoPagamento();
		if (tp != null) {
			tipoPag = tp;
		}
		String na = attovendita.getNumeroAttoVendita();
		if (na != null) {
			numAtto = na;
		}
	}

	public String printAttovendita() {
		GoldmanagerLogger.info(AttovenditaStampaV.class, "printAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String idhidden = (String) HttpJSFUtil.getRequestParameter("addattr:hiddenId");
		AttovenditaB avb = new AttovenditaB();
		Attovendita atto = avb.getAttovendita(idhidden);
		String numatto = (String) HttpJSFUtil.getRequestParameter("addattr:numeroAttoVendita");
		String tp = (String) HttpJSFUtil.getRequestParameter("addattr:tipoPagamento");
		atto.setNumeroAttoVendita(numatto);
		atto.setTipoPagamento(tp);
		avb.updateAttovendita(atto);
		GoldmanagerReport gr = new GoldmanagerReport();
		try {
			gr.pdfReportVendita(atto);
		} catch (JRException e) {
			GoldmanagerLogger.error(AttovenditaStampaV.class, "printAttovendita error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		} catch (IOException e) {
			GoldmanagerLogger.error(AttovenditaStampaV.class, "printAttovendita error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		}
		GoldmanagerLogger.debug(AttovenditaStampaV.class, "printAttovendita salvato atto vendita e suoi attributi", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "/pages/home.xhtml";
	}
	
	public String createNumAttovendita() {
		GoldmanagerLogger.info(AttovenditaStampaV.class, "createNumAttovendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String idhidden = (String) HttpJSFUtil.getRequestParameter("addattr:hiddenId");
		AttovenditaB avb = new AttovenditaB();
		Attovendita atto = avb.getAttovendita(idhidden);
		String numatto = (String) HttpJSFUtil.getRequestParameter("addattr:numeroAttoVendita");
		String tp = (String) HttpJSFUtil.getRequestParameter("addattr:tipoPagamento");
		atto.setNumeroAttoVendita(numatto);
		atto.setTipoPagamento(tp);
		avb.updateAttovendita(atto);	
		GoldmanagerLogger.debug(AttovenditaStampaV.class, "createNumAttovendita atto vendita e suoi attributi", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.mod.attovendita") + " " + atto.getNumeroAttoVendita(), null));
		return "goToHome";
	}

	public String getTipoPag() {
		return tipoPag;
	}

	public void setTipoPag(String tipoPag) {
		this.tipoPag = tipoPag;
	}

	public String getNumAtto() {
		return numAtto;
	}

	public void setNumAtto(String numAtto) {
		this.numAtto = numAtto;
	}

	public Attovendita getAttovendita() {
		return attovendita;
	}

	public void setAttovendita(Attovendita attovendita) {
		this.attovendita = attovendita;
	}

	public List<Attovenditaattr> getListattoattr() {
		return listattoattr;
	}

	public void setListattoattr(List<Attovenditaattr> listattoattr) {
		this.listattoattr = listattoattr;
	}
}
