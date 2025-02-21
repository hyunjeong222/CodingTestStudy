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
        Arrays.sort(date, Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            date[i] = (i+1)+date[i];
        }
        Arrays.sort(date, Comparator.reverseOrder());
        System.out.println(date[0]+1);
    }
}