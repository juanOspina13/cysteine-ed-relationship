package nprot.modelo;
import NProt.accesoDatos.ManejoBD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JOptionPane;

public class AminosCount 
{

    int top_proteins = 50000;
    int acum = 0;
    int protein_number = 0;
    //COMPLETE LIST OF AMINOS
    //create the aminoacids
    
    Aminoacid Alanine = new Aminoacid("Alanina", "A", 0);
    Aminoacid Arginine = new Aminoacid("Arginina", "R", 0);
    Aminoacid Asparaginine = new Aminoacid("Asparagina", "N", 0);
    Aminoacid Aspartic_acid = new Aminoacid("Ácido aspártico", "D", 0);
    Aminoacid Cisteine = new Aminoacid("Cisteína", "C", 0);
    Aminoacid Glutamine = new Aminoacid("Glutamina", "Q", 0);
    Aminoacid Glicine = new Aminoacid("Glicina", "E", 0);
    Aminoacid Glutamic_acid = new Aminoacid("Ácido glutámico", "G", 0);
    Aminoacid Histidine = new Aminoacid("Histidina", "H", 0);
    Aminoacid Leucine = new Aminoacid("Leucina", "L", 0);
    Aminoacid Lisine = new Aminoacid("Lisina", "K", 0);
    Aminoacid Isoleucine = new Aminoacid("Isoleucina", "I", 0);
    Aminoacid Metionine = new Aminoacid("Metionina", "M", 0);
    Aminoacid Fenialanine = new Aminoacid("Fenilalanina", "F", 0);
    Aminoacid Proline = new Aminoacid("Prolina", "P", 0);
    Aminoacid Serine = new Aminoacid("Serina", "S", 0);
    Aminoacid Treonine = new Aminoacid("Treonina", "T", 0);
    Aminoacid Triptophane = new Aminoacid("Triptófano", "W", 0);
    Aminoacid Tirosine = new Aminoacid("Tirosina", "Y", 0);
    Aminoacid Valine = new Aminoacid("Valina", "V", 0);
    
    //add the aminoacids to a list
    
    LinkedList<Aminoacid> lista = new LinkedList();
    
    /*
    * Inicializo la lista 
    */
    public AminosCount() 
    {
        lista.add(Alanine);
        lista.add(Arginine);
        lista.add(Asparaginine);
        lista.add(Aspartic_acid);
        lista.add(Cisteine);
        lista.add(Glutamine);
        lista.add(Glicine);
        lista.add(Glutamic_acid);
        lista.add(Histidine);
        lista.add(Leucine);
        lista.add(Lisine);
        lista.add(Isoleucine);
        lista.add(Metionine);
        lista.add(Fenialanine);
        lista.add(Proline);
        lista.add(Serine);
        lista.add(Treonine);
        lista.add(Triptophane);
        lista.add(Tirosine);
        lista.add(Valine);
        boolean triplets = true;
    }
    
    /*
    * Lee el archivo
    */
    
    public String[] getInformation(
            File f
    ) throws FileNotFoundException, IOException 
    {
        //GENERATE ALL THE POSIBLE TRIPLETS AND ADD THEM TO A HASH TABLE, WORKS FOR TRIPLETS ONLY
        HashMap hashmap_for_nplets = new HashMap();
        HashMap hashmap_for_aminos = new HashMap();
        String organism_name = "";

        for (int i = 0; i < lista.size(); i++) {
            hashmap_for_aminos.put(lista.get(i).nomenclature, 0);
        }

        BufferedReader br = new BufferedReader(new FileReader(f));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null && protein_number < top_proteins) {
                line = br.readLine();
                try {
                    if (line.substring(0, 2).equals(">s")) 
                    {
                        if(protein_number==1)
                        {
                            organism_name = line.split("OS=")[1].split("GN=")[0];
                        }
                        protein_number++;
                    } else {
                        for (int i = 0; i < line.length(); i++) 
                        {
                            
                            int val = (int) hashmap_for_aminos.get
                            (
                                    "" + 
                                    line.charAt(i) + 
                                    ""
                            );
                            
                            val++;
                            
                            hashmap_for_aminos.put(
                                    "" + line.charAt(i) + "", 
                                    val++
                            );
                        
                        }
                    }
                } catch (Exception e) 
                {
                }
                acum++;
            }
        } finally {
            br.close();
        }
        System.out.println("Aminos : " + hashmap_for_aminos);
        int total_acum = 0;
        for (int i = 0; i < lista.size(); i++) 
        {
            try 
            {
                total_acum += (int) hashmap_for_aminos.get(
                        lista.get(i).getNomenclature()
                    );
                
            } catch (Exception e) {
            }
        }
        System.out.println(hashmap_for_aminos.get("C"));
        System.out.println(total_acum);
        double double_cisteine_total = (double) (int) hashmap_for_aminos.get("C");
        double double_total_acum = (double) total_acum;
        System.out.println(double_cisteine_total / double_total_acum);
        System.out.println(organism_name);
        String tmp[]=organism_name.split(" ");
        String[] output = {
            "" + total_acum, "" + double_cisteine_total, 
            "", 
            ("" + double_cisteine_total / double_total_acum).substring(0,5),
            tmp[0]+" "+tmp[1], 
            "" + f, "ninguno"
        };
        ManejoBD manejador = new ManejoBD();
        manejador.saveSequence(output);
        return output;
    }

}
