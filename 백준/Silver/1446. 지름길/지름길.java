import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n, d;
    static ArrayList<ArrayList<pos>> list;
    static int[] dist;
    static public class pos {
        int start; int dist;

        public pos(int start, int dist) {
            this.start = start;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 지름길의 개수
        d = Integer.parseInt(st.nextToken()); // 고속도로의 길이

        list = new ArrayList<>();
        for (int i = 0; i < 10001; i++) {
            list.add(new ArrayList<>());
        }

        dist = new int[d+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); // 시작 위치
            int end = Integer.parseInt(st.nextToken()); // 도착 위치
            int dist = Integer.parseInt(st.nextToken()); // 지름길의 거리

            if (end > d) continue;
            
            if (end - start > dist) {
                list.get(end).add(new pos(start, dist));
            }
        }

        dist[0] = 0;
        for (int i = 1; i <= d; i++) {
            if (list.get(i).size() > 0) {
                for (pos next : list.get(i)) {
                    if (dist[next.start] + next.dist > dist[i]) continue;
                    dist[i] = Math.min(dist[i-1]+1, dist[next.start] + next.dist);
                }
                continue;
            }
            dist[i] = dist[i-1]+1;
        }

        System.out.println(dist[d]);
    }
}