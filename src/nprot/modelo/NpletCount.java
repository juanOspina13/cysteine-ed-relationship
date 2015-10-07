package nprot.modelo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import nprot.vista.organismEDBar;
import org.jfree.ui.RefineryUtilities;

public class NpletCount extends AminosCount 
{
    int totalCombi;
    int windowSize;
    int topProtein;
    File file;
    boolean triplets = true;
    LinkedList <String> liTotProt = new LinkedList<String>();
    HashMap hashmap_for_nplets = new HashMap();
    HashMap hashmap_percentages = new HashMap();
    String organism_name = "";
    LinkedList<String>possibleCombinations = new LinkedList();
        
    public NpletCount(
            int windowSize,
            //int topProtein, 
            File file 
    ){
        if(windowSize != 3){
            triplets = false;
        }
        //this.topProtein = topProtein;
        this.windowSize = windowSize;
        this.file = file;
    }
    
    @Override
    public String[] getInformation(
            File f
    ) throws FileNotFoundException, IOException 
    {
        //GENERATE ALL THE POSIBLE TRIPLETS AND ADD THEM TO A HASH TABLE, WORKS FOR TRIPLETS ONLY
        if( triplets )
        {
             String result="";
            int acum=0;
            for(int i=0; i<lista.size() ; i++)
            {
                for(int j = 0;j<lista.size();j++)
                {
                    for(int k = 0;k<lista.size();k++)
                    {
                        acum++;
                        
                        hashmap_for_nplets.put(
                                lista.get(i).getNomenclature()+
                                lista.get(j).getNomenclature()+
                                lista.get(k).getNomenclature(), 
                                0
                        );
                        
                        possibleCombinations.add(
                                lista.get(i).getNomenclature()+
                                lista.get(j).getNomenclature()+
                                lista.get(k).getNomenclature()
                        );
                    }
                }
            }
            
        } else if(windowSize == 2)
        {
            for(int i=0;i<lista.size();i++)
            {
                for(int j = 0; j < lista.size(); j++)
                {
                    hashmap_for_nplets.put(
                            lista.get(i).getNomenclature()+
                            lista.get(j).getNomenclature(), 
                            0
                    );
                    
                    possibleCombinations.add(
                        lista.get(i).getNomenclature()+
                        lista.get(j).getNomenclature()
                    );
                }
            }
        } else if(windowSize == 1)
        {
                for(int j = 0; j < lista.size(); j++)
                {
                    hashmap_for_nplets.put(
                            lista.get(j).getNomenclature(), 
                            0
                    );
                    
                    possibleCombinations.add(
                        lista.get(j).getNomenclature()
                    );
                }
            
        }
        
        BufferedReader br = new BufferedReader(new FileReader( f ));
        try 
        {
            String line = br.readLine();
            String actual_protein="";
            while 
            (
                line != null   
            )  
            {
                
                line = br.readLine();
                actual_protein += line;
                try
                {
                    if(line.length() > 3)
                    {
                        if(line.substring(0,2).equals(">s"))
                        {
                            if( protein_number == 1)
                            {
                                organism_name = line.split("OS=")[1].split("GN=")[0];
                            }
                            
                            protein_number++;
                            
                            liTotProt.add(actual_protein);
                            actual_protein="";
                        }
                    }
                    }catch(Exception e){}
                    acum++;
            }
        } finally 
        {
                br.close();
        }
        countNplets();
        WriteTofile writer = new WriteTofile();
         
        writer.writeResult(
                 organism_name,
                 top_proteins,
                 protein_number,
                 windowSize,
                 hashmap_for_nplets,
                 possibleCombinations,
                 totalCombi
         );
        
        //Aqui debe graficar
        for(String nplet : possibleCombinations)
        {
            hashmap_percentages.put(
                nplet,
               ((Integer)hashmap_for_nplets.get(nplet)/(float)totalCombi)*100
            );
        }
        System.out.println(hashmap_percentages);
        
        //    hashmap_percentages
        if(windowSize == 1 ){
            organismEDBar nuevo = new organismEDBar (
                "nplets",
                "Nplets with window ="+windowSize,
                ""+possibleCombinations.size(),
                hashmap_percentages,
                possibleCombinations
            );
            nuevo.pack();
            RefineryUtilities.centerFrameOnScreen(nuevo);
            nuevo.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            nuevo.setLocationRelativeTo(null);
            nuevo.setVisible(true);
            nuevo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            
            JOptionPane.showMessageDialog(
                    null,
                    "creo el archivo con el nombre"+
                    organism_name.subSequence(0, 12)+
                    "_results.txt"
            );
        }
        
        String[] cualquierBobada={"1","2"};
        return cualquierBobada;
    }
    
    public void countNplets()
    {
        for(int ix = 0 ; ix < liTotProt.size(); ix++ )
        {
            String[] result = liTotProt.get(ix).split(">sp");
            for(int i = 0; i < result[0].length()-windowSize+1; i++)
            {
                totalCombi++;
                String substr = result[0].substring(
                    i, 
                    i + windowSize
                );
                                
                if(triplets||windowSize == 2)
                {
                    
                    if(hashmap_for_nplets.get(substr) != null) 
                    {
                        int last_value=(int)hashmap_for_nplets.get(substr);
                        hashmap_for_nplets.put(substr,++last_value);
                    }else
                    {
                        hashmap_for_nplets.put(
                            substr,
                            1
                        );
                    }
                                    
                }else
                { 
                    if(hashmap_for_nplets.get(substr) != null) 
                    {
                        int last_value = (int)hashmap_for_nplets.get(substr);
                        hashmap_for_nplets.put(substr,++last_value);
                    }else
                    {
                        hashmap_for_nplets.put(
                            substr,
                            1
                        );
                    }
                }
            }
        }
    }
    
}
