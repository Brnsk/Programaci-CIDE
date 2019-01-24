package exercicisSolts;

import java.util.ArrayList;

public class OrdenaAlfabeticament {
	public static void main(String[] args) {
		int num_lletres = 0, aux = 0, c = 0, x = 1;
		ArrayList <String> llista_paraules = new ArrayList <String>();
		
		llista_paraules.add("Albaricoque");
		llista_paraules.add("Albari");
		llista_paraules.add("Castania");
		llista_paraules.add("Zelda");
		
		
		for(int i=0;i<llista_paraules.size();i++) {
			while(c < llista_paraules.get(i).length()) {
				aux++;
				if(aux > num_lletres) {
					num_lletres = aux;
				}
				c++;
			}
			aux = 0;
			c=0;
		}
		
		
		c=1;
		
		for(int i = 0; i < llista_paraules.size(); i++) {
			while(c < llista_paraules.size()) {
				if(llista_paraules.get(i).charAt(0) == llista_paraules.get(i+c).charAt(0)) {
					while(x < llista_paraules.get(i).length() || x < llista_paraules.get(i+c).length()) {
						if(llista_paraules.get(i).charAt(x) > llista_paraules.get(i+c).charAt(x)) {
							System.out.println(llista_paraules.get(i));
							break;
						}else if(llista_paraules.get(i).charAt(x) < llista_paraules.get(i+c).charAt(x)){
							System.out.println(llista_paraules.get(i+c));
							break;
						}else {
							x++;
						}
						
					}
				}else if(llista_paraules.get(i).charAt(0) > llista_paraules.get(i+c).charAt(0)) {
						System.out.println(llista_paraules.get(i));
				}
				c++;
			}
			c=0;
		}
	}
}