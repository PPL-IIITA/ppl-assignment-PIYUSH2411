/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ques7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author dell
 */
public class Ques7 {

    /**
     * @param args the command line arguments
     */
    static girls[] G = new girls[10];
    static boys[] B = new boys[15];
    static int cg, cb;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String name;
        int attr, i, j, min_attr, min_budget;
        int maintenance;
        int intellect;
        int ng = 10, nb = 15;
        
        
        try{
            FileWriter boyFile = new FileWriter("Boy_file.csv");
            boyFile.write("");
            boyFile.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"1");
        }
        String wrt = "";
        char[] chars = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        String str = "";
        Random random = new Random();
        for(j = 0; j < 15; j++){
            str = "";
            for(i = 0; i < 10; i++){
                char c = chars[random.nextInt(chars.length)];
                str += c;           
            }
            wrt = str.toString();
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(10000);
            wrt += "," + random.nextInt(10);
            wrt += "," + "null,single";
            try{
                BufferedWriter br = new BufferedWriter(new FileWriter("Boy_file.csv", true));
                br.write(wrt+"\r\n");
                br.flush();
            }catch(Exception e){
               System.out.println(e.getMessage()+"2");
            }
        }
        try{
            FileWriter girlFile = new FileWriter("Girl_file.csv");
            girlFile.write("");
            girlFile.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"3");
        }
        for(j = 0; j < 10; j++){
            str = "";
            for(i = 0; i < 10; i++){
                char c = chars[random.nextInt(chars.length)];
                str += c;           
            }
            wrt = str.toString();
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(10000);
            wrt += "," + random.nextInt(10);
            wrt += "," + "null,single";
            try{
                BufferedWriter br = new BufferedWriter(new FileWriter("Girl_file.csv", true));
                br.write(wrt+"\r\n");
                br.flush();
            }catch(Exception e){
               System.out.println(e.getMessage()+"4");
            }
        }
        try{
            cb = 0;
            File fboy = new File("Boy_file.csv");
            BufferedReader br = new BufferedReader(new FileReader(fboy));
            String str1 = null;
            while((str1 = br.readLine()) != null){
                String word[] = str1.split(",");
                B[cb] = new boys(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]), Integer.parseInt(word[3]), Integer.parseInt(word[4]), word[5], word[6]);
                cb++;
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"5");
            e.printStackTrace();
        }
        
        
        try{
            cg = 0;
            File fgirl = new File("Girl_file.csv");
            BufferedReader br = new BufferedReader(new FileReader(fgirl));
            String str1 = null;
            while((str1 = br.readLine()) != null){
                String[] word = str1.split(",");
                G[cg] = new girls(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]), Integer.parseInt(word[3]), word[4], word[5]);
		cg++;
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"6");
        }
        for(i = 0; i < cg; i++){
            for(j = 0; j < cb; j++){
                if(G[i].status.equals("single") && G[i].maintenance_cost < B[j].budget && B[j].status.equals("single") && B[j].min_attr < G[i].attr){
                    G[i].bf = B[j].name;
                    B[j].gf = G[i].name;
                    G[i].status = "committed";
                    B[j].status = "committed";
                    break;
                }
            }
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv"));
            for(i = 0; i < cg; i++){
                if(G[i].status.equals("committed")){
                    String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                    String timeStamp1 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                    str = G[i].name+" is committed to "+G[i].bf+" on "+timeStamp+" at "+timeStamp1;
                    bw.write(str+ "\r\n");
                    bw.flush();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage()+"7");
        }
    }
}
