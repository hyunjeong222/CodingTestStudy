package src.jung.week10;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 어드벤처 게임(GOL)
 * 시간 : 340ms
 * 메모리 : 37080KB
 * 링크 : https://www.acmicpc.net/problem/2310
 */
public class Boj_2301 {
    static int n; // 미로의 방 수
    static char[] roomType; // 각 방의 정보, E 빈방, L 레프리콘, T 트롤
    static int[] getMoney; // 입력으로 들어온 소지금
    static ArrayList<Integer>[] nextRoom; // 현재 방에서 갈 수 있는 방을 담을 list
    static boolean[] checked;
    static boolean flag;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            n = Integer.parseInt(br.readLine());

            if (n == 0) break; // 미로의 방 수가 0개면 종료

            // 크기 지정
            roomType = new char[n+1];
            getMoney = new int[n+1];
            nextRoom = new ArrayList[n+1];
            checked = new boolean[n+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                roomType[i] = st.nextToken().charAt(0);
                getMoney[i] = Integer.parseInt(st.nextToken());
                nextRoom[i] = new ArrayList<>();

                while (true) {
                    int roomNum = Integer.parseInt(st.nextToken()); // 각 문의 번호
                    if (roomNum == 0) break; // 각 줄에 0이 들어오면 종료
                    nextRoom[i].add(roomNum);
                }
            }

            flag = false; // 도착 여부
            dfs(1, 0); // 시작 방 번호, 소지금
            if (!flag) bw.append("No" + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int room, int money) throws IOException {
        if (flag) return;

        if (room == n) { // n번방 도착했다면
            flag = true;
            bw.append("Yes" + "\n");
            return;
        }

        for (int next : nextRoom[room]) {
            // 방문했던 방이거나, 트롤 방인데 소지금 부족하면 못감
            if (checked[next] || (roomType[next] == 'T' && getMoney[next] > money)) continue;

            if (roomType[next] == 'L') { // 레프리콘 방
                if (getMoney[next] > money) money = getMoney[next]; // 큰 값으로 소지금 변경
            } else if (roomType[next] == 'T') { // 트롤 방
                if (getMoney[next] <= money) money -= getMoney[next]; // 통행료 지불
            }
            checked[next] = true;
            dfs(next, money);
            checked[next] = false;
        }
        return;
    }
}
