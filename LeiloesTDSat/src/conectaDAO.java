
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/uc11";
    private final String USER = "root";
    private final String PASS = "123456789";
    
    Connection conn = null;
    
    public Connection connectDB(){

        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexao Realizada");
        } catch (ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
        }
        return conn;
    }
    
    public void disconnectDB(){
        try{
            conn.close();
            System.out.println("Desconectado");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
        }
    
    }
}
