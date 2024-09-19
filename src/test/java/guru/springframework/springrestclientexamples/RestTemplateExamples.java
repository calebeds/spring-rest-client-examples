package guru.springframework.springrestclientexamples;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplateExamples {
    public static final String API_ROOT = "https://api.predic8.de/shop/v2/";

    @Test
    public void getCustomers() {
        String apiUrl = API_ROOT + "/customers";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    public void getProducts() {
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    public void createProduct() {
        String apiUrl = API_ROOT + "/products";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Calebe");
        postMap.put("price", 1);

        JsonNode productCreated = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(productCreated.toString());
    }

    @Test
    public void updateProduct() {
        String apiUrl = API_ROOT + "/products/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Calebe");
        postMap.put("price", 1);

        JsonNode productCreated = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        String customerUrl = productCreated.get("self_link").textValue();

        String id = customerUrl.split("/")[4];

        System.out.println("Created product id: " + id);

        postMap.put("name", "Calebe 2");
        postMap.put("price", 10);

        restTemplate.put(apiUrl + id, postMap);

        JsonNode updatedProduct = restTemplate.getForObject(apiUrl + id, JsonNode.class);

        System.out.println(updatedProduct.toString());
    }

    @Test
    public void deleteProduct() {

        String apiUrl = API_ROOT + "/products/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("name", "Calebe");
        postMap.put("price", 1);

        JsonNode productCreated = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        String customerUrl = productCreated.get("self_link").textValue();

        String id = customerUrl.split("/")[4];

        System.out.println("Created product id: " + id);

        //should go boom on 404

        restTemplate.delete(apiUrl + id);

    }
}
