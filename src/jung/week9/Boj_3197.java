package src.jung.week9;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 백조의 호수(PLA)
 * 시간 : 208984ms
 * 메모리 : 868KB
 * 링크 : https://www.acmicpc.net/problem/3197
 */
public class Boj_3197 {
    static int r, c;
    static char[][] map; // 호수
    static pos[] swan; // 백조 위치
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] checked;
    static Queue<pos> swanQue = new LinkedList<>(); // 첫번째 백조가 이동할 수 있는 위치
    static Queue<pos> waterQue = new LinkedList<>(); // 물의 위치
    static int ans; // 백조 만날 수 있는 날짜

    static public class pos {
        int x, y;

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()); // 행
        c = Integer.parseInt(st.nextToken()); // 열

        swan = new pos[2]; // 백조 위치
        map = new char[r][c]; // 호수
        int idx = 0;

        // 호수 입력
        // . 물, X 빙판, L 백조
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'L') { // 백조인 경우
                    // 백조 위치 저장
                    swan[idx++] = new pos(i, j); // 백조 위치 저장
                    map[i][j] = '.'; // 물로 변경, 백조는 물 위에 있음 ^^
                }

                // 물인 경우
                if (map[i][j] == '.') {
                    waterQue.offer(new pos(i, j)); // 물 위치 담기
                }
            }
        }

        checked = new boolean[r][c];

        swanQue.offer(new pos(swan[0].x, swan[0].y)); // 첫 번째 백조의 위치 담기, 첫 번째 백조의 위치부터 시작
        checked[swan[0].x][swan[0].y] = true;

        ans = 0; // 백조가 만나는 날짜

        while (true) { // 두 백조가 만날때까지
            if (swanMeet()) break; // 백조가 만났다면 끝내기
            melt(); // 하루가 지나면 얼음 녹음
            ans++; // 만나지 못했으면 날짜 증가
        }

        bw.append(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean swanMeet() {
        // 다음 탐색 위치를 담을 큐
        Queue<pos> que = new LinkedList<>();

        while (!swanQue.isEmpty()) { // 이동할 곳이 없을 때까지
            pos pq = swanQue.poll(); // 현재 백조의 위치

            for (int i = 0; i < 4; i++) {
                int nx = pq.x + dx[i];
                int ny = pq.y + dy[i];

                // 범위 벗어나는지, 다음 갈 위치 방문했는지 체크
                if (nx < 0 || ny < 0 || nx >= r || ny >= c || checked[nx][ny]) continue;

                checked[nx][ny] = true;

                if (nx == swan[1].x && ny == swan[1].y) return true; // 백조 만났으면  true 리턴
                else if (map[nx][ny] == 'X') que.offer(new pos(nx, ny)); // 다음날 얼음이 녹아야 백조가 지나갈 수 있음
                else swanQue.offer(new pos(nx, ny));
            }
        }
        swanQue = que;
        return false;
    }

    private static void melt() {
        // 물에 닿아있는 얼음 녹이기
        // while 사용하면 첫날에 다 녹음 ^^
        int size = waterQue.size();
        for (int i = 0; i < size; i++) {
            pos pq = waterQue.poll();

            for (int j = 0; j < 4; j++) {
                int nx = pq.x + dx[j];
                int ny = pq.y + dy[j];

                if (nx >= 0 && ny >= 0 && nx < r && ny < c && map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    waterQue.offer(new pos(nx, ny));
                }
            }
        }
    }
}