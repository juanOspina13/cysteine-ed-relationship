package nprot.modelo;
import NProt.accesoDatos.ManejoBD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
public class loEvoDist {
    String  sbSrcPath = "";
    String sbResuVal = "";
    public loEvoDist(String sbSrcPath) 
    {
        this.sbSrcPath = sbSrcPath;
        fnLoadFil();
    }
    
    /*
    * Carga el archivo en la BD
    TODO: revisar porque no concatena el error afuera
    */

    public void fnLoadFil()
    {
        
        try
        {
            ManejoBD manejobd = new ManejoBD();
            manejobd.inEvoDist();
           
        } catch (Exception e) 
        {
        }
 
	try {
            ManejoBD manejobd=new ManejoBD();
            String sCurrentLine;
            BufferedReader br = new BufferedReader(
                    new FileReader(sbSrcPath)
            );
            int acum = 1 ;
            while ((sCurrentLine = br.readLine()) != null) 
            {
                String tmp[] = sCurrentLine.split(",");
                try 
                {
                    String query = "";
                    Float valor = Float.parseFloat(tmp[2]);
                    if(tmp[2].length() > 1 )
                    {
                        query = 
                        "INSERT INTO evolutionary_distance"
                            + "("
                            + "organism_1,"
                            + "organism_2,"
                            + "evolutionary_distance,"
                            + "comments"
                            + ") VALUES"
                            + "(TRIM('" +
                            tmp[0].toUpperCase() +"'),'" +
                            tmp[1].toUpperCase() +
                            "','"+
                            valor +"','"
                            +tmp[3]
                            + "');";
                        manejobd.runQuery(query);
                        sbResuVal += "Linea " + acum + ": Exito " + tmp[0] + "-" + tmp[1]+"\n";
                    }
                        
                }
                catch(Exception e)
                {
                    sbResuVal += "Linea " + acum + ": Fallo " + tmp[0] + "-" + tmp[1]+"\n";
                    System.out.println("fallo");
                }
                acum++;
            }
            
 	} catch (IOException e) 
        {
        }     
    }
    /*
    * esto es por si se implementa la funcion d cargar un folder
    */
    public static void listFilesForFolder(final File folder) 
    {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    public String getSbResuVal() {
        return sbResuVal;
    }
    
}
