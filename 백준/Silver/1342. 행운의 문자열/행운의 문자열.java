import java.io.*;

public class Main {
    static String str;
    static int[] alpha;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        str = br.readLine(); // 문자열
        alpha = new int[27];
        for (int i = 0; i < str.length(); i++) {
            alpha[str.charAt(i)-'a']++;
        }
        dfs(0, ' ');
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int idx, char pre) {
        if (idx == str.length()) {
            ans++;
            return;
        }

        for (int i = 0; i < 27; i++) {
            if (alpha[i] == 0) continue;

            if (pre != (char)(i+'a')) {
                alpha[i]--;
                dfs(idx+1, (char)(i+'a'));
                alpha[i]++;
            }
        }
    }
}