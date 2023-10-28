import java.io.*;

public class Main {
    static long ans = 0;
    static char[] arr;
    static String vowel = "AIEOU";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = br.readLine().toCharArray(); // 즐겁지 않은 단어

        ans = dfs(0, 0, 0, false);
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long dfs(int idx, int consonantCnt, int vowelCnt, boolean flagL) {
        if (consonantCnt == 3 || vowelCnt == 3) return 0;

        if (idx == arr.length) {
            if (flagL) return 1;
            else return 0;
        }

        long cnt = 0;
        if (arr[idx] == '_') {
            cnt += dfs(idx + 1, 0, vowelCnt + 1, flagL) * 5;
            cnt += dfs(idx + 1, consonantCnt + 1, 0, flagL) * 20;
            cnt += dfs(idx + 1, consonantCnt + 1, 0, true);
        } else if (vowel.contains(arr[idx] + "")) {
            cnt += dfs(idx + 1, 0, vowelCnt + 1, flagL);
        } else {
            if (arr[idx] == 'L') cnt += dfs(idx + 1, consonantCnt + 1, 0, true);
            else cnt += dfs(idx + 1, consonantCnt + 1, 0, flagL);
        }

        return cnt;
    }
}