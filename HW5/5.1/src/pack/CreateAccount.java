package pack;

import pack.AccountManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CreateAccServlet", urlPatterns = {"/CreateAccServlet"})
public class CreateAccount extends HttpServlet {
    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AccountManager acc= ( AccountManager ) getServletContext ().getAttribute ( "data" );
        response.setContentType("text/html");
        PrintWriter out=response.getWriter ();
        if(acc.createUser ( request.getParameter ( "username" ), request.getParameter ( "password" ) )){

            String bla="<html><head>" +
                    "<title>Welcome "+ request.getParameter("username")+"</title></head><body>" +
                    "<h1>Welcome "+request.getParameter("username")+"</h1></body></html>";
            out.print ( bla );
            //response.sendRedirect ( "Welcome.jsp" );
        }else{
            String bla="<html>\n" +
                    "<head>\n" +
                    "    <title>Create Account</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>The name "+request.getParameter("username")+"is Already In Use</h1>\n" +
                    "<!-- https://howtoprogramwithjava.com/html-forms/ -->\n" +
                    "<form action=\"CreateAccServlet\" method=\"POST\">\n" +
                    "    Username: <input type=\"text\" name=\"username\" /><br/>\n" +
                    "    Password: <input type=\"password\" name=\"password\" /><br/>\n" +
                    "    <input type=\"submit\" value=\"Submit\"/>\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "</html>";
            out.print ( bla );
            //response.sendRedirect ( "CreateAccFail.jsp" );
        }


    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
