package application;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class SampleController implements Initializable {

    @FXML
    private Button a;

    @FXML
    private Button b;

    @FXML
    private Button c;

    @FXML
    private Button d;

    @FXML
    private Button e;

    @FXML
    private Button f;

    @FXML
    private Button g;

    @FXML
    private Button h;

    @FXML
    private Button i;

    @FXML
    private Button j;

    @FXML
    private Button k;

    @FXML
    private Button l;

    @FXML
    private Button m;

    @FXML
    private Button n;

    @FXML
    private Button o;

    @FXML
    private Button p;

    @FXML
    private Button q;

    @FXML
    private Button r;

    @FXML
    private Button s;

    @FXML
    private Button t;

    @FXML
    private Button u;

    @FXML
    private Button v;

    @FXML
    private Button w;

    @FXML
    private Button x;

    @FXML
    private Button y;

    @FXML
    private Button z;
    
    @FXML
    private Text arvattavaSana;
    
    @FXML
    private Text arvatutKirjaimet;

    @FXML
    private Text arvauksiaJaljella;
    
    @FXML
    private Button alustusNappi;
    
    @FXML
    private Text hirsiBase;

    @FXML
    private Text hirsiKoysi;

    @FXML
    private Text hirsiPaa;

    @FXML
    private Text hirsiParru;

    @FXML
    private Text hirsiTolppa;

    @FXML
    private Text hirsiVartalo;
    
    private HashMap <Button, Character> kirjaimet;
    private Sanalista sanalista;
    private Hirsipuu hirsipuu;
    private List <Text> puuGrafiikat;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sanalista = new Sanalista("words.txt");
		hirsipuu = new Hirsipuu(sanalista, 6);
		puuGrafiikat = new ArrayList<>(Arrays.asList(
													 hirsiVartalo, 
													 hirsiPaa, 
													 hirsiKoysi, 
													 hirsiParru, 
													 hirsiTolppa, 
													 hirsiBase));
		puuGrafiikat.forEach(txt -> {
			txt.setVisible(false);
		});
		alustusNappi.setFocusTraversable(false);
		arvattavaSana.setText(hirsipuu.getSb().toString());
		kirjaimet = new HashMap<>();
		kirjaimet.put(q, 'q');
		kirjaimet.put(w, 'w');
		kirjaimet.put(e, 'e');
		kirjaimet.put(r, 'r');
		kirjaimet.put(t, 't');
		kirjaimet.put(y, 'y');
		kirjaimet.put(u, 'u');
		kirjaimet.put(i, 'i');
		kirjaimet.put(o, 'o');
		kirjaimet.put(p, 'p');
		kirjaimet.put(a, 'a');
		kirjaimet.put(s, 's');
		kirjaimet.put(d, 'd');
		kirjaimet.put(f, 'f');
		kirjaimet.put(g, 'g');
		kirjaimet.put(h, 'h');
		kirjaimet.put(j, 'j');
		kirjaimet.put(k, 'k');
		kirjaimet.put(l, 'l');
		kirjaimet.put(z, 'z');
		kirjaimet.put(x, 'x');
		kirjaimet.put(c, 'c');
		kirjaimet.put(v, 'v');
		kirjaimet.put(b, 'b');
		kirjaimet.put(n, 'n');
		kirjaimet.put(m, 'm');
		kirjaimet.keySet().forEach(btn ->{
			btn.setFocusTraversable(false);
			napinPainallus(btn);
		});
	}
	
    @FXML
    void uusiPeli(ActionEvent event) {
    	hirsipuu.randomSana();
    	hirsipuu.setArvaustenMaara(6);
    	arvauksiaJaljella.setText("Wrong guesses left: " + hirsipuu.arvauksiaOnJaljella());
    	arvattavaSana.setText(hirsipuu.getSb().toString());
    	puuGrafiikat.forEach(txt -> {
    		txt.setVisible(false);
    	});
    	kirjaimet.keySet().forEach(btn->{
    		btn.setDisable(false);
    		btn.setStyle("-fx-text-fill: black;");
    	});
    }
    
	private void napinPainallus(Button btn) {
		btn.setOnMouseClicked(MouseEvent ->{
			if(hirsipuu.arvaa(kirjaimet.get(btn))) {
				oikeaArvaus(btn);
				peliTilanne();
			}else
				vaaraArvaus(btn);
				peliTilanne();
		});
	}
	
	private void oikeaArvaus(Button btn) {
		btn.setStyle("-fx-text-fill: #66ff00;");
		btn.setDisable(true);
		arvattavaSana.setText(hirsipuu.getSb().toString());
	}
	
	private void vaaraArvaus(Button btn) {
		btn.setStyle("-fx-text-fill: #ff0000;");
		btn.setDisable(true);
		arvauksiaJaljella.setText("Wrong guesses left: " + hirsipuu.arvauksiaOnJaljella());
		piirraHirsi(hirsipuu.arvauksiaOnJaljella());
	}
	
	private void peliTilanne() {
		if(hirsipuu.onLoppu()) {
			arvattavaSana.setText("You won! the word was: " + hirsipuu.sana());
			kirjaimet.keySet().forEach(btn->{
				btn.setDisable(true);
			});
		}else if(hirsipuu.arvauksiaOnJaljella() == 0) {
			arvattavaSana.setText("You lost, the word was: " + hirsipuu.sana());
			kirjaimet.keySet().forEach(btn->{
				btn.setDisable(true);
			});
		}
	}
	
	private void piirraHirsi(int arvaustenMaara) {
		puuGrafiikat.get(arvaustenMaara).setVisible(true);
	}
}