package apprespaldo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase Ventana
 * Muestra la estructuta que deberia tener una Ventana en Java con la libreria
 * Swing, contiene una etiqueta, un caja de texto y un boton, que tiene la
 * accion de mostrar el texto en la caja por una ventana de mensaje.
 * @author Daniel Alvarez (a3dany)
 */
public class AppRespaldo extends JFrame implements ActionListener {

    private Principal inicio;           // etiqueta o texto no editable
            // caja de texto, para insertar datos
             // boton con una determinada accion

    public AppRespaldo() {
        super(); 
        this.inicializarComponentes();// usamos el contructor de la clase padre JFrame
         // inicializamos los atributos o componentes
    }


    private void inicializarComponentes() {
        // creamos los componentes
        inicio= new  Principal();
    }



    public static void main(String[] args) {
           // creamos una ventana
                new AppRespaldo().inicio.setVisible(true); // hacemos visible la ventana creada
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}