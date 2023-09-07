package src.jung.programmers;

/**
 * 문제 이름(난이도) : 문자열 나누기(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/140108
 */
public class Prg_140108 {
    public static void main(String[] args) {
        String s1 = "banana";
        System.out.println(solution(s1)); // 3
        String s2 = "abracadabra";
        System.out.println(solution(s2)); // 6
        String s3 = "aaabbaccccabba";
        System.out.println(solution(s3)); // 3
    }

    public static int solution(String s) {
        int answer = 0; // 분해 횟수
        char x = s.charAt(0); // 문자열의 첫 글자
        int x_count = 0; // 첫 글자와 같은 글자가 나온 횟수
        int y_count = 0; // 첫 글자와 다른 글자들이 나온 횟수
        for (int i = 0; i < s.length(); i++) {
            if (x_count == y_count) { // 문자열 분해
                answer++;
                x = s.charAt(i); // 분해 후 첫 글자 변경
            }
            if (x == s.charAt(i)) x_count++;
            else y_count++;
        }
        return answer;
    }
}
