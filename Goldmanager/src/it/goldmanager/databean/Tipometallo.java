package it.goldmanager.databean;

import java.util.HashSet;
import java.util.Set;

public class Tipometallo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idTipometallo;
	private String nomeTipometallo;
	private String descTipometallo;
	private boolean attivoTipometallo;
	private String gruppoTipometallo;
	private Set<Attovenditaattr> attovenditaattrs = new HashSet<Attovenditaattr>(0);
	private Configurazione configurazione;

	public Tipometallo() {
	}

	public Tipometallo(String nomeTipometallo, boolean attivoTipometallo, String gruppoTipometallo) {
		this.nomeTipometallo = nomeTipometallo;
		this.attivoTipometallo = attivoTipometallo;
		this.gruppoTipometallo = gruppoTipometallo;
	}

	public Tipometallo(Configurazione configurazione, String nomeTipometallo, String descTipometallo, boolean attivoTipometallo, String gruppoTipometallo, Set<Attovenditaattr> attovenditaattrs) {
		this.configurazione = configurazione;
		this.nomeTipometallo = nomeTipometallo;
		this.descTipometallo = descTipometallo;
		this.attivoTipometallo = attivoTipometallo;
		this.gruppoTipometallo = gruppoTipometallo;
		this.attovenditaattrs = attovenditaattrs;
	}

	public Integer getIdTipometallo() {
		return this.idTipometallo;
	}

	public void setIdTipometallo(Integer idTipometallo) {
		this.idTipometallo = idTipometallo;
	}

	public String getNomeTipometallo() {
		return this.nomeTipometallo;
	}

	public void setNomeTipometallo(String nomeTipometallo) {
		this.nomeTipometallo = nomeTipometallo;
	}

	public String getDescTipometallo() {
		return this.descTipometallo;
	}

	public void setDescTipometallo(String descTipometallo) {
		this.descTipometallo = descTipometallo;
	}

	public boolean isAttivoTipometallo() {
		return this.attivoTipometallo;
	}

	public void setAttivoTipometallo(boolean attivoTipometallo) {
		this.attivoTipometallo = attivoTipometallo;
	}

	public String getGruppoTipometallo() {
		return this.gruppoTipometallo;
	}

	public void setGruppoTipometallo(String gruppoTipometallo) {
		this.gruppoTipometallo = gruppoTipometallo;
	}

	public Set<Attovenditaattr> getAttovenditaattrs() {
		return this.attovenditaattrs;
	}

	public void setAttovenditaattrs(Set<Attovenditaattr> attovenditaattrs) {
		this.attovenditaattrs = attovenditaattrs;
	}

	public Configurazione getConfigurazione() {
		return configurazione;
	}

	public void setConfigurazione(Configurazione configurazione) {
		this.configurazione = configurazione;
	}
}
