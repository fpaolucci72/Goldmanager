package it.goldmanager.databean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Cliente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idCliente;
	private Documento documento;
	private String cognome;
	private String nome;
	private String email;
	private String telefono;
	private String mobile;
	private String codicefiscale;
	private boolean sesso;
	private String professione;
	private String docAltroDesc;
	private String docNumero;
	private String docEmessoda;
	private Date docDataril;
	private Date docDatascad;
	private Date docDatanas;
	private String docLuogonas;
	private String docNazionenas;
	private String docCittadinanza;
	private String resIndirizzo;
	private String resCap;
	private String resCitta;
	private String resProvincia;
	private String resNazione;
	private String domIndirizzo;
	private String domCap;
	private String domCitta;
	private String domProvincia;
	private String domNazione;
	private Date inserito;
	private Date modificato;
	private Set<Attovendita> attovenditas = new HashSet<Attovendita>(0);

	public Cliente() {
	}

	public Cliente(Documento documento, String cognome, String nome, boolean sesso, String docNumero, String docEmessoda, Date docDataril, Date docDatascad, Date docDatanas, String docLuogonas,
	        String docNazionenas, String docCittadinanza, String resIndirizzo, String resCap, String resCitta, String resProvincia, String resNazione, Date inserito) {
		this.documento = documento;
		this.cognome = cognome;
		this.nome = nome;
		this.sesso = sesso;
		this.docNumero = docNumero;
		this.docEmessoda = docEmessoda;
		this.docDataril = docDataril;
		this.docDatascad = docDatascad;
		this.docDatanas = docDatanas;
		this.docLuogonas = docLuogonas;
		this.docNazionenas = docNazionenas;
		this.docCittadinanza = docCittadinanza;
		this.resIndirizzo = resIndirizzo;
		this.resCap = resCap;
		this.resCitta = resCitta;
		this.resProvincia = resProvincia;
		this.resNazione = resNazione;
		this.inserito = inserito;
	}

	public Cliente(Documento documento, String cognome, String nome, String email, String telefono, String mobile, String codicefiscale, boolean sesso, String professione, String docAltroDesc,
	        String docNumero, String docEmessoda, Date docDataril, Date docDatascad, Date docDatanas, String docLuogonas, String docNazionenas, String docCittadinanza, String resIndirizzo,
	        String resCap, String resCitta, String resProvincia, String resNazione, String domIndirizzo, String domCap, String domCitta, String domProvincia, String domNazione, Date inserito,
	        Date modificato, Set<Attovendita> attovenditas) {
		this.documento = documento;
		this.cognome = cognome;
		this.nome = nome;
		this.email = email;
		this.telefono = telefono;
		this.mobile = mobile;
		this.codicefiscale = codicefiscale;
		this.sesso = sesso;
		this.professione = professione;
		this.docAltroDesc = docAltroDesc;
		this.docNumero = docNumero;
		this.docEmessoda = docEmessoda;
		this.docDataril = docDataril;
		this.docDatascad = docDatascad;
		this.docDatanas = docDatanas;
		this.docLuogonas = docLuogonas;
		this.docNazionenas = docNazionenas;
		this.docCittadinanza = docCittadinanza;
		this.resIndirizzo = resIndirizzo;
		this.resCap = resCap;
		this.resCitta = resCitta;
		this.resProvincia = resProvincia;
		this.resNazione = resNazione;
		this.domIndirizzo = domIndirizzo;
		this.domCap = domCap;
		this.domCitta = domCitta;
		this.domProvincia = domProvincia;
		this.domNazione = domNazione;
		this.inserito = inserito;
		this.modificato = modificato;
		this.attovenditas = attovenditas;
	}

	public Integer getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Documento getDocumento() {
		return this.documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
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

	public String getCodicefiscale() {
		return this.codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public boolean isSesso() {
		return this.sesso;
	}

	public void setSesso(boolean sesso) {
		this.sesso = sesso;
	}

	public String getProfessione() {
		return this.professione;
	}

	public void setProfessione(String professione) {
		this.professione = professione;
	}

	public String getDocAltroDesc() {
		return this.docAltroDesc;
	}

	public void setDocAltroDesc(String docAltroDesc) {
		this.docAltroDesc = docAltroDesc;
	}

	public String getDocNumero() {
		return this.docNumero;
	}

	public void setDocNumero(String docNumero) {
		this.docNumero = docNumero;
	}

	public String getDocEmessoda() {
		return this.docEmessoda;
	}

	public void setDocEmessoda(String docEmessoda) {
		this.docEmessoda = docEmessoda;
	}

	public Date getDocDataril() {
		return this.docDataril;
	}

	public void setDocDataril(Date docDataril) {
		this.docDataril = docDataril;
	}

	public Date getDocDatascad() {
		return this.docDatascad;
	}

	public void setDocDatascad(Date docDatascad) {
		this.docDatascad = docDatascad;
	}

	public Date getDocDatanas() {
		return this.docDatanas;
	}

	public void setDocDatanas(Date docDatanas) {
		this.docDatanas = docDatanas;
	}

	public String getDocLuogonas() {
		return this.docLuogonas;
	}

	public void setDocLuogonas(String docLuogonas) {
		this.docLuogonas = docLuogonas;
	}

	public String getDocNazionenas() {
		return this.docNazionenas;
	}

	public void setDocNazionenas(String docNazionenas) {
		this.docNazionenas = docNazionenas;
	}

	public String getDocCittadinanza() {
		return this.docCittadinanza;
	}

	public void setDocCittadinanza(String docCittadinanza) {
		this.docCittadinanza = docCittadinanza;
	}

	public String getResIndirizzo() {
		return this.resIndirizzo;
	}

	public void setResIndirizzo(String resIndirizzo) {
		this.resIndirizzo = resIndirizzo;
	}

	public String getResCap() {
		return this.resCap;
	}

	public void setResCap(String resCap) {
		this.resCap = resCap;
	}

	public String getResCitta() {
		return this.resCitta;
	}

	public void setResCitta(String resCitta) {
		this.resCitta = resCitta;
	}

	public String getResProvincia() {
		return this.resProvincia;
	}

	public void setResProvincia(String resProvincia) {
		this.resProvincia = resProvincia;
	}

	public String getResNazione() {
		return this.resNazione;
	}

	public void setResNazione(String resNazione) {
		this.resNazione = resNazione;
	}

	public String getDomIndirizzo() {
		return this.domIndirizzo;
	}

	public void setDomIndirizzo(String domIndirizzo) {
		this.domIndirizzo = domIndirizzo;
	}

	public String getDomCap() {
		return this.domCap;
	}

	public void setDomCap(String domCap) {
		this.domCap = domCap;
	}

	public String getDomCitta() {
		return this.domCitta;
	}

	public void setDomCitta(String domCitta) {
		this.domCitta = domCitta;
	}

	public String getDomProvincia() {
		return this.domProvincia;
	}

	public void setDomProvincia(String domProvincia) {
		this.domProvincia = domProvincia;
	}

	public String getDomNazione() {
		return this.domNazione;
	}

	public void setDomNazione(String domNazione) {
		this.domNazione = domNazione;
	}

	public Date getInserito() {
		return this.inserito;
	}

	public void setInserito(Date inserito) {
		this.inserito = inserito;
	}

	public Date getModificato() {
		return this.modificato;
	}

	public void setModificato(Date modificato) {
		this.modificato = modificato;
	}

	public Set<Attovendita> getAttovenditas() {
		return this.attovenditas;
	}

	public void setAttovenditas(Set<Attovendita> attovenditas) {
		this.attovenditas = attovenditas;
	}
}
