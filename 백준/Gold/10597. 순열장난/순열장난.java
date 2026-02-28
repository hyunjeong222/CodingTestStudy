import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String s;
    static int len;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        len = s.length();
        checked = new boolean[51];

        dfs(0, 0, "");

        br.close();
    }

    private static void dfs(int idx, int n, String ans) {
        if (idx == len) {
            for (int i = 1; i <= n; i++) {
                if (!checked[i]) {
                    return;
                }
            }
            System.out.println(ans.trim());
            System.exit(0);
            return;
        }

        String tmp = s.substring(idx, idx+1);
        int num = Integer.parseInt(tmp);
        if (!checked[num]) {
            checked[num] = true;
            dfs(idx+1, (num>n)?num:n, ans+" "+tmp);
            checked[num] = false;
        }
        if (idx < len-1) {
            tmp = s.substring(idx, idx+2);
            num = Integer.parseInt(tmp);
            if (num < 51 && !checked[num]) {
                checked[num] = true;
                dfs(idx+2, (num>n)?num:n, ans+" "+tmp);
                checked[num] = false;
            }
        }
    }
}