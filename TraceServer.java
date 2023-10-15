import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TraceServer {
    public static void main(String[] args) {
        try {
            String str;
            System.out.print("Enter domain name: ");
            BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));
            String ip = buf1.readLine();

            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec("traceroute " + ip);
            BufferedReader buf2 = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while((str = buf2.readLine()) != null) {
                System.out.println(" " + str);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
