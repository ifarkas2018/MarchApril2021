/*
 * autor   : Ingrid Farkaš¡
 * projekat: Ptica
 * ConnectionManager.java: povezivanje sa bazom
 */
package konekcija;

import java.sql.*;

public class ConnectionManager {
    static Connection con;
    static String url;
            
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // String url = "jdbc:mysql://localhost:3305/knjige?useSSL=false"; // !!!!!!!!!! useUnicode=yes&characterEncoding=UTF-8&
            /* locally
            String url = "jdbc:mysql://localhost:3305/knjige"; // default PORT 3306
        	String korime="root";
        	String lozinka = "bird&2018";
            */
            
            String url = "jdbc:mysql://inet00.de:3306/k4050695_knjige"; // default PORT 3306
        	String korime="ifarkas2018@gmail.com";
        	String lozinka = "Donau2021%%";
        	
            /* on heliohost
        	String url = "jdbc:mysql://localhost:3306/ifarkas2_knjige"; // default PORT 3306
        	String korime = "ifarkas2";
        	String lozinka = "Apple2910";
            */
            
            try { 
               // povezivanje sa bazom, sa korisniÄ�kim imenom: "root" i lozinkom: "bird&2018" 
               con = DriverManager.getConnection(url, korime, lozinka);
            }
            
            catch (SQLException ex) {
               ex.printStackTrace();
               
            }
        }

        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        return con;
    }
}
    

