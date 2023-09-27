package model;

import java.io.*;
import java.net.*;

public class MulticastClient {

    private MulticastSocket socket;
    private InetAddress group;

    public MulticastClient() {
        try {
            socket = new MulticastSocket(6789);
            group = InetAddress.getByName("228.5.6.7");
            socket.joinGroup(group);
            System.out.println("Cliente multicast conectado al grupo " + group.getHostAddress());

            Thread receiverThread = new Thread(new Receiver());
            receiverThread.start();
        } catch (IOException e) {
            System.err.println("Error al configurar el cliente multicast: " + e.getMessage());
        }
    }

    private class Receiver implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    byte[] buffer = new byte[4 * 1024];
                    DatagramPacket recv = new DatagramPacket(buffer, buffer.length);

                    // Esperar a recibir el nombre del archivo del servidor
                    socket.receive(recv);
                    String nombreArchivo = new String(recv.getData(), 0, recv.getLength());
                    System.out.println("Recibiendo archivo: " + nombreArchivo);

                    // Crear el archivo en el cliente
                    FileOutputStream fos = new FileOutputStream(nombreArchivo);

                    while (true) {
                        socket.receive(recv);
                        if (recv.getLength() == 0) {
                            break; // Fin del archivo actual
                        }

                        // Escribir los datos en el archivo
                        fos.write(recv.getData(), 0, recv.getLength());
                    }

                    fos.close();
                    System.out.println("Archivo recibido con éxito: " + nombreArchivo);
                }
            } catch (IOException e) {
                System.err.println("Error durante la comunicación multicast: " + e.getMessage());
            }
        }
    }

}



