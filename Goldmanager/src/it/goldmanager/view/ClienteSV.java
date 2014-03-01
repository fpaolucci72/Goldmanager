package it.goldmanager.view;

import it.goldmanager.business.ClienteB;
import it.goldmanager.business.ComunicfB;
import it.goldmanager.common.CalcolaCodiceFiscale;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Cliente;
import it.goldmanager.databean.Comunicf;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class ClienteSV {

	private List<Cliente> listCliente = new ArrayList<Cliente>();
	private Cliente selectedItem;
	private ClienteBV clienteBV;
	private List<SelectItem> sexList = new ArrayList<SelectItem>();
	private Integer idDocumento;
	private List<SelectItem> cfList = new ArrayList<SelectItem>();
	private String codicefiscale;

	@PostConstruct
	public void postConstruct() {
		ClienteB clienteB = new ClienteB();
		listCliente = clienteB.getAllCliente();
		sexList.add(new SelectItem(new Boolean(true), "Uomo"));
		sexList.add(new SelectItem(new Boolean(false), "Donna"));
		ComunicfB comB = new ComunicfB();
		List<Comunicf> com = comB.getComunicfB();
		for (Comunicf c : com) {
			cfList.add(new SelectItem(c.getCodice(), c.getComune()));
		}
	}

	public void calculateCf() {
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateCf init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		String cognome = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:cognome");
		String nome = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:nome");
		String sesso = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:sesso_input");
		String data = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:datanascdoc_input");
		String codComune = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:cittanascf_input");
		if (sesso.equals("true")) {
			sesso = "M";
		} else {
			sesso = "F";
		}
		codicefiscale = CalcolaCodiceFiscale.calcolaCf(cognome, nome, sesso, GoldmanagerUtility.convertStringToDate(data, "dd/MM/yyyy"), codComune);
		GoldmanagerLogger.debug(BollavenditaV.class, "calculateCf cf:" + codicefiscale, GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
	}

	public void deleteCliente() {
		GoldmanagerLogger.debug(ClienteSV.class, "deleteCliente init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		ClienteB clienteB = new ClienteB();
		clienteB.delCliente(selectedItem.getIdCliente());
		listCliente.remove(selectedItem);
	}

	public String goToMod() {
		GoldmanagerLogger.debug(ClienteSV.class, "goToMod init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		selectedItem.setIdCliente(selectedItem.getIdCliente());
		clienteBV = new ClienteBV();
		clienteBV.setCliente(selectedItem);
		idDocumento = selectedItem.getDocumento().getIdDocumento();
		clienteBV.setIdDocumento(idDocumento);
		HttpJSFUtil.setManagedBean("clienteBV", clienteBV);
		return "goToClienteMod";
	}

	public String goToVendita() {
		GoldmanagerLogger.debug(ClienteSV.class, "goToVendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		clienteBV = new ClienteBV();
		clienteBV.setCliente(selectedItem);
		HttpJSFUtil.flashScope().put("attovenditaV", clienteBV);
		HttpJSFUtil.setManagedBean("cliente", selectedItem);
		HttpJSFUtil.flashScope().put("idclienteflash", clienteBV.getCliente().getIdCliente());
		return "goToVendita";
	}

	public String goToAtto() {
		GoldmanagerLogger.debug(ClienteSV.class, "goToAtto init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		clienteBV = new ClienteBV();
		clienteBV.setCliente(selectedItem);
		HttpJSFUtil.flashScope().put("attovenditaV", clienteBV);
		HttpJSFUtil.flashScope().put("idclienteflash", clienteBV.getCliente().getIdCliente());
		HttpJSFUtil.setManagedBean("cliente", selectedItem);
		return "goToAtto";
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public void noConfirm() {
		selectedItem = new Cliente();
	}

	public List<SelectItem> getSexList() {
		return sexList;
	}

	public void setSexList(List<SelectItem> sexList) {
		this.sexList = sexList;
	}

	public List<Cliente> getListCliente() {
		return listCliente;
	}

	public void setListCliente(List<Cliente> listCliente) {
		this.listCliente = listCliente;
	}

	public Cliente getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Cliente selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ClienteBV getClienteBV() {
		return clienteBV;
	}

	public void setClienteBV(ClienteBV clienteBV) {
		this.clienteBV = clienteBV;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public List<SelectItem> getCfList() {
		return cfList;
	}

	public void setCfList(List<SelectItem> cfList) {
		this.cfList = cfList;
	}
}
