package exercicisSolts;

import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioTest {

	public static void main(String[] args) {
		 try {

	        Clip sonido = AudioSystem.getClip();

	        //Carregar fitxer
	        sonido.open(AudioSystem.getAudioInputStream(new File("D:\\01.Descargas\\California.wav")));

	        //Comença audio
	        sonido.start();
	        
	        try {
				Thread.sleep(1);		
	        }catch(Exception e) {
	        	System.out.println(e.getMessage());
	        }
	        
	        while (sonido.isRunning()) {
		       	System.out.println("");
		    }

	        //Tancar Clip
	        sonido.close();
	    } catch (Exception e) {
	            System.out.println(e.getMessage());
	    }
	}
}