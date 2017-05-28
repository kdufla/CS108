import java.util.HashMap;

public class AccountManager {

    private HashMap userList;

    public AccountManager(){
        userList=new HashMap ();
        userList.put ( "Patrick", "1234" );
        userList.put ( "Molly", "FloPup" );
    }

    public boolean createUser(String name, String pass){
        if(userList.containsKey (name)) return false;
        userList.put ( name, pass );
        return true;
    }

    public boolean inDataBase(String name, String pass){
        if ( userList.containsKey ( name ) && userList.get ( name )==pass )return true;
        return false;
    }
}