package DBRPC.DBQuery;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       // System.out.println( "Hello World!" );
        DBExecute ss=new DBExecute();
        ReturnResult r=  ss.querySql("SELECT *  from  \"Test\"\r\n" + 
                "");
        System.out.println(r.rows);
    }
}
