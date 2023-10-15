import java.io.*;
import java.net.*;

class RARPclient {
    public static void main(String args[]) {
        try {
            Socket client = new Socket("localhost", 2500);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintStream ps = new PrintStream(client.getOutputStream());

            String haddr, ipaddr = null;
            BufferedReader sin = new BufferedReader(new InputStreamReader(client.getInputStream()));

            System.out.print("Enter the physical address: ");
            haddr = br.readLine();
            ps.println(haddr);

            ipaddr = sin.readLine();
            if (ipaddr.equals("Host does not exist"))
                System.out.println(ipaddr);
            else
                System.out.println("Logical Address " + ipaddr);

            ps.close();
            br.close();
            client.close();
        } catch (IOException io) {
            System.err.println(io.toString());
        }
    }
}
