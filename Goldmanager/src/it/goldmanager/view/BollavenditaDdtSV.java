package it.goldmanager.view;

import it.goldmanager.business.BollavenditaB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerReport;
import it.goldmanager.common.GoldmanagerSession;
import it.goldmanager.databean.Bollavendita;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
@ViewScoped
public class BollavenditaDdtSV {

	private List<Bollavendita> listbolla = new ArrayList<Bollavendita>();
	private Bollavendita selectedItem;

	@PostConstruct
	public void postConstruct() {
		BollavenditaB bollaB = new BollavenditaB();
		listbolla = bollaB.getAllBollavenditaDdt();
	}

	public String printBollavenditaDdt() {
		GoldmanagerLogger.debug(BollavenditaDdtSV.class, "printBollavendita init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		BollavenditaB bb = new BollavenditaB();
		Bollavendita bolla = bb.getBollavendita(String.valueOf(selectedItem.getIdBollavendita()));
		GoldmanagerReport gr = new GoldmanagerReport();
		try {
			gr.pdfReportDdt(bolla, bolla.getBancometalli());
		} catch (JRException e) {
			GoldmanagerLogger.error(BollavenditaDdtSV.class, "printBollavendita error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		} catch (IOException e) {
			GoldmanagerLogger.error(BollavenditaDdtSV.class, "printBollavendita error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		}
		return null;
	}

	public List<Bollavendita> getListbolla() {
		return listbolla;
	}

	public void setListbolla(List<Bollavendita> listbolla) {
		this.listbolla = listbolla;
	}

	public Bollavendita getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Bollavendita selectedItem) {
		this.selectedItem = selectedItem;
	}
}
