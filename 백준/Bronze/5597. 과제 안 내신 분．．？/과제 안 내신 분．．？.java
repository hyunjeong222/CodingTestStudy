import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[31];
        for (int i = 0; i < 28; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[n] = n;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 30; i++) {
            if (arr[i] == 0) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}