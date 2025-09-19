import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map, rotation;
    static int[] arr;
    static boolean[] checked;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); // 회전 연산의 개수

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 회전 칸 범위 : (r-s, c-s), (r+s, c+s)
        rotation = new int[k][3];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            rotation[i][0] = Integer.parseInt(st.nextToken()); // r
            rotation[i][1] = Integer.parseInt(st.nextToken()); // c
            rotation[i][2] = Integer.parseInt(st.nextToken()); // s
        }

        arr = new int[k];
        checked = new boolean[k];
        permutation(0, k); // 회전 순서 뽑기 - 순열

        // print
        System.out.println(min);

        br.close();
    }

    private static void permutation(int idx, int k) {
        if (idx == k) {
            int[][] copy = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copy[i][j] = map[i][j];
                }
            }
            findMin(copy);
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!checked[i]) {
                checked[i] = true;
                arr[idx] = i;
                permutation(idx+1, k);
                checked[i] = false;
            }
        }
    }

    private static void findMin(int[][] copy) {
        for (int i = 0; i < arr.length; i++) {
            int lx = rotation[arr[i]][0] - rotation[arr[i]][2] - 1;
            int ly = rotation[arr[i]][1] - rotation[arr[i]][2] - 1;
            int rx = rotation[arr[i]][0] + rotation[arr[i]][2] - 1;
            int ry = rotation[arr[i]][1] + rotation[arr[i]][2] - 1;

            rotate(lx, ly, rx, ry, copy); // 회전
        }
        
        rowcal(copy); // 행에 대한 최솟값 찾기
    }

    private static void rowcal(int[][] copy) {
        for(int i = 0; i < copy.length; i++) {
            int sum = 0;
            for(int j = 0; j < copy[i].length; j++) {
                sum += copy[i][j];
            }
            min = Math.min(min, sum);
        }
    }

    private static void rotate(int lx, int ly, int rx, int ry, int[][] copy) {
        if (lx == rx && ly == ry) {
            return;
        }

        int[] tmp = new int[3];
        tmp[0] = copy[lx][ry];
        tmp[1] = copy[rx][ry];
        tmp[2] = copy[rx][ly];

        // 오른쪽으로 회전
        for(int i = ry; i > ly; i--) {
            copy[lx][i] = copy[lx][i-1];
        }
        // 아래로 회전
        for(int i = rx; i > lx; i--) {
            if(i == lx + 1) copy[i][ry] = tmp[0];
            else copy[i][ry] = copy[i-1][ry];
        }
        // 왼쪽으로 회전
        for(int i = ly; i < ry; i++) {
            if(i == ry - 1) copy[rx][i] = tmp[1];
            else copy[rx][i] = copy[rx][i+1];
        }
        // 위로 회전
        for(int i = lx; i < rx; i++) {
            if(i == rx - 1) copy[i][ly] = tmp[2];
            else copy[i][ly] = copy[i+1][ly];
        }

        rotate(lx+1, ly+1, rx-1, ry-1, copy);
    }
}