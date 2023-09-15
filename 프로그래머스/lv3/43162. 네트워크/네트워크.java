class Solution {
    int[] parents;
    int n;
    public int solution(int n, int[][] computers) {
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && computers[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (parents[i] == i) answer++;
        }
        
        return answer;
    }
    
    public void union(int i, int j) {
        i = find(i);
        j = find(j);

        if (i > j) parents[i] = j;
        else parents[j] = i;
    }
    
    public int find(int i) {
        if (parents[i] == i) return i;
        return parents[i] = find(parents[i]);
    }
}