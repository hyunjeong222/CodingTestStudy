import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < tangerine.length; i++) {
            map.put(tangerine[i], map.getOrDefault(tangerine[i], 0)+1);
        }
        // System.out.println(map);
        ArrayList<Integer> list = new ArrayList<>(map.values()); // 갖고있는 개수
        Collections.sort(list, Collections.reverseOrder()); // 많이 갖고 있는 순
        // System.out.println(list);
        int cnt = 0; // 크기가 서로 다른 종류의 수의 최솟값
        int sum = 0; // 고를 귤의 개수
        for(int i : list) {
            if (sum + i >= k) {
                cnt++;
                break;
            } else {
                sum += i;
                cnt++;
            }
        }
        
        return cnt;
    }
}