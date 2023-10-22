import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[] checked; // 뽑은 치킨집 체크
    static int min = Integer.MAX_VALUE;
    static ArrayList<pos> chickenList = new ArrayList<>(); // 치킨집 위치 저장
    static ArrayList<pos> houseList = new ArrayList<>(); // 집 위치 저장
    static class pos {
        int x; int y;
        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        // 0 빈칸, 1 집, 2 치킨집
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) houseList.add(new pos(i, j));
                else if (map[i][j] == 2) chickenList.add(new pos(i, j));
            }
        }

        checked = new boolean[chickenList.size()];
        dfs(0, 0);
        bw.append(min + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == m) {
            int total = 0;
            for (int i = 0; i < houseList.size(); i++) {
                int sum = Integer.MAX_VALUE;
                for (int j = 0; j < chickenList.size(); j++) {
                    if (checked[j]) {
                        int dist = Math.abs(houseList.get(i).x - chickenList.get(j).x)
                                + Math.abs(houseList.get(i).y - chickenList.get(j).y);
                        sum = Math.min(sum, dist);
                    }
                }
                total += sum;
            }
            min = Math.min(total, min);
            return;
        }

        for (int i  = start; i < chickenList.size(); i++) {
            if (!checked[i]) {
                checked[i] = true;
                dfs(i+1, depth+1);
                checked[i] = false;
            }
        }
    }
}