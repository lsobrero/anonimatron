package com.rolfje.anonimatron.anonymizer;

import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

import java.util.HashMap;
import java.util.Map;

public class CFAnonymizer  implements Anonymizer {
    private Integer shiftValue = 5;
    static String alfa =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String crypt = "FKYVWOZGSRJTQHCDABPNLUMEXI";
    static String alfa9 =  "ABCDEHLMPRST";
    static String crypt9 = "CDEHLMPSTARB";


    static Map<String,String> cipher;
    static {
        cipher = new HashMap<>();
        char[] alfaByte = alfa.toCharArray();
        char[] cryptByte = crypt.toCharArray();

        for (int i = 0; i < alfaByte.length; i++) {
            cipher.put(String.valueOf(alfaByte[i]), String.valueOf(cryptByte[i]));
        }
    }

    static Map<String,String> cipher9;
    static {
        cipher9 = new HashMap<>();
        char[] alfaByte = alfa9.toCharArray();
        char[] cryptByte = crypt9.toCharArray();

        for (int i = 0; i < alfaByte.length; i++) {
            cipher9.put(String.valueOf(alfaByte[i]), String.valueOf(cryptByte[i]));
        }
    }

    @Override
    public String getType() {
        return "COD_FISCALE";
    }

    @Override
    public Synonym anonymize(Object from, int size, boolean shortlived) {
        String srcString = ((String)from).toUpperCase();
        return new StringSynonym(getType(),
                (String) from,
                doShift(srcString),
                shortlived
        );
    }

    public String doShift(String srcString){
        if(srcString.length() != 16) return srcString;

        String shiftCF = doShiftCF(srcString);
        String shift9 = doShift9(srcString);
        String one = shiftCF.substring(0,8);
        String two = shift9.substring(8,9);
        String three = shiftCF.substring(9,11);
        String four = srcString.substring(11,12);
        String five = shiftCF.substring(12);

        return shiftCF.substring(0,8) + shift9.substring(8,9) + shiftCF.substring(9,11) + srcString.substring(11, 12) + shiftCF.substring(12) ;
    }

    public String doShiftCF(String srcString){
        char[] buffer = srcString.toCharArray();
        for (int i = 0; i < buffer.length; i++) {
            String letter = String.valueOf(buffer[i]);
            buffer[i] = cipher.get(letter)!=null?cipher.get(letter).charAt(0):letter.charAt(0);
        }
        return new String(buffer);
    }

    public String doShift9(String srcString){
        char[] buffer = srcString.toCharArray();
        for (int i = 0; i < buffer.length; i++) {
            String letter = String.valueOf(buffer[i]);
            buffer[i] = cipher9.get(letter)!=null?cipher9.get(letter).charAt(0):letter.charAt(0);
        }
        return new String(buffer);
    }


}
