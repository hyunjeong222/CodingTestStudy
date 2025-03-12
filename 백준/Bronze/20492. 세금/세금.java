import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        
        System.out.println((int)(n * 0.78) + " " + (int)(n - (n * 0.044)));
    }
}