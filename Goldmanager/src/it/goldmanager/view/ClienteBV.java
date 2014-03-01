package it.goldmanager.view;

import it.goldmanager.business.ClienteB;
import it.goldmanager.business.DocumentoB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerUtility;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Cliente;
import it.goldmanager.databean.Documento;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class ClienteBV {

	@ManagedProperty(value = "#{cliente}")
	private Cliente cliente;
	private Integer idDocumento;

	public String saveCliente() {
		GoldmanagerLogger.debug(ClienteBV.class, "saveCliente", null);
		String activeTab = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom_active");
		if (activeTab != null && activeTab.equals("0")) {
			String indirizzo = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:indirizzodom");
			if (indirizzo == null || indirizzo.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.indirizzodom.required"), null));
				return null;
			}
			String citta = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:cittadom");
			if (citta == null || citta.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cittadom.required"), null));
				return null;
			}
			String provincia = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:provinciadom");
			if (provincia == null || provincia.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.provinciadom.required"), null));
				return null;
			}
			String nazione = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:nazionedom");
			if (nazione == null || nazione.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.nazionedom.required"), null));
				return null;
			}
		}
		String cf = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:codicefiscale");		
		Cliente cliente = (Cliente) HttpJSFUtil.getRequestMap("cliente");
		cliente.setCodicefiscale(cf);
		String idDoc = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:tipodoc_input");
		DocumentoB docB = new DocumentoB();
		Documento doc = docB.getUtente(idDoc);
		cliente.setDocumento(doc);
		cliente.setInserito(GoldmanagerUtility.today());
		try {
			ClienteB cliB = new ClienteB();
			cliB.saveOrUpCliente(cliente);
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
			return null;
		}
		GoldmanagerLogger.info(ClienteBV.class, "saveCliente fine", null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.nuovo.cliente") + " " + cliente.getCognome(), null));
		return "goToHome";
	}

	public String salvaVendi() {
		GoldmanagerLogger.debug(ClienteBV.class, "salvaVendi", null);
		String activeTab = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom_active");
		if (activeTab != null && activeTab.equals("0")) {
			String indirizzo = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:indirizzodom");
			if (indirizzo == null || indirizzo.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.indirizzodom.required"), null));
				return null;
			}
			String citta = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:cittadom");
			if (citta == null || citta.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cittadom.required"), null));
				return null;
			}
			String provincia = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:provinciadom");
			if (provincia == null || provincia.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.provinciadom.required"), null));
				return null;
			}
			String nazione = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:paneldom:nazionedom");
			if (nazione == null || nazione.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.nazionedom.required"), null));
				return null;
			}
		}
		String cf = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:codicefiscale");		
		Cliente cliente = (Cliente) HttpJSFUtil.getRequestMap("cliente");
		cliente.setCodicefiscale(cf);
		String idDoc = (String) HttpJSFUtil.getRequestParameter("formnuovocliente:tipodoc_input");
		DocumentoB docB = new DocumentoB();
		Documento doc = docB.getUtente(idDoc);
		cliente.setDocumento(doc);
		cliente.setInserito(GoldmanagerUtility.today());
		try {
			ClienteB cliB = new ClienteB();
			int id = cliB.saveOrUpCliente(cliente);
			HttpJSFUtil.getRequest().setAttribute("id_Cliente", String.valueOf(id));		
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
			return null;
		}
		GoldmanagerLogger.info(ClienteBV.class, "salvaVendi fine", null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.nuovo.cliente") + " " + cliente.getCognome(), null));
		return "goToNuovaVenditaDopoSave";
	}

	public String modCliente() {
		GoldmanagerLogger.debug(ClienteBV.class, "modCliente", null);
		String activeTab = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:paneldom_active");
		if (activeTab != null && activeTab.equals("0")) {
			String indirizzo = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:paneldom:indirizzodom");
			if (indirizzo == null || indirizzo.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.indirizzodom.required"), null));
				return null;
			}
			String citta = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:paneldom:cittadom");
			if (citta == null || citta.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.cittadom.required"), null));
				return null;
			}
			String provincia = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:paneldom:provinciadom");
			if (provincia == null || provincia.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.provinciadom.required"), null));
				return null;
			}
			String nazione = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:paneldom:nazionedom");
			if (nazione == null || nazione.equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.nazionedom.required"), null));
				return null;
			}
		}
		Cliente cliente = (Cliente) HttpJSFUtil.getRequestMap("cliente");
		ClienteB cliB = new ClienteB();
		Cliente cli = cliB.getCliente(String.valueOf(cliente.getIdCliente()));
		String idDoc = (String) HttpJSFUtil.getRequestParameter("formmodificacliente:tipodoc_input");
		DocumentoB docB = new DocumentoB();
		Documento doc = docB.getUtente(idDoc);
		cliente.setDocumento(doc);
		cliente.setModificato(GoldmanagerUtility.today());
		cliente.setInserito(cli.getInserito());
		try {
			cliB.saveOrUpCliente(cliente);
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
			return null;
		}
		GoldmanagerLogger.info(ClienteBV.class, "modCliente fine", null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.modifica.cliente") + " " + cliente.getCognome(), null));
		return "goToClienteSearch";
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}
}
