import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()); // 주사위의 개수
        int[][] dice = new int[n][6];

        // 주사위 입력
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        int ans = 0;

        for (int i = 0; i < 6; i++) {
            int bottom = dice[0][i];
            int top = dice[0][getTop(i)];

            for (int k = 0; k < n; k++) {
                int max = 0; // 위 아래 제외하고, 각 주사위 가장 큰 값
                for (int l = 0; l < 6; l++) {
                    if (dice[k][l] == top) {
                        bottom = top;
                        top = dice[k][getTop(l)];
                        
                        max = findMax(bottom, top);
                        break;
                    }
                }
                sum += max;
            }
            ans = Math.max(ans, sum);
            sum = 0;
        }
        System.out.println(ans);
    }

    private static int findMax(int bottom, int top) {
        for (int i = 6; i > 0; i--) {
            if (i != bottom && i != top) return i;
        }
        return 0;
    }

    private static int getTop(int idx) { // 마주보는 인덱스 찾기
        if (idx == 0) return 5;
        else if (idx == 1) return 3;
        else if (idx == 2) return 4;
        else if (idx == 3) return 1;
        else if (idx == 4) return 2;
        else return 0; // idx 5
    }
}