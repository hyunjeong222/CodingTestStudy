import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] a;
    static int b;
    static boolean[] checked;
    static int ans = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] temp = st.nextToken().toCharArray();
        a = new int[temp.length];
        b = Integer.parseInt(st.nextToken());
        for (int i = 0; i < a.length; i++) {
            a[i] = temp[i] - '0';
        }
        checked = new boolean[a.length];
        permutation(0, 0);
        System.out.println(ans);
    }

    private static void permutation(int value, int depth) {
        if (depth == a.length) {
            ans = Math.max(ans, value);
            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (checked[i] || (depth == 0 && a[i] == 0)) continue;
            if (value*10+a[i] > b) continue;

            checked[i] = true;
            permutation(value*10+a[i], depth+1);
            checked[i] = false;
        }
    }
}