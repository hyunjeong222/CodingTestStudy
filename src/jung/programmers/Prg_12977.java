package src.jung.programmers;

/**
 * 문제 이름(난이도) : 소수 만들기(LV1)
 * 시간 : ms
 * 메모리 : KB
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12977
 */
public class Prg_12977 {
    public static void main(String[] args) {
        int[] nums = {1,2,7,6,4};
        System.out.println(solution(nums));

    }
    public static int solution(int[] nums) {
        int answer = 0;
        int sum = 0; // 3개의 수를 더한 값
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    sum = nums[i] + nums[j] + nums[k];
                    if (isPrime(sum)) { // 소수라면 true
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    public static boolean isPrime(int sum) {
        // 제곱근을 기준으로 대칭을 이루기 때문에 타깃 숫자부터 제곱근까지만 비교
        for (int i = 2; i <= Math.sqrt(sum); i++) { // 0, 1 소수 X
            if (sum % i == 0) return false; // 자기 자신이 아닌 수로 나누어진다면 소수 X
        }
        return true;
    }
}
