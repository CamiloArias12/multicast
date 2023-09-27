package view;

import java.util.Scanner;

public class View {

   private  Scanner scanner;

   public View (){
      scanner=new Scanner(System.in);
   }

   public int menu (){
      System.out.println("Enter some  option: ");
	 System.out.println("1. Start as client");
         System.out.println("2.Start as server");
         System.out.println("3.Exit");
	 int number=scanner.nextInt();

	 return number;
            
   }

    public int send (){
      System.out.println("Enter some  option: ");
	 System.out.println("1. Send txt");
         System.out.println("2. Send jpg");
         System.out.println("3.Send video ");
         System.out.println("4.Send mp3");
	 int number=scanner.nextInt();

	 return number;
            
   }

   public String readOption(String title){
      System.out.println(title);
      return scanner.next();
   }

   public void showMessage(String title){
      System.out.println(title);
   }

}
