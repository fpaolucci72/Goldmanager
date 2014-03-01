package it.goldmanager.view;

import it.goldmanager.business.ClienteB;
import it.goldmanager.business.TipometalloB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Cliente;
import it.goldmanager.databean.Tipometallo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class AttovenditaV {

	private Cliente cliente;
	private List<SelectItem> metalliList = new ArrayList<SelectItem>();
	private String metallo;
	private List<SelectItem> caratiList = new ArrayList<SelectItem>();
	private String prezzoRiferimento;
	private String pesoNetto;
	private String prezzoTotale;

	@PostConstruct
	public void postConstruct() {
		String idCliente = (String) HttpJSFUtil.getRequest().getAttribute("id_Cliente");
		if (idCliente != null && !idCliente.equals("")) {
			ClienteB cli = new ClienteB();
			cliente = cli.getCliente(idCliente);
		} else {
			cliente = (Cliente) HttpJSFUtil.getRequestMap("cliente");
		}
		metalliList.add(new SelectItem(new String("1"), "Oro"));
		metalliList.add(new SelectItem(new String("2"), "Argento"));
	}

	public void calculateWeightPrice() {
		GoldmanagerLogger.debug(AttovenditaV.class, "calculateWeight init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String pn = (String) HttpJSFUtil.getRequestParameter("addattr:peso");
		String scarto = (String) HttpJSFUtil.getRequestParameter("addattr:scarto");
		String pr = (String) HttpJSFUtil.getRequestParameter("addattr:prezzoRiferimento");
		String tot = (String) HttpJSFUtil.getRequestParameter("addattr:totale");
		if (!pn.equals("") && !scarto.equals("")) {
			BigDecimal bd = new BigDecimal(pn);
			bd = bd.subtract(new BigDecimal(scarto));
			pesoNetto = String.valueOf(bd);
		}
		if (!pesoNetto.equals("") && !pr.equals("")) {
			BigDecimal bd1 = new BigDecimal(pesoNetto);
			bd1 = bd1.multiply(new BigDecimal(pr));
			prezzoTotale = String.valueOf(bd1);
		}
		if (!tot.equals("")) {
			BigDecimal bd2 = new BigDecimal(tot);
			bd2 = bd2.divide(new BigDecimal(pesoNetto), BigDecimal.ROUND_HALF_UP);
			prezzoRiferimento = String.valueOf(bd2);
		}
		GoldmanagerLogger.debug(AttovenditaV.class, "calculateWeight pesoNetto e prezzototale: " + pesoNetto + " " + prezzoTotale, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	public void handleMetalChange() {
		GoldmanagerLogger.debug(AttovenditaV.class, "handleMetalChange init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String metallo = (String) HttpJSFUtil.getRequestParameter("addattr:metallo_input");
		if (metallo != null && !metallo.equals("")) {
			prezzoRiferimento = "";
			caratiList = new ArrayList<SelectItem>();
			TipometalloB tb = new TipometalloB();
			List<Tipometallo> ltm = tb.getTipometallo(metallo);
			for (Tipometallo tm : ltm) {
				caratiList.add(new SelectItem(tm.getIdTipometallo(), tm.getNomeTipometallo()));
			}
		} else {
			caratiList = new ArrayList<SelectItem>();
			prezzoRiferimento = "";
		}
	}

	public void handlePrezzoChange() {
		GoldmanagerLogger.debug(AttovenditaV.class, "handlePrezzoChange init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String carati_input = (String) HttpJSFUtil.getRequestParameter("addattr:carati_input");
		if (carati_input != null && !carati_input.equals("")) {
			TipometalloB tb = new TipometalloB();
			Tipometallo tm = tb.getTipometalloId(carati_input);
			int i = tm.getConfigurazione().getIdConfigurazione();
			if (i == 1) {
				// è oro fare i calcoli
				String prezzo = tm.getConfigurazione().getDescConfigurazione();
				if (tm.getNomeTipometallo().equals("999")) {
					prezzoRiferimento = prezzo;
				} else {
					String carati = tm.getNomeTipometallo();
					BigDecimal bd = GoldmanagerUtility.percentageForGold(new BigDecimal(prezzo), new BigDecimal(carati));
					prezzoRiferimento = String.valueOf(bd);
				}
			} else {
				// è argento
				prezzoRiferimento = tm.getConfigurazione().getDescConfigurazione();
			}
		} else {
			prezzoRiferimento = "";
		}
	}

	public String getPesoNetto() {
		return pesoNetto;
	}

	public void setPesoNetto(String pesoNetto) {
		this.pesoNetto = pesoNetto;
	}

	public String getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(String prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<SelectItem> getMetalliList() {
		return metalliList;
	}

	public void setMetalliList(List<SelectItem> metalliList) {
		this.metalliList = metalliList;
	}

	public String getMetallo() {
		return metallo;
	}

	public void setMetallo(String metallo) {
		this.metallo = metallo;
	}

	public List<SelectItem> getCaratiList() {
		return caratiList;
	}

	public void setCaratiList(List<SelectItem> caratiList) {
		this.caratiList = caratiList;
	}

	public String getPrezzoRiferimento() {
		return prezzoRiferimento;
	}

	public void setPrezzoRiferimento(String prezzoRiferimento) {
		this.prezzoRiferimento = prezzoRiferimento;
	}
}
