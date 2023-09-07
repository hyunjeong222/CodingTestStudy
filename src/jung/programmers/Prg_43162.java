package src.jung.programmers;

import java.util.Arrays;

/**
 * 문제 이름(난이도) : 네트워크(LV3)
 * 시간 : 0.29ms
 * 메모리 : 81.4MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43162
 */
public class Prg_43162 {
    public static void main(String[] args) {
        int n = 3;
        // int[][] computers = {{1,1,0},{1,1,0},{0,0,1}};
        int[][] computers = {{1,1,0},{1,1,1},{0,0,1}};
        System.out.println(solution(n,computers));
    }

    static int[] parents;
    static int n;

    public static int solution(int n, int[][] computers) {
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        // System.out.println(Arrays.toString(parents)); [0, 1, 2, 3]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        // System.out.println(Arrays.toString(parents)); [0, 0, 0, 3]
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) answer++;
        }
        return answer;
    }

    private static void union(int i, int j) {
        i = find(i);
        j = find(j);

        if (i > j) parents[i] = j;
        else parents[j] = i;
    }

    private static int find(int i) {
        if (parents[i] == i) return i;
        return parents[i] = find(parents[i]);
    }
}
