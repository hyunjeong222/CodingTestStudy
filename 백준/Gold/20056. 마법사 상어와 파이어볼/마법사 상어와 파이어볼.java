import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m, k;
    static int r, c, w, s, d; // 파이어볼 위치, 질량, 속력, 방향
    static ArrayList<Pos>[][] map; // 파이어볼 이동 정보
    static ArrayList<Pos> fireBall; // 파이어볼 정보
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static public class Pos {
        // 파이어볼 위치, 질량, 속력, 방향
        int r; int c; int w; int s; int d;
        public Pos(int r, int c, int w, int s, int d) {
            this.r = r; this.c = c; this.w = w; this.s = s; this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 파이어볼 정보 개수
        k = Integer.parseInt(st.nextToken()); // k번 명령
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        fireBall = new ArrayList<>();
        while (m --> 0) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            w = Integer.parseInt(st.nextToken()); // 질량
            s = Integer.parseInt(st.nextToken()); // 속력
            d = Integer.parseInt(st.nextToken()); // 방향
            fireBall.add(new Pos(r, c, w, s, d));
        }
        while (k --> 0) { // k번 명령만큼
            move(); // 이동
            splitting(); // 분열
        }
        int ans = 0;
        for (Pos now : fireBall) {
            ans += now.w;
        }
        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void splitting() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].size() < 2) {
                    map[i][j].clear();
                    continue;
                }
                int nw = 0; // 다음 질량
                int ns = 0; // 다음 속력
                int odd = 0; // 홀수
                int even = 0; // 짝수
                int len = map[i][j].size();
                for (Pos now : map[i][j]) {
                    nw += now.w;
                    ns += now.s;
                    if (now.d % 2 == 1) odd++;
                    else even++;
                    fireBall.remove(now);
                }
                map[i][j].clear(); // 분열 시키고 현재 위치에서 제거
                nw /= 5;
                if (nw == 0) continue; // 분열 실패
                ns /= len;
                if (even == len || odd == len) {
                    for (int d = 0; d < 8; d+=2) {
                        fireBall.add(new Pos(i, j, nw, ns, d));
                    }
                } else {
                    for (int d = 1; d < 8; d+=2) {
                        fireBall.add(new Pos(i, j, nw, ns, d));
                    }
                }
            }
        }
    }

    private static void move() {
        for (Pos now : fireBall) {
            now.r = (n + now.r + dx[now.d] * (now.s % n)) % n;
            now.c = (n + now.c + dy[now.d] * (now.s % n)) % n;
            map[now.r][now.c].add(now);
        }
    }
}