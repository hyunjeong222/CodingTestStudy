import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int s = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();
        int cnt = 0, ans = 0;
        for (int i = 1; i < s-1;) {
            if (arr[i]=='O' && arr[i+1]=='I') {
                cnt++;
                if (cnt == n) {
                    if (arr[i-(cnt*2-1)]=='I') {
                        ans++;
                    }
                    cnt--;
                }
                i += 2;
            } else {
                cnt = 0;
                i++;
            }
        }

        System.out.println(ans);

        br.close();
    }
}