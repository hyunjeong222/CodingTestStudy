package ka.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 문제 이름(난이도) : 정수 삼각형(LV3)
 * 시간 : 7.79 ms
 * 메모리 : 62.8 MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43105
 */
public class PRG_43105 {

    int[] dx = {0, 1};
    int[] dy = {1, 1};

    public void showMatrix(int[][] dp) {
        Arrays.stream(dp)
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println("----------------");
    }

    public int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < triangle.length; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];

            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]) + triangle[i][j];
            }
        }

        for (int i = 0; i < triangle.length; i++) {
            answer = Math.max(answer, dp[triangle.length - 1][i]);
        }

        return answer;
    }

    @Test
    void solutionTest() {
        int[][] triangle = {{7},
                            {3, 8},
                            {8, 1, 0},
                            {2, 7, 4, 4},
                            {4, 5, 2, 6, 5}};
        int result = solution(triangle);
        System.out.println(result);
        assertTrue(result == 30);
    }
}
