package it.goldmanager.databean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Attovenditaattr implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idAttovenditaattr;
	private Attovendita attovendita;
	private Tipometallo tipometallo;
	private String descOggetto;
	private String peso;
	private String scarto;
	private String pesoNetto;
	private String totale;
	private String prezzoRiferimento;

	public Attovenditaattr() {
	}

	public Attovenditaattr(Attovendita attovendita, Tipometallo tipometallo, String descOggetto, String peso, String prezzoRiferimento, String scarto, String pesoNetto, String totale) {
		this.attovendita = attovendita;
		this.tipometallo = tipometallo;
		this.descOggetto = descOggetto;
		this.peso = peso;
		this.scarto = scarto;
		this.pesoNetto = pesoNetto;
		this.totale = totale;
		this.prezzoRiferimento = prezzoRiferimento;
	}

	public Integer getIdAttovenditaattr() {
		return this.idAttovenditaattr;
	}

	public void setIdAttovenditaattr(Integer idAttovenditaattr) {
		this.idAttovenditaattr = idAttovenditaattr;
	}

	public Attovendita getAttovendita() {
		return this.attovendita;
	}

	public void setAttovendita(Attovendita attovendita) {
		this.attovendita = attovendita;
	}

	public Tipometallo getTipometallo() {
		return this.tipometallo;
	}

	public void setTipometallo(Tipometallo tipometallo) {
		this.tipometallo = tipometallo;
	}

	public String getDescOggetto() {
		return this.descOggetto;
	}

	public void setDescOggetto(String descOggetto) {
		this.descOggetto = descOggetto;
	}

	public String getPeso() {
		return this.peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getScarto() {
		return this.scarto;
	}

	public void setScarto(String scarto) {
		this.scarto = scarto;
	}

	public String getPesoNetto() {
		return this.pesoNetto;
	}

	public void setPesoNetto(String pesoNetto) {
		this.pesoNetto = pesoNetto;
	}

	public String getTotale() {
		return this.totale;
	}

	public void setTotale(String totale) {
		this.totale = totale;
	}

	public String getPrezzoRiferimento() {
		return this.prezzoRiferimento;
	}

	public void setPrezzoRiferimento(String prezzoRiferimento) {
		this.prezzoRiferimento = prezzoRiferimento;
	}
}
