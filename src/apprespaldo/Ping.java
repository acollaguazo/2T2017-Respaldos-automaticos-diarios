/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apprespaldo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;

/**Esta clase se encarga de realizar un ping a los dispositivos de la red y comprueba si
están o no conectados
@author grupo1
@version 1.0*/
public class Ping {
private String address;

/**Constructor de la clase
 @param address Direccion ip del equipo*/    
 public Ping(String address_){
 this.address=address_;}

 
 /**Esta funcion ejecuta el comando "ping + ipAddress" y muestra por pantalla los resultados 
  * que se obtienen al correr el comando.
  * @param command Comando que se desea que se ejecute (ping + direccion_ip)
  */
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
  
  
  /**Esta funcion determina si una direccion ip es alcanzable
   @return Un valor booleno, yes si el dispositivo fue alcanzado, no
   si no se pudo alcanzar*/
  public boolean isReachable() {
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
}