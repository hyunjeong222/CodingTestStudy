import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append("     /~\\").append("\n");
        sb.append("    ( oo|").append("\n");
        sb.append("    _\\=/_").append("\n");
        sb.append("   /  _  \\").append("\n");
        sb.append("  //|/.\\|\\\\").append("\n");
        sb.append(" ||  \\ /  ||").append("\n");
        sb.append("============").append("\n");
        sb.append("|          |").append("\n");
        sb.append("|          |").append("\n");
        sb.append("|          |");
        
        System.out.println(sb.toString());
    }
}