package it.goldmanager.databean;

public class Comunicf implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idComunicf;
	private String codice;
	private String comune;

	public Comunicf() {
	}

	public Comunicf(String codice, String comune) {
		this.codice = codice;
		this.comune = comune;
	}

	public Integer getIdComunicf() {
		return this.idComunicf;
	}

	public void setIdComunicf(Integer idComunicf) {
		this.idComunicf = idComunicf;
	}

	public String getCodice() {
		return this.codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}
}
