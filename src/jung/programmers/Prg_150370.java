package src.jung.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 문제 이름(난이도) : 개인정보 수집 유효기간(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * */
public class Prg_150370 {
    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(Arrays.toString(solution(today,terms,privacies)));
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>(); // 약관종류, 유효기간
        ArrayList<Integer> list = new ArrayList<>(); // 파기해야 할 개인정보 번호
        String[] today_arr = today.split("\\."); // .을 기준으로 split
        // 모든 달은 28일까지
        int today_cnt = Integer.parseInt(today_arr[0])*12*28+Integer.parseInt(today_arr[1])*28+Integer.parseInt(today_arr[2]); // today 일수로 계산
        for (String t : terms) {
            String[] term_arr = t.split(" ");
            String kind = term_arr[0];
            int e_date = Integer.parseInt(term_arr[1]);
            map.put(kind, e_date);
        }
        for (int i = 0; i < privacies.length; i++) {
            String[] p_arr = privacies[i].split(" ");

            String[] date_arr = p_arr[0].split("\\.");
            int p_year = Integer.parseInt(date_arr[0]);
            int p_month = Integer.parseInt(date_arr[1]);
            int p_date = Integer.parseInt(date_arr[2]);

            int e_month = map.get(p_arr[1]); // 수집된 개인정보의 약관 종류로 해당 약관의 유효기간 찾기
            p_month += e_month; // 개인정보 수집 달 + 유효기간

            int pri_cnt = (p_year*12*28+p_month*28+p_date)-1; // 보관 가능 날짜

            if (pri_cnt < today_cnt) { // today 일수가 크다면 해당 약관은 파기해야 됨!
                list.add(i+1);
            }
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
