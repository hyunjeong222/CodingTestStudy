import java.util.*;

class Solution {
    public String solution(String s) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] arr = s.split(" ");
        for (String a : arr) {
            list.add(Integer.parseInt(a));
        }
        Collections.sort(list);
        
        StringBuilder answer = new StringBuilder();
        answer.append(list.get(0)).append(" ").append(list.get(list.size()-1));
        return answer.toString();
    }
}