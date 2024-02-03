import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        System.out.println(dfs(t, s));
    }

    private static int dfs(String t, String s) {
        if (s.length() == t.length()) {
            if (s.equals(t)) return 1;
            else return 0;
        }
        int result = 0;
        if (t.charAt(0) == 'B') {
            String string = t.substring(1);
            StringBuilder sb = new StringBuilder(string);
            String substring = sb.reverse().toString();
            result += dfs(substring, s);
        }
        if (t.charAt(t.length()-1) == 'A') {
            result += dfs(t.substring(0, t.length()-1), s);
        }
        return result > 0 ? 1 : 0;
    }
}