/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ques4;

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
public class Ques4 {

    /**
     * @param args the command line arguments
     */
    static girls[] G = new girls[10];
    static boys[] B = new boys[15];
    static gifts[] Gift = new gifts[50];
    int[] couples = new int[10];
    static int cg, cb, cgift;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String name;
        int attr, i, j, min_attr, min_budget;
        int maintenance;
        int intellect;
        int ng = 10, nb = 15, ngift = 50;
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
        String[] typeb = new String[3];
        typeb[0] = "Miser";
        typeb[1] = "Geek";
        typeb[2] = "Generous";
        String[] typeg = new String[3];
        typeg[0] = "Normal";
        typeg[1] = "Choosy";
        typeg[2] = "Desperate";
        String[] typegift = new String[]{"Luxury","Utility","Essential"};
        for(j = 0; j < 15; j++){
            str = "BOY-";
            String c = Integer.toString(j);
            str += c;
            wrt = str.toString();
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(500);
            wrt += "," + random.nextInt(10);
            wrt += "," + "null,single";
            wrt += "," + typeb[random.nextInt(3)];
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
            str = "GIRL-";
            String c = Integer.toString(j);
            str += c;           
            wrt = str.toString();
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(500);
            wrt += "," + random.nextInt(10);
            wrt += "," + random.nextInt(10);
            wrt += "," + "null,single";
            wrt += "," + typeg[random.nextInt(3)];
            try{
                BufferedWriter br = new BufferedWriter(new FileWriter("Girl_file.csv", true));
                br.write(wrt+"\r\n");
                br.flush();
            }catch(Exception e){
               System.out.println(e.getMessage()+"4");
            }
        }
        try{
            FileWriter giftsFile = new FileWriter("gifts_file.csv");
            giftsFile.write("");
            giftsFile.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"1");
        }
        for(j = 0; j < ngift; j++){
            wrt = typegift[random.nextInt(3)];
            wrt += ","+ random.nextInt(20);
            wrt += ","+ random.nextInt(20);
            try{
                BufferedWriter br = new BufferedWriter(new FileWriter("gifts_file.csv", true));
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
                B[cb] = new boys(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]), Integer.parseInt(word[3]), Integer.parseInt(word[4]),word[5], word[6], word[7]);
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
                G[cg] = new girls(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]), Integer.parseInt(word[3]), Integer.parseInt(word[4]), word[5], word[6], word[7]);
		cg++;
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"6");
        }
        
        try{
            cgift = 0;
            File fgifts = new File("gifts_file.csv");
            BufferedReader br = new BufferedReader(new FileReader(fgifts));
            String str1 = null;
            while((str1 = br.readLine()) != null){
                String[] word = str1.split(",");
                Gift[cgift] = new gifts(word[0], Integer.parseInt(word[1]), Integer.parseInt(word[2]));
		cgift++;
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage()+"6");
        }
        for(i = 0; i < cg; i++){
            //System.out.println("name = "+G[i].name+" attr = "+G[i].attr+" main_cost = "+G[i].maintenance_cost+ " intellect = "+G[i].intellect+" criterion = "+G[i].criterion+" bf = "+G[i].bf+" status = "+G[i].status+" type = "+G[i].type);
            for(j = 0; j < cb; j++){
                //System.out.println("name = "+B[j].name+" attr = "+B[j].attr+" intellect = "+B[j].intellect+" budget = "+B[j].budget+" min_attr = "+B[j].min_attr+" type = "+B[j].type+" gf = "+B[j].gf+" status = "+B[j].status);
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
            BufferedWriter bw1 = new BufferedWriter(new FileWriter("new_couple.csv"));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("breakup_couple.csv"));
            for(i = 0; i < (cg/2); i++){
                if(G[i].status.equals("committed")){
                    String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                    String timeStamp1 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                    str = G[i].name+" broke-up with "+G[i].bf+" on "+timeStamp+" at "+timeStamp1;
                    bw2.write(str+"\r\n");
                    bw2.flush();
                }
            }
            for(i = 0; i < cg; i++){
                if(G[i].status.equals("committed")){
                    String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                    String timeStamp1 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                    str = G[i].name+" is committed to "+G[i].bf+" on "+timeStamp+" at "+timeStamp1;
                    String str1 = G[i].name+" is committed with "+G[i].bf+" on "+timeStamp+" at "+timeStamp1;
                    bw.write(str+ "\r\n");
                    bw1.write(str1+ "\r\n");
                    bw.flush();
                    bw1.flush();
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage()+"7");
        }
    }
}
