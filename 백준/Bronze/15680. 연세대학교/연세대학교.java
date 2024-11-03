import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        if (n == 0) sb.append("YONSEI").append("\n");
        else sb.append("Leading the Way to the Future").append("\n");
        
        System.out.println(sb.toString());
    }
}
