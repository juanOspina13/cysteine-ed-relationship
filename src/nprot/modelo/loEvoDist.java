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
                if(acum > 1)
                {
                    String tmp[] = sCurrentLine.split(",");
                try 
                {
                    String query = "";
                    Float valor = Float.parseFloat(tmp[2]);
                    if(tmp[2].length() > 0 )
                    {
                        query = 
                        "INSERT INTO evolutionary_distance1"
                            + "("
                            + "organism_1,"
                            + "organism_2,"
                            + "evolutionary_distance,"
                            + "comments"
                            + ") VALUES"
                            + "(TRIM('" +
                            tmp[0].toUpperCase() +"'),TRIM('" +
                            tmp[1].toUpperCase() +
                            "'),'"+
                            valor +"','"
                            +tmp[3]
                            + "');";
                            System.out.println(query);
                            if( manejobd.runQuery(query) == 1 ){
                                sbResuVal += "Linea " + acum + ": Exito " + tmp[0] + "-" + tmp[1]+"\n";
                            }else{
                                sbResuVal += "Linea " + acum + ": Fallo" + tmp[0] + "-" + tmp[1]+"\n";
                            
                            };
                            
                        }

                    }
                    catch(Exception e)
                    {
//                        sbResuVal += "Linea " + acum + ": Fallo " + tmp[0] + "-" + tmp[1]+"\n";
//                        System.out.println("Fallo " + tmp[0] + "-" + tmp[1]+"\n");
                        e.printStackTrace();
                    }
                }
                
                acum++;
            }
            
 	} catch (IOException e) 
        {
            System.out.println("falli por fuera");
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
