package exercicisSolts;

public class HexToDec_and_viceversa {
	
	public static int hexToDec(String hex) {
		int decimal = 0;
		
		decimal = Integer.parseInt(hex,16);
		
		return decimal;
	}
	
	public static String decToHex(int dec) {
		String hexadecimal = "";
		
		hexadecimal = Integer.toHexString(dec);
		
		return hexadecimal;
	}
	
	public static void main(String[] args) {
		String hex = "ac01b0";
		int dec;
		
		dec = hexToDec(hex);
		System.out.println(dec);
		
		hex = decToHex(dec);
		System.out.println(hex);
	}
}
