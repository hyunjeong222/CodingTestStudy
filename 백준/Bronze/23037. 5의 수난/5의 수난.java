import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] n = br.readLine().toCharArray();

        int ans = 0;
        for (int i = 0; i < n.length; i++) {
            ans += Math.pow(n[i]-'0', 5);
        }

        System.out.println(ans);

        br.close();
    }
}