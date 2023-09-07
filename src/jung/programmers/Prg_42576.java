package src.jung.programmers;

import java.util.HashMap;

/**
 * 문제 이름(난이도) : 완주하지 못한 선수(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
public class Prg_42576 {
    public static void main(String[] args) {
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        System.out.println(solution(participant, completion));
    }

    public static String solution(String[] participant, String[] completion) {
        String answer = ""; // 완주하지 못한 선수
        HashMap<String, Integer> marathon = new HashMap<>();
        for (String runner : participant) { // 마라톤 선수 목록
            marathon.put(runner, marathon.getOrDefault(runner, 0)+1); // 동명이인이 있다면, value 값 2 이상
            // getOrDefault
            // key 값이 존재한다면, value 값에 +1
            // key 값이 존재하지 않는다면, default 값
        }
        for (String comp_runner : completion) { // 완주하지 못한 선수 목록
            marathon.put(comp_runner, marathon.get(comp_runner)-1); // 완주한 선수라면 value 값 - 1
        }
        for (String key : marathon.keySet()) {
            if (marathon.get(key) > 0) { // 완주하지 못했다면 value 값 1 이상
                answer = key;
            }
        }
        return answer;
    }
}
