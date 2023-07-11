package com.erdemserhat.ultimatebox.rsa;
import java.math.BigInteger;

public class TextChiper {
    private Rsa rsa;
    
    public TextChiper(Rsa rsa){
    this.rsa=rsa;
    
    }

    public Rsa getRsa() {
        return rsa;
    }

    public void setRsa(Rsa rsa) {
        this.rsa = rsa;
    }
    
    public String encryptText(String text){
    String chiperText=new String();
    for(int i=0; i<text.length(); i++){
        
        
        chiperText+=String.valueOf(rsa.encrypt((int)(text.charAt(i))))+",";
       
    }
        
    return chiperText;
    }
    
    public String decryptText(String chiperText){
    String text = new String();
    String temp= new String();
    for(int i=0; i<chiperText.length(); i++){
        
        if(chiperText.charAt(i)==','){
            text+=(char)(rsa.decrypt(Integer.parseInt(temp)));  
            temp="";
        }else{
        temp+=chiperText.charAt(i);
        
        }
    
    
    }
        
    return text;
    }
    

    
}
