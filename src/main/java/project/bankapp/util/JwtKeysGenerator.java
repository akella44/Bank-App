package project.bankapp.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtKeysGenerator {

    public static void main(String[] args) {
        System.out.println(generateKey());
        System.out.println(generateKey());
    }

    private static String generateKey() {
        return Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
    }

}