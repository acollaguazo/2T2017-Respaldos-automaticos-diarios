/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apprespaldo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


/**
 *
 * @author cardenasm
 */
    
public class ClienteFTP {
    
private String user;
private String password;
private String ftp;
private String url;
private String directorio;
private boolean login;
FTPClient ftpCliente;
FileOutputStream file_out;



//Constructor de la clase ClienteFTP
    public ClienteFTP() {
        this.user = "andscard";
        this.password ="xxxxxx";
        this.ftp = "192.168.15.1";
        ftpCliente = new FTPClient();
        this.file_out= null;
        try {
            //Se realiza la conexión con el usuario y contraseña
            ftpCliente.connect(ftp);
            this.login = ftpCliente.login(this.user, this.password);
            if (this.login) {
                System.out.println("Login success...");
            } else {
                System.out.println("Failure success...");
            }
            
            // Cerrando sesión
            //this.ftpCliente.logout();
	    // Desconectandose con el servidor
            //this.ftpCliente.disconnect();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
   //Se crea una lista con todos los archivos que contiene un directorio
   public FTPFile[]  listar() throws IOException{
     FTPFile[] ftpFiles = null;
     if(this.login){ // si es true
      int j=1;  
            ftpFiles = ftpCliente.listFiles();
             
             for (int i=2; i<ftpFiles.length; i++) {
                 FTPFile ftpFile= new FTPFile();
                 ftpFile=ftpFiles[i];
                  System.out.println(j+"."+ftpFile.getName());
                  j++;
                 }
            
            }else{
               System.out.println("No logeado...");}
     return ftpFiles;
 }
   
     
   public void  imprimirLista() throws IOException{
     //List lista= new ArrayList(); // creamos una array List
     FTPFile[] ftpFiles = null;
     if(this.login){ // si es true
      int j=1;  
            ftpFiles = this.listar();
             
             for (int i=0; i<ftpFiles.length; i++) {
                 FTPFile ftpFile= new FTPFile();
                 ftpFile=ftpFiles[i];
                  System.out.println(j+"."+ftpFile.getName());
                  j++;
                 }
            
            }else{
               System.out.println("No logeado...");}
 }
     
    
   //Llena la Jlist que aparece en la aplicación
   public void  llenarLista(DefaultListModel lista1) {
     FTPFile[] ftpFiles = null;
     if(this.login){ try {
         // si es true
         ftpFiles = this.listar();
         
         for (int i=0; i<ftpFiles.length; i++) {
             FTPFile ftpFile= new FTPFile();
             ftpFile=ftpFiles[i];
             lista1.addElement(ftpFile.getName());
         }
         } catch (IOException ex) {
             Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            }else{
               System.out.println("No logeado...");}
 }
   
     
     public void showDirectorio(){
    try {
        System.out.println("Directorio Actual:"+ftpCliente.printWorkingDirectory());
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
     
     
   //Dado el nomnbre de una nueva ruta Permite cambiarnos a otro directorio dentro del servidor 
   public void changeDirectorio(String ruta){
    try {
        ftpCliente.changeWorkingDirectory(ruta);
        //this.showDirectorio();
        
        //this.imprimirLista();
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
   
   
   
   //Dado el nombre de un archivo permite descargarlo desde del servidor
   public void desrcargarArchivoFTP(String new_file) {
  
    try {
        //Se crea un carpeta en el Disco Local C
         File carpeta = new File("C:/Users/cardenasm/Desktop/RespaldosDispositivos");
         carpeta.mkdir();
        File downloadFile = new File(carpeta.getAbsolutePath()+"/"+new_file);
        file_out = new FileOutputStream(downloadFile);
        if(ftpCliente.retrieveFile(new_file, file_out)){
            
            System.out.println("Descarga correcta");
        }else
            System.out.println("Error Descarga");
        file_out.close();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
   
   
   
  public String escogerArchivo(int num) {
    String nombre="";
    FTPFile[] file_array=null;
      try {
        file_array= this.listar();
        
        for (int i=0; i<file_array.length; i++) {
                 FTPFile ftpFile= new FTPFile();
                 ftpFile=file_array[i];
                 nombre=ftpFile.getName();
                 System.out.println("\nSe descargará el archivo "+nombre);
                 }
        
        
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
    return nombre;
  } 
}
    

