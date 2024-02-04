import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String s, t;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        t = br.readLine();
        int ans = dfs(t, s);
        System.out.println(ans);
    }

    private static int dfs(String t, String s) {
        if (s.length() == t.length()) {
            if (s.equals(t)) return 1;
            else return 0;
        }

        int result = 0;
        if (t.charAt(t.length()-1) == 'B') {
            StringBuilder sb = new StringBuilder(t);
            String string = sb.reverse().toString();
            result += dfs(string.substring(1), s);
        }
        if (t.charAt(t.length()-1) == 'A') {
            result += dfs(t.substring(0, t.length()-1), s);
        }
        return result > 0 ? 1 : 0;
    }
}