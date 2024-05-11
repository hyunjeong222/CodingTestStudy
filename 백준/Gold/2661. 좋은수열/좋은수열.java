import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        backtracking("1");
    }

    public static void backtracking(String cur) {
        if (cur.length() == n) {
            System.out.println(cur);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            if (isGood(cur+i)) {
                backtracking(cur+i);
            }
        }
    }

    private static boolean isGood(String s) {
        for (int i = 1; i <= s.length()/2; i++) {
            for (int j = 0; j <= s.length()-(i*2); j++) {
                if (s.substring(j, j+i).equals(s.substring(j+i, j+i*2))) return false;
            }
        }
        return true;
    }
}