import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] arr;
        int n = 5;
        while (t-->0) {
            st = new StringTokenizer(br.readLine());
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int sum = 0;
            for (int i = 1; i < n-1; i++) {
                sum += arr[i];
            }

            if (arr[n-2]-arr[1] >= 4) sb.append("KIN").append("\n");
            else sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}