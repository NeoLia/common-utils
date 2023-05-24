package org.mjh.commonutils.signature;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author Neo Lia
 * @date 2023/5/23 17:51
 */
public class DigitSignatureUtils {
    public static PrivateKey transformToPrivateKey(String privateKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static PublicKey transformToPublicKey(String publicKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public static void main(String[] args) {
        final String privateKeyStr = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCPmoFreMe0IjigSxPP3IjgLHZny+67U0DytE497rQ0xlD8GsPGPq/eRH0HMu6WxNllQAImJfHBsl7OcBRrT9pyiWwgEB/KFYthxjtalZ2jl+q1sQpqfxrI4dlSo1zKcTapewCZN/i71GqgLr/WwXNOHqP/z54NFeMuW2Z3Rd68gM2ZtBje5ac/b7GZ+6sUwOaJ0C0diUafdDjcr5sRSk+cD2rlYh8ZPJ/AMAV+eZZ60hpRogbpAIBNOaGc8XAzYXSmxud2NY2yv2BegmgSKajL60pneRpGjDJDqVXZMUGA6sqGlniaxrGiabvLIKqbWlebzTw50e5n1FP5zx6cxgpfAgMBAAECggEAXKL6G/9FLLHqv5TPCgclER/9ngXTUmppemfQFWm4PFc2hPsrEeb8grDByvQTL5V6/DLnb/6IT0CmohtxQKy5qat24dQk/bMaQA+l2FCt6YulsGGt63dlKvNuwz15LWsNLRWZFD4kKd7kjcK6pGoU3O8tMmeXeK+G5y+1EPo0rnqcWTcW73YOrzO01amf66nBQk4WXPiIyrwhe2uyPWUe/a+eCuOPIqj7hQYbOFNlbQYQfV4Oe2do5RkWBgWkdkSOH+9Z4QoKycMniK2K7MuYyD74lwer57vYtQjdJVezbnZwjvlbOL1o/beJ3LyeYlZjRy43kajgyQpMSFPilLYxmQKBgQDRFmwkT1EYwRfGf/ZUAM6xsvzqmH3KlkQNh8+7FhhOAbzDtrIXumzBmKOhe3wcXHmzbhnPWm31IhgIvKsemHigiBiwuodcymMffuC4UuiOFqTfQep3V1xWNfQe5zzax/cEtLHzvy9pqfHDXxGwDnQVi/PkVQrBwjl6QuO0SBGjzQKBgQCv0s8KyVqeXOdYEhRfNulRVDjuLNBNKo2n36enTAgL9vDqg6bnPuNBesTIP5/MBR88m6oaeHP8nIqF0DZE8B6djwDk3n+3t9C7PXha/S3BD3Ckpdlp7WC/VwvVqluUM+4d8LDAQnwf7qGxqH5aZ/UaF+4JK1/D68LYAM4sw1SS2wKBgCoLw0Vt+dXrhpdxRhzQaMBNSRT4A1vI6fh6lih+IfcV3FrTE284GwS3aDhh96xQYFzXyDLtcW05JPIk1ph5etB1bdmkOPJM9K/xuMj4SzG/lfobbEYBeF0RznykbZVlXuRAXgSxLpymAysRpCHuViO7GBmTjG04XIychOPNFlcFAoGAKCllNbeMxYO/LHTkxmMKpJMydeUyp43C0cfayfIqHGGJ4me0JjpNOtXKQVuXKsk4u2VrU6SUCe3QDoTjAJhAxgi2UZEpZ1PLkBmYdmBCpUjbmCByKyoX4V0GzQWe6Zl7GMez+Ba3t6uQr74nLot0h0NUEjAgLeaamTCF59+uX+UCgYAPWiYasUbQx2iQdoLMg5nV7fYwyZgh9X0StnSHLswC0P/YJjVXt7Sr/5rdxXdGcc9e1dLW8f0NljnDRjZ5Qy5EDWBiyKK6DkHnA9XfPBTWIVdqyE5HJ+IID/bLJknqxlcwWmUmywwDoUbx0lvOB5G+aQuNE9/A4J+PGJvTpt1yfQ==";
        final String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj5qBa3jHtCI4oEsTz9yI4Cx2Z8vuu1NA8rROPe60NMZQ/BrDxj6v3kR9BzLulsTZZUACJiXxwbJeznAUa0/acolsIBAfyhWLYcY7WpWdo5fqtbEKan8ayOHZUqNcynE2qXsAmTf4u9RqoC6/1sFzTh6j/8+eDRXjLltmd0XevIDNmbQY3uWnP2+xmfurFMDmidAtHYlGn3Q43K+bEUpPnA9q5WIfGTyfwDAFfnmWetIaUaIG6QCATTmhnPFwM2F0psbndjWNsr9gXoJoEimoy+tKZ3kaRowyQ6lV2TFBgOrKhpZ4msaxomm7yyCqm1pXm808OdHuZ9RT+c8enMYKXwIDAQAB";

        // 私钥字符串转私钥
        PrivateKey privateKey = null;
        try {
            privateKey = transformToPrivateKey(privateKeyStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println(privateKey);

        // 公钥字符串转公钥
        PublicKey publicKey = null;
        try {
            publicKey = transformToPublicKey(publicKeyStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        System.out.println(publicKey);
    }
}
