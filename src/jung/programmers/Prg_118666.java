package src.jung.programmers;

import java.util.HashMap;

/**
 * 문제 이름(난이도) : 성격 유형 검사하기(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/118666
 * */
public class Prg_118666 {
    public static void main(String[] args) {
        String[] survey = {"TR", "RT", "TR"};
        int[] choices = {7, 1, 3};
        System.out.println(solution(survey, choices));
    }

    // switch case 문, index 위치 활용 !!!
    public static String solution(String[] survey, int[] choices) {
        String answer = "";
        char[] type = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'}; // 성격 유형
        // Hashmap 사용 시 Integer value 값을 증위 연산자 등을 이용하여 증가시킬 수 없음
        // 따라서 score 배열을 정의하여 사용
        int[] score = new int[type.length]; // 각 유형의 점수를 담을 배열
        HashMap<Character, Integer> map = new HashMap<>(); // 성격 유형, 인덱스 번호
        for (int i = 0; i < type.length; i++) {
            map.put(type[i], i);
        }
        for (int i = 0; i < survey.length; i++) {
            char[] arr = survey[i].toCharArray();
            switch (choices[i]) {
                case 1 : score[map.get(arr[0])] += 3; break;
                case 2 : score[map.get(arr[0])] += 2; break;
                case 3 : score[map.get(arr[0])] += 1; break;
                case 4 : break;
                case 5 : score[map.get(arr[1])] += 1; break;
                case 6 : score[map.get(arr[1])] += 2; break;
                case 7 : score[map.get(arr[1])] += 3; break;
                default : break;
            }
        }
        for (int i = 0; i < type.length; i+=2) { // 2개씩 비교
            if (score[i] > score[i+1]) {
                answer += type[i];
            } else if (score[i] == score[i+1]) { // 동일한 점수라면
                if (type[i] < type[i+1]) { // 사전순일 경우 작은 값이 먼저
                    answer += type[i];
                } else {
                    answer += type[i+1];
                }
            } else {
                answer += type[i+1];
            }
        }
        return answer;
    }
}
