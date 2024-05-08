import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static char[][] map = new char[5][9];
    static boolean[] checked = new boolean[13];
    static ArrayList<Pos> list = new ArrayList<>();
    static public class Pos {
        int x; int y;
        public Pos(int x, int y) {
            this.x = x; this.y = y;
        }
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 9; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'x') { // 채워야할 곳 리스트에 담아놓기
                    list.add(new Pos(i, j));
                } else if (map[i][j] != '.') { // 채워져있는 곳 방문체크
                    checked[map[i][j]-65] = true;
                }
            }
        }

        backtracking(0);
    }

    private static void backtracking(int depth) {
        if (depth == list.size()) { // 매직스타를 다 채웠다면
            if (check()) { // 모든 합이 26이 되는지 체크
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 9; j++) {
                        sb.append(map[i][j]);
                    }
                    sb.append("\n");
                }
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < 12; i++) { // 1~12 숫자 탐색
            if (!checked[i]) { // 쓰이지 않은 알파벳이라면
                Pos now = list.get(depth);
                char alpha = (char)(65+i);
                map[now.x][now.y] = alpha;
                checked[i] = true;
                backtracking(depth+1);
                checked[i] = false;
                map[now.x][now.y] = 'x';
            }
        }
    }

    private static boolean check() {
        int sum1 = (map[0][4]-'A'+1)+(map[1][3]-'A'+1)+(map[2][2]-'A'+1)+(map[3][1]-'A'+1);
        int sum2 = (map[0][4]-'A'+1)+(map[1][5]-'A'+1)+(map[2][6]-'A'+1)+(map[3][7]-'A'+1);
        int sum3 = (map[1][1]-'A'+1)+(map[1][3]-'A'+1)+(map[1][5]-'A'+1)+(map[1][7]-'A'+1);
        int sum4 = (map[3][1]-'A'+1)+(map[3][3]-'A'+1)+(map[3][5]-'A'+1)+(map[3][7]-'A'+1);
        int sum5 = (map[4][4]-'A'+1)+(map[3][3]-'A'+1)+(map[2][2]-'A'+1)+(map[1][1]-'A'+1);
        int sum6 = (map[4][4]-'A'+1)+(map[3][5]-'A'+1)+(map[2][6]-'A'+1)+(map[1][7]-'A'+1);

        if (sum1==26 && sum2==26 && sum3==26 && sum4==26 && sum5==26 && sum6==26) {
            return true;
        }

        return false;
    }
}