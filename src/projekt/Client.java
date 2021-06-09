package projekt;

import java.net.*;
import java.io.*;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;


public class Client {
	
	public static double[][] array = new double[8][2]; 
	//public static Object array = null;

    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 6666;

        /**************************************************************/
        JFrame frame = new JFrame("Problem plecakowy");
        frame.setSize(400, 500);

        JTextArea wpiszX = new JTextArea();
        JTextArea miejscex = new JTextArea(1, 16);
        JTextArea miejscex2 = new JTextArea(1, 16);
        JTextArea miejscex3 = new JTextArea(1, 16);
        JTextArea miejscex4 = new JTextArea(1, 16);
        JTextArea miejscec = new JTextArea(1, 16);

        JTextArea textArea = new JTextArea(16, 30);

        textArea.setEditable(false);
        wpiszX.setEditable(false);


        JButton b = new JButton("Oblicz");
        JButton change_color_button = new JButton("Change color");


        JPanel panel = new JPanel();
        panel.add(wpiszX);
        panel.add(miejscex);
        panel.add(miejscex2);
        panel.add(miejscex3);
        panel.add(miejscex4);
        panel.add(miejscec);
        wpiszX.append("Kolejność: X^4, X^3, X^2, X, C");
        //panel.add(textArea);
        panel.add(b);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);


        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try (Socket socket = new Socket(hostname, port)) {

                    double[] wspWielomianu = {Integer.parseInt(miejscex.getText()),
                    Integer.parseInt(miejscex2.getText()),
                    Integer.parseInt(miejscex3.getText()),
                    Integer.parseInt(miejscex4.getText()),
                    Integer.parseInt(miejscec.getText()),
                    };

                    System.out.println(wspWielomianu);


                    DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
                    for (int i = 0; i < wspWielomianu.length; i++) {
                        outToServer.writeDouble(wspWielomianu[i]);
                    }

                    DataInputStream inFromServer = new DataInputStream(socket.getInputStream());
                    for(int i=0;i<array.length;i++) {
                        array[i][0]=inFromServer.readDouble();
                        array[i][1]=inFromServer.readDouble();
                    }


                } catch (UnknownHostException ex) {

                    System.out.println("Server not found: " + ex.getMessage());

                } catch (IOException ex) {

                    System.out.println("I/O error: " + ex.getMessage());
                }

                double a2[][] = {{0, 0}, {50, 50}, {50, 100}, {250, 250}};
                new Plottin(array).setVisible(true);
       //         new Plottin(a2).setVisible(true);

            }
        });
        /******************************************************************/

    }
}
