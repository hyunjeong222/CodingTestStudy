import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double sum = 0;
        double max = 0.0;
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            sum += value;
            max = Math.max(max, value);
        }
        System.out.println(((sum/max)*100.0)/n);
    }
}