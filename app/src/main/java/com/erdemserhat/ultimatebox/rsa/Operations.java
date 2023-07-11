package com.erdemserhat.ultimatebox.rsa;

import java.util.ArrayList;

public class Operations {
    //Fields
    private int firstBorder;
    private int lastBorder;
    private int p;
    private int q;
    private int e;
    private int f;
    private int n;
    private int d;
    
    //Constructor
    public Operations(int firstBorder, int lastBorder){
    this.firstBorder=firstBorder;
    this.lastBorder=lastBorder;
    
    }
    
    //Setters
    public void setE(int e) {
        this.e = e;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public void setFirstBorder(int firstBorder){
    this.firstBorder=firstBorder;
    }
    
    public void setLastBorder(int lastBorder){
    this.lastBorder=lastBorder;
    }
    
    
    //Getters
    public int getFirstBorder(){
    return this.firstBorder;
    }
    
    public int getLastBorder(){
    return this.lastBorder;
   
    }
     
    public int getP(){
    return p;
    }
    
     
    public int getQ(){
    return q;
    }
    
    
    public int getE() {
        return e;
    }

    public int getF() {
        return f;
    }

    public int getN() {
        return n;
    }
    
     public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }
    
    
    //Behaviours
    public void findSuitablePandQ(){
    ArrayList <Integer> primes = new ArrayList <>();
    boolean isPrime;
    for(int i=firstBorder; i<=lastBorder; i++){
        isPrime=true;
        for(int j=2; j<=Math.sqrt(i); j++){  
            if(i%j==0){
                isPrime=false;
                break;
            }

        }
        if(isPrime){
        primes.add(i);
        }

    }
    
    int random=primes.size()/2;
    p=primes.get(random);
    q=primes.get(random+1);

    }
    
    
    public void findSuitableN(){
     n=p*q;

    }
    
    
    public void findSuitableF(){
    f=(p-1)*(q-1);
    
    }
    
    
    public void findSuitableE(){
    ArrayList <Integer> values = new ArrayList <>();
    boolean control;
    for (int i=2; i<f ; i++){
        control=true;
        for(int j=2; j<=Math.sqrt(i); j++){
        if(i%j==0){
        control = false;
        break;
        }
 
        }
        if(f%i==0 ){
        continue;
    
        }else if(control && hammingWeight(i)==2){
        values.add(i);
        }
   
    
    }
    int index =values.size()/2;
    e=values.get(index);
   
   
    }
     
    public void findSuitableD(){
        ArrayList <Integer> values = new ArrayList<>();
        for(int i=1; true; i++){
            if((i*e)%f==1){
            values.add(i);
            }
            
            if(values.size()>0) break;
         
    }
     
        
        d=values.get(0);
       
    
}

   
    public void prepareSuitableValues(){
    findSuitablePandQ();
    findSuitableN();
    findSuitableF();
    findSuitableE();
    findSuitableD();
 
    }
    
    public void partlyPrepareSuitableValues(){
        //for testing any user defined values..
        setP(61);
        setQ(53);
        findSuitableN();
        findSuitableF();
        setE(17);
        setD(2753);
        
 
    }
    
    
    public  int hammingWeight(int num) {
    int weight = 0;
    while (num != 0) {
        if (num % 2 == 1) {
            weight++;
        }
        num = num / 2;
    }
    return weight;
}
    //Integer.bitCount(Number) calculates to hamming weight.
    
}