package it.goldmanager.databean;

public class Negozio implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idNegozio;
	private String intestazione;
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
	private String iban;
	private String slIntestazione;
	private String slIndirizzo;
	private String slCap;
	private String slCitta;
	private String slProvincia;
	private String slCodicefiscale;
	private String slPartitaIva;

	public Negozio() {
	}

	public Negozio(String indirizzo, String cap, String citta, String provincia, String codicefiscale, String iban, String slIndirizzo, String slCap, String slCitta, String slProvincia,
	        String slCodicefiscale) {
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
		this.provincia = provincia;
		this.codicefiscale = codicefiscale;
		this.iban = iban;
		this.slIndirizzo = slIndirizzo;
		this.slCap = slCap;
		this.slCitta = slCitta;
		this.slProvincia = slProvincia;
		this.slCodicefiscale = slCodicefiscale;
	}

	public Negozio(String intestazione, String indirizzo, String cap, String citta, String provincia, String codicefiscale, String partitaIva, String email, String telefono, String mobile,
	        String fax, String iban, String slIntestazione, String slIndirizzo, String slCap, String slCitta, String slProvincia, String slCodicefiscale, String slPartitaIva) {
		this.intestazione = intestazione;
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
		this.iban = iban;
		this.slIntestazione = slIntestazione;
		this.slIndirizzo = slIndirizzo;
		this.slCap = slCap;
		this.slCitta = slCitta;
		this.slProvincia = slProvincia;
		this.slCodicefiscale = slCodicefiscale;
		this.slPartitaIva = slPartitaIva;
	}

	public Integer getIdNegozio() {
		return this.idNegozio;
	}

	public void setIdNegozio(Integer idNegozio) {
		this.idNegozio = idNegozio;
	}

	public String getIntestazione() {
		return this.intestazione;
	}

	public void setIntestazione(String intestazione) {
		this.intestazione = intestazione;
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

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getSlIntestazione() {
		return this.slIntestazione;
	}

	public void setSlIntestazione(String slIntestazione) {
		this.slIntestazione = slIntestazione;
	}

	public String getSlIndirizzo() {
		return this.slIndirizzo;
	}

	public void setSlIndirizzo(String slIndirizzo) {
		this.slIndirizzo = slIndirizzo;
	}

	public String getSlCap() {
		return this.slCap;
	}

	public void setSlCap(String slCap) {
		this.slCap = slCap;
	}

	public String getSlCitta() {
		return this.slCitta;
	}

	public void setSlCitta(String slCitta) {
		this.slCitta = slCitta;
	}

	public String getSlProvincia() {
		return this.slProvincia;
	}

	public void setSlProvincia(String slProvincia) {
		this.slProvincia = slProvincia;
	}

	public String getSlCodicefiscale() {
		return this.slCodicefiscale;
	}

	public void setSlCodicefiscale(String slCodicefiscale) {
		this.slCodicefiscale = slCodicefiscale;
	}

	public String getSlPartitaIva() {
		return this.slPartitaIva;
	}

	public void setSlPartitaIva(String slPartitaIva) {
		this.slPartitaIva = slPartitaIva;
	}
}
