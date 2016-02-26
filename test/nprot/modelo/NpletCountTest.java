/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nprot.modelo;

import java.io.File;
import java.util.HashMap;
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
public class NpletCountTest {

    public NpletCountTest() {
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
     * Test of getInformation method, of class NpletCount.
     */
    @Test
    public void testGetInformation() throws Exception {
        System.out.println("getInformation");
        File f = null;
        String[] secuencias = {"MMP", "CQS", "MAK", "FLN", "ALR"};
        HashMap expected_results = new HashMap();
        expected_results.put("MMP", 66);
        expected_results.put("CQS", 268);
        expected_results.put("MAK", 233);
        expected_results.put("FLN", 186);
        expected_results.put("ALR", 987);
        HashMap result_as_hashmap = new HashMap();

        NpletCount instance = null;
        String[] expResult = null;
        String[] result = instance.getInformation(f);
        for (String sequence : result) {
            String[] tmp = sequence.split("_");
            result_as_hashmap.put(tmp[0], tmp[1]);
        }

        for (String secuencia : secuencias) {
            if (result_as_hashmap.get(secuencia) != null) {
                assertSame(result_as_hashmap.get(secuencia) , expected_results.get(secuencia));
                 
            } else {
                fail("Fallo la prueba.");
            }

            assertArrayEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            
        }
    }

        /**
         * Test of countNplets method, of class NpletCount.
         */
        @Test
        public void testCountNplets() {
        System.out.println("countNplets");
            NpletCount instance = null;
            instance.countNplets();
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        }

    }
