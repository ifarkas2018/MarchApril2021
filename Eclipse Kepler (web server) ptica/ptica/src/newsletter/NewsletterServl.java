/*
 * autor    : Ingrid FarkaÅ¡
 * projekat : Ptica
 * NewsletterServl.java : posle klika na dugme Prijavite se (footer.jsp) poziva se ovaj servlet
 */
package newsletter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import razno.PticaMetodi;

public class NewsletterServl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ptica - Subscribe</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsletterServl at " + request.getContextPath() + "</h1>");
            out.println("Subscribe Servlet");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    
    public void procitajKolacice(HttpServletRequest request){
        Cookie[] kolacici = request.getCookies();
        
        for (Cookie kolacic : kolacici) {
            // postavljanje vrednosti varijabli sesije na "" za varijable sa imenom input0, input1, ...
            String ime_kolacic = kolacic.getName();
            String value = kolacic.getValue(); // vrednost kolaÄ�iÄ‡a
            value = value + " ";      
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String valid_email = "false"; // da li je to vaÅ¾eÄ‡i email 
        String vr_kolacic = ""; // vrednost kolaÄ�iÄ‡a
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {	    
            HttpSession hSesija2 = PticaMetodi.vratiSesiju(request);
            // ako ne postoji kolaÄ�iÄ‡ Ä�ije ime poÄ�inje sa "input" tada nema podataka kojima treba da se popuni formular
            hSesija2.setAttribute("popunjeno", "false");
            
            // Ä�itanje teksta iz newsl_email (footer.jsp) - email adresa za Newsletter
            String newslEmail = request.getParameter("newsl_email"); // tekst u input kontroli newsl_email (footer.jsp)
            newslEmail = PticaMetodi.dekoder(newslEmail); // moÅ¾e da sadrÅ¾i Ä‡Ä†Ä�ÄŒÅ¡Å Å¾Å½Ä‘Ä�
            String imeVebStr = "";
            if (PticaMetodi.varSesijePostoji(hSesija2, "ime_vebstr") ) {
                imeVebStr = String.valueOf(hSesija2.getAttribute("ime_vebstr"));
            }
           
            // ako je pre unoÅ¡enja email-a jedna od veb stranica (index.jsp, contact_page.jsp, onama_str.jsp) bila prikazana nije potrebno
            // da Ä�itam kolaÄ�iÄ‡e koji sadrÅ¾e vrednosti input polja i da ih Ä�uvam u varijablama sesije 
            if ((!(imeVebStr.equalsIgnoreCase("index.jsp"))) && (!(imeVebStr.equalsIgnoreCase("contact_page.jsp"))) && 
                    (!(imeVebStr.equalsIgnoreCase("onama_str.jsp"))) && (!(imeVebStr.equalsIgnoreCase("")))) {
                Cookie[] kolacici = request.getCookies();
                boolean prvi_put = false; // da li je prvi kolaÄ�iÄ‡ Ä�ije ime poÄ�inje sa "input" 

                // prolazim kroz kolaÄ�iÄ‡e
                for (Cookie kolacic : kolacici) {
                    // postavljanje vrednosti varijabli sesije na "" za varijable sa imenom input0, input1, ...
                    String ime_kolacic = kolacic.getName();
                    // da li kolaÄ�iÄ‡ koji sam proÄ�itala sadrÅ¾i tekst koji je bio u jednom od input polja
                    boolean je_input = ime_kolacic.startsWith("input", 0); 

                    if (je_input) {
                        // ako je ovo prvi kolaÄ�iÄ‡ Ä�ije ime poÄ�inje sa "input" postavi varijablu popunjeno na true
                        // kada uÄ�itam stranicu ako je popunjeno postavljen na true tada obrazac treba da se popuni sa vrednostima iz 
                        // varijabli sesije
                        if (!prvi_put) {
                            prvi_put = true;
                            hSesija2.setAttribute("popunjeno", "true");
                        }
                        vr_kolacic = kolacic.getValue();
                        
                        // dodajem ovu vrednost sesiji zbog prijave na NEWSLETTER
                        // IDEJA prijave na NEWSLETTER: posle unosa email adrese i klika na dugme (prijava na Newsletter) korisnik se uspeÅ¡no 
                        // prijavio na Newsletter - posle toga kada korisnik klikne na Zatvori dugme veb stranica koja je bila prethodno prikazana
                        // treba da se popuni sa vrednostima - ove vrednosti su vrednosti koje treba da zapamtim u sesiji
                        hSesija2.setAttribute(ime_kolacic, vr_kolacic);
                    }
                }  
            }
            
            String izuzetak = "false";
            
            Cookie[] kolacici = request.getCookies();
            // prolazim kroz kolaÄ�iÄ‡e
            for (Cookie kolacic : kolacici) {
                String ime_kolacic = kolacic.getName();
                if (ime_kolacic.equalsIgnoreCase("valid_email")){
                    // da li je to vaÅ¾eÄ‡i email 
                    valid_email = kolacic.getValue();
                    // formiram varijablu sesije sa imenom ime_kolacic koja sadrÅ¾i vrednost valid_email 
                    hSesija2.setAttribute(ime_kolacic, valid_email);
                }
            }
            
            // metod addEmail dodaje emajl za Newsletter u tabelu subscription
            // vraÄ‡a TRUE ako je doÅ¡lo do izuzetka inaÄ�e vraÄ‡a FALSE 
            if (valid_email.equalsIgnoreCase("true")) {
                izuzetak = NewsletterDAO.addEmail(newslEmail);
            }
            hSesija2.setAttribute("baza_izuzetak", izuzetak);
            procitajKolacice(request);
            // prikaÅ¾i veb stranicu newsletter_str.jsp
            // @@@@ response.sendRedirect("newsletter_str.jsp");
            request.getRequestDispatcher("newsletter_str.jsp").forward(request, response);
        } catch (Exception e){ // Throwable
            System.out.print("Izuzetak: ");
            System.out.println(e.getMessage());
        }
    }

    /**.
     * Returns a short description of the servlet     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
