package control;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import model.MulticastClient;
import model.MulticastServer;
import view.View;

public class Control{
   
    private View view ;
    private MulticastServer multicastServer;
   public Control (){
      
      view= new View();

      multicastServer=new MulticastServer();
   }

   public void options() throws Exception {
      int option =0;
      String file="";

      while (option!=3) {
	 option = view.menu();
	 switch (option) {
            case 1:
	        new MulticastClient();

                break;
	    case 2:
            sendOption();
                break;

	    default:
	       break;
	 }
      }
   }


   public void sendOption () throws IOException{
      
      int option = view.send();

      switch (option) {
         case 1:
               multicastServer.enviarArchivo("texto.txt");
             break;
            case 2:
               multicastServer.enviarArchivo("descargar.jpg");
             break;
              case 3:
               multicastServer.enviarArchivo("video.mp4");
             break;  
             case 4:
               multicastServer.enviarArchivo("audio.mp3");
             break;  
            
             default:
               break;
         }
   }
}

   
