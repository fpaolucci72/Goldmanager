package it.goldmanager.view;

import it.goldmanager.business.AttovenditaB;
import it.goldmanager.business.BancometalliB;
import it.goldmanager.business.BollavenditaB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerReport;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Attovenditaattr;
import it.goldmanager.databean.Bancometalli;
import it.goldmanager.databean.Bollavendita;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.event.FlowEvent;

@ManagedBean
@ViewScoped
public class BollavenditaV {

	private ArrayList<Bancometalli> listBancometalli = new ArrayList<Bancometalli>();
	private Map<String, String> listAtti;
	private List<String> selectedAtti;
	private String pesoTotale;
	private String valoreGr;
	private String numeroDdt;
	private String numeroFda;
	private String importo;
	private String tipomet;
	private String selectedBm;
	private List<Attovenditaattr> listAttr = new ArrayList<Attovenditaattr>();

	public BollavenditaV() {
		String tm = (String) HttpJSFUtil.getRequestParameter("tm");
		if (tm != null && tm.equals("o")) {
			this.setTipomet("ORO");
		} else {
			this.setTipomet("ARGENTO");
		}
		AttovenditaB av = new AttovenditaB();
		ArrayList<Attovendita> atti = av.getAllAttovendita(this.tipomet);
		listAtti = new HashMap<String, String>();
		BancometalliB bm = new BancometalliB();
		listBancometalli = bm.getBancometalli();
		for (Attovendita a : atti) {
			listAtti.put(GoldmanagerUtility.nullStr(a.getNumeroAttoVendita()), String.valueOf(a.getIdAttovendita()));
		}
	}

	public String onFlowProcess(FlowEvent event) {
		GoldmanagerLogger.debug(BollavenditaV.class, "onFlowProcess init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String bm = (String) HttpJSFUtil.getRequestParameter("bolla:bancometalli_input");
		if (bm != null) {
			this.setSelectedBm(bm);
		}
		AttovenditaB av = new AttovenditaB();
		BigDecimal bd = new BigDecimal(0);
		listAttr = new ArrayList<Attovenditaattr>();
		for (String s : selectedAtti) {
			Attovendita a = av.getAttovendita(s);
			for (Attovenditaattr attve : a.getAttovenditaattrs()) {
				bd = bd.add(new BigDecimal(attve.getPesoNetto()));
				listAttr.add(attve);
			}
		}
		pesoTotale = String.valueOf(bd);
		return event.getNewStep();
	}

	public String savePrintBollaDdt() {
		GoldmanagerLogger.debug(BollavenditaV.class, "savePrintBollaDdt init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		if (numeroDdt == null || numeroDdt.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.bolladdt.numero.required"), null));
			return null;
		}
		Bollavendita bv = new Bollavendita();
		bv.setPesoGrBolla(pesoTotale);
		bv.setTipometalloBolla(this.getTipomet());
		bv.setTotaleBolla(importo);
		bv.setValoreGrBolla(valoreGr);
		bv.setNumeroDdt(numeroDdt);
		bv.setNumeroFda(numeroFda);
		bv.setInserito(new Date());
		bv.setInseritoDa(GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Set<Attovendita> ava = new HashSet<Attovendita>();
		Attovendita atto;
		for (String si : selectedAtti) {
			AttovenditaB av = new AttovenditaB();
			atto = av.getAttovendita(si);
			ava.add(atto);
		}
		bv.setAttovenditas(ava);
		BancometalliB bm = new BancometalliB();
		Bancometalli banco = bm.getBancometalli(this.getSelectedBm());
		bv.setBancometalli(banco);
		bv.getAttovenditas().addAll(ava);
		BollavenditaB bb = new BollavenditaB();
		int i = bb.saveBollavendita(bv);
		if (i > 0) {
			GoldmanagerReport gr = new GoldmanagerReport();
			try {
				gr.pdfReportDdt(bv, banco);
			} catch (JRException e) {
				GoldmanagerLogger.error(AttovenditaattrV.class, "savePrintBollaDdt error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
				e.printStackTrace();
			} catch (IOException e) {
				GoldmanagerLogger.error(AttovenditaattrV.class, "savePrintBollaDdt error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
				e.printStackTrace();
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("bollaV");
		return "/pages/home.xhtml";
	}

	public String savePrintBollaFda() {
		GoldmanagerLogger.debug(BollavenditaV.class, "savePrintBollaFda init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Bollavendita bv = new Bollavendita();
		bv.setPesoGrBolla(pesoTotale);
		bv.setTipometalloBolla(this.getTipomet());
		bv.setTotaleBolla(importo);
		bv.setValoreGrBolla(valoreGr);
		bv.setNumeroFda(numeroFda);
		bv.setInserito(new Date());
		bv.setInseritoDa(GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		Set<Attovendita> ava = new HashSet<Attovendita>();
		Attovendita atto;
		for (String si : selectedAtti) {
			AttovenditaB av = new AttovenditaB();
			atto = av.getAttovendita(si);
			ava.add(atto);
		}
		bv.setAttovenditas(ava);
		BancometalliB bm = new BancometalliB();
		Bancometalli banco = bm.getBancometalli(this.getSelectedBm());
		bv.setBancometalli(banco);
		bv.getAttovenditas().addAll(ava);
		BollavenditaB bb = new BollavenditaB();
		int i = bb.saveBollavendita(bv);
		if (i > 0) {
			GoldmanagerReport gr = new GoldmanagerReport();
			try {
				gr.pdfReportFda(bv, banco);
			} catch (JRException e) {
				GoldmanagerLogger.error(AttovenditaattrV.class, "savePrintBollaFda error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
				e.printStackTrace();
			} catch (IOException e) {
				GoldmanagerLogger.error(AttovenditaattrV.class, "savePrintBollaFda error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
				e.printStackTrace();
			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("bollaV");
		return "/pages/home.xhtml";
	}

	public void calculateImport() {
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateImport init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BigDecimal bd = new BigDecimal(valoreGr);
		BigDecimal pesoG = new BigDecimal(0);
		for (Attovenditaattr av : listAttr) {
			String carati = av.getTipometallo().getNomeTipometallo();
			if (carati.equals("999")) {
				pesoG = pesoG.add(new BigDecimal(av.getPesoNetto()));
			} else {
				BigDecimal vg = GoldmanagerUtility.percentageForGold(av.getPesoNetto(), carati);
				pesoG = pesoG.add(vg);
			}
		}
		bd = bd.multiply(pesoG);
		importo = String.valueOf(bd);
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateImport importo: " + importo, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	public void calculateValoreGr() {
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateValoreGr init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BigDecimal bd = new BigDecimal(importo);
		BigDecimal pesoG = new BigDecimal(0);
		for (Attovenditaattr av : listAttr) {
			String carati = av.getTipometallo().getNomeTipometallo();
			if (carati.equals("999")) {
				pesoG = pesoG.add(new BigDecimal(av.getPesoNetto()));
			} else {
				BigDecimal vg = GoldmanagerUtility.percentageForGold(av.getPesoNetto(), carati);
				pesoG = pesoG.add(vg);
			}
		}
		bd = bd.divide((pesoG), BigDecimal.ROUND_HALF_UP);
		valoreGr = String.valueOf(bd);
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateValoreGr valoreGr: " + valoreGr, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	public List<Attovenditaattr> getListAttr() {
		return listAttr;
	}

	public void setListAttr(List<Attovenditaattr> listAttr) {
		this.listAttr = listAttr;
	}

	public String getImporto() {
		return importo;
	}

	public void setImporto(String importo) {
		this.importo = importo;
	}

	public String getNumeroDdt() {
		return numeroDdt;
	}

	public void setNumeroDdt(String numeroDdt) {
		this.numeroDdt = numeroDdt;
	}

	public String getNumeroFda() {
		return numeroFda;
	}

	public void setNumeroFda(String numeroFda) {
		this.numeroFda = numeroFda;
	}

	public String getValoreGr() {
		return valoreGr;
	}

	public void setValoreGr(String valoreGr) {
		this.valoreGr = valoreGr;
	}

	public String getPesoTotale() {
		return pesoTotale;
	}

	public void setPesoTotale(String pesoTotale) {
		this.pesoTotale = pesoTotale;
	}

	public Map<String, String> getListAtti() {
		return listAtti;
	}

	public void setListAtti(Map<String, String> listAtti) {
		this.listAtti = listAtti;
	}

	public List<String> getSelectedAtti() {
		return selectedAtti;
	}

	public void setSelectedAtti(List<String> selectedAtti) {
		this.selectedAtti = selectedAtti;
	}

	public ArrayList<Bancometalli> getListBancometalli() {
		return listBancometalli;
	}

	public void setListBancometalli(ArrayList<Bancometalli> listBancometalli) {
		this.listBancometalli = listBancometalli;
	}

	public String getTipomet() {
		return tipomet;
	}

	public void setTipomet(String tipomet) {
		this.tipomet = tipomet;
	}

	public String getSelectedBm() {
		return selectedBm;
	}

	public void setSelectedBm(String selectedBm) {
		this.selectedBm = selectedBm;
	}
}
