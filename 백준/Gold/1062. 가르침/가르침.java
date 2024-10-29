import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static String[] word;
    static boolean[] checked;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (k < 5) {
            System.out.println(0);
            return;
        } else if (k == 26) {
            System.out.println(n);
            return;
        }

        word = new String[n];
        checked = new boolean[26];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            word[i] = str.substring(4, str.length()-4);
        }

        checked['a'-'a'] = true;
        checked['c'-'a'] = true;
        checked['i'-'a'] = true;
        checked['n'-'a'] = true;
        checked['t'-'a'] = true;

        dfs(5,0);

        System.out.println(max);
    }

    static void dfs(int depth, int idx) {
        if (depth == k) {
            check();
            return;
        }

        for (int i = idx; i < 26; i++) {
            if(!checked[i]){
                checked[i] = true;
                dfs(depth+1, i);
                checked[i] = false;
            }
        }
    }

    private static void check() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < word[i].length(); j++) {
                if (!checked[word[i].charAt(j)-'a']) {
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }

        max = Math.max(max, cnt);
    }
}