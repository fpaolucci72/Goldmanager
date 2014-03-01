package it.goldmanager.databean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Attovendita implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idAttovendita;
	private Bollavendita bollavendita;
	private Cliente cliente;
	private Date inserito;
	private String inseritoDa;
	private String numeroAttoVendita;
	private String totaleVendita;
	private String tipoPagamento;
	private String tipoMetallo;
	private Set<Attovenditaattr> attovenditaattrs = new HashSet<Attovenditaattr>(0);

	public Attovendita() {
	}

	public Attovendita(Cliente cliente, Date inserito, String inseritoDa, String totaleVendita, String prezzoRiferimento, String tipoMetallo) {
		this.cliente = cliente;
		this.inserito = inserito;
		this.inseritoDa = inseritoDa;
		this.totaleVendita = totaleVendita;
		this.tipoMetallo = tipoMetallo;
	}

	public Attovendita(Bollavendita bollavendita, Cliente cliente, Date inserito, String inseritoDa, String numeroAttoVendita, String totaleVendita, String tipoPagamento, String tipoMetallo,
	        Set<Attovenditaattr> attovenditaattrs) {
		this.bollavendita = bollavendita;
		this.cliente = cliente;
		this.inserito = inserito;
		this.inseritoDa = inseritoDa;
		this.numeroAttoVendita = numeroAttoVendita;
		this.totaleVendita = totaleVendita;
		this.tipoPagamento = tipoPagamento;
		this.tipoMetallo = tipoMetallo;
		this.attovenditaattrs = attovenditaattrs;
	}

	public Integer getIdAttovendita() {
		return this.idAttovendita;
	}

	public void setIdAttovendita(Integer idAttovendita) {
		this.idAttovendita = idAttovendita;
	}

	public Bollavendita getBollavendita() {
		return this.bollavendita;
	}

	public void setBollavendita(Bollavendita bollavendita) {
		this.bollavendita = bollavendita;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getInserito() {
		return this.inserito;
	}

	public void setInserito(Date inserito) {
		this.inserito = inserito;
	}

	public String getInseritoDa() {
		return this.inseritoDa;
	}

	public void setInseritoDa(String inseritoDa) {
		this.inseritoDa = inseritoDa;
	}

	public String getNumeroAttoVendita() {
		return this.numeroAttoVendita;
	}

	public void setNumeroAttoVendita(String numeroAttoVendita) {
		this.numeroAttoVendita = numeroAttoVendita;
	}

	public String getTotaleVendita() {
		return this.totaleVendita;
	}

	public void setTotaleVendita(String totaleVendita) {
		this.totaleVendita = totaleVendita;
	}

	public String getTipoPagamento() {
		return this.tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public String getTipoMetallo() {
		return this.tipoMetallo;
	}

	public void setTipoMetallo(String tipoMetallo) {
		this.tipoMetallo = tipoMetallo;
	}

	public Set<Attovenditaattr> getAttovenditaattrs() {
		return this.attovenditaattrs;
	}

	public void setAttovenditaattrs(Set<Attovenditaattr> attovenditaattrs) {
		this.attovenditaattrs = attovenditaattrs;
	}
}
