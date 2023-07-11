package com.erdemserhat.ultimatebox.rsa;
import java.math.BigInteger;
public class Rsa {
  private int securityLevel;
  //Aggregation relationship with RsaCore class.
  private RsaCore rsaCore;
  
 
  
  public Rsa(int securityLevel){
      
      switch (securityLevel) {
          case 1 -> {
             rsaCore= new RsaCore(100,200);
          }
          case 2 -> {
            rsaCore= new RsaCore(300,600);
          }
          case 3 -> {
             rsaCore= new RsaCore(600,1200);
          }
        
          default -> {
              System.err.println("You must select a number from 1 to 3\n"
                      + "1 -> Normal Securty Level\n"
                      + "1 -> Standard+ Securty Level\n"
                      + "2 -> Above Standard Security Level\n"
                      + "3 -> Strong Security Level :) \n");
                     
              
          }
      }

  }
  
  public int encrypt(int message){
   
  BigInteger chiperText=rsaCore.encrypt(BigInteger.valueOf(message));
  String strC=chiperText.toString();
  return Integer.parseInt(strC);
  }
  
  
  public int decrypt(int chiperText){
   
  BigInteger message=rsaCore.decrypt(BigInteger.valueOf(chiperText));
  String strM=message.toString();
  return Integer.parseInt(strM);
  }
  
  public static void main(String...args){
      
      Rsa rsa = new Rsa(3);
      //rsa.encrypt(18648);
      
      
      
     
  
      
      
   
  
  }
    
    
}
