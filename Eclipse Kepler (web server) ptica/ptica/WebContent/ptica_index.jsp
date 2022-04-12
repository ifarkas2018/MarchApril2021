<%-- 
    Dokument   : ptica_knj se poziva kada korisnik klikne na jednu od knjiga na starnici index_knjige
    Formiran   : 21-Sep-2019, 20:48:11
    Autor      : Ingrid Farkaš
    Projekat   : Ptica
--%>

<%@ page import="java.sql.Connection" %>
<%@ page import="konekcija.ConnectionManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="razno.PticaMetodi" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/css_pravila.css" type="text/css" rel="stylesheet"/>
        <title>Ptica - Novi naslovi</title>
        <%@ include file="header.jsp" %>
    </head>
    
    <body> 
        <%! 
            // citajOpisKnjige: čita naslov, ime autora, cenu, isbn, opis knjige
            // kada korisnik klikne na jednu od knjiga na starnici index_knige sa indeksom index
            public static ResultSet citajOpisKnjige(int index) throws SQLException {
                Connection con = ConnectionManager.getConnection(); // povezivanje sa bazom 
                Statement stmt = con.createStatement();
                                    
                String upit = "SELECT p.naslov, a.ime_autora, p.uzrast, p.cena, p.isbn, p.opis from ptica_knj p, autor a where a.br_autora = p.br_autora and br_knjige = '" + index + "';";                    
                
                // izvrši upit - rezultat će biti u rs
                ResultSet rs = stmt.executeQuery(upit);
                return rs;
            }
        %>
        
        <%
            HttpSession hSesija = PticaMetodi.vratiSesiju(request);
        	Integer index = 0;
        	Object indexObj = hSesija.getAttribute("indeks_knjige"); 
        	if (indexObj != null ) { 
        		try {
        			index = Integer.parseInt(String.valueOf(indexObj)); 
        			int a = 10/0;
        		} catch (Exception e) {
                    String sPoruka = "GRESKA";
                    String sNaslov = "Greška"; // koristi se za prosleđivanje naslova
                    hSesija.setAttribute("ime_izvora", "Novi naslovi"); // naziv veb strane na kojoj sam sada
                    hSesija.setAttribute("naslov", sNaslov); 
                    hSesija.setAttribute("poruka", sPoruka); 
                    // response.sendRedirect("gr_uspeh.jsp"); // @@@@ preusmeravanje na gr_uspeh.jsp
                    request.getRequestDispatcher("gr_uspeh.jsp").forward(request, response);
                }
            } else {
        		index = 1;
        	}
        	out.println("index=" + index);
        
            hSesija.setAttribute("ime_vebstr", "ptica_knj.jsp");
            // postavljanje vrednosti varijabli sesije na inicijalnu vrednost: ako je korisnik završio prijavu na Newsletter,
            // obrazac na sledećoj veb stranici ne treba da prikaže prethdne vrednosti
            hSesija.setAttribute("newsletter", "false");
            
            // try {  DO NOT DELETE THIS             
       
        %>  
                                
        <div class="belapoz">
            <div class="row"> 
                <div class="col-lg-5 col-md-5"> 
                    <br/><br/>
                    <div> 
                    	<br/><br/>  
                    	some text comes here @@@@@@@@@
                    	<img src="images/vel_slike/vel_homepg_sl_1.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
                    	<% switch(index) {
                    		   case 1: %>
                    		   	   <!-- slika-centar postavlja sliku u sredinu (horizontalno), img-fluid je za responzivan image -->
                    		   	   <img src="images/vel_slike/vel_homepg_sl_1.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
                    			   <% break;
                    		   case 2: %>	  
                    		   	   <img src="images/vel_slike/vel_homepg_sl_2.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
                    			   <% break; 
                    		   case 3: %>	  
                		   	   	   <img src="images/vel_slike/vel_homepg_sl_3.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
                			       <% break;
                    		   case 4: %>	  
            		   	   	       <img src="images/vel_slike/vel_homepg_sl_4.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
            			           <% break;
                    		   case 5: %>	  
        		   	   	           <img src="images/vel_slike/vel_homepg_sl_5.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
        			               <% break;
                    		   case 6: %>	  
    		   	   	               <img src="images/vel_slike/vel_homepg_sl_6.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
    			                   <% break;
                    		   case 7: %>	  
    		   	   	               <img src="images/vel_slike/vel_homepg_sl_7.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
    			                   <% break;
                    		   case 8: %>	  
		   	   	                   <img src="images/vel_slike/vel_homepg_sl_8.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
			                       <% break;
                    		   case 9: %>	  
		   	   	                   <img src="images/vel_slike/vel_homepg_sl_9.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
			                       <% break;
                    		   case 10: %>	  
		   	   	                   <img src="images/vel_slike/vel_homepg_sl_10.jpg" class="img-fluid slika-centar" alt="slika knjige" title="slika knjige">
			                       <% break;    
                    		   default: %>
                    		   	   <img src="images/books.png" class="img-fluid slika-centar" alt="slika sa knjigama" title="slika sa knjigama"> 
                    	<%    
                    	   }   	   
                    	%>
                    </div>
                </div>
                       
                <div class="col-lg-6 col-md-6"> 
                    <div class="container">
                        <div class="row"> <!-- dodavanje novog reda u Bootstrap grid -->
                            <div class="col">
                                &nbsp; &nbsp;
                                <br/>
                                <h3 class="text-info">Novi naslovi</h3><br/>
                                    <%
                                         // čitanje podataka o knjizi i čuva ih u rs
                                        ResultSet rs = citajOpisKnjige(index);

                                        // posle klika na dugme prikazuje se index.jsp
                                        out.println("<form action=\"index.jsp\" method=\"post\">");
                                    
                                        if (!(rs.next())) { // nema podataka o knjizi
                                            out.println("<br /><br /><br />");
                                            out.println("<span class=\"text-warning\">Podaci o ovoj knjizi ne postoje!</span>");
                                            out.println("<br /><br /><br /><br /><br />");
                                        } else {
                                            // prikaži rezultat u neuređenoj listi
                                            out.print("<ul>");
                                            
                                            // čitanje naslova
                                            String naslov = rs.getString("naslov");
                                            // čitanje imena autora
                                            String autor = rs.getString("ime_autora");
                                            // čitanje cene
                                            String cena = rs.getString("cena");
                                            // čitanje uzrasta
                                            String uzrast = rs.getString("uzrast");
                                            // čitanje ISBN
                                            String isbn = rs.getString("isbn");
                                            // prikaži naslov i autor
                                            String opis = rs.getString("opis");
                                            out.print("<li><b>" + naslov + "</b> od (<b>autora</b>) " + autor ); 
                                            
                                            // ako cena postoji prikaži je 
                                            if (cena != null && !cena.equalsIgnoreCase("")){
                                                // u bazi cena je u obliku 99999.99; na obrazcu cena se prikazuje u obliku 99.999,99
                                                cena = cena.replace('.', ','); // zamenjujem decimalnu . sa ,
                                                cena = PticaMetodi.dodajTacku(cena); // dodajem tačku u cenu iza hiljadu dinara 
                                                out.print(" (<b>cena: </b>" + cena + " RSD)" + "<br/>");
                                            }
                                             
                                         	// ako uzrast postoji: prikaži ga
                                            if (uzrast != null && !uzrast.equalsIgnoreCase("")) {
                                                out.print("<br /><b>" + "Uzrast: </b>" + uzrast + "<br/>" );
                                            }
                                            
                                            // ako ISBN postoji: prikaži ga
                                            if (isbn != null && !isbn.equalsIgnoreCase("")) {
                                                out.print("<br /><b>" + "Isbn: </b>" + isbn + "<br/>" );
                                            }
                                                
                                            // ako opis knjige postoji: prikaži ga
                                            if (opis != null && !opis.equalsIgnoreCase("")) {
                                                out.print("<br /><b>" + "Opis: </b>" + opis );
                                            }
                                                
                                            out.print("</li>");
                                            out.print("</ul>");
                                        }
                                        out.print("<br />");
                                        // btn-sm se koristi za manju (užu) veličinu kontrole
                                        out.print("<button type=\"submit\" class=\"btn btn-info btn-sm\">Ptica</button>");
                                        out.println("</form>");
                                    
                                %>
                            </div> <!-- završetak class="col" -->
                        </div> <!-- završetak class="row" --> 
                    </div> <!-- završetak class="container" -->
                </div> <!-- završetak class="col-lg-5 col-md-5" -->
            </div> <!-- završetak class="row" -->
        </div> <!-- završetak class="belapoz" -->
        
        <%
            //} catch(Exception e) {
            	/*
                String sPoruka = "GR_BAZA";
                String sNaslov = "Greška"; // koristi se za prosleđivanje naslova
                hSesija.setAttribute("ime_izvora", "Novi naslovi"); // naziv veb strane na kojoj sam sada
                hSesija.setAttribute("naslov", sNaslov); 
                hSesija.setAttribute("poruka", sPoruka); 
                response.sendRedirect("gr_uspeh.jsp"); // preusmeravanje na gr_uspeh.jsp
                */
            //}
        %>
            
        <!-- dodavanje novog reda u Bootstrap grid; klasa belapoz: postavljanje pozadine u belu boju -->
        <div class="belapoz">
            <div class="col">
                &nbsp; &nbsp;
            </div>
        </div> 
        
        
        <!-- dodavanje novog reda u Bootstrap grid; klasa belapoz: postavljanje pozadine u belu boju -->
        <div class="belapoz">
            <div class="col">
                &nbsp; &nbsp;
            </div>
        </div>  
        <%@ include file="footer.jsp" %>
    </body>
</html>