package projekt;

import java.io.*;
import java.net.*;



public class Server{
	
	public static double wynik=0;

    public static void main(String[] args) {

        int port = 6666;
        double[][] array = new double[8][2]; 
        

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("New client connected");//polaczenie z klientem

                //cztanie tablicy wspolczynnikow wieolomianu
                double[] wspWielomianu = new double[5];
                DataInputStream inFromClient = new DataInputStream(socket.getInputStream());
            	for(int i=0;i<wspWielomianu.length;i++) {
            		System.out.println("czytam");
            		wspWielomianu[i]=inFromClient.readDouble();
            	}
            	//testowe wypisanie tablicy wspolczynnikow wieolomianu
            	for(int i=0;i<wspWielomianu.length;i++) {
            		System.out.println("a"+(i+1)+" = " + wspWielomianu[i]);
            	}
               
                //obliczanie punktow wielomianu
                Compute comp = new Compute(wspWielomianu[0], wspWielomianu[1],
                		wspWielomianu[2], wspWielomianu[3], wspWielomianu[4]); //obliczenia
                array = comp.resolve();
                for(int i=0; i<array.length; i++) {
                    System.out.println(array[i][0]+" "+array[i][1]);
                }
                
                
                //wysylanie tablicy policzonych ponktow
                DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
                for (int i = 0; i < array.length; i++) {
                    outToClient.writeDouble(array[i][0]);//+" "+array[i][1]);
                    outToClient.writeDouble(array[i][1]);
                }
                

               // OutputStream output = socket.getOutputStream();//wysylanie informacji zwrotnej
              //  PrintWriter writer = new PrintWriter(output, true);
              //  String str = Double.toString(wynik);
              //  writer.println(str);
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    

}

