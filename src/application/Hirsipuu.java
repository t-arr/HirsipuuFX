package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hirsipuu {

	private int arvaustenMaara;
	private List<Character> arvatutKirjaimet = new ArrayList<>();
	private String oikeaSana;
	private Sanalista sanalista;
	private StringBuilder sb;

	public Hirsipuu(Sanalista sanalista, int arvaustenMaara) {
		
		this.arvaustenMaara = arvaustenMaara;
		this.sanalista = sanalista;
		randomSana();
	}

	public boolean arvaa(Character merkki) {
		arvatutKirjaimet.add(merkki);
		arvatutKirjaimet.add(' ');
		for(int i = 0; i < oikeaSana.length(); i++) {
			if(oikeaSana.charAt(i) == merkki) {
				setSb(merkki);
				return true;
			}
		}
		arvaustenMaara -= 1;
		return false;

	}

	public List<Character> arvaukset(){
		return arvatutKirjaimet;
	} 

	public int arvauksiaOnJaljella() {
		return arvaustenMaara;
	}
	
	public void setArvaustenMaara(int arvaustenMaara) {
		this.arvaustenMaara = arvaustenMaara;
	}

	public String sana() {
		return oikeaSana;
	}
	
	public void randomSana() {
		Random random = new Random();
		int randomNumber = random.nextInt(sanalista.annaSanat().size());
		oikeaSana = sanalista.annaSanat().get(randomNumber);
		oikeaSana = oikeaSana.toLowerCase();
		sb = new StringBuilder(oikeaSana.replaceAll("[A-Öa-ö]", "*"));
	}

	public boolean onLoppu() {
		for(int i = 0; i < getSb().length(); i++) {
			if(sb.charAt(i) != sana().charAt(i)) {
				return false;
			}
		}
		return true;
	} 

	public StringBuilder setSb(char merkki) {		
		for(int i = 0; i < oikeaSana.length(); i++) {
			if(oikeaSana.charAt(i) == merkki) {
				sb.setCharAt(i, merkki);
			}
		}
		return sb; 
	} 

	public StringBuilder getSb() {
		return sb;
	}
}

