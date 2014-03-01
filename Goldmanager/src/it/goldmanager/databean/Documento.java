package it.goldmanager.databean;

import java.util.HashSet;
import java.util.Set;

public class Documento implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idDocumento;
	private String nome;
	private String descrizione;
	private boolean attivoDocumento;
	private Set<Cliente> clientes = new HashSet<Cliente>(0);

	public Documento() {
	}

	public Documento(String nome, String descrizione, boolean attivoDocumento) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.attivoDocumento = attivoDocumento;
	}

	public Documento(String nome, String descrizione, boolean attivoDocumento, Set<Cliente> clientes) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.attivoDocumento = attivoDocumento;
		this.clientes = clientes;
	}

	public Integer getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public boolean isAttivoDocumento() {
		return this.attivoDocumento;
	}

	public void setAttivoDocumento(boolean attivoDocumento) {
		this.attivoDocumento = attivoDocumento;
	}

	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
}
