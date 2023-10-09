import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		HashMap<String, Integer> tree_map = new HashMap<String, Integer>();
		String name = br.readLine();
		int allCount = 0;
		while (true) {
			tree_map.put(name, tree_map.getOrDefault(name, 0)+1);
			allCount++;
			name = br.readLine();
			if (name == null || name.equals("")) break;
		}
		
		Object[] keys = tree_map.keySet().toArray();
		Arrays.sort(keys);
		
		StringBuilder sb = new StringBuilder();
		
		for (Object key : keys) {
			String tree_name = (String) key;
			int count = tree_map.get(tree_name);
			double per = (double)(count*100.0) / allCount;
			sb.append(tree_name + " " + String.format("%.4f", per) + "\n");
		}
		bw.append(sb.toString() + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
