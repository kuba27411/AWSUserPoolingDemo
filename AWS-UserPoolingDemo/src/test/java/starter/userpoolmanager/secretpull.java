package starter.userpoolmanager;
import org.json.JSONObject;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class secretpull {
    public static void main(String[] args) {
        getSecret();
    }
    public static String getSecret() {

        String secretName = "AdminLogins";
        Region region = Region.of("us-east-1");

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        String secret = getSecretValueResponse.secretString();
        JSONObject jsonObject = new JSONObject(secret);
        String username = jsonObject.getString("Admin_Username");
        String password = jsonObject.getString("Admin_Password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        return secretName;
    }
}
