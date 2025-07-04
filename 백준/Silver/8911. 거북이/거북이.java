import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, 0, -1, 0}; // 상우하좌
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String command;
        while (t --> 0) {
            command = br.readLine();
            // 거북이는 가장 처음에 (0, 0)에 있고, 북쪽을 쳐다보고 있음
            int nowX = 0, nowY = 0; int d = 0;
            int minX = 0, maxX = 0, minY = 0, maxY = 0;

            for (int i = 0; i < command.length(); i++) {
                char c = command.charAt(i);

                if (c == 'F') {
                    nowX += dx[d];
                    nowY += dy[d];
                } else if (c == 'B') {
                    nowX -= dx[d];
                    nowY -= dy[d];
                } else if (c == 'L') {
                    if (d == 0) d = 3;
                    else d--;
                } else { // c == R
                    if (d == 3) d = 0;
                    else d++;
                }

                minX = Math.min(nowX, minX);
                maxX = Math.max(nowX, maxX);
                minY = Math.min(nowY, minY);
                maxY = Math.max(nowY, maxY);
            }

            sb.append((Math.abs(minX)+Math.abs(maxX))*(Math.abs(minY)+Math.abs(maxY))).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }
}