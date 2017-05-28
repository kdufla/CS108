import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kdufla on 5/27/17.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void doPost ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        AccountManager acc= ( AccountManager ) getServletContext ().getAttribute ( "data" );

        if(acc.inDataBase ( request.getParameter ( "username" ), request.getParameter ( "password" ) )){
            response.sendRedirect ( "Welcome.jsp" );
        }else{
            response.sendRedirect ( "IncorrectInfo.jsp" );
        }
    }

    protected void doGet ( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
