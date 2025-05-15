import java.util.*;

class Solution {
    static boolean[] checked;
    static int len;
    static ArrayList<String> list;
    public String[] solution(String[][] tickets) {
        len = tickets.length;
        checked = new boolean[len];
        list = new ArrayList<>();
        dfs("ICN", "ICN", 0, tickets);
        Collections.sort(list);
        
        // System.out.println(list);
        
        String[] answer = list.get(0).split(" ");
        return answer;
    }
    
    private static void dfs(String start, String route, int depth, String[][] tickets) {
        if (depth == len) {
            list.add(route);
            return;
        }
        
        for (int i = 0; i < len; i++) {
            if (!checked[i] && start.equals(tickets[i][0])) {
                checked[i] = true;
                dfs(tickets[i][1], route+" "+tickets[i][1], depth+1, tickets);
                checked[i] = false;
            }
        }
    } 
}