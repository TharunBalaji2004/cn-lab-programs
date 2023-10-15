import java.io.*;
import java.net.*;

class RARPserver {
    public static void main(String args[]) throws IOException {
        try {
            ServerSocket soc = new ServerSocket(2500);
            System.out.println("Server started");
            Socket client = soc.accept();
            String str;
            PrintStream ps = new PrintStream(client.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            Runtime r = Runtime.getRuntime();
            Process p = r.exec("ifconfig eth0");
            BufferedReader pin = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ipaddr = "";
            String haddr = br.readLine();
            int flag = 0;

            while ((str = pin.readLine()) != null) {
                System.out.println(str);
                if ((str.indexOf(haddr)) != -1) {
                    flag = 1;
                } else if ((str.indexOf("inet addr")) != -1) {
                    int pos = str.indexOf("inet addr:") + 10;
                    int offset = pos + 13;
                    ipaddr = str.substring(pos, offset);
                }
            }

            if (flag == 1)
                ps.println(ipaddr);
            else
                ps.println("Host does not exist");

            ps.close();
            br.close();
            pin.close();
            client.close();
            soc.close();
        } catch (IOException io) {
            System.err.println("Exception : " + io.toString());
        }
    }
}
