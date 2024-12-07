import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static long[] guitarBit;
    static int max = 0;
    static int ans = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 기타 개수
        m = Integer.parseInt(st.nextToken()); // 곡 개수

        guitarBit = new long[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            char[] guitarArr = st.nextToken().toCharArray();
            for (int j = 0; j < m; j++) {
                if (guitarArr[j] == 'Y') { // 연주 가능 1, 불가능 0
                    guitarBit[i] |= (1L << j);
                }
            }
        }

        back(0, 0L, 0);

        StringBuilder sb = new StringBuilder();
        if (ans == 0) { // 연주할 수 있는 곡 X
            sb.append(-1).append("\n");
        } else sb.append(ans).append("\n");

        System.out.println(sb.toString());
    }

    private static void back(int depth, long bit, int usingGuitar) {
        int bitCnt = Long.bitCount(bit); // 현재 연주 가능한 곡 개수

        if (bitCnt == max && usingGuitar < ans) {
            ans = usingGuitar;
        }

        if (max < bitCnt) {
            ans = usingGuitar;
            max = bitCnt;
        }

        if (bitCnt == m || depth == n) {
            return;
        }

        // 현재 기타 사용
        back(depth+1, bit | guitarBit[depth], usingGuitar+1);
        // 사용 X
        back(depth+1, bit, usingGuitar);
    }
}