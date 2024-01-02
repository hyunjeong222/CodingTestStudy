import java.io.*;
import java.util.Arrays;

public class Main {
    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();

            if (str.equals("end")) break;

            map = new char[3][3];
            int xCnt = 0;
            int oCnt = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map[i][j] = str.charAt(3*i+j);
                    if (map[i][j] == 'X') xCnt++;
                    else if (map[i][j] == 'O') oCnt++;
                }
            }

            if (xCnt+oCnt == 9 && xCnt-oCnt == 1) {
                if (check('X') && check('O')) sb.append("invalid").append("\n");
                else if (check('O')) sb.append("invalid").append("\n");
                else sb.append("valid").append("\n");
            } else {
                if (check('X') && check('O')) sb.append("invalid").append("\n");
                else if (check('X') && xCnt-oCnt == 1) sb.append("valid").append("\n");
                else if (check('O') && xCnt == oCnt) sb.append("valid").append("\n");
                else sb.append("invalid").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean check(char c) {
        for (int i = 0; i < 3; i++) {
            int cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == c) cnt++;
            }
            if (cnt == 3) return true;
        }
        
        for (int j = 0; j < 3; j++) {
            int cnt = 0;
            for (int i = 0; i < 3; i++) {
                if (map[i][j] == c) cnt++;
            }
            if (cnt == 3) return true;
        }
        
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (map[i][i] == c) cnt++;
            if (cnt == 3) return true;
        }
        cnt = 0;
        for (int i = 0; i < 3; i++) {
            if (map[2-i][i] == c) cnt++;
            if (cnt == 3) return true;
        }

        return false;
    }
}