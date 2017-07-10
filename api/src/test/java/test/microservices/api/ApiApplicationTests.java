package test.microservices.api;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

	@Test
	public void testGreetRestService() {
		String plainCreds = "admin:admin1234";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(Base64.encodeBase64(plainCreds.getBytes())));
		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:4001/greet", HttpMethod.GET,
				request, String.class);

		Assert.assertEquals("Hello microservices", response.getBody());

	}

    @Test
    public void testOAuthService() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setUsername("admin");
        resource.setPassword("admin1234");

        resource.setAccessTokenUri("http://localhost:4001/oauth/token");
        resource.setClientId("trustedclient");
        resource.setClientSecret("trustedclient123");
        resource.setGrantType("password");

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource, clientContext);
        String greet = restTemplate.getForObject("http://localhost:4001/greet", String.class);
        Assert.assertEquals("Hello microservices", greet);
    }
}
