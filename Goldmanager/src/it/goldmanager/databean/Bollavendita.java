package it.goldmanager.databean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Bollavendita implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idBollavendita;
	private Bancometalli bancometalli;
	private Date inserito;
	private String inseritoDa;
	private String totaleBolla;
	private String pesoGrBolla;
	private String valoreGrBolla;
	private String tipometalloBolla;
	private String numeroFda;
	private String numeroDdt;
	private Set<Attovendita> attovenditas = new HashSet<Attovendita>(0);

	public Bollavendita() {
	}

	public Bollavendita(Bancometalli bancometalli, Date inserito, String inseritoDa, String totaleBolla, String pesoGrBolla, String valoreGrBolla, String tipometalloBolla, String numeroFda,
	        String numeroDdt) {
		this.bancometalli = bancometalli;
		this.inserito = inserito;
		this.inseritoDa = inseritoDa;
		this.totaleBolla = totaleBolla;
		this.pesoGrBolla = pesoGrBolla;
		this.valoreGrBolla = valoreGrBolla;
		this.tipometalloBolla = tipometalloBolla;
		this.numeroFda = numeroFda;
		this.numeroDdt = numeroDdt;
	}

	public Bollavendita(Bancometalli bancometalli, Date inserito, String inseritoDa, String totaleBolla, String pesoGrBolla, String valoreGrBolla, String tipometalloBolla, String numeroFda,
	        String numeroDdt, Set<Attovendita> attovenditas) {
		this.bancometalli = bancometalli;
		this.inserito = inserito;
		this.inseritoDa = inseritoDa;
		this.totaleBolla = totaleBolla;
		this.pesoGrBolla = pesoGrBolla;
		this.valoreGrBolla = valoreGrBolla;
		this.tipometalloBolla = tipometalloBolla;
		this.numeroFda = numeroFda;
		this.numeroDdt = numeroDdt;
	}

	public Integer getIdBollavendita() {
		return this.idBollavendita;
	}

	public void setIdBollavendita(Integer idBollavendita) {
		this.idBollavendita = idBollavendita;
	}

	public Bancometalli getBancometalli() {
		return this.bancometalli;
	}

	public void setBancometalli(Bancometalli bancometalli) {
		this.bancometalli = bancometalli;
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

	public String getTotaleBolla() {
		return this.totaleBolla;
	}

	public void setTotaleBolla(String totaleBolla) {
		this.totaleBolla = totaleBolla;
	}

	public String getPesoGrBolla() {
		return this.pesoGrBolla;
	}

	public void setPesoGrBolla(String pesoGrBolla) {
		this.pesoGrBolla = pesoGrBolla;
	}

	public String getValoreGrBolla() {
		return this.valoreGrBolla;
	}

	public void setValoreGrBolla(String valoreGrBolla) {
		this.valoreGrBolla = valoreGrBolla;
	}

	public String getTipometalloBolla() {
		return this.tipometalloBolla;
	}

	public void setTipometalloBolla(String tipometalloBolla) {
		this.tipometalloBolla = tipometalloBolla;
	}

	public Set<Attovendita> getAttovenditas() {
		return this.attovenditas;
	}

	public void setAttovenditas(Set<Attovendita> attovenditas) {
		this.attovenditas = attovenditas;
	}

	public String getNumeroFda() {
		return numeroFda;
	}

	public void setNumeroFda(String numeroFda) {
		this.numeroFda = numeroFda;
	}

	public String getNumeroDdt() {
		return numeroDdt;
	}

	public void setNumeroDdt(String numeroDdt) {
		this.numeroDdt = numeroDdt;
	}
}
