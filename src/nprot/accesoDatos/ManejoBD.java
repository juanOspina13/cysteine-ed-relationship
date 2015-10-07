 
package NProt.accesoDatos;
 
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import javax.swing.JOptionPane;
 
public class ManejoBD {
    accesoDatos fachada;
    public ManejoBD(){
        fachada= new accesoDatos();
    }
    /*
    * Insert the evolutionary distances from a plain file into the BD ??
    */
    public void inEvoDist() throws IOException
    {
        String acumulador="";
	FileWriter fWriter = null;
        BufferedWriter writer = null;
        LinkedList <String[]> organisms;
        organisms = getTopOrganisms(91);
        for(int i=0;i<91;i++){
        //   System.out.println(organisms.get(i)[0]);
        }
        fWriter = new FileWriter("plano");
        writer = new BufferedWriter(fWriter);
        //WRITES 1 LINE TO FILE AND CHANGES LINE
        writer.write("This line is written");
        writer.newLine();
        writer.close();
    }
    /*
    * Funcion que ejecuta un query, copiada de loEvoDist
    */
    
    public int runQuery(String query){
        int numFilas=0;
        try{
        Connection conn= fachada.conectar();
        Statement sentencia = conn.createStatement();
        conn.close();
       }catch(SQLException e){ 
        System.out.println(e); 
       }
       try{
            Connection conn= fachada.conectar();
            Statement sentencia = conn.createStatement();

          numFilas = sentencia.executeUpdate(query);
            conn.close();
            return numFilas;
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return -1;
    }//fin 
    
    /*
    *   Funcion que devuelve los n primeros datos ordenados 
    *   por porcentaje de cisteinas
    */
    
    public LinkedList<String[]>getEDResults(String organismName,String top)
    {
        LinkedList<String[]> total_result=new LinkedList();
        String query =
                "SELECT * "+ 
                "FROM evolutionary_distance "+
                "WHERE organism_1 like '" +
                organismName + 
                "' OR organism_2 like '" +
                organismName +
                "' ORDER BY CAST(evolutionary_distance AS float)  " +
                " LIMIT "+
                top ;
        System.out.println(query);
        //CAST(evolutionary_distance AS float) System.out.println(query);
        try
        {
            Connection conn = fachada.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String[] result=new String[4];
                result[0]=rs.getString("organism_1");
                result[1]=rs.getString("organism_2");
                result[2]=rs.getString("evolutionary_distance");   
                total_result.add(result);
            }
        }catch(Exception e){
        }
        return total_result;
    }
    
    /*
    *   Funcion que devuelve los n primeros datos ordenados 
    *   por porcentaje de cisteinas
    */
    
    public LinkedList<String[]>getCisDifference(String organismName,String top)
    {
        Float nuOrgaVal = null ;
        Float nuTmpValu = null;
        LinkedList<String[]> total_result=new LinkedList();
        String sbOrgaVal =
                "SELECT * "+ 
                "FROM organisms "+
                "WHERE COMPLETE_NAME LIKE '"+
                organismName + "'";
        
        
        
        //CAST(evolutionary_distance AS float) System.out.println(query);
        try
        {
            Connection conn= fachada.conectar();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sbOrgaVal);
            while(rs.next())
            {
                nuOrgaVal = Float.parseFloat( rs.getString("aminos_vs_cyst") );
            }
        }catch(Exception e)
        {
        }
        
        /*
        String sbOtheVal =
                "SELECT complete_name ,"+ 
                " ABS( CAST(aminos_vs_cyst AS float)- "+ 
                nuOrgaVal +
                ") as result FROM organisms "+
                "WHERE COMPLETE_NAME NOT LIKE '" +
                organismName + 
                "' ORDER BY ABS ( CAST(aminos_vs_cyst AS float)-" +
                nuOrgaVal +
                ") LIMIT "+
                top ;
        */
        String sbOtheVal =
                "SELECT complete_name , "+
                nuOrgaVal +
                "-CAST(aminos_vs_cyst AS float) "+ 
                " as result FROM organisms "+
                "WHERE COMPLETE_NAME NOT LIKE '" +
                organismName + 
                "' ORDER BY "+
                nuOrgaVal+
                "-CAST(aminos_vs_cyst AS float)" +
                " LIMIT "+
                top ;
        
        System.out.println(sbOtheVal);
        try
        {
            Connection conn= fachada.conectar();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sbOtheVal);
            while(rs.next())
            {
                String[] result = new String[4];
                result[0]=rs.getString("complete_name");
                nuTmpValu = Float.parseFloat(rs.getString("result"));
                //Float nuResuVal = Math.abs(nuTmpValu);
                Float nuResuVal = nuTmpValu;
                result[1] = ""+nuResuVal+"";
                total_result.add(result);
            }
        }catch(Exception e){
        }
        return total_result;
    }
    
    
    /*
    *   Funcion que devuelve los n primeros datos ordenados 
    *   por porcentaje de cisteinas
    */
    
    public LinkedList<String[]>getTopOrganisms(int top){
        LinkedList<String[]> total_result=new LinkedList();
        String query=
                "SELECT * "+ 
                "FROM organisms "+
                "ORDER BY aminos_vs_cyst DESC "
                + "LIMIT "+
                top;
        try
        {
            Connection conn= fachada.conectar();
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next()){
                String[] result=new String[4];
                result[0]=rs.getString("complete_name");
                result[1]=rs.getString("aminoacid_count");
                result[2]=rs.getString("cysteine_count");
                result[3]=rs.getString("aminos_vs_cyst");
                total_result.add(result);
            }
        }catch(Exception e){
        }
        return total_result;
    }
    /*
    *   Funcion que guarda la secuencia en la base de datos
    */
    public int saveSequence(String[] result){
       String sql_guardar;
       int numFilas=0;
       String sql_borrar="DELETE FROM organisms WHERE complete_name='" +result[5]+ "' ";
       try{
        Connection conn= fachada.conectar();
        Statement sentencia = conn.createStatement();
        numFilas = sentencia.executeUpdate(sql_borrar);
        conn.close();
       }catch(SQLException e){ 
        System.out.println(e); 
       }
       sql_guardar = "INSERT INTO organisms VALUES ('" +
               result[0] + "', '" + 
               result[1] + "', '" +
               result[2]+ "', '" + 
               result[3]+ "', UPPER('" + 
               result[4] + "'), '" +
               result[5]+ "', '" + 
               result[6]  + "')";
       try{
            Connection conn= fachada.conectar();
            Statement sentencia = conn.createStatement();

          numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return -1;
    }//fin guardar
 

    public void modificarPrograma( int codigoPrograma){

    }
    public void borrarPrograma(int codigoPrograma){
    }


}//fin clase



