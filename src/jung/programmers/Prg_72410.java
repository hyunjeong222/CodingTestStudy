package src.jung.programmers;

/**
 * 문제 이름(난이도) : 신규 아이디 추천(LV1)
 * 시간 : ms
 * 메모리 : MB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/72410
 * */
public class Prg_72410 {
    public static void main(String[] args) {
        String new_id = "...!@BaT#*..y.abcdefghijklm";
        System.out.println(solution(new_id));
    }

    public static String solution(String new_id) {
        String answer = "";
        String temp = new_id.toLowerCase();
        for (char c : temp.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                answer += String.valueOf(c);
            } else if (c >= '0' && c <= '9') {
                answer += String.valueOf(c);
            } else if (c == '-' || c == '_' || c == '.') {
                answer += String.valueOf(c);
            }
        }
        for (int i = 0; i < answer.length(); i++) {
            int j = i + 1;
            String dot = ".";

            while (j != answer.length() && answer.charAt(j) == '.') {
                dot += ".";
                j++;
            }

            if (dot.length() > 1) {
                answer = answer.replace(dot, ".");
            }
        }
        if (answer.startsWith(".")) {
            answer = answer.substring(1, answer.length());
        } else if (answer.endsWith(".")) {
            answer = answer.substring(0, answer.length()-1);
        }
        if (answer.equals("")) {
            answer += "a";
        }
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15);
        }
        if (answer.endsWith(".")) {
            answer = answer.substring(0, answer.length()-1);
        }
        if (answer.length() <= 2) {
            while (answer.length() < 3) {
                answer += answer.charAt(answer.length()-1);
            }
        }
        return  answer;

        /* case 1 정규표현식, replaceAll
        String answer = "";
        String temp = new_id.toLowerCase(); // 모두 소문자 치환
        temp = temp.replaceAll("[^a-z0-9-_.]", ""); // 소문자, 숫자, -, ㅡ, . 제외하고 제거
        temp = temp.replaceAll("[.]{2,}", "."); // . 2번 이상이면 연속된 부분 하나로 치환
        temp = temp.replaceAll("^[.]|[.]$", ""); // . 처음이나 끝에 온다면 제거
        // 빈 문자열이면 a 대입
        if (temp.equals("")) {
            temp += "a";
        }
        // 길이가 16 이상이면 15까지
        if (temp.length() >= 16) {
            temp = temp.substring(0, 15);
        }
        // . 마지막에 위치한다면 제거
        temp = temp.replaceAll("[.]$", "");
        // 길이가 2 이하라면 마지막 문자를 길이 3까지 반복해서 붙이기

        answer = temp;
        return  answer;
        */
    }
}
