package it.goldmanager.databean;

import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Bancometalli implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idBancometalli;
	private String intestazione;
	private String cognome;
	private String nome;
	private String indirizzo;
	private String cap;
	private String citta;
	private String provincia;
	private String codicefiscale;
	private String partitaIva;
	private String email;
	private String telefono;
	private String mobile;
	private String fax;
	private Set<Bollavendita> bollavenditas = new HashSet<Bollavendita>(0);

	public Bancometalli() {
	}

	public Bancometalli(String intestazione, String cognome, String nome, String indirizzo, String cap, String citta, String provincia) {
		this.intestazione = intestazione;
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
	}

	public Bancometalli(String intestazione, String cognome, String nome, String indirizzo, String cap, String citta, String provincia, String codicefiscale, String partitaIva, String email,
	        String telefono, String mobile, String fax, Set<Bollavendita> bollavenditas) {
		this.intestazione = intestazione;
		this.cognome = cognome;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
		this.codicefiscale = codicefiscale;
		this.partitaIva = partitaIva;
		this.email = email;
		this.telefono = telefono;
		this.mobile = mobile;
		this.fax = fax;
		this.bollavenditas = bollavenditas;
	}

	public Integer getIdBancometalli() {
		return this.idBancometalli;
	}

	public void setIdBancometalli(Integer idBancometalli) {
		this.idBancometalli = idBancometalli;
	}

	public String getIntestazione() {
		return this.intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCodicefiscale() {
		return this.codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Set<Bollavendita> getBollavenditas() {
		return this.bollavenditas;
	}

	public void setBollavenditas(Set<Bollavendita> bollavenditas) {
		this.bollavenditas = bollavenditas;
	}
}
