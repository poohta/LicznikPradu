import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
	public static String path = "urzadzenia.txt";
	public static File plik = new File(path);
	public static double suma=0;	//suma pobor�w mocy urz�dze�, suma=u+u+u+...
	public static double koszt=0;	//koszt=suma*cena, wynik
	public static double cena=0;	//cena za 1kWh
	public static double p=0;		//moc urz�dzenia
	public static double t=0;		//czas u�ywania urz�dzenia
	public static double u=0;		//u�ycie pr�du w kWh, u=P*t
	public static String nazwaUrzadzenia="";	//nazwa dodawanego urzadenia
	public static String pomoc="";				//pomocniczy string u�ywany przy konwersji "String to int"

	public static Okno ekran = new Okno();

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		//je�eli nie ma pliku to go stw�rz
		if(!plik.exists()) { 
			PrintWriter writer = new PrintWriter("path", "UTF-8");
			writer.print("");
			writer.close();
		}
		reset();	//reset warto�ci z poprzedniej sesji
		if (ekran.isVisible() == false)
			ekran.setVisible(true);
		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void obliczKoszt() {	//metoda obliczaj�ca koszt
		try{
			pomoc=JOptionPane.showInputDialog(null,"Podaj cen� za 1kWh w groszach");
			cena=Double.parseDouble(pomoc);
			koszt=cena*suma/100;
			//System.out.println(koszt);
	        koszt *= 100; 
	        koszt = Math.round(koszt);	//zaokr�glanie do 2 miejsc po przecinku
	        koszt /= 100; 
			//System.out.format("%.2f%n", koszt);
			
			JOptionPane.showMessageDialog(null,"Koszt zu�ycia pr�du wynosi: "+koszt+"z� dziennie");
	} catch(Exception e){
		JOptionPane.showMessageDialog(null, "Podana warto�� nie jest liczb� lub jej warto�� przekracza zakres");
	}

	}

	public static void reset() { // metoda resetuj�ca gr�
		suma=0;
		try {
			//String tekst = "";
			FileWriter zapis = new FileWriter(path);
			//zapis.write(tekst);
			zapis.close();
		} catch (IOException e) {
			System.out.println("B��d zapisu");
			JOptionPane.showMessageDialog(null,
					"B��d zapisu! Sprawd� czy plik .jar jest w tym samym folderze co plik z wynikami.", "B��d zapisu!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}
	
	public static void dodajUrzadzenie() {	//metoda dodaj�ce urz�dzenie do listy
		nazwaUrzadzenia = JOptionPane.showInputDialog("Podaj nazw� urz�dzenia");
		try{
			pomoc = JOptionPane.showInputDialog("Podaj pob�r mocy urz�dzenia w watach[W]");
			p = Double.parseDouble(pomoc);
			try{
				pomoc = JOptionPane.showInputDialog("Podaj �redni dzienny czas u�ytkowania urz�dzenia w godzinach[h]");
				//pomoc = pomoc.replace(',', '.');
				t = Double.parseDouble(pomoc);
				u=p*t/1000;
				suma=suma+u;
				zapisWyniku();
				} catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Podana warto�� nie jest liczb� lub jej warto�� przekracza zakres");
				}
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "Podana warto�� nie jest liczb� lub jej warto�� przekracza zakres");
		}
	}
	
	public static void zapisWyniku() { // metoda zapisuj�ca
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(plik));
			String l = bReader.readLine();
			String tekst = "";
			while (l != null) {
				l = bReader.readLine();
				if (l != null)
					tekst = tekst + "\n" + l;
			}
			FileWriter zapis = new FileWriter(path);
			zapis.write(tekst +"\n"+ nazwaUrzadzenia +"      "+ p + "[W]" +"       "+ t + "[h]" +"       "+ u+"[kWh]");
			zapis.close();
		} catch (IOException e) {
			System.out.println("B��d zapisu");
			JOptionPane.showMessageDialog(null,
					"B��d zapisu! Sprawd� czy plik .jar jest w tym samym folderze co plik z wynikami.", "B��d zapisu!",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	public static void odczytWyniku() throws IOException {
		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(plik));
			String l = bReader.readLine();
			String tekst = l;
			while (l != null) {
				System.out.println(l);
				l = bReader.readLine();
				if (l != null)
					tekst = tekst + "\n" + l;
			}
			JOptionPane.showMessageDialog(null, tekst, "Tabela wynik�w", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (bReader != null) {
				bReader.close();
			}
		}
	}
}