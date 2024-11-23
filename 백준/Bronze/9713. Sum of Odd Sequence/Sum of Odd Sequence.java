import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int sum;
		for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            sum = 0;
            for (int j = 1; j <= n; j+=2) {
                sum += j;
            }
            sb.append(sum).append("\n");
        }
        
        System.out.println(sb.toString());
	}
}