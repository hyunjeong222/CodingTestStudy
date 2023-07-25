package ka.array;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 문제 이름(난이도) : 삼각 달팽이(LV2)
 * 시간 : 1.03ms
 * 메모리 : 83.4MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/68645
 */
public class PRG_68645 {
    // 지도
    int[][] map;

    // 방향 조절 : 아래 -> 오른쪽 -> 좌측 상단 대각선 순서
    int[] dy = {1, 0, -1};
    int[] dx = {0, 1, -1};

    // 이동할 방향
    int dir = 0;

    // 최대 숫자, 지도 크기, 시작 숫자
    int maxNum, size, num = 1;

    /*public int[] solution(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
        }

        int maxNum = n * (n + 1) / 2; // 전체 숫자 개수

        int[] dx = {1, 0, -1}; // 오른쪽 아래, 왼쪽, 위
        int[] dy = {0, 1, -1};
        int dir = 0; // 방향 (오른쪽 아래 방향으로 시작)

        int x = 0, y = 0;
        int num = 1; // 채워나갈 숫자

        while (num <= maxNum) {
            triangle[x][y] = num;
            num++;

            // 다음 위치로 이동
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            // 다음 위치가 범위를 벗어나거나 이미 숫자가 채워져있는 경우 방향 전환
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY > nextX || triangle[nextX][nextY] != 0) {
                dir = (dir + 1) % 3;
                x += dx[dir];
                y += dy[dir];
            } else {
                x = nextX;
                y = nextY;
            }
        }

        // 결과 배열 생성
        int[] answer = new int[maxNum];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }*/

    public int[] solution(int n) {
        // 비정방형 배열 초기화 과정
        map = new int[n][];
        for (int i = 0; i < n; i++) {
            map[i] = new int[i + 1];
        }

        // 지도 사이즈
        size = n;

        // 배열에 들어 갈 수 있는 최대값 지정
        maxNum = n * (n + 1) / 2; // long 형으로 계산

        // BFS를 통해 값 지정
        dfs(0, 0);

        // 결과 배열 생성
        int[] answer = new int[(int) maxNum]; // int로 형변환
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = map[i][j];
            }
        }

        return answer;
    }

    public void dfs(int y, int x) {
        // num이 최대 숫자를 넘어가면 재귀 종료
        if (num > maxNum) {
            return;
        }

        // 지도의 (x, y) 좌표에 num 추가 후 값 증가
        map[y][x] = num++;
//        Arrays.stream(map)
//                .map(Arrays::toString)
//                .forEach(System.out::println);
//        System.out.println("----------------");

        // 다음 좌표 지정
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        // 다음 좌표가 유효하지 않을 경우 방향 조정
        if (ny < 0 || ny >= size || nx > ny || map[ny][nx] != 0) {
            // dir이 계속 증가할 경우 dx, dy 함수의 인덱스에서 벗어남
            // 때문에 dx, dy 배열의 크기만큼 나눈 값의 나머지를 통해 0, 1, 2 반복하도록 지정
            dir = (dir + 1) % 3;
            ny = y + dy[dir];
            nx = x + dx[dir];
        }
        // dfs 재귀
        dfs(ny, nx);
    }

    @Test
    @DisplayName("n이 4일 경우")
    void solutionTest1() {
        int n = 4;
        int[] answer = solution(n);
        System.out.println(Arrays.toString(answer));
        assertTrue(Arrays.equals(answer, new int[]{1, 2, 9, 3, 10, 8, 4, 5, 6, 7}));
    }

    @Test
    @DisplayName("n이 5일 경우")
    void solutionTest2() {
        int n = 5;
        int[] answer = solution(n);
        System.out.println(Arrays.toString(answer));
        assertTrue(Arrays.equals(answer, new int[]{1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9}));
    }

    @Test
    @DisplayName("n이 6일 경우")
    void solutionTest3() {
        int n = 6;
        int[] answer = solution(n);
        System.out.println(Arrays.toString(answer));
        assertTrue(Arrays.equals(answer, new int[]{1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11}));
    }

    @Test
    @DisplayName("n이 100일 경우")
    void solutionTest4() {
        int n = 1000;
        int[] answer = solution(n);
        System.out.println(Arrays.toString(answer));
        assertTrue(Arrays.equals(answer, new int[]{1}));
    }
}

/*class Solution {
    public int[] solution(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
        }

        int maxNum = n * (n + 1) / 2; // 전체 숫자 개수

        int[] dx = {1, 0, -1}; // 오른쪽 아래, 왼쪽, 위
        int[] dy = {0, 1, -1};
        int dir = 0; // 방향 (오른쪽 아래 방향으로 시작)

        int x = 0, y = 0;
        int num = 1; // 채워나갈 숫자

        while (num <= maxNum) {
            triangle[x][y] = num;
            num++;

            // 다음 위치로 이동
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            // 다음 위치가 범위를 벗어나거나 이미 숫자가 채워져있는 경우 방향 전환
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY > nextX || triangle[nextX][nextY] != 0) {
                dir = (dir + 1) % 3;
                x += dx[dir];
                y += dy[dir];
            } else {
                x = nextX;
                y = nextY;
            }
        }

        // 결과 배열 생성
        int[] answer = new int[maxNum];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = triangle[i][j];
            }
        }

        return answer;
    }
}*/
