import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int left = 0;
        int right = people.length-1;
        int twoSafe = 0; // 두 명이 한번에 간 겨우
        while(left < right) {
            if (people[left]+people[right] <= limit) { // 두 명이 한번에 간 겨우
                twoSafe++;
                left++;
                right--;
            } else {
                right--;
            }
        }
        
        int answer = people.length-twoSafe;
        return answer;
    }
}