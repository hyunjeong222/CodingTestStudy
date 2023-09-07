package src.jung.programmers;

/**
 * 문제 이름(난이도) : 카드 뭉치(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/159994
 * */
public class Prg_159994 {
    public static void main(String[] args) {
        String[] cards1 = {"i", "water", "drink"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};
        System.out.println(solution(cards1, cards2, goal));
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        // 각 배열의 인덱스
        int card1Index = 0;
        int card2Index = 0;
        for (int i = 0; i < goal.length; i++) {
            // 배열의 크기를 벗어나지 않으면서 goal 문자와 같다면
            if (card1Index < cards1.length && goal[i].equals(cards1[card1Index])) {
                card1Index++;
                continue; // 다음탐색
            }
            if (card2Index < cards2.length && goal[i].equals(cards2[card2Index])) {
                card2Index++;
                continue; // 다음탐색
            }
            // goal 문자와 다르다면
            answer = "No";
            break; // 탐색 종료
        }
        return answer;
    }
}
