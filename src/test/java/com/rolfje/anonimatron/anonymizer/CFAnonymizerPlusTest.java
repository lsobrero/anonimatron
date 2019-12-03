package com.rolfje.anonimatron.anonymizer;

import com.rolfje.anonimatron.synonyms.Synonym;
import junit.framework.TestCase;

public class CFAnonymizerPlusTest  extends TestCase {

    public  void testCFAnonymizer00(){
        CFAnonymizerPlus cfAn = new CFAnonymizerPlus();
        Synonym result = cfAn.anonymize("GRDFNC53M29D086U", 16, false);
        assertEquals(cfAn.getType(), result.getType());
        assertEquals("GRDFNC53M29D086U", result.getFrom());
        assertFalse("GRDFNC53M29D086U".equals(result.getTo()));
        assertEquals(16, ((String) result.getTo()).length());
        assertFalse(result.isShortLived());
        assertEquals("ZBVOHY53Q29V086X", result.getTo());
    }

    public  void testCFAnonymizer01(){
        CFAnonymizer cfAn = new CFAnonymizer();
        Synonym result = cfAn.anonymize("", 16, false);
        assertEquals(cfAn.getType(), result.getType());
        assertEquals("", result.getFrom());
        assertFalse("A".equals(result.getTo()));
        assertEquals(0, ((String) result.getTo()).length());
        assertFalse(result.isShortLived());
        assertEquals("", result.getTo());
    }

    public  void testCFAnonymizer02(){
        CFAnonymizer cfAn = new CFAnonymizer();
        Synonym result = cfAn.anonymize("ABC", 16, false);
        assertEquals(cfAn.getType(), result.getType());
        assertEquals("ABC", result.getFrom());
        assertFalse("A".equals(result.getTo()));
        assertEquals(3, ((String) result.getTo()).length());
        assertFalse(result.isShortLived());
        assertEquals("FKY", result.getTo());
    }

}
