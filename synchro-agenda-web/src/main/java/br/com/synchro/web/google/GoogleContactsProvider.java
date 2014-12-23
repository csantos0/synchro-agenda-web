package br.com.synchro.web.google;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.synchro.web.domain.GoogleContact;
import br.com.synchro.web.exception.GoogleException;
import br.com.synchro.web.util.StringUtil;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.util.ServiceException;

/**
 * 
 * GoogleContactsService.java
 * Criado em Sep 27, 2014
 * @author Ciro S. Santos
 * @version 1.0
 */
@Repository
public class GoogleContactsProvider {
	
	private static final String GOOGLE_CONTACT_SERVICE = "https://www.google.com/m8/feeds/contacts/default/full?max-results=100000";
	
	public List<GoogleContact> getAllContacts(String user, String password) throws GoogleException {
		List<GoogleContact> contactsList = new ArrayList<GoogleContact>();
		try{
			ContactsService myService = new ContactsService("contactsclientapp");
			myService.setUserCredentials(user, password);		
	
			URL feedUrl = new URL(GOOGLE_CONTACT_SERVICE);
			ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
			
			for (int i = 0; i < resultFeed.getEntries().size(); i++) {			
				ContactEntry entry = resultFeed.getEntries().get(i);		
				
				if (entry.hasName()) {
					GoogleContact gc = new GoogleContact();
					
					Name name = entry.getName();	
					if(name.hasFullName()){
						String fullNameToDisplay = name.getFullName().getValue();
						if(fullNameToDisplay != null && !fullNameToDisplay.equals("")){
							String split[] = fullNameToDisplay.split("\\s+");
							if(split.length > 1){
								gc.setName(split[0]);
								gc.setSurname(split[1]);
							}else{
								gc.setName(fullNameToDisplay);
								gc.setSurname("");
							}
						}else{
							gc.setName(fullNameToDisplay);
							gc.setSurname("");
						}				
					}else{
						gc.setName(name.getGivenName().getValue());
						gc.setSurname("");
					}
					
					for (Email email : entry.getEmailAddresses()) {
						if(email.getPrimary()){
							gc.setEmail(email.getAddress());
							break;
						}
					}
					
					for(PhoneNumber phone : entry.getPhoneNumbers()){
						if(phone.getPhoneNumber() != null && !phone.getPhoneNumber().equals("")){
							gc.setPhone(phone.getPhoneNumber());
							break;
						}
					}
					
					gc.setModel(new Long(i+1));
					contactsList.add(gc);
				}
			}
			
		}catch(ServiceException ex){
			throw new GoogleException(this.getClass().getName() + ".getAllContacts(String user, String password)", ex.getMessage(), StringUtil.getStackTrace(ex));
		}catch(IOException ex){
			throw new GoogleException(this.getClass().getName() + ".getAllContacts(String user, String password)", ex.getMessage(), StringUtil.getStackTrace(ex));
		}catch(Exception ex){
			throw new GoogleException(this.getClass().getName() + ".getAllContacts(String user, String password)", ex.getMessage(), StringUtil.getStackTrace(ex));
		}		
		return contactsList;
	}
}
