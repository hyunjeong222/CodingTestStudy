import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] date = new Integer[n];
        for (int i = 0; i < n; i++) {
            date[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(date);
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, date[i] - i);
        }
        System.out.println(max+n+1);
    }
}