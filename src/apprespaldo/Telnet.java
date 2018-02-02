/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apprespaldo;

import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *Esta clase permite la conexion via Telnet dispositivos  intermedios 
 * marca CISCO (router o swtich), para ejecutar comandos remotamente
 * @author grupo2
 * @version 1.0
 */
public class Telnet {
    private TelnetClient telnet = new TelnetClient();//Cliente Telnet
    private Fecha date; //Instancia de objeto fecha
    private InputStream in;
    private PrintStream out;
    private String prompt = "#"; //Prompt en la cosola
    private String prompt2 = "?";
    private String prompt3 = "!";
   
    /**Constructor
     @param ipaddress Es la direccion ip a la cual se desea hacer telnet
     @param hostname Nombre del dispositivo intermedio al cual se desea acceder
     @param directory nombre del directorio donde guardaremos los comandos
     ejecutados via telnet*/
    public Telnet(String ipaddress, String hostname, String directory) {
        try {
            // Conexion al dispositivo
            telnet.connect(ipaddress);
            //Declaracion de un objeto fecha
            date = new Fecha();
            // Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            // Se lee la palabra Username del router/switch
            readUntil("Username:");
            //Escribimos el usuario
            write("admin");
            //Se lee la palabra PAssword del router/swtich
            readUntil("Password:");
            //EScribrimos la contraseña
            write("admin");
             //ejecución de comandos ya dentro del switch
            this.sendCommand(hostname, directory);
            System.out.println("DONE");
            //cerrar la sesion de telnet
            telnet.disconnect();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/**Esta funcion permite leer las lineas que muestra un router o switch
 a medida que se va interactuando con estos. Se deben tener conocimiento
 * de estas lineas.
 @param pattern Es la línea que deseamos que se lea de la consola del router
 o switch*/
    public String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**Esta función permite escribir comandos en la consola del dispositivo
     @param value Es el comando que deseamos escribir en la consola del dispositivo*/
    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Función que encarga de ejectuar comandos para realizar un respaldo de los 
     * archivos de configuración de un router o switch en el servidor FTP*/
    public void sendCommand(String hostname, String directory) {
        try {
            readUntil(prompt);
            write("copy running-config ftp"); // copia el running-config en el sevidor 
            readUntil(prompt2+ " ");
            write("192.168.1.15"); //direción del servidor FTP
            readUntil(prompt2+ " ");
            //dirección en la que se guardará el archivo
            //el nombre posee el sgt formato: hostname-dia_mes_año.cfg
            write("/Documentos/"+directory+"/"+hostname+"-"+date.imprimirFecha()+".cfg");
            readUntil(prompt3);
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
 
    
}
