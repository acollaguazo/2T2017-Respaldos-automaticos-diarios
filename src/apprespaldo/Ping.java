/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apprespaldo;

/**
 *
 * @author cardenasm
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class Ping {
    
 public Ping(){}

  public  void runSystemCommand(String command) {

		try {
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader inputStream = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String s = "";
			// reading output stream of the command}
                       
			while ((s = inputStream.readLine()) != null) {
				System.out.println(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  
  public boolean isReachable(String address) {
      boolean isReachable=true;  
      try {
            InetAddress inetAddress = InetAddress.getByName(address);
             isReachable= inetAddress.isReachable(5000);
            //System.out.printf("Is the address [%s] reachable? -%s\n", address, isReachable ? "Yes" : "No");
        } catch (Exception e) {
            e.printStackTrace();
        }
      return isReachable;
        
       
    }

	/*public static void main(String[] args) {
		
		String ip = "192.168.1.235";
		runSystemCommand("ping " + ip);

	
	}*/
}