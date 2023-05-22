import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ProdutosDAO {
    
    private conectaDAO conn = new conectaDAO();
    
    public boolean cadastraProduto (ProdutosDTO p){
        
        PreparedStatement stmt = null;
        String sql = "INSERT INTO uc11.produtos VALUES (?,?,?,?)";
        
        try{
            stmt = conn.connectDB().prepareStatement(sql);
            
            stmt.setNull(1, 0);
            stmt.setString(2, p.getNome());
            stmt.setInt(3, p.getValor());
            stmt.setString(4, p.getStatus());
            
            stmt.execute();
            
            return true;
            
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Dados!");
            System.out.println(sqle.getMessage());
            return false;
            
        }
        finally{
            conn.disconnectDB();
        }   
    }

    public ArrayList<ProdutosDTO> listaProdutos(){
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        String sql = "SELECT * FROM produtos";
        
        try{
           PreparedStatement stmt = conn.connectDB().prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               
               ProdutosDTO p = new ProdutosDTO();
               
               p.setId(rs.getInt("id"));
               p.setNome(rs.getString("nome"));
               p.setValor(rs.getInt("valor"));
               p.setStatus(rs.getString("status"));
               
               listagem.add(p);
               
           }
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Dados!");
            System.out.println(sqle.getMessage());  
        }finally{
            conn.disconnectDB();
        }
        
        return listagem;
    }   
    
    public boolean vendeProduto(int id){
        boolean statusVenda = false;
        
        PreparedStatement stmt = null;
        
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        try{
         
            stmt = conn.connectDB().prepareStatement(sql);
        
            stmt.setString(1, "Vendido");
            stmt.setInt(2, id);
            stmt.execute();
            
            return statusVenda = true; 
        }catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Dados!\n"
                                                           + "Por favor, verificar os dados que queira alterar e tentar Novamente.");
            System.out.println(sqle.getMessage());
            return false;
        }finally{
         conn.disconnectDB();
        }
    }
    
    public ArrayList<ProdutosDTO> listaProdutosVendidos(){
        
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        
        PreparedStatement stmt = null;
        
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try{
         
           stmt = conn.connectDB().prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while(rs.next()){
               
               ProdutosDTO p = new ProdutosDTO();
               
               p.setId(rs.getInt("id"));
               p.setNome(rs.getString("nome"));
               p.setValor(rs.getInt("valor"));
               p.setStatus(rs.getString("status"));
               
               listagem.add(p);  
           }
           
        }catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Dados!\n");
            System.out.println(sqle.getMessage());
        }finally{
         conn.disconnectDB();
        }
        
        return listagem;
    }
}

