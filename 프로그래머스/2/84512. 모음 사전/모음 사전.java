import java.util.*;

class Solution {
    static String[] words = {"A", "E", "I", "O", "U"};
    static ArrayList<String> list;
    public int solution(String word) {
        list = new ArrayList<>();
        dfs("", 0);

        int ans = 0; // 사전에서 몇 번째 단어
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                ans = i;
                break;
            }
        }
        
        return ans;
    }
    
    private static void dfs(String str, int depth) {
        list.add(str);

        if (depth == 5) return;

        for (int i = 0; i < 5; i++) {
            dfs(str+words[i], depth+1);
        }
    }
}