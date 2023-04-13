package application;

import java.util.*;
import java.io.*;

public class Customer{
  public String name;
  public String user_name;
  public String email;
  public String phone_number;
  public String payment;
  public String password;

  public Customer(String user, String u_name, String mail, String phone, String pay, String pass){
    name = new String(user);
    user_name = new String(u_name);
    email = new String(mail);
    phone_number = new String(phone);
    payment = new String(pay);
    password = new String(pass);
  }

  
  public Customer(){
    this.name = new String("null");
    this.email = new String("null");
      this.user_name = new String("null");
    this.phone_number = new String("null");
      this.payment = new String("null");
      this.password = new String("null");
  }


  public static void createAccount(String name, String user_name, String email, String phoneNumber, String password){
      CustFile temp = new CustFile();
      temp.addAccount(name, user_name, email, phoneNumber, password);
  }

   public static boolean checkUserAndPass(String user, String pass){
    CustFile temp = new CustFile();
     return temp.check(user, pass);
  }

   public static void payByCard(String username, String cardnumber, String name, String expdate, String cvv, String zip){
    CustFile temp = new CustFile();
    temp.cardpay(username, cardnumber,name,expdate,cvv, zip);
  }

   public static void payByPaypal(String username, String paypalname, String password){
    CustFile temp = new CustFile();
    temp.paypal(username, paypalname, password);
  }


   public String toString(){
    return(name + "\n" + user_name + "\n"+ email + "\n"+ phone_number +"\n"+ payment +"\n" + password);
  }
}
