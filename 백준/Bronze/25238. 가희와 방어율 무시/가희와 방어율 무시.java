import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        double defense = a - (double)(a*b)/100;

        StringBuilder sb = new StringBuilder();
        if (defense >= 100) {
            sb.append(0).append("\n");
        } else sb.append(1).append("\n");
        
        System.out.println(sb.toString());
    }
}