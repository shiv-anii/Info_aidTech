package info_aidtech;
import java.util.Scanner;
import java.util.Random;
public class PasswordGenerator {
	
	public static String passGenerator(int len, String char_set) {
		Random random = new Random();
		StringBuilder password = new StringBuilder();
		
		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(char_set.length());
			char randomChar = char_set.charAt(randomIndex);
			password.append(randomChar);
		}
		
		return password.toString();
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("This is a password generator.");
		System.out.println("Enter the length of your password : ");
		int len = s.nextInt();
		System.out.println("Enter the character set you want to use : ");
	    System.out.println("1. Default (ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=)");
	    System.out.println("2. Custom");
	    int choice = s.nextInt();
	        
	    String char_set;
	    switch (choice) {
	        case 1:
	            char_set = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
	            break;
	        case 2:
	            System.out.println("Enter custom character set : ");
	            s.nextLine();
	            char_set = s.nextLine();
	            break;
	        default:
	            System.out.println("Invalid choice. Using default character set...");
	            char_set = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=";
	        }
	    
	    String password = passGenerator(len, char_set);
	    System.out.println(password);
		
		
		s.close();
	}

}
