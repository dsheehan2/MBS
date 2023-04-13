package application;

import java.util.*;
import java.io.*;


public class CustFile{
  public String filename = new String("custfile.txt");
  public int num_cust;
  ArrayList<Customer> customer_list = new ArrayList<>();


  public CustFile(){
    readFile();
  }

  public void readFile(){
    //long phonenum;
    try{
      Scanner fileIn = new Scanner(new FileInputStream(filename));
      num_cust = fileIn.nextInt();
      fileIn.nextLine();
      //customer_list = new Customer[num_cust];
      for (int i = 0; i < num_cust; i++){
        customer_list.add(new Customer(fileIn.nextLine(), fileIn.nextLine(),
                                        fileIn.nextLine(), fileIn.nextLine(), 
                                        fileIn.nextLine(),
                                        fileIn.nextLine()));
        //System.out.println(customer_list.get(i));
      }
      fileIn.close();
    }
    catch(FileNotFoundException e){
      System.out.println("file not found");
    }
    

  }

  public void addAccount(String name, String username, String email, String phoneNumber, String password){
    readFile();
    num_cust = num_cust + 1;
    try{
      FileWriter writer = new FileWriter(filename);
      writer.write(String.valueOf(num_cust) + "\n");
      for (int i = 0; i < (num_cust - 1); i++)
        writer.write(customer_list.get(i) + "\n");
      writer.close();
    }catch(IOException e){
      System.out.println("could not write to file");
    }

    
    try{
      BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
      out.write(name + "\n" + username + "\n"+ email + "\n" + phoneNumber + "\n" + null + "\n" + password);
      out.close();
      
    }catch (IOException e){
      System.out.println("unable to write new customer to file");
    }

    
  }


  public boolean check(String user, String pass){
    boolean temp = false;
    for (int i = 0; i < num_cust; i++){
      if (customer_list.get(i).user_name.equals(user) && customer_list.get(i).password.equals(pass))
        temp = true;
    
    }
    System.out.println();
    return temp;
  }

  public void cardpay(String username,String cardnumber, String name,String expdate,String cvv,String zip){
    //Customer temp = new Customer();
    for (int i = 0; i<num_cust; i++){
      if (customer_list.get(i).name.equals(name) || customer_list.get(i).user_name.equals(username)){
        customer_list.get(i).payment = new String(cardnumber + " " + expdate + " " + cvv + " " + zip);
        
      }
      
    }
    try{
          FileWriter writer = new FileWriter(filename);
          writer.write(String.valueOf(num_cust) + "\n");
          for (int j = 0; j < (num_cust); j++)
            writer.write(customer_list.get(j) + "\n");
          writer.close();
        }catch(IOException e){
          System.out.println("could not write to file");
      }
    
  }
    

    
  

   public void paypal(String username, String paypalname, String password){
     for (int i = 0; i<num_cust; i++){
      if (customer_list.get(i).user_name.equals(username)){
        customer_list.get(i).payment = new String("Paypal " + paypalname + " " + password);
        
      }
      
    }
    try{
          FileWriter writer = new FileWriter(filename);
          writer.write(String.valueOf(num_cust) + "\n");
          for (int j = 0; j < (num_cust); j++)
            writer.write(customer_list.get(j) + "\n");
          writer.close();
        }catch(IOException e){
          System.out.println("could not write to file");
      }
   }

  
    
   public String toString(){
     String customers = new String();
     for (int i = 0; i < num_cust; i++)
       customers = new String(customers + customer_list.get(i));
     return customers;
  } 

}
    

