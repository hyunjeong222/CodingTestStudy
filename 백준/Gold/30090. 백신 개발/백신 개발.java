import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n, minLength;
    static boolean[] checked;
    static String[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new String[n];

        for (int i = 0; i < n; i++) {
            list[i] = br.readLine();
        }

        minLength = Integer.MAX_VALUE;
        checked = new boolean[n];

        dfs("", 0);
        System.out.println(minLength);
    }

    private static void dfs(String current, int depth) {
        if (depth == n) {
            minLength = Math.min(minLength, current.length());
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(merge(current, list[i]), depth + 1);
                checked[i] = false;
            }
        }
    }

    private static String merge(String current, String word) {
        if (current.isEmpty()) return word;

        int cLen = current.length();
        int wLen = word.length();
        int maxOverlap = 0;

        for (int k = 1; k <= Math.min(cLen, wLen); k++) {
            if (current.substring(cLen - k).equals(word.substring(0, k))) {
                maxOverlap = k;
            }
        }

        return current + word.substring(maxOverlap);
    }
}