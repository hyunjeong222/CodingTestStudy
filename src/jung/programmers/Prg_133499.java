package src.jung.programmers;

/**
 * 문제 이름(난이도) : 옹알이 (2)(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/133499
 */
public class Prg_133499 {
    public static void main(String[] args) {
        String[] babbling = {"aya", "yee", "u", "maa"};
        System.out.println(solution(babbling));
    }

    public static int solution(String[] babbling) {
        // "aya", "ye", "woo", "ma" 4가지 발음만 가능
        int answer = 0;
        for (int i = 0; i < babbling.length; i++) {
            // 연속된 발음 불가
            if (babbling[i].contains("ayaaya") || babbling[i].contains("yeye")
                    || babbling[i].contains("woowoo") || babbling[i].contains("mama")) {
                continue;
            }
            // ""으로 replace 할 경우 앞뒤 단어가 합쳐져 발음할 수 있는 단어가 만들어질 수 있음
            babbling[i] = babbling[i].replace("aya", " ");
            babbling[i] = babbling[i].replace("ye", " ");
            babbling[i] = babbling[i].replace("woo", " ");
            babbling[i] = babbling[i].replace("ma", " ");
            // 발음할 수 있는 단어라면 " "을 ""으로 치환
            babbling[i] = babbling[i].replace(" ", "");
            if (babbling[i].length() == 0) answer++;
        }
        return answer;
    }
}
