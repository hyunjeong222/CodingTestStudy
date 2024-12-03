import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        
        StringBuilder sb = new StringBuilder();
        if (n == m) sb.append("1").append("\n");
        else sb.append("0").append("\n");
        
        System.out.println(sb.toString());
    }
}