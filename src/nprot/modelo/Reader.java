package nprot.modelo;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
 
public class Reader {
 
    public Reader() {
        } 
    public String[] result(String cadena) throws IOException{
    Scanner scanner = new Scanner(cadena);
    String content="";
    String head="";
    int acum=0;
    BufferedReader rdr = new BufferedReader(new StringReader(cadena));
    List<String> lines = new ArrayList<String>();
    for (String line = rdr.readLine(); line != null; line = rdr.readLine()) {
        lines.add(line);
       // for(int i=0;i<line.length();i++){
         //   if(line.charAt(i)=='='){
           //     acum++;
            //}
        //}
    }
    JOptionPane.showMessageDialog(null,"listo");
    System.exit(1);
    rdr.close();
        
       String[]result=lines.get(0).split(">sp");
        String[] orgaismInfo=result[1].split("GN=");
        JOptionPane.showMessageDialog(null,result.length);
        for(int i=0;i<result.length;i++){
            System.out.println(i+result[i]);
        }
        System.exit(1);
        //orgaismInfo[0] is the name of the organism
        //goes through each line 
        int cisteineCount=0;
        int aminosCount=0;
        LinkedList<String> pred_suc=new LinkedList();  
        String[] secuence=result[1].split("SV=");
             for(int j=0;j<secuence[1].length();j++){
                 aminosCount++;
                if(secuence[1].charAt(j)=='C'){
                    cisteineCount++;
                    if(j==0){
                          pred_suc.add(j+":"+"_"+secuence[1].charAt(j+1));
                    }
                    else if(j==secuence[1].length()-1){
                        pred_suc.add(j+":"+"_"+secuence[1].charAt(j-1));
                    }
                    else{
                        pred_suc.add(j+":"+secuence[1].charAt(j-1)+"_"+secuence[1].charAt(j+1));
                    }
                }
             }
             System.out.println(result[0]);
      String writeInTextArea="Organism->"+orgaismInfo[0]+"\n";
      writeInTextArea+="Aminoacid Count->"+aminosCount+"\n";
      writeInTextArea+="Cisteine Count->"+cisteineCount+"\n";
      writeInTextArea+="%"+(float)cisteineCount/(float)aminosCount+"\n";
      writeInTextArea+="Pred_suc->\n";
      String aux="" ;
         System.out.println("Cisteine Count"+(float)cisteineCount/(float)aminosCount);
        for(int i=0;i<pred_suc.size();i++){
            
           writeInTextArea+=pred_suc.get(i)+"\n";
           aux+=pred_suc.get(i)+"\n";
        }
        //organize the info so we can store it into the DB
        String[] organized_info_for_db=new String[7];
        organized_info_for_db[0]=""+aminosCount;
        organized_info_for_db[1]=""+cisteineCount;
        organized_info_for_db[2]=""+aux;
        organized_info_for_db[3]=""+(float)cisteineCount/(float)aminosCount;
        organized_info_for_db[4]=""+orgaismInfo[0];
        organized_info_for_db[5]=""+result[0];
        organized_info_for_db[6]=cadena; 
        
        //database storage
//         ManejoBD bd=new ManejoBD();
  //       int result_saving=bd.saveSequence(organized_info_for_db);
         String [] answer=new String[2];
         answer[0]=writeInTextArea;
         //answer[1]=""+result_saving;
         
         return answer;
    }   
    public String[] readSingleTxt(
            int window,
            //int top_proteins,
            File f,
            String type
    ) throws FileNotFoundException, IOException {
        String[]result = null;
        if(type.equalsIgnoreCase("aminos_count"))
        {
            AminosCount nuevo=new AminosCount();
            result =nuevo.getInformation(f);
        }else
        {
            NpletCount nuevo = new NpletCount(
                    window,
                    f
            );
            result =nuevo.getInformation(f);
       
        }
         return result;
        
        
    }
 
}
