package pack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class Login extends HttpServlet {

    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AccountManager acc= ( AccountManager ) getServletContext ().getAttribute ( "data" );
        response.setContentType("text/html");
        PrintWriter out=response.getWriter ();

        if(acc.inDataBase ( request.getParameter ( "username" ), request.getParameter ( "password" ) )){
            String bla="<html><head>" +
                    "<title>Welcome "+ request.getParameter("username")+"</title></head><body>" +
                    "<h1>Welcome "+request.getParameter("username")+"</h1></body></html>";
            out.print ( bla );

            //response.sendRedirect ( "Welcome.jsp" );
        }else{
            String bla="<html>\n" +
                    "<head>\n" +
                    "    <title>Information Incorrect</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Please Try Again</h1>\n" +
                    "<h2>Either your username or password is incorrect. Please try again.</h2>\n" +
                    "<!-- https://howtoprogramwithjava.com/html-forms/ -->\n" +
                    "<form action=\"LoginServlet\" method=\"POST\">\n" +
                    "    Username: <input type=\"text\" name=\"username\" /><br/>\n" +
                    "    Password: <input type=\"password\" name=\"password\" /><br/>\n" +
                    "    <input type=\"submit\" value=\"Submit\"/>\n" +
                    "</form>\n" +
                    "<a href = \"CreateAcc.jsp\">Create account</a>\n" +
            request.getParameter ( "username" )+request.getParameter ( "password" )+
                    "</body>\n" +
                    "</html>\n";
            out.print ( bla );

            //response.sendRedirect ( "IncorrectInfo.jsp" );
        }
    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}