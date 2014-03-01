package it.goldmanager.databean;

public class Utente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idUtente;
	private Ruolo ruolo;
	private String cognome;
	private String nome;
	private String username;
	private String password;
	private String codicefiscale;
	private boolean attivoUtente;

	public Utente() {
	}

	public Utente(Ruolo ruolo, String cognome, String username, String password, boolean attivoUtente) {
		this.ruolo = ruolo;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.attivoUtente = attivoUtente;
	}

	public Utente(Ruolo ruolo, String cognome, String nome, String username, String password, String codicefiscale, boolean attivoUtente) {
		this.ruolo = ruolo;
		this.cognome = cognome;
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.codicefiscale = codicefiscale;
		this.attivoUtente = attivoUtente;
	}

	public Integer getIdUtente() {
		return this.idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Ruolo getRuolo() {
		return this.ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCodicefiscale() {
		return this.codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public boolean isAttivoUtente() {
		return this.attivoUtente;
	}

	public void setAttivoUtente(boolean attivoUtente) {
		this.attivoUtente = attivoUtente;
	}
}
