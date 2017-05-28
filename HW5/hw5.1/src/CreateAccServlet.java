import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateAccServlet", urlPatterns = {"/CreateAccServlet"})
public class CreateAccServlet extends HttpServlet {
    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AccountManager acc= ( AccountManager ) getServletContext ().getAttribute ( "data" );

        if(acc.createUser ( request.getParameter ( "username" ), request.getParameter ( "password" ) )){
            response.sendRedirect ( "Welcome.jsp" );
        }else{
            response.sendRedirect ( "CreateAccFail.jsp" );
        }


    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
