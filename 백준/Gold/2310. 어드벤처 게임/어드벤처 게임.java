import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n; // 방의 수
    static char[] roomType; // 방의 내용물
    static ArrayList<Integer>[] nextRoom; // 현재 방에서 다른 방으로 갈 수 있는 문의 번호들
    static int[] getMoney; // 방으로 갈 때 지불하는 금액
    static boolean[] checked;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            n = Integer.parseInt(br.readLine()); // 미로의 방 수
            if (n == 0) break; // 0입력 시 종료

            roomType = new char[n+1];
            nextRoom = new ArrayList[n+1];
            getMoney = new int[n+1];
            checked = new boolean[n+1];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                roomType[i] = st.nextToken().charAt(0); // E 빈방, L 레프리콘, T 트롤
                getMoney[i] = Integer.parseInt(st.nextToken()); // 빈방이면 0
                nextRoom[i] = new ArrayList<>(); // 다른 방으로 갈 수 있는 문의 번호

                while (true) {
                    int roomNum = Integer.parseInt(st.nextToken());
                    if (roomNum == 0) break; // 0입력 시 종료
                    nextRoom[i].add(roomNum);
                }
            }
            flag = false;
            dfs(1, 0); // 1번방에서 시작, 소지금은 0원
            if (flag) bw.append("Yes" + "\n");
            else bw.append("No" + "\n");
        }
        bw.flush();
        bw.close();
        bw.close();
    }

    private static void dfs(int room, int money) {
        if (flag) return;
        if (room == n) {
            flag = true;
            return;
        }

        for (int next : nextRoom[room]) {
            if (checked[next]) continue;

            if (roomType[next] == 'L') {
                if (getMoney[next] > money) {
                    money = getMoney[next];
                }
            } else if (roomType[next] == 'T') {
                if (getMoney[next] <= money) {
                    money -= getMoney[next];
                } else return;
            }
            checked[next] = true;
            dfs(next, money);
            checked[next] = false;
        }
        return;
    }
}