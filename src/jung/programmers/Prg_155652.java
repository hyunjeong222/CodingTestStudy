package src.jung.programmers;

/**
 * 문제 이름(난이도) : 둘만의 암호(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/155652
 * */
public class Prg_155652 {
    public static void main(String[] args) {
        String s = "aukks";
        String skip = "wbqd";
        int index = 5;
        System.out.println(solution(s, skip, index));
    }

    public static String solution(String s, String skip, int index) {
        String answer = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int count = 0;
            while (count < index) { // 인덱스 만큼 알파벳 이동
                c++;
                if (c > 'z') { // 알파벳이 z 넘어가면
                    c -= 26; // a로 돌아감
                }
                if (skip.contains(c+"")) {
                    continue; // 만나면 해당 반복 탈출 후 다음 반복 실행
                    // break; : 만나는 즉시 반복문 전체 탈출
                } else {
                    count++;
                }
            }
            answer += c;
        }
        return answer;
    }
}
