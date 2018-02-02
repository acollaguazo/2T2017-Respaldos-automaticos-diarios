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
 * Esta clase se encarga de establecer una conexión con un servidor FTP
 *y permite la trasnferencia de archivos que se encuentran en sus directorios
 */
    
public class ClienteFTP {
    
private String user; //Nombre del usuario del server FTP
private String password;//Contraseña del servidor FTP
private String ftp; // 
private String url;
private String directorio;
private boolean login;
FTPClient ftpCliente; //Instancia de objeto clienteFTP
FileOutputStream file_out;



/**Constructor*/
    public ClienteFTP() {
        this.user = "server";
        this.password ="SERVIDOR123";
        this.ftp = "192.168.1.15";
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
    
   /**Esta funcion crea una lista con todos los archivos que contiene un directorio*/
   public FTPFile[]  listar() throws IOException{
     FTPFile[] ftpFiles = null;
     if(this.login){ // si es true
      int j=1;  
            ftpFiles = ftpCliente.listFiles();
             
             for (int i=0; i<ftpFiles.length; i++) {
                 FTPFile ftpFile= new FTPFile();
                 ftpFile=ftpFiles[i];
                  System.out.println(j+"."+ftpFile.getName());
                  j++;
                 }
            }else{
               System.out.println("No logeado...");}
     return ftpFiles;
 }
   
/**Muestra por pantalla la lista de archivos que se encuentran almacenados en una lista*/
   public void  imprimirLista() throws IOException{
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
     
    
   /**Esta funcion permita llenar un objeto tipo DefaultLisModel, el cual se
    lo implementa en el menu de los dispositivos para mostrar los archivos por 
    pantalla*/
   public void  llenarLista(DefaultListModel lista1) {
     FTPFile[] ftpFiles = null;
     if(this.login){ try {
         // si es true
         ftpFiles = this.listar();
         
         for (int i=0; i<ftpFiles.length; i++) {
             FTPFile ftpFile= new FTPFile();
             ftpFile=ftpFiles[i];
             lista1.addElement(ftpFile.getName());//añade un elementos a la lista
         }
         } catch (IOException ex) {
             Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
         }
            }else{
               System.out.println("No logeado...");}
 }
   
     /**Permite mostar el directorio actual en el nos encontramos dentro 
      del servidor FTP*/
     public void showDirectorio(){
    try {
        System.out.println("Directorio Actual:"+ftpCliente.printWorkingDirectory());
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
     
     
   /**Dado el nomnbre de una nueva ruta Permite cambiarnos a otro directorio dentro del servidor*/
   public void changeDirectorio(String ruta){
    try {
        ftpCliente.changeWorkingDirectory(ruta);
        //this.showDirectorio();
        //this.imprimirLista();
    } catch (IOException ex) {
        Logger.getLogger(ClienteFTP.class.getName()).log(Level.SEVERE, null, ex);
    }
     }
   
   
   
   /**Dado el nombre de un archivo permite descargarlo desde del servidor*/
   public void desrcargarArchivoFTP(String new_file) {
  
    try {
        //Se crea un carpeta en el Disco Local
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
   
   
 /*Esta funcion permite escoger un archivo de un diretorio dado una opcion*/
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
    

