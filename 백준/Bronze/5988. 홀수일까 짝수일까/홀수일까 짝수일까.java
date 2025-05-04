import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        if (n != 0) {
            for (int i = 0; i < n; i++) {
                String[] arr = br.readLine().split("");
                if (Integer.parseInt(arr[arr.length-1]) % 2 == 0) sb.append("even").append("\n");
                else sb.append("odd").append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
}