import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        String s = br.readLine();

        int[] prefixD = new int[n+1]; // 왼쪽에서 B만 남기기 위해 뒤집어야 할 D 개수
        for (int i = 1; i <= n; i++) {
            prefixD[i] = prefixD[i-1] + (s.charAt(i-1) == 'D' ? 1 : 0);
        }
        // System.out.println(Arrays.toString(prefixD));

        int[] suffixB = new int[n+1]; // 오른쪽에서 D만 남기기 위해 뒤집어야 할 B 개수
        for (int i = n-1; i >= 0; i--) {
            suffixB[i] = suffixB[i+1] + (s.charAt(i) == 'B' ? 1 : 0);
        }
        // System.out.println(Arrays.toString(suffixB));

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            int flip = prefixD[i]+suffixB[i];
            min = Math.min(min, flip);
        }

        System.out.println(min);

        br.close();
    }
}