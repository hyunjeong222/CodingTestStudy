import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append("       _.-;;-._").append("\n");
        sb.append("'-..-'|   ||   |").append("\n");
        sb.append("'-..-'|_.-;;-._|").append("\n");
        sb.append("'-..-'|   ||   |").append("\n");
        sb.append("'-..-'|_.-''-._|");

        System.out.println(sb.toString());
    }
}