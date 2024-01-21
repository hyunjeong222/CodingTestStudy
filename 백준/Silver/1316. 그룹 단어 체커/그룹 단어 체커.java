import java.io.*;

public class Main {
    static boolean[] checked;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int ans = 0; // 그룹 단어의 개수
        while (n --> 0) {
            str = br.readLine();
            checked = new boolean[26];
            if (check()) ans++;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean check() {
        int prev = 0;
        for (int i = 0; i < str.length(); i++) {
            int now = str.charAt(i);
            if (prev != now) {
                if (!checked[now-'a']) {
                    checked[now-'a'] = true;
                    prev = now;
                } else return false;
            } else continue;
        }
        return true;
    }
}