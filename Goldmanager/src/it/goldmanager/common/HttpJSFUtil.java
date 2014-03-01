package it.goldmanager.common;

import java.util.Enumeration;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class HttpJSFUtil {

	private HttpJSFUtil() {
	}

	public static HttpSession getNewSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	public static HttpServletRequest getRequest() {
		HttpServletRequest r = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return r;
	}

	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	}

	public static Object getRequestMap(Object _ob) {
		return (Object) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get(_ob);
	}

	public static void setManagedBean(String nomeManagedBean, Object _ob) {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put(nomeManagedBean, _ob);
	}

	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}

	public static Object getSessionBean(String _nomeBean) {
		Object sBean = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(_nomeBean);
		return sBean;
	}

	public static String getMex(String _key) {
		ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "mex");
		String value = bundle.getString(_key);
		return value;
	}

	public static Flash flashScope() {
		return (FacesContext.getCurrentInstance().getExternalContext().getFlash());
	}

	@SuppressWarnings("unchecked")
	public static void getRequestParameterRead() {
		Enumeration<String> enu = HttpJSFUtil.getRequest().getParameterNames();
		while (enu.hasMoreElements()) {
			String o = enu.nextElement();
			String v = HttpJSFUtil.getRequest().getParameter(o);
			GoldmanagerLogger.info(HttpJSFUtil.class, "Request par key: " + o + " value: " + v, null);
		}
	}

	@SuppressWarnings("unchecked")
	public static void getLogSessionRequest(HttpSession session, HttpServletRequest req) {
		GoldmanagerLogger.debug(HttpJSFUtil.class, "Session id: " + session.getId() + " req ip: " + req.getRemoteAddr(), null);
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = session.getAttribute(name).toString();
			GoldmanagerLogger.debug(HttpJSFUtil.class, "Session name: " + name + " value: " + value, null);
		}
		Enumeration<String> enu = req.getParameterNames();
		while (enu.hasMoreElements()) {
			String o = (String) enu.nextElement();
			String v = req.getParameter(o);
			GoldmanagerLogger.info(HttpJSFUtil.class, "Request par key: " + o + " value: " + v, null);
		}
		Enumeration<String> en = req.getAttributeNames();
		while (en.hasMoreElements()) {
			String o = (String) en.nextElement();
			String v = req.getParameter(o);
			GoldmanagerLogger.info(HttpJSFUtil.class, "Request att key: " + o + " value: " + v, null);
		}
	}
}