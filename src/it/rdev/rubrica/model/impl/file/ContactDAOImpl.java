package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact, Integer> implements ContactDAO {

	@Override
	public boolean persist(Contact t) throws SQLException {

		DataSource oggetto = DataSource.getInstance();
		FileWriter ok = oggetto.getFile();
		StringBuilder emailbuffer = new StringBuilder();
		try {
			ok.write(t.getId().toString() + ',' + t.getName() + ',' + t.getSurname());
			if (t.getEmails()!=null) {
				Iterator<String> emailIteratore = t.getEmails().iterator();
				while(emailIteratore.hasNext()!=false) {
					//emailbuffer.append(","+emailIteratore.next());
					ok.write(","+emailIteratore.next());
					
				}
				
			}
			ok.write(emailbuffer.toString());
			StringBuilder sb = new StringBuilder();
			if (t.getPhoneNumbers()!=null) {
				Iterator<String> phoneIteratore = t.getPhoneNumbers().iterator();
				while(phoneIteratore.hasNext()!=false) {
					//sb.append(","+phoneIteratore.next());
					ok.write(","+phoneIteratore.next());
				}
			}
			
			
			ok.write("\n");
			ok.close();

		} catch (IOException e) {
		}
		return false;
	}
		

	@Override
	public boolean delete(Contact t) throws SQLException {
		String path = DataSource.getInstance().getPath();
		BufferedReader reader;
		try {
	        BufferedReader file = new BufferedReader(new FileReader(path));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

	        while ((line = file.readLine()) != null) {
	        	char first = line.charAt(0);
				String myfirst = Character. toString(first);
				if (myfirst.equals(t.getId().toString())){
					line = "";
		            inputBuffer.append(line);
				}
				else {
					inputBuffer.append(line);
		            inputBuffer.append('\n');
				}
	        }
	        file.close();

	        FileOutputStream fileOut = new FileOutputStream(path);
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Non esiste questo id");
	    }
		return false;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		String path = DataSource.getInstance().getPath();
		BufferedReader reader;
		try {
	        BufferedReader file = new BufferedReader(new FileReader(path));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;

			while ((line = file.readLine()) != null) {
				char first = line.charAt(0);
				String myfirst = Character.toString(first);
				if (myfirst.equals(t.getId().toString())) {
					String name = t.getName();
					String surname = t.getSurname();
					String id = t.getId().toString();
					inputBuffer.append(id + "," + name + "," + surname);
					if (t.getEmails() != null) {
						Iterator<String> emailIteratore = t.getEmails().iterator();
						while (emailIteratore.hasNext() != false) {
							inputBuffer.append("," + emailIteratore.next());
						}

					}
					if (t.getPhoneNumbers() != null) {
						Iterator<String> phoneIteratore = t.getPhoneNumbers().iterator();
						while (phoneIteratore.hasNext() != false) {
							inputBuffer.append("," + phoneIteratore.next());
						}

					}

				} else {
					inputBuffer.append(line);
					inputBuffer.append('\n');
				}
			}
			file.close();

	        FileOutputStream fileOut = new FileOutputStream(path);
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Non esiste questo id");
	    }
		return false;
	}

	@Override
	public List<Contact> getAll() {
		List<Contact> contacts = new ArrayList<>();
		String path = DataSource.getInstance().getPath();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contacts;
	}


	

}
