import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 물 웅덩이 개수
        int l = Integer.parseInt(st.nextToken()); // 널빤지의 길이

        int[][] arr = new int[n][2]; // 물 웅덩이 시작 위치와 끝 위치
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 시작점을 기준으로 오름차순
        // 시작점이 같으면 끝점으로 오름차순
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        int ans = 0; // 널빤지의 최소개수
        int range = 0; // 널빤지를 덮을 수 있는 널빤지의 범위
        for (int[] nn : arr) {
            if (nn[0] > range) range = nn[0];

            if (nn[1] > range) {
                while (nn[1] > range) {
                    range += l;
                    ans++;
                }
            }
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}