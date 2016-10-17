/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Satwik Gupta
 */
public class Crawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            Document doc = Jsoup.connect("https://www.google.com").get();  
            String title = doc.title();  
            System.out.println("title is: " + title);
        }
        catch(Exception ex){
            System.out.println("problem: "+ex);
        }
    }
    
}
