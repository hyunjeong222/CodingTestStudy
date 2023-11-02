import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine(); // 특정 문자열

        int[][] alpha = new int[s.length()][26]; // s에 대한 각 알파벳의 누적합을 저장할 배열
        alpha[0][s.charAt(0)-'a']++;
        for (int i = 1; i < s.length(); i++) {
            int tmp = s.charAt(i) - 'a';

            for (int j = 0; j < 26; j++) {
                alpha[i][j] = alpha[i-1][j];
            }
            alpha[i][tmp]++;
        }

        int q = Integer.parseInt(br.readLine()); // 질문의 수
        while (q --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char find = st.nextToken().charAt(0); // 찾아야 하는 특정 알파벳
            // 문자열의 구간
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            if (l == 0) bw.append(alpha[r][find-'a'] + "\n");
            else bw.append(alpha[r][find-'a']-alpha[l-1][find-'a'] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}