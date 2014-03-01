package it.goldmanager.databean;

import java.util.HashSet;
import java.util.Set;

public class Ruolo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idRuolo;
	private String nomeRuolo;
	private String descRuolo;
	private boolean attivoRPc;
	private boolean attivoRMobile;
	private Set<Utente> utentes = new HashSet<Utente>(0);

	public Ruolo() {
	}

	public Ruolo(String nomeRuolo, boolean attivoRPc, boolean attivoRMobile) {
		this.nomeRuolo = nomeRuolo;
		this.attivoRPc = attivoRPc;
		this.attivoRMobile = attivoRMobile;
	}

	public Ruolo(String nomeRuolo, String descRuolo, boolean attivoRPc, boolean attivoRMobile, Set<Utente> utentes) {
		this.nomeRuolo = nomeRuolo;
		this.descRuolo = descRuolo;
		this.attivoRPc = attivoRPc;
		this.attivoRMobile = attivoRMobile;
		this.utentes = utentes;
	}

	public Integer getIdRuolo() {
		return this.idRuolo;
	}

	public void setIdRuolo(Integer idRuolo) {
		this.idRuolo = idRuolo;
	}

	public String getNomeRuolo() {
		return this.nomeRuolo;
	}

	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	public String getDescRuolo() {
		return this.descRuolo;
	}

	public void setDescRuolo(String descRuolo) {
		this.descRuolo = descRuolo;
	}

	public boolean isAttivoRPc() {
		return this.attivoRPc;
	}

	public void setAttivoRPc(boolean attivoRPc) {
		this.attivoRPc = attivoRPc;
	}

	public boolean isAttivoRMobile() {
		return this.attivoRMobile;
	}

	public void setAttivoRMobile(boolean attivoRMobile) {
		this.attivoRMobile = attivoRMobile;
	}

	public Set<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(Set<Utente> utentes) {
		this.utentes = utentes;
	}
}
