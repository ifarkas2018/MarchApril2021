/*
 * autor   : Ingrid Farkaš¡
 * projekat: Ptica
 * DodajDAO.java : izvrÅ¡avanje SQL upita (koristi se u DodajServlet.java)
 */
package dodaj;

import konekcija.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */

public class DodajDAO {
    static Connection con;
    static ResultSet rs = null;  // objekat gde ae Ä�uvaju rezultati upita
    
    // izdPostoji: proverava da li slog sa unetim unetim imenom izdavaÄ�a i gradom postoji. Ako ne postoji tada dodaje novi slog
    // ako je doÅ¡lo do izuzetka onda vraÄ‡a TRUE inaÄ�e vraÄ‡a FALSE
    public static boolean izdPostoji(String izdavac) {
        boolean izuzetak = false; // da li doÅ¡lo do izuzetka
        Statement stmt;
        
        try {
            stmt = con.createStatement();
            rs = null;
       
            // Prvo proveravam da li u tabeli IZDAVAÄŒ slog sa unetim IMENOM IZDAVAÄŒA i GRADOM postoji
            // Da bih to uradila prvo izvrÅ¡avam select upit u tabeli izdavaÄ� da bih proverila da li slog sa unetim imenom izdavaÄ�a i gradom postoji
            // Ako select ne vrati slog tada treba da izvrÅ¡im insert
            // 1. select upit
            String rs_upit = ""; 
          
            if (!((izdavac.equalsIgnoreCase("")))) {
                PreparedStatement preparedStmt;
                // proveri da li u bazi postoji samo ime izdavaÄ�a bez grada
                rs_upit = "select br_izdavaÄ�a from izdavaÄ� where (ime_izdavaÄ�a = '" + izdavac + "');";
                rs = stmt.executeQuery(rs_upit);
                if (!rs.next()) {
                    // 2. insert upit
                    String upit = "insert into izdavaÄ�(ime_izdavaÄ�a) values ('" + izdavac + "');";
                    preparedStmt = con.prepareStatement(upit);
                    preparedStmt.execute();
                }
            }
            izuzetak = false; // nije doÅ¡lo do izuzetka
        } catch (SQLException e) {
            izuzetak = true; // doÅ¡lo je do izuzetka
        }
        return izuzetak; // vraÄ‡a da li je doÅ¡lo do izuzetka
    }
   
    // autPostoji: proverava da li u tabeli AUTOR slog sa unetim IMENOM AUTORA postoji. Ako slog ne postoji tada dodaje slog.
    // ako je doÅ¡lo do izuzetka vraÄ‡a TRUE inaÄ�e vraÄ‡a FALSE 
    public static boolean autPostoji(String autor) {
        boolean izuzetak = false; // doÅ¡lo je do izuzetka
        Statement stmt;
        
        try {
            String rs_upit = ""; 
            boolean prazno_polje = false; // da li je input polje prazno
            
            // tabela autor
            // Prvo proveravam da li u tabeli AUTOR slog sa unesenim IMENOM AUTORA postoji
            // Da bih to uradila ja prvo izvrÅ¡avam select u tabeli autor da proverim da li slog sa unetim imenom autora postoji.
            // Ako select nije vratio ni jedan slog tada izvrÅ¡avam insert
            // 1. select upit
            stmt = con.createStatement(); 
            rs_upit = "";
            rs = null;
            prazno_polje = false; // korisnik nije uneo ime autora u input polje
            // ako korisnik nije uneo ime autora tada niÅ¡ta nije potrebno da se uradi, inaÄ�e dodaj vrednosti u bazu
            if (autor.equalsIgnoreCase(""))
                prazno_polje = true; 
            if (!(prazno_polje)) {
                rs_upit = "select ime_autora from autor where (ime_autora = '" + autor + "');";
                rs = stmt.executeQuery(rs_upit);
                PreparedStatement preparedStmt;
                
                // 2. upit insert - sluÄ�aj kada autor sa tim imenom ne postoji u tabeli autor
                if (!rs.next()) {
                    String upit = "insert into autor (ime_autora) values ('" + autor + "');";
                    preparedStmt = con.prepareStatement(upit);
                    preparedStmt.execute();
                }
            }
            izuzetak = false; // nije doÅ¡lo do izuzetka
        } catch (SQLException e) {
            izuzetak = true; // doÅ¡lo je do izuzetka
        }
        return izuzetak; // vraÄ‡a da li je doÅ¡lo do izuzetka
    }
    
    // dodNovuKnj: dodaje novu knjigu u tebelu knjiga (vraÄ‡a String na osnovu koga gr_uspeh.jsp prikazuje poruku)
    // poziva se iz DodajServlet.java, metod doPost
    public static String dodNovuKnj(HttpSession hSesija, String naslov, String autor, String izdavac, String isbn, String cena, String strane, String zanr, //
                                    String opis, String gd_izdav) {
        String povratniStr = ""; // String koji ovaj metod vraÄ‡a 
        // objekati koji se koriste za pristup bazi
        Statement stmt = null;  
        PreparedStatement preparedStmt = null;
        rs = null;
        boolean izuzetak; // da li je doÅ¡lo do izuzetka priliko pristupa bazi
        
        try {
            con = ConnectionManager.getConnection(); // povezivanje sa bazom
            stmt = con.createStatement();
            if (cena.equalsIgnoreCase("")) {
                cena = "0.00";
            }
        
            String rs_upit = ""; 
            boolean prazno_polje = false;
            
            // izdPostoji: proverava da li slog sa unetim unetim imenom izdavaÄ�a postoji. Ako ne postoji tada dodaje nov slog
            // ako je doÅ¡lo do izuzetka onda vraÄ‡a TRUE inaÄ�e vraÄ‡a FALSE
            izuzetak = izdPostoji(izdavac);
            if (izuzetak)
                povratniStr = "GR_DODAJ";
            else {    
                // autPostoji: proverava da li u tabeli AUTOR slog sa unetim IMENOM AUTORA postoji. Ako slog ne postoji tada dodaje slog.
                // ako je doÅ¡lo do izuzetka vraÄ‡a TRUE inaÄ�e vraÄ‡a FALSE 
                izuzetak = autPostoji(autor);
                if (izuzetak)
                    povratniStr = "GR_DODAJ";
                else {
                    // da li knjiga sa tim naslovom postoji u tabeli KNJIGA
                    boolean isbn_postoji = false; // da li knjiga sa tim NASLOVOM ili ISBN-om veÄ‡ postoji u bazi

                    if (!isbn_postoji) {
                        rs_upit = "select isbn from knjiga where (isbn = '" + isbn + "');";
                        rs = stmt.executeQuery(rs_upit);
                        if (rs.next()) {
                            isbn_postoji = true;
                            povratniStr = "GR_NOVA_POST"; // rezultat dodavanja knjige u bazu
                        }
                    }
                    
                    // da li je korisnik uneo ime izdavaÄ�a i ime autora i (naslov knjige ILI isbn) tada dodaj (insert) unesene vrednsti u bazu
                    if ((!isbn_postoji) && ((!(izdavac.equalsIgnoreCase(""))) && (!(autor.equalsIgnoreCase("")))) && ((!(naslov.equalsIgnoreCase(""))) || (!(isbn.equalsIgnoreCase(""))))) {
                        String upit = "insert into knjiga (br_autora, br_izdavaÄ�a";
                        if (!(naslov.equalsIgnoreCase(""))) {
                            upit += ", naslov"; // dodaj "naslov" listi kolona   
                        }
                        if (!(isbn.equalsIgnoreCase(""))) {
                            upit += ", isbn"; // dodaj "isbn" listi kolona
                        }
                        if (!(cena.equalsIgnoreCase(""))) {
                            upit += ", cena"; // dodaj "cena" listi kolona
                        }
                        if (!(strane.equalsIgnoreCase(""))) {
                            upit += ", br_strana"; // dodaj "br_strana" listi kolona
                        }
                        if (!(zanr.equalsIgnoreCase(""))) {
                            upit += ", Å¾anr"; // dodaj "Å¾anr" listi kolona
                        }
                        if (!(opis.equalsIgnoreCase(""))) {
                            upit += ", opis"; // dodaj "opis" listi kolona
                        }
                        if (!(gd_izdav.equalsIgnoreCase(""))) {
                            upit += ", god_izdavanja"; // dodaj "god_izdavanja" listi kolona
                        }
                        
                        upit += ") values ((select br_autora from autor where ime_autora='" + autor + "'),"; // naÄ‘i br_autora za autora
                        upit += " (select br_izdavaÄ�a from izdavaÄ� where (ime_izdavaÄ�a='" + izdavac + "') "; // naÄ‘i br_izdavaÄ�a za izdavaÄ�a
                        upit += "), ";
                        if (!(naslov.equalsIgnoreCase(""))) {
                            upit += "'" + naslov + "'"; // dodaj naslov upitu
                        }
                        if ((!(naslov.equalsIgnoreCase(""))) && (!(isbn.equalsIgnoreCase("")))) {
                            upit +=  ", ";
                        }
                        if (!(isbn.equalsIgnoreCase(""))) {
                            upit += "'" + isbn + "'"; // dodaj isbn upitu 
                        }
                        if (!(cena.equalsIgnoreCase(""))) {
                            upit += ", '" + cena + "'"; // dodaj cenu upitu
                        }
                        if (!(strane.equalsIgnoreCase(""))) {
                            upit += ", '" + strane + "'"; // dodaj broj stranica upitu
                        }
                        if (!(zanr.equalsIgnoreCase(""))) {
                            upit += ", '" + zanr + "'"; // dodaj Å¾anr upitu
                        }
                        if (!(opis.equalsIgnoreCase(""))) {
                            upit += ", '" + opis + "'"; // dodaj opis knjige upitu
                        }
                        if (!(gd_izdav.equalsIgnoreCase(""))) {
                            upit += ", '" + gd_izdav + "'"; // dodaj godinu izdavanja upitu
                        }
                        upit += ");";
                        
                        preparedStmt = con.prepareStatement(upit);
                        preparedStmt.execute(); // izvrÅ¡i upit
                        
                        // PrikaÅ¾i veb stranicu sa porukom da je knjiga uspeÅ¡no dodata bazi
                        povratniStr = "USPEH_DOD"; // rezultat dodavanja knjige bazi
                    } 
                } // kraj else (od if (excOccured))
            } // kraj else (od if (excOccured))
        } catch (SQLException e) {
            povratniStr = "GR_DODAJ";
        }
        
        // rukovanje izuzetkom
        finally {
            if (con != null) {
                try {
                    con.close(); // zatvaranje objekta Connection
                } catch (Exception e) {
                    System.out.print("Izuzetak: ");
                    System.out.println(e.getMessage());
                }
                con = null;
            }
            
            if (rs != null) {
                try {
                    rs.close(); // zatvaranje objekta RecordSet
                } catch (Exception e) {
                    System.out.print("Izuzetak: ");
                    System.out.println(e.getMessage());
                }
                rs = null;
            }
	
            if (stmt != null) {
                try {
                    stmt.close(); // zatvaranje objekta Statement
                } catch (Exception e) {
                    System.out.print("Izuuzetak: ");
                    System.out.println(e.getMessage());
                }
                stmt = null;
            }
            
            if (preparedStmt != null) {
                try {
                    preparedStmt.close(); // zatvaranje objekta Statement
                } catch (Exception e) {
                    System.out.print("Izuuzetak: ");
                    System.out.println(e.getMessage());
                }
                preparedStmt = null;
            }
        }
        return povratniStr;
    }
}
