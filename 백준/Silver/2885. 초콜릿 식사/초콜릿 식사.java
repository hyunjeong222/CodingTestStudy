import java.util.*;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = 1;
        int count = 0;
        
        while(n<k) n= n*2;
        
        StringBuilder sb= new StringBuilder();
        sb.append(n).append(" ");
        
        while(k>0){
            if(k>=n){
                k-=n;
            }else{
                n/=2;
                count++;
            }
        }
        
        sb.append(count);
        System.out.println(sb);
    }
}