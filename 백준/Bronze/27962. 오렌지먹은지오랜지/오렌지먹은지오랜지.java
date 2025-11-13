import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        s = br.readLine();
        while (--n > 0) if (compare(n)) break;

        bw.write(n > 0 ? "YES" : "NO");
        bw.flush();
    }

    private static boolean compare(int n) {
        boolean b = false;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == s.charAt(s.length() - n + i)) continue;
            if (b) return false;
            else b = true;
        }

        return b;
    }

}