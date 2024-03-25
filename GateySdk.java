import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public class GatewaySdk {

    private final OkHttpClient httpClient;
    private final String appId;

    public GatewaySdk(String appId) {
        this.httpClient = new OkHttpClient();
        this.appId = appId;
    }

    public void post(String endpoint, RequestPayload payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonPayload = mapper.writeValueAsString(payload);

        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), jsonPayload);
        Request request = new Request.Builder()
                .url("https://open.xiadandt.com/" + endpoint)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("X-Gateway-AppId", appId)
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    public static class RequestPayload {
        private long timestamp;
        private Content content;

        // Getters and setters...

        public static class Content {
            private String extTradeNo;
            private String redirectUrl;
            private Company company;
            private Customer customer;
            private Product product;
            private Installment installment;

            // Getters and setters...

            public static class Company {
                private String id;
                private String name;

                // Getters and setters...
            }

            public static class Customer {
                private String extId;
                private String name;
                private String addr;
                private String phone;
                private String idCard;

                // Getters and setters...
            }

            public static class Product {
                private String extId;
                private String name;
                private String price;
                private String content;

                // Getters and setters...
            }

            public static class Installment {
                private double limit;
                private double first;
                private int num;
                private String type;

                // Getters and setters...
            }
        }
    }
}