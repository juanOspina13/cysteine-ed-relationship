/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nprot.modelo;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class AminosCountTest {
    
    public AminosCountTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getInformation method, of class AminosCount.
     */
    @Test
    public void testGetInformation() throws Exception 
    {
        String yersinia_pestis[]= {"1448730" , "16064" , "0.011088333" } ;
        String shigella_flexneri[] = { "747065" , "8527", "0.011414" };
        String pyrococcus_horikoshii[] = {"288358","1606", "0.005569466"};
        String ovis_aries[] = {"257438" , "5942" , "0.023081286"};
        String pseudomonas_aeruginosa[] = {"1169907" , "0.011441935"};
        String bos_taurus[] = { "550980" , "11190" , "0.020309267"};
        String zea_mays[] = { "281869" , "4778" , "0.016951137"};
        String brucella_melitensis[] = { "435532" , "3585" , "0.008231313"};
        String canis_familiaris[] = {"442332" , "9687" , "0.02189984"};
        String deinoccoccus_radiodurans[] = { "171056" ,"961" ,"0.005618043"};










File f = null;
        AminosCount instance = new AminosCount();
        String[] expResult = null;
        String[] result = instance.getInformation(f);
        
        if( result[3].equalsIgnoreCase("YERSINIA PESTIS")){
            assertSame(result[0], yersinia_pestis[0]);
            assertSame(result[1], yersinia_pestis[1]);
            assertSame(result[2].substring(0,4), yersinia_pestis[2].substring(0,4));
        }else if(result[3].equalsIgnoreCase("SHIGELLA FLEXNERI")){
            assertSame(result[0], shigella_flexneri[0]);
            assertSame(result[1], shigella_flexneri[1]);
        }else if(result[3].equalsIgnoreCase("PYROCOCCUS HORIKOSHII")){
            assertSame(result[0], pyrococcus_horikoshii[0]);
            assertSame(result[1], pyrococcus_horikoshii[1]);       
        }else if(result[3].equalsIgnoreCase("OVIS ARIES")){
            assertSame(result[0], ovis_aries[0]);
            assertSame(result[1], ovis_aries[1]);       
        }else if(result[3].equalsIgnoreCase("PSEUDOMONAS AERUGINOSA")){
            assertSame(result[0], pseudomonas_aeruginosa[0]);
            assertSame(result[1], pseudomonas_aeruginosa[1]);       
        }else if(result[3].equalsIgnoreCase("BOS TAURUS")){
            assertSame(result[0], bos_taurus[0]);
            assertSame(result[1], bos_taurus[1]);       
        }else if(result[3].equalsIgnoreCase("ZEA MAYS")){
            assertSame(result[0], zea_mays[0]);
            assertSame(result[1], zea_mays[1]);       
        }else if(result[3].equalsIgnoreCase("BRUCELLA MELITENSIS")){
            assertSame(result[0], brucella_melitensis[0]);
            assertSame(result[1], brucella_melitensis[1]);       
        }else if(result[3].equalsIgnoreCase("ZEA MAYS")){
            assertSame(result[0], zea_mays[0]);
            assertSame(result[1], zea_mays[1]);       
        }else if(result[3].equalsIgnoreCase("CANIS FAMILIARIS")){
            assertSame(result[0], canis_familiaris[0]);
            assertSame(result[1], canis_familiaris[1]);       
        }else if(result[3].equalsIgnoreCase("DEINOCCOCCUS RADIODURANS")){
            assertSame(result[0], deinoccoccus_radiodurans[0]);
            assertSame(result[1], deinoccoccus_radiodurans[1]);       
        }
       
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
