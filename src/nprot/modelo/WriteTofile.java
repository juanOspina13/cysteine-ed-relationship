package nprot.modelo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
 
public class WriteTofile {
    void writeResult(
            String organismName,
            int topProtein,
            int proteinNumber,
            int windowSize,
            HashMap results,
            LinkedList<String> possibleCombinations,
            int totalCombi
    ){
        FileWriter outFile;
        try {
            
            outFile = new FileWriter("nplets_count/"+
                    organismName.subSequence(0, 12)+
                    windowSize +
                    "_results.csv"
            );
            PrintWriter out = new PrintWriter(outFile);
            
            out.println( 
                    "Organism="+
                    organismName+
                    " PROTEIN NUMBER="+
                    proteinNumber+
                    " WINDOW SIZE="+
                    windowSize+
                    " TOTAL COMBINATIONS="+
                    totalCombi
            );
            out.println( 
                    "Organism, Total , percentage"
            );
            if( windowSize == 1 ||  windowSize == 2 ||  windowSize == 3 )
            {
                for(String combination:possibleCombinations){
                    
                    out.println(
                            combination+
                            ","+
                            results.get(combination)+ ","+
                            ((Integer)results.get(combination)/(float)totalCombi)*100
                    );
                }
            }else{
                for(String combination:possibleCombinations){
                    out.println(
                            combination+
                            "->"+
                            results.get(combination)+ " "+
                            ((Integer)results.get(combination)/(float)totalCombi)*100
                    );
                }
            }    
            out.println("END");
            out.close();        
            
            
        } catch (IOException ex) {
            Logger.getLogger(WriteTofile.class.getName()).log(Level.SEVERE, null, ex);
        }
 
   }
}