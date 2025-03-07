import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int INF = 100;
    static int n, minLength;
    static String[] word;
    static boolean[] checked;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        word = new String[n];

        for (int i = 0; i < n; i++) {
            word[i] = br.readLine();
        }

        minLength = INF;

        checked = new boolean[n];
        dfs(0, "", 0);

        System.out.println(minLength);
    }

    private static void dfs(int cnt, String current, int depth) {
        if (cnt == n) {
            minLength = Math.min(minLength, current.length());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                int idx = getMatchedIdx(current, word[i]);
                dfs(cnt+1, current+word[i].substring(idx), depth+1);
                checked[i] = false;
            }
        }
    }

    private static int getMatchedIdx(String current, String word) {
        int cLen = current.length();
        int wLen = word.length();
        int min = Math.min(cLen, wLen);

        for (int i = min; i >= 1; i--) {
            if (current.substring(cLen - i).equals(word.substring(0, i))) {
                return i;
            }
        }

        return 0;
    }
}