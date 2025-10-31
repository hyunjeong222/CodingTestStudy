import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String ch = scanner.next();

        if(ch.equals("NLCS")){
            System.out.println("North London Collegiate School");
        }else if(ch.equals("BHA")){
            System.out.println("Branksome Hall Asia");
        }else if(ch.equals("KIS")){
            System.out.println("Korea International School");
        }else if(ch.equals("SJA")){
            System.out.println("St. Johnsbury Academy");
        }
    }
}