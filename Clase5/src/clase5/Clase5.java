/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase5;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author rodri
 */
public class Clase5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String opcMenu="",opcRepetir="";
        int n1=0,n2=0,R=0;
        Scanner datos=new Scanner(System.in);
    do {
        System.out.println("Menu Principal");
        System.out.println("1)Sumar");
        System.out.println("2)Restar");
        System.out.println("3)Salir");
        System.out.println("Seleccione la opcion deseada");
        opcMenu=datos.nextLine();
        switch (opcMenu){
            case "1":{
                JOptionPane.showMessageDialog(null,"Ingrese el primer numero:");//Mostar Mensaje en pantalla
                JOptionPane.showInputDialog(n1);
                JOptionPane.showMessageDialog(null,"Ingrese el segundo numero:");
                JOptionPane.showInputDialog(n2);
                datos.nextLine();
                R=n1+n2;
                JOptionPane.showMessageDialog(null,"El Resultado de la suma es "+R);
                break;
            }
            default:{
                System.out.println("Opcion Incorrecta");
                break;
            }
        }
        System.out.println("Desea Regresar al menu? Si/No");
        opcRepetir=datos.nextLine();
    }while(opcRepetir.toLowerCase().equals("si"));//opcRepetir.toLowerCase()=="si"
        System.out.println("Gracias Por utilizar el programa");
    
    }
    
}
