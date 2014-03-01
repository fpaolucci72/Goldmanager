package it.goldmanager.databean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Configurazione implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idConfigurazione;
	private String nomeConfigurazione;
	private String descConfigurazione;
	private boolean attivoConfigurazione;
	private Date modificato;
	private Set<Tipometallo> tipometallos = new HashSet<Tipometallo>(0);

	public Configurazione() {
	}

	public Configurazione(String nomeConfigurazione, boolean attivoConfigurazione) {
		this.nomeConfigurazione = nomeConfigurazione;
		this.attivoConfigurazione = attivoConfigurazione;
	}

	public Configurazione(String nomeConfigurazione, String descConfigurazione, boolean attivoConfigurazione, Date modificato, Set<Tipometallo> tipometallos) {
		this.nomeConfigurazione = nomeConfigurazione;
		this.descConfigurazione = descConfigurazione;
		this.attivoConfigurazione = attivoConfigurazione;
		this.modificato = modificato;
		this.tipometallos = tipometallos;
	}

	public Integer getIdConfigurazione() {
		return this.idConfigurazione;
	}

	public void setIdConfigurazione(Integer idConfigurazione) {
		this.idConfigurazione = idConfigurazione;
	}

	public String getNomeConfigurazione() {
		return this.nomeConfigurazione;
	}

	public void setNomeConfigurazione(String nomeConfigurazione) {
		this.nomeConfigurazione = nomeConfigurazione;
	}

	public String getDescConfigurazione() {
		return this.descConfigurazione;
	}

	public void setDescConfigurazione(String descConfigurazione) {
		this.descConfigurazione = descConfigurazione;
	}

	public boolean isAttivoConfigurazione() {
		return this.attivoConfigurazione;
	}

	public void setAttivoConfigurazione(boolean attivoConfigurazione) {
		this.attivoConfigurazione = attivoConfigurazione;
	}

	public Date getModificato() {
		return this.modificato;
	}

	public void setModificato(Date modificato) {
		this.modificato = modificato;
	}

	public Set<Tipometallo> getTipometallos() {
		return this.tipometallos;
	}

	public void setTipometallos(Set<Tipometallo> tipometallos) {
		this.tipometallos = tipometallos;
	}
}
