package it.goldmanager.common;

import it.goldmanager.business.NegozioB;
import it.goldmanager.databean.Attovendita;
import it.goldmanager.databean.Attovenditaattr;
import it.goldmanager.databean.Bancometalli;
import it.goldmanager.databean.Bollavendita;
import it.goldmanager.databean.Negozio;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@ManagedBean
public class GoldmanagerReport {

	public void pdfReportVendita(Attovendita _atto) throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		StringBuffer sb = new StringBuffer();
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		sb.append(" ");
		sb.append(n.getIndirizzo());
		sb.append(" ");
		sb.append(n.getCap());
		sb.append(" ");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		sb.append(" (");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		sb.append(")");
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(_atto.getAttovenditaattrs());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ReportTitle", "Vendita");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("numeroAttoVendita", _atto.getNumeroAttoVendita());
		parameters.put("inseritoAttoVendita", GoldmanagerUtility.convertDateToString(_atto.getInserito(), "dd-MM-yyyy"));
		parameters.put("sesso", GoldmanagerUtility.convertSesso(_atto.getCliente().isSesso()));
		parameters.put("vendeA", sb.toString());
		StringBuffer sb1 = new StringBuffer();
		sb1.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getCognome()));
		sb1.append(" ");
		sb1.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getNome()));
		parameters.put("cognomeNome", sb1.toString());
		parameters.put("totaleAtto", _atto.getTotaleVendita());
		parameters.put("codiceFiscaleCliente", GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getCodicefiscale()));
		parameters.put("cittaNato", GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getDocLuogonas()));
		parameters.put("dataNato", GoldmanagerUtility.convertDateToString(_atto.getCliente().getDocDatanas(), "dd-MM-yyyy"));
		StringBuffer sb2 = new StringBuffer();
		sb2.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getResCitta()));
		sb2.append(" (");
		sb2.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getResProvincia()));
		sb2.append(") ");
		sb2.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getResIndirizzo()));
		parameters.put("cittaIndRes", sb2.toString());
		StringBuffer sb3 = new StringBuffer();
		sb3.append("N.");
		sb3.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getDocNumero()));
		sb3.append(" ");
		sb3.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getDocumento().getNome()));
		parameters.put("docTipoNum", sb3.toString());
		StringBuffer sb4 = new StringBuffer();
		sb4.append(GoldmanagerUtility.convertDateToString(_atto.getCliente().getDocDataril(), "dd-MM-yyyy"));
		sb4.append(" da ");
		sb4.append(GoldmanagerUtility.toUppercaseNotNull(_atto.getCliente().getDocEmessoda()));
		sb4.append(" con scadenza ");
		sb4.append(GoldmanagerUtility.convertDateToString(_atto.getCliente().getDocDatascad(), "dd-MM-yyyy"));
		parameters.put("docRilEmeSca", sb4.toString());
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("tipoPagamento", _atto.getTipoPagamento());
		StringBuffer sb5 = new StringBuffer();
		sb5.append(GoldmanagerUtility.convertDateToString(_atto.getInserito(), "yyyyMMdd"));
		sb5.append(_atto.getNumeroAttoVendita());
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Vendita.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "inline; filename=" + sb5.toString() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void pdfReportFda(Bollavendita _bolla, Bancometalli _banco) throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("ReportTitle", "Bolla");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("numeroBolla", _bolla.getNumeroFda());
		parameters.put("inseritoBolla", GoldmanagerUtility.convertDateToString(_bolla.getInserito(), "dd-MM-yyyy"));
		parameters.put("iva", _banco.getPartitaIva());
		parameters.put("codfiscale", _banco.getCodicefiscale());
		parameters.put("intestazioneBM", GoldmanagerUtility.toUppercaseNotNull(_banco.getIntestazione()));
		parameters.put("indirizzoBM", GoldmanagerUtility.toUppercaseNotNull(_banco.getIndirizzo()));
		StringBuffer sb = new StringBuffer();
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getCap()));
		sb.append(" ");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getCitta()));
		sb.append(" (");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getProvincia()));
		sb.append(") ");
		parameters.put("capCittaPrBM", sb.toString());
		parameters.put("regIva", "");
		parameters.put("iban", n.getIban());
		parameters.put("regVidimato", " ******************");
		parameters.put("pesoGr", _bolla.getPesoGrBolla());
		parameters.put("valoreGr", _bolla.getValoreGrBolla());
		parameters.put("totaleBolla", _bolla.getTotaleBolla());
		parameters.put("tipoMet", _bolla.getTipometalloBolla());
		StringBuffer sb1 = new StringBuffer();
		sb1.append(GoldmanagerUtility.convertDateToString(_bolla.getInserito(), "yyyyMMdd"));
		sb1.append(_bolla.getNumeroFda());
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Fda.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + sb1.toString() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}

	public void pdfReportVenditaEmpty() throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		StringBuffer sb = new StringBuffer();
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		sb.append(" ");
		sb.append(n.getIndirizzo());
		sb.append(" ");
		sb.append(n.getCap());
		sb.append(" ");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		sb.append(" (");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		sb.append(")");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ReportTitle", "Vendita");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("numeroAttoVendita", "");
		parameters.put("inseritoAttoVendita", "");
		parameters.put("sesso", "");
		parameters.put("vendeA", "");
		parameters.put("cognomeNome", "");
		parameters.put("totaleAtto", "");
		parameters.put("codiceFiscaleCliente", "");
		parameters.put("cittaNato", "");
		parameters.put("dataNato", "");
		parameters.put("cittaIndRes", "");
		parameters.put("docTipoNum", "");
		parameters.put("docRilEmeSca", "");
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("tipoPagamento", "");
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Vendita.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "inline; filename=VuotoVendita.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	
	public void pdfReportFdaEmpty() throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("ReportTitle", "Bolla");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("numeroBolla", "");
		parameters.put("inseritoBolla", "");
		parameters.put("iva", "");
		parameters.put("codfiscale", "");
		parameters.put("intestazioneBM", "");
		parameters.put("indirizzoBM", "");
		parameters.put("capCittaPrBM", "");
		parameters.put("regIva", "");
		parameters.put("iban", "");
		parameters.put("regVidimato", "");
		parameters.put("pesoGr", "");
		parameters.put("valoreGr", "");
		parameters.put("totaleBolla", "");
		parameters.put("tipoMet", "");
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Fda.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=VuotoFattAcconto.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	public void pdfReportDdtEmpty() throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("ReportTitle", "Bolla");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("intestazioneBM", "");
		parameters.put("indirizzoBM", "");
		parameters.put("capCittaPrBM", "");
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Ddt.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "attachment; filename=VuotoDdt.pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}
	
	
	public void pdfReportDdt(Bollavendita _bolla, Bancometalli _banco) throws JRException, IOException {
		NegozioB nb = new NegozioB();
		Negozio n = nb.getNegozio("1");
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("intestazione", GoldmanagerUtility.toUppercaseNotNull(n.getIntestazione()));
		parameters.put("indirizzo", n.getIndirizzo());
		parameters.put("cap", n.getCap());
		parameters.put("citta", GoldmanagerUtility.toUppercaseNotNull(n.getCitta()));
		parameters.put("provincia", GoldmanagerUtility.toUppercaseNotNull(n.getProvincia()));
		parameters.put("telefono", n.getTelefono());
		parameters.put("fax", n.getFax());
		parameters.put("email", n.getEmail());
		parameters.put("slIntestazione", n.getSlIntestazione());
		parameters.put("slIndirizzo", n.getSlIndirizzo());
		parameters.put("slCap", n.getSlCap());
		parameters.put("slCitta", GoldmanagerUtility.toUppercaseNotNull(n.getSlCitta()));
		parameters.put("slProvincia", GoldmanagerUtility.toUppercaseNotNull(n.getSlProvincia()));
		parameters.put("slPartitaIva", n.getSlPartitaIva());
		parameters.put("slCodicefiscale", n.getSlCodicefiscale());
		parameters.put("ReportTitle", "Ddt");
		parameters.put("Logo", FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/logo.jpg"));
		parameters.put("intestazioneBM", GoldmanagerUtility.toUppercaseNotNull(_banco.getIntestazione()));
		parameters.put("indirizzoBM", GoldmanagerUtility.toUppercaseNotNull(_banco.getIndirizzo()));
		StringBuffer sb = new StringBuffer();
		parameters.put("numeroBolla", _bolla.getNumeroDdt());
		parameters.put("inseritoBolla", GoldmanagerUtility.convertDateToString(_bolla.getInserito(), "dd-MM-yyyy"));
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getCap()));
		sb.append(" ");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getCitta()));
		sb.append(" (");
		sb.append(GoldmanagerUtility.toUppercaseNotNull(_banco.getProvincia()));
		sb.append(") ");
		parameters.put("capCittaPrBM", sb.toString());
		parameters.put("valGrBolla", _bolla.getValoreGrBolla());
		parameters.put("totBolla", _bolla.getTotaleBolla());
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+_bolla.getValoreGrBolla());
		
		System.out.println("!!!!!!!!!---------------------!!!!!!!!!!!!!!!!!!!! "+_bolla.getTotaleBolla());
		
		Set<Attovenditaattr> att = new HashSet<Attovenditaattr>();
		for(Attovendita a : _bolla.getAttovenditas()){
			for(Attovenditaattr avt : a.getAttovenditaattrs()){
				att.add(avt);
			}
		}
		
		
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(att);
		StringBuffer sb1 = new StringBuffer();
		sb1.append(GoldmanagerUtility.convertDateToString(_bolla.getInserito(), "yyyyMMdd"));
		sb1.append(_bolla.getNumeroDdt());
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/Ddt.jrxml");
		JasperDesign jasperDesign = JRXmlLoader.load(reportPath);
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);
		HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		httpServletResponse.addHeader("Content-disposition", "inline; filename=" + sb1.toString() + ".pdf");
		ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
	}


}