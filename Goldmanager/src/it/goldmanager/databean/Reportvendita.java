package it.goldmanager.databean;

import it.goldmanager.common.GoldmanagerUtility;
import java.util.ArrayList;
import java.util.Date;

public class Reportvendita implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numeroAttoVendita;
	private String inseritoAttoVendita;
	private String sesso;
	private String cognome;
	private String nome;
	private String codiceFiscaleCliente;
	private String cittaNato;
	private String dataNato;
	private String indirizzoRes;
	private String cittaRes;
	private String provinciaRes;
	private String docTipo;
	private String docNum;
	private String docDataRil;
	private String docEmesso;
	private String docDataScad;
	private String cognomeNome;
	private String cittaIndRes;
	private String docTipoNum;
	private String docRilEmeSca;
	private String vendeA;
	private ArrayList<Tipometallo> vendite = new ArrayList<Tipometallo>();
	
	public Reportvendita() {
	    super();
	    
	    vendite.add(new Tipometallo("oro",true,"1"));
	    vendite.add(new Tipometallo("argento",true,"1"));
	    vendite.add(new Tipometallo("bronzo",true,"1"));
	    
	    this.setNumeroAttoVendita("12");
		this.setInseritoAttoVendita(GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy"));
		
		//1maschio 0femmina
		this.setSesso("1");
		this.setCognomeNome("Polletti di giannandrea giancataldo");
		
		this.setCodiceFiscaleCliente("123456789qwertyu");
		this.setCittaNato("romanato");
		this.setDataNato(GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy"));
		this.setIndirizzoRes("indirizzo di residenza,12P");
		this.setCittaRes("citta residenza");
		this.setProvinciaRes("pr");
		
		this.setCittaIndRes("citta residenza pr in indirizzo di residenza,12P");
		
		this.setDocTipoNum("Patente N. 1234598765432");
		this.setDocRilEmeSca(GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy") + " da supermegaprefetto "+ " con scadenza "+GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy"));
		
		this.setDocTipo("Patente");
		this.setDocNum("1234598765432");
		this.setDocDataRil(GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy"));
		this.setDocEmesso("supermegaprefetto");
		this.setDocDataScad(GoldmanagerUtility.convertDateToString(new Date(),"dd-MM-yyyy"));
    }
	
	







	
	
    
    
    
    
    
    
    
    
    public ArrayList<Tipometallo> getVendite() {
    	return vendite;
    }


















	
    public void setVendite(ArrayList<Tipometallo> vendite) {
    	this.vendite = vendite;
    }


















	public String getVendeA() {
    	return vendeA;
    }

















	
    public void setVendeA(String vendeA) {
    	this.vendeA = vendeA;
    }

















	public String getDocTipoNum() {
    	return docTipoNum;
    }
















	
    public void setDocTipoNum(String docTipoNum) {
    	this.docTipoNum = docTipoNum;
    }
















	
    public String getDocRilEmeSca() {
    	return docRilEmeSca;
    }
















	
    public void setDocRilEmeSca(String docRilEmeSca) {
    	this.docRilEmeSca = docRilEmeSca;
    }
















	public String getCittaIndRes() {
    	return cittaIndRes;
    }















	
    public void setCittaIndRes(String cittaIndRes) {
    	this.cittaIndRes = cittaIndRes;
    }















	public String getCognomeNome() {
    	return cognomeNome;
    }









	
    public void setCognomeNome(String cognomeNome) {
    	this.cognomeNome = cognomeNome;
    }









	public String getCittaNato() {
    	return cittaNato;
    }






	
    public void setCittaNato(String cittaNato) {
    	this.cittaNato = cittaNato;
    }






	
    public String getDataNato() {
    	return dataNato;
    }






	
    public void setDataNato(String dataNato) {
    	this.dataNato = dataNato;
    }






	
    public String getIndirizzoRes() {
    	return indirizzoRes;
    }






	
    public void setIndirizzoRes(String indirizzoRes) {
    	this.indirizzoRes = indirizzoRes;
    }






	
    public String getCittaRes() {
    	return cittaRes;
    }






	
    public void setCittaRes(String cittaRes) {
    	this.cittaRes = cittaRes;
    }






	
    public String getProvinciaRes() {
    	return provinciaRes;
    }






	
    public void setProvinciaRes(String provinciaRes) {
    	this.provinciaRes = provinciaRes;
    }






	
    public String getDocTipo() {
    	return docTipo;
    }






	
    public void setDocTipo(String docTipo) {
    	this.docTipo = docTipo;
    }






	
    public String getDocNum() {
    	return docNum;
    }






	
    public void setDocNum(String docNum) {
    	this.docNum = docNum;
    }






	
    public String getDocDataRil() {
    	return docDataRil;
    }






	
    public void setDocDataRil(String docDataRil) {
    	this.docDataRil = docDataRil;
    }






	
    public String getDocEmesso() {
    	return docEmesso;
    }






	
    public void setDocEmesso(String docEmesso) {
    	this.docEmesso = docEmesso;
    }






	
    public String getDocDataScad() {
    	return docDataScad;
    }






	
    public void setDocDataScad(String docDataScad) {
    	this.docDataScad = docDataScad;
    }






	public String getCognome() {
    	return cognome;
    }





	
    public void setCognome(String cognome) {
    	this.cognome = cognome;
    }





	
    public String getNome() {
    	return nome;
    }





	
    public void setNome(String nome) {
    	this.nome = nome;
    }





	
    public String getCodiceFiscaleCliente() {
    	return codiceFiscaleCliente;
    }





	
    public void setCodiceFiscaleCliente(String codiceFiscaleCliente) {
    	this.codiceFiscaleCliente = codiceFiscaleCliente;
    }





	public String getSesso() {
    	return sesso;
    }




	
    public void setSesso(String sesso) {
    	this.sesso = sesso;
    }




	public String getNumeroAttoVendita() {
    	return numeroAttoVendita;
    }
	
    public void setNumeroAttoVendita(String numeroAttoVendita) {
    	this.numeroAttoVendita = numeroAttoVendita;
    }




	
    public String getInseritoAttoVendita() {
    	return inseritoAttoVendita;
    }




	
    public void setInseritoAttoVendita(String inseritoAttoVendita) {
    	this.inseritoAttoVendita = inseritoAttoVendita;
    }
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
