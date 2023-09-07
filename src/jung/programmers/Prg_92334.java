package src.jung.programmers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 문제 이름(난이도) : 신고 결과 받기(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92334
 */
public class Prg_92334 {
    public static void main(String[] args) {
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
        System.out.println(Arrays.toString(solution(id_list, report, k)));
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, HashSet<String>> report_map = new HashMap<>(); // 이용자, 이용자가 신고한 이용자들, 동일한 유저 신고 시 1회로 처리
        HashMap<String, Integer> count_map = new HashMap<>(); // 신고 당한 이용자, 신고 당한 횟수
        // 초깃값 세팅
        for (int i = 0; i < id_list.length; i++) {
            report_map.put(id_list[i], new HashSet<String>());
            count_map.put(id_list[i], 0);
        }
        for (String s : report) {
            String[] arr = s.split(" ");
            String user_id = arr[0];
            String target_id = arr[1];

            if (report_map.get(user_id).add(target_id)) {
                count_map.put(target_id, count_map.getOrDefault(target_id, 0)+1);
            }
        }
        for (String s : report) {
            String[] arr = s.split(" ");
            String user_id = arr[0];
            String target_id = arr[1];

            if (count_map.get(target_id) < k) {
                report_map.get(user_id).remove(target_id);
            }
        }
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = report_map.get(id_list[i]).size();
        }
        return answer;
    }
}
