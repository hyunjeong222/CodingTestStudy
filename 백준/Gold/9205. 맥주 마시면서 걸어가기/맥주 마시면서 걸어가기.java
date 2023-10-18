import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int t, n;
    static public class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x; this. y = y;
        }
    }
    static boolean[] checked;
    static ArrayList<pos> list; // 집, 편의점, 페스티벌 위치 저장
    static ArrayList<ArrayList<Integer>> graph;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            n = Integer.parseInt(br.readLine()); // 맥주를 파는 편의점의 개수

            checked = new boolean[n+2];
            list = new ArrayList<>();

            for (int i = 0; i < n+2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                list.add(new pos(x, y));
            }

            graph = new ArrayList<>();
            for (int i = 0; i < n+2; i++) {
                graph.add(new ArrayList<>());
            }

            // 맨해튼 거리 1000m 이하를 만족시키는 두 정점을 찾아 양방향 연결
            for (int i = 0; i < n+2; i++) {
                for (int j = 0; j < n+2; j++) {
                    if (Manhattan(list.get(i), list.get(j)) <= 1000) {
                        graph.get(i).add(j);
                        graph.get(j).add(i);
                    }
                }
            }
            flag = bfs();
            if (flag) bw.append("happy" + "\n");
            else bw.append("sad" + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean bfs() {
        Queue<Integer> que = new LinkedList<>();
        que.offer(0);
        checked[0] = true;

        while (!que.isEmpty()) {
            int now = que.poll();

            if (now == n+1) return true;

            for (int next : graph.get(now)) {
                if (!checked[next]) {
                    checked[next] = true;
                    que.offer(next);
                }
            }
        }
        return false;
    }

    private static int Manhattan(pos p1, pos p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}