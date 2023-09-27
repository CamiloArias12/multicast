package model;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MulticastServer {

    private boolean bandera = true;
    private MulticastSocket socket;
    private InetAddress group;

    public MulticastServer() {
        try {
            socket = new MulticastSocket(6789);
            group = InetAddress.getByName("228.5.6.7");
        } catch (IOException e) {
            System.err.println("Error al configurar el servidor multicast: " + e.getMessage());
        }
    }

      public void enviarArchivo(String nombreArchivo) throws IOException {
        FileInputStream fis = new FileInputStream("src/document/" + nombreArchivo);

        // Enviar el nombre del archivo al cliente
        DatagramPacket nombrePacket = new DatagramPacket(nombreArchivo.getBytes(), nombreArchivo.length(), group, 6789);
        socket.send(nombrePacket);

        byte[] buffer = new byte[4 * 1024];
        int count;
        while ((count = fis.read(buffer)) > 0) {
            DatagramPacket packet = new DatagramPacket(buffer, count, group, 6789);
            socket.send(packet);
        }

        // Enviar un paquete vacío para marcar el final del archivo
        DatagramPacket finPacket = new DatagramPacket(new byte[0], 0, group, 6789);
        socket.send(finPacket);

        fis.close();
    }

}
