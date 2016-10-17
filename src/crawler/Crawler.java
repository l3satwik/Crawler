/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.sql.DriverManager;
import java.sql.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Satwik Gupta
 */
public class Crawler {
    
    public static Connection con;
    public static ResultSet rs;
    
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_projects", "root", null);
        } catch (Exception ex) {
            System.out.println("Some problem occured: "+ex);
        }
        try{
            crawlPage("http://www.dituniversity.edu.in/");
        } catch (Exception ex){
            System.out.println("Some problem occured: "+ex);
        }
    }
    
    public static void crawlPage(String URL){
        System.out.println(URL);
        try{
            PreparedStatement ps = con.prepareStatement("select * from crawler where url=?");
            ps.setString(1, URL);
            rs = ps.executeQuery();
            if(rs.next()){
                //do nothing
            }
            else{
                PreparedStatement stmt = con.prepareStatement("insert into crawler(url) values(?)");
                stmt.setString(1, URL);
                stmt.executeUpdate();
                
                Document doc = Jsoup.connect(URL).get();
                
                Elements links = doc.select("a[href]");
//                System.out.println(links);
                    for(Element link: links){
                        if(link.attr("href").contains("dituniversity.edu.in"))
                            crawlPage(link.attr("abs:href"));
                    }
            }
        } catch (Exception ex){
            System.out.println("Problem occured: "+ex);
        }
    }
}
