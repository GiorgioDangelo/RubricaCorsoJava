package it.rdev.rubrica.model.impl.file;

import java.io.FileWriter;
import java.io.IOException;

class DataSource {

	private FileWriter testo;
	private String path;

	private static DataSource ds;

	public static DataSource getInstance() {
		if (ds == null) {
			ds = new DataSource();
		}
		return ds;
	}

	private DataSource() {
		try {
			this.path="C:\\Users\\ultim\\Desktop\\testo_12.txt";
			FileWriter myWriter = new FileWriter(path, true);
			this.testo = myWriter;
			System.out.println("Il file è stato aperto con successo.");
		} catch (IOException e) {
			System.out.println("Errore nell'apertura del file.");
			e.printStackTrace();
		}
	}

	public FileWriter getFile() {
		return testo;
	}
	
	public String getPath() {
		return path;
	}

}
