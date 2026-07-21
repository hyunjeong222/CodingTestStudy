class Solution {
    public int solution(String name) {
        int ans = 0;
        int idx;
        int move = name.length()-1;
        for (int i = 0; i < name.length(); i++) {
            ans += Math.min(name.charAt(i)-'A', 'Z'-name.charAt(i)+1); // 상하 이동

            idx = i+1;
            while (idx < name.length() && name.charAt(idx) == 'A') {
                idx++;
            }

            move = Math.min(move, i*2+name.length()-idx);
            move = Math.min(move, (name.length()-idx)*2+i);
        }
        
        return ans+move;
    }
}