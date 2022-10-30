package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sanalista {

	private List<String> sanat = new ArrayList<>();
	
	
	public Sanalista(String sanat){
		File sanaTiedosto = new File(sanat);
		Scanner listaScanner = null;
		try {
			
			listaScanner = new Scanner(sanaTiedosto);
			while(listaScanner.hasNextLine()) {
				this.sanat.add(listaScanner.nextLine());
			}
			listaScanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<String> annaSanat(){
		return sanat;
	}	
}