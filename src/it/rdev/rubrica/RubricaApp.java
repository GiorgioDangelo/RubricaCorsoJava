package it.rdev.rubrica;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;
import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;
import it.rdev.rubrica.model.impl.DaoManager;
import it.rdev.rubrica.ui.AppFrame;

public class RubricaApp {

	public static void main(String[] strings) {
		String persistenza=Configuration.getInstance().getValue(ConfigKeys.PERSISTENCE_TYPE);
		//System.out.println(persistenza);
		RubricaController punto_ingresso=new RubricaController();
		
		Contact o1=new Contact();
		o1.setId(1);
		o1.setName("giorgio");
		o1.setSurname("D'Angelo");
		o1.addEmail("giorgio@gmail");
		o1.addPhoneNumber("39140234");
		o1.addPhoneNumber("34343535353");
		o1.addEmail("salve@gmail");
		Contact o2=new Contact()
		.setName("ale")
		.setId(2)
		.setSurname("rossi")
		.addEmail("ale@gmail.com");		
		Contact o3=new Contact()
		.setId(2)
		.setName("laura")
		.setSurname("cognome")
		.addEmail("laura@gmail.com");
		//punto_ingresso.addContact(o2);
		//punto_ingresso.getContactList();
		//punto_ingresso.removeContact(o3);
		//System.out.println(punto_ingresso.getContactList());
		//DaoManager punto_ingresso=new DaoManager();
		punto_ingresso.updateContact(o3);

		
		if (persistenza=="RDBMS") {
			new AppFrame().setVisible(true);
		}
		
	}
	
}
