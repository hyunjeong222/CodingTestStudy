import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static public class Pos {
        int x; int y;
        public Pos (int x, int y) {
            this.x = x; this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 사대의 수
        int n = Integer.parseInt(st.nextToken()); // 동물의 수
        int l = Integer.parseInt(st.nextToken()); // 사정거리

        int[] arr = new int[m]; // 사대의 위치
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);

        Pos[] animals = new Pos[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            animals[i] = new Pos(a, b);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int left = 0; int right = m;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int dist = Math.abs(arr[mid] - animals[i].x) + animals[i].y;

                if (dist <= l) { // 사정거리 안
                    ans++;
                    break;
                }

                if (animals[i].x < arr[mid]) right = mid-1;
                else left = mid+1;
            }
        }

        System.out.println(ans);
    }
}