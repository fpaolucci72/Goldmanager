package it.goldmanager.view;

import it.goldmanager.business.DocumentoB;
import it.goldmanager.databean.Documento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class ClienteV {

	private List<Documento> listDocAttivo = new ArrayList<Documento>();
	private Integer idDocumento;
	private List<SelectItem> sexList = new ArrayList<SelectItem>();

	@PostConstruct
	public void postConstruct() {
		DocumentoB docB = new DocumentoB();
		listDocAttivo = docB.getDocumento();
		sexList.add(new SelectItem(new Boolean(true), "Uomo"));
		sexList.add(new SelectItem(new Boolean(false), "Donna"));
	}

	public List<Documento> getListDocAttivo() {
		return listDocAttivo;
	}

	public void setListDocAttivo(List<Documento> listDocAttivo) {
		this.listDocAttivo = listDocAttivo;
	}

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public List<SelectItem> getSexList() {
		return sexList;
	}

	public void setSexList(List<SelectItem> sexList) {
		this.sexList = sexList;
	}
}
