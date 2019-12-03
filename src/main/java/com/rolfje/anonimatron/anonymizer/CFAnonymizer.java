package com.rolfje.anonimatron.anonymizer;

import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

import java.util.HashMap;
import java.util.Map;

public class CFAnonymizer  implements Anonymizer {
    private Integer shiftValue = 5;
    static String alfa =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String crypt = "FKYVWOZGSRJTQHCDABPNLUMEXI";

    static Map<String,String> cipher;
    static {
        cipher = new HashMap<>();
        char[] alfaByte = alfa.toCharArray();
        char[] cryptByte = crypt.toCharArray();

        for (int i = 0; i < alfaByte.length; i++) {
            cipher.put(String.valueOf(alfaByte[i]), String.valueOf(cryptByte[i]));
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
        char[] buffer = srcString.toCharArray();
        for (int i = 0; i < buffer.length; i++) {
            String letter = String.valueOf(buffer[i]);
            buffer[i] = cipher.get(letter)!=null?cipher.get(letter).charAt(0):letter.charAt(0);
        }
        return new String(buffer);
    }

}
