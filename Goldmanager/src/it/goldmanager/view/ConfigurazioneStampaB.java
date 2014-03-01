package it.goldmanager.view;

import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.GoldmanagerReport;
import it.goldmanager.common.GoldmanagerSession;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import net.sf.jasperreports.engine.JRException;

@ManagedBean
public class ConfigurazioneStampaB {

	public String printAttovenditaEmpty() {
		GoldmanagerLogger.info(ConfigurazioneStampaB.class, "printAttovenditaEmpty init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		GoldmanagerReport gr = new GoldmanagerReport();
		try {
			gr.pdfReportVenditaEmpty();
		} catch (JRException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printAttovenditaEmpty error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		} catch (IOException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printAttovenditaEmpty error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		}
		GoldmanagerLogger.debug(ConfigurazioneStampaB.class, "printAttovenditaEmpty finish", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "/pages/home.xhtml";
	}
	
	public String printFdaEmpty() {
		GoldmanagerLogger.info(ConfigurazioneStampaB.class, "printBollavenditaEmpty init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		GoldmanagerReport gr = new GoldmanagerReport();
		try {
			gr.pdfReportFdaEmpty();
		} catch (JRException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printBollavenditaEmpty error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		} catch (IOException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printBollavenditaEmpty error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		}
		GoldmanagerLogger.debug(ConfigurazioneStampaB.class, "printBollavenditaEmpty finish", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "/pages/home.xhtml";
	}
	
	public String printDdtEmpty() {
		GoldmanagerLogger.info(ConfigurazioneStampaB.class, "printDdtEmpty init", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		GoldmanagerReport gr = new GoldmanagerReport();
		try {
			gr.pdfReportDdtEmpty();
		} catch (JRException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printDdtEmpty error jre", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		} catch (IOException e) {
			GoldmanagerLogger.error(ConfigurazioneStampaB.class, "printDdtEmpty error io", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
			e.printStackTrace();
		}
		GoldmanagerLogger.debug(ConfigurazioneStampaB.class, "printDdtEmpty finish", GoldmanagerSession.getCurrentInstance().getUtente().getUsername());
		return "/pages/home.xhtml";
	}
	
}
