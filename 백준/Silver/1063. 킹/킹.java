import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int kingX, kingY;
    static int stoneX, stoneY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String king = st.nextToken(); // 처음 킹 위치
        kingX = king.charAt(1)-'0';
        kingY = king.charAt(0)-'A'+1;
        String stone = st.nextToken(); // 처음 돌 위치
        stoneX = stone.charAt(1)-'0';
        stoneY = stone.charAt(0)-'A'+1;
        int n = Integer.parseInt(st.nextToken()); // 움직이는 횟수
        int nx, ny;
        while (n --> 0) {
            String d = br.readLine();

            if (d.equals("R")) { // 오른쪽
                nx = 0; ny = 1;
            } else if (d.equals("L")) { // 왼쪽
                nx = 0; ny = -1;
            } else if (d.equals("B")) { // 아래
                nx = -1; ny = 0;
            } else if (d.equals("T")) { // 위
                nx = 1; ny = 0;
            } else if (d.equals("RT")) { // 오른쪽 위
                nx = 1; ny = 1;
            } else if (d.equals("LT")) { // 왼쪽 위
                nx = 1; ny = -1;
            } else if (d.equals("RB")) { // 오른쪽 아래
                nx = -1; ny = 1;
            } else { // LB, 왼쪽 아래
                nx = -1; ny = -1;
            }
            move(nx, ny);
        }
        char ky = (char)('A'+kingY-1);
        char sy = (char)('A'+stoneY-1);
        System.out.println(ky+""+kingX+"\n"+sy+""+stoneX);
    }

    private static void move(int x, int y) {
        int nx = kingX + x;
        int ny = kingY + y;

        if (check(nx, ny)) {
            if (nx == stoneX && ny == stoneY) {
                if (check(stoneX+x, stoneY+y)) {
                    stoneX += x; stoneY += y;
                    kingX = nx; kingY = ny;
                }
            } else {
                kingX = nx; kingY = ny;
            }
        }
    }

    private static boolean check(int nx, int ny) {
        return nx > 0 && nx < 9 && ny > 0 && ny < 9;
    }
}