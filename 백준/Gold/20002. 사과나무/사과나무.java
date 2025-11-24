import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int[][] prefix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine()); // 과수원의 크기
        map = new int[n+1][n+1]; // 각 위치의 총이익
        prefix = new int[n+1][n+1]; // 2차원 누적합 배열
        
        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                
                // 2차원 누적합 계산
                prefix[i][j] = map[i][j]
                        + prefix[i-1][j]
                        + prefix[i][j-1]
                        - prefix[i-1][j-1];
            }
        }

        int ans = Integer.MIN_VALUE; // 가능한 모든 k×k 구간에서 얻을 수 있는 최대 총이익
        
        // k의 크기를 1부터 N까지 모두 체크
        for (int i = 1; i <= n; i++) {
            int tmp = check(i); // 크기가 k인 정사각형에서 얻을 수 있는 최대 이익
            ans = Math.max(ans, tmp);
        }
        
        bw.append(ans + "\n");
        bw.flush();
        
        bw.close();
        br.close();
    }

    private static int check(int k) {
        int max = Integer.MIN_VALUE;
        
        // 시작점 (i, j) 기준으로 k×k가 과수원 범위를 벗어나지 않도록 탐색
        for (int i = 1; i <= n - k + 1; i++) {
            for (int j = 1; j <= n - k + 1; j++) {
                // 2차원 누적합을 이용해 k×k 구간의 합 계산
                int tmp = prefix[i+k-1][j+k-1]
                        - prefix[i-1][j+k-1]
                        - prefix[i+k-1][j-1]
                        + prefix[i-1][j-1];

                max = Math.max(max, tmp);
            }
        }
        
        return max;
    }
}