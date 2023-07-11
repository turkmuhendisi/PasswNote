package com.erdemserhat.ultimatebox.rsa;
import java.math.BigInteger;
public class RsaCore {
    private final BigInteger p;
    private final BigInteger q;
    private final BigInteger n;
    private final BigInteger f;
    private final BigInteger e;
    private final BigInteger d;
    private int firstBorder;
    private int lastBorder;
    
    public RsaCore(int firstBorder, int lastBorder){
        //Composition Relationship with Operations class.
        Operations operations = new Operations(firstBorder,lastBorder);
        operations.prepareSuitableValues();
        p=BigInteger.valueOf(operations.getP());
        q=BigInteger.valueOf(operations.getQ());
        n=BigInteger.valueOf(operations.getN());
        f=BigInteger.valueOf(operations.getF());
        e=BigInteger.valueOf(operations.getE());
        d=BigInteger.valueOf(operations.getD());
    }
    
    public RsaCore(){
        //Composition Relationship with Operations class.
        Operations operations = new Operations(this.firstBorder,this.lastBorder);
        operations.prepareSuitableValues();
        p=BigInteger.valueOf(operations.getP());
        q=BigInteger.valueOf(operations.getQ());
        n=BigInteger.valueOf(operations.getN());
        f=BigInteger.valueOf(operations.getF());
        e=BigInteger.valueOf(operations.getE());
        d=BigInteger.valueOf(operations.getD());
    
    }

    public int getFirstBorder() {
        return firstBorder;
    }

    public void setFirstBorder(int firstBorder) {
        this.firstBorder = firstBorder;
    }

    public int getLastBorder() {
        return lastBorder;
    }

    public void setLastBorder(int lastBorder) {
        this.lastBorder = lastBorder;
    }
    
    
  
    
    public BigInteger encrypt(BigInteger message){
    String strE = e.toString();
    BigInteger chiperText=(message.pow(Integer.parseInt(strE))).mod(n);   
    //System.out.println(chiperText);
    return chiperText;
    }
    
    public BigInteger decrypt(BigInteger chiperText){
        String strD=d.toString();
        BigInteger message= (chiperText.pow(Integer.parseInt(strD))).mod(n);
       // System.out.println(message);
        return message;
    
    
    }
    
    public void showInfoCryptology(){
        System.out.println("p:"+p);
        System.out.println("q:"+q);
        System.out.println("n:"+n);
        System.out.println("f:"+f);
        System.out.println("e:"+e);
        System.out.println("d:"+d);
        
    
    
    
    
    }
   
}
