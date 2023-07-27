package ka.bfs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PRG_49994 {
    int[][] map;
    boolean[][] visited;
    int foot = 1;
    int answer = 0;
    int x, y, nx, ny;

    public int solution(String dirs) {
        map = new int[11][11];
        visited = new boolean[11][11];
        x = y = 5;
        visited[y][x] = true;
        map[y][x] = foot;

        String[] control = dirs.split("");
        for (String s : control) {

            // nx, ny 좌표 구함
            selectNextPos(s);

            if (nx >= 0 && nx < map.length && ny >= 0 && ny < map.length) {
                map[ny][nx] = foot++;
                if (!visited[ny][nx]) {
                    visited[ny][nx] = true;
                    answer++;
                }
                showMatrix();
                x = nx;
                y = ny;
            }
        }
        showMatrix();

        return answer;
    }

    public void selectNextPos(String s) {
        if (s.equals("U")) {
            nx = x;
            ny = y - 1;
        } else if (s.equals("D")) {
            nx = x;
            ny = y + 1;
        } else if (s.equals("R")) {
            nx = x + 1;
            ny = y;
        } else {
            nx = x - 1;
            ny = y;
        }
    }

    public void showMatrix() {
        Arrays.stream(map)
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    @Test
    void solutionTest1() {
        String dirs = "ULURRDLLU";
        int result = solution(dirs);
        System.out.println(result);
        assertTrue(result == 7);
    }

    @Test
    void solutionTest2() {
        String dirs = "LULLLLLLU";
        int result = solution(dirs);
        System.out.println(result);
        assertTrue(result == 7);
    }
}
