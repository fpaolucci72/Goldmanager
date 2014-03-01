package it.goldmanager.common;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class MultiPageMessagesSupport implements PhaseListener {

	private static final long serialVersionUID = 3328743500652081238L;
	private static final String sessionToken = "MULTI_PAGE_MESSAGES_SUPPORT";

	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

	public void beforePhase(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
			FacesContext facesContext = event.getFacesContext();
			restoreMessages(facesContext);
		}
	}

	public void afterPhase(PhaseEvent event) {
		if (event.getPhaseId() == PhaseId.APPLY_REQUEST_VALUES || event.getPhaseId() == PhaseId.PROCESS_VALIDATIONS || event.getPhaseId() == PhaseId.INVOKE_APPLICATION) {
			FacesContext facesContext = event.getFacesContext();
			saveMessages(facesContext);
		}
	}

	@SuppressWarnings("unchecked")
	private int saveMessages(FacesContext facesContext) {
		// remove messages from the context
		Set<FacesMessage> messages = new HashSet<FacesMessage>();
		for (Iterator<FacesMessage> i = facesContext.getMessages(null); i.hasNext();) {
			FacesMessage msg = (FacesMessage) i.next();
			messages.add(msg);
			i.remove();
		}
		// store them in the session
		if (messages.size() == 0)
			return 0;
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		// if there already are messages
		Set<FacesMessage> existingMessages = (Set<FacesMessage>) sessionMap.get(sessionToken);
		if (existingMessages != null) {
			existingMessages.addAll(messages);
			// if these are the first messages
		} else {
			sessionMap.put(sessionToken, messages);
		}
		return messages.size();
	}

	@SuppressWarnings("unchecked")
	private int restoreMessages(FacesContext facesContext) {
		// remove messages from the session
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		Set<FacesMessage> messages = (Set<FacesMessage>) sessionMap.remove(sessionToken);
		// store them in the context
		if (messages == null)
			return 0;
		int restoredCount = messages.size();
		// set that contains wich messages already exists in the FacesContext
		Set<FacesMessage> facesContextMessages = new HashSet<FacesMessage>();
		for (Iterator<FacesMessage> i = facesContext.getMessages(null); i.hasNext();) {
			FacesMessage msg = (FacesMessage) i.next();
			facesContextMessages.add(msg);
			i.remove();
		}
		// add the messages that aren't in the FacesContext
		for (FacesMessage facesMessage : messages) {
			if (!facesContextMessages.contains(facesMessage))
				facesContext.addMessage(null, facesMessage);
		}
		return restoredCount;
	}
}
