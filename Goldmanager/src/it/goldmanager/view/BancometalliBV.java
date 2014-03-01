package it.goldmanager.view;

import it.goldmanager.business.BancometalliB;
import it.goldmanager.common.GoldmanagerLogger;
import it.goldmanager.common.HttpJSFUtil;
import it.goldmanager.databean.Bancometalli;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class BancometalliBV {

	public String saveBancometalli() {
		GoldmanagerLogger.debug(BancometalliBV.class, "saveBancometalli", null);
		Bancometalli bm = (Bancometalli) HttpJSFUtil.getRequestMap("bancometalli");
		try {
			BancometalliB bmb = new BancometalliB();
			bmb.saveOrUpBancometalli(bm);
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
			return null;
		}
		GoldmanagerLogger.info(BancometalliBV.class, "saveBancometalli fine", null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.nuovo.bancometalli") + " " + bm.getIntestazione(), null));
		return "goToHome";
	}

	public String modBancometalli() {
		GoldmanagerLogger.debug(BancometalliBV.class, "modBancometalli", null);
		Bancometalli bm = (Bancometalli) HttpJSFUtil.getRequestMap("bancometalli");
		String idhidden = (String) HttpJSFUtil.getRequestParameter("formmodbancometallo:hiddenId");
		bm.setIdBancometalli(Integer.valueOf(idhidden));
		try {
			BancometalliB bmb = new BancometalliB();
			bmb.saveOrUpBancometalli(bm);
		} catch (Throwable cv) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, HttpJSFUtil.getMex("mex.network.problem"), null));
			return null;
		}
		GoldmanagerLogger.info(BancometalliBV.class, "modBancometalli fine", null);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, HttpJSFUtil.getMex("mex.modificato.bancometalli") + " " + bm.getIntestazione(), null));
		return "goToBancometalliSearch";
	}
}
