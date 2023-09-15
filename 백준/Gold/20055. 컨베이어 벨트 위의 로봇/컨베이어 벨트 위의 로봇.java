import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] belt;
    static boolean[] robot;
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 길이가 n인 컨베이너 벨트, 벨트는 2n
        k = Integer.parseInt(st.nextToken()); // 내구도가 0개 이상이면 종료

        st = new StringTokenizer(br.readLine());
        belt = new int[n*2];
        for (int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken()); // 내구도 담기
        }

        // 로봇 올리는 위치 0, 내리는 위치 n
        // 로봇이 올라가져 있으면 true, 그렇지 않다면 false
        robot = new boolean[n];

        int stage = 0; // 단계 : 1에서 4번 과정을 하나로 묶은 것
        while (isEnd()) { // 내구도가 0인 칸의 개수가 k개 이상인지 확인, 4단계
            // 1단계
            beltMove();
            // 2, 3단계
            robotMove();
            stage++;
        }
        bw.append(stage + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void beltMove() {
        // 벨트 이동
        int tmp = belt[belt.length-1];
        for (int i = belt.length-1; i > 0; i--) {
            belt[i] = belt[i-1];
        }
        belt[0] = tmp;
        // 로봇 이동
        for (int i = robot.length-1; i > 0; i--) {
            robot[i] = robot[i-1];
        }
        robot[0] = false; // 이동 시 로봇은 n에서 내림
    }

    private static void robotMove() {
        // 로봇 이동
        robot[n-1] = false; // 로봇 내리기
        for (int i = n-1; i > 0; i--) {
            if (robot[i-1] && !robot[i] && belt[i] >= 1) { // 다음 칸에 로봇을 올릴 수 있다면
                robot[i] = true;
                robot[i-1] = false;
                belt[i]--;
            }
        }
        // 올라가는 위치에 로봇 없고, 내구도 1이상 로봇 올리기
        if (!robot[0] && belt[0] >= 1) {
            robot[0] = true;
            belt[0]--; // 내구도 감소
        }
    }

    private static boolean isEnd() {
        int durability = 0;
        for (int i = 0; i < belt.length; i++) {
            if (belt[i] == 0) durability++;
            if (durability >= k) return false;
        }
        return true;
    }
}