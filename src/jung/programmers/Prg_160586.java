package src.jung.programmers;

import java.util.Arrays;

/**
 * 문제 이름(난이도) : 대충 만든 자판(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/160586
 */
public class Prg_160586 {
    public static void main(String[] args) {
        String[] keymap = {"ABACD", "BCEFD"};
        String[] targets = {"ABCD","AABB"};
        System.out.println(Arrays.toString(solution(keymap, targets)));
    }

    public static int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int sum = 0; // 문자열을 만드는데 자판 누른 횟수
            for (int j = 0; j < targets[i].length(); j++) {
                int count = keyPress(keymap, targets[i].charAt(j));
                if (count == -1) { // 문자열을 만들 수 없다면
                    sum = -1;
                    break; // 다음 탐색
                }
                sum += count;
            }
            answer[i] = sum;
        }
        return answer;
    }
    // 문자열의 각 문자를 만들기 위해 누른 횟수를 구할 메서드
    public static int keyPress(String[] keymap, char c) {
        int min = -1; // 자판 누른 최소 횟수, -1은 한 번도 안 눌렀을 경우
        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) { // 자판의 각 문자
                if (c == keymap[i].charAt(j)) { // 자판의 문자와 만들 문자열의 문자와 같다면
                    if (min == -1) { // 아무것도 누르지 않았을 경우
                        min = j+1;
                    } else if (min > j) { // 최솟값 변경
                        min = j+1;
                    }
                }
            }
        }
        return min;
    }
}
