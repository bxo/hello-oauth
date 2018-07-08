package io.github.bxo.myoauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Map;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Ignore
public class MyOauthApplicationTests {

	@Autowired
	private MockMvc mvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void everythingIsSecuredByDefault() throws Exception {
        this.mvc.perform(get("/").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isUnauthorized());
        this.mvc.perform(get("/flights").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isUnauthorized());
        this.mvc.perform(get("/flights/1").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isUnauthorized());
        this.mvc.perform(get("/alps").accept(MediaTypes.HAL_JSON))
                .andExpect(status().isUnauthorized());
    }

	@Test
	public void userEndpoint() throws Exception {
		this.mvc.perform(get("/user").accept(MediaTypes.HAL_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Ignore
	public void accessingRootUriPossibleWithUserAccount() throws Exception {
		MockHttpServletRequestBuilder request = get("/")
				.accept(MediaTypes.HAL_JSON)
				.with(httpBasic("greg", "turnquist"));
		this.mvc.perform(request)
				.andExpect(
						header().string("Content-Type", MediaTypes.HAL_JSON_VALUE))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void useAppSecretsPlusUserAccountToGetBearerToken() throws Exception {
		MockHttpServletRequestBuilder tokenRequest = post("/oauth/token")
				.with(httpBasic("foo", "bar"))
				.param("grant_type", "password")
				.param("scope", "read")
				.param("username", "greg")
				.param("password", "turnquist");
		MvcResult result = this.mvc.perform(tokenRequest)
				.andExpect(status().isOk())
				.andReturn();
		Object accessToken = this.objectMapper
				.readValue(result.getResponse().getContentAsString(), Map.class)
				.get("access_token");

		MockHttpServletRequestBuilder flightsRequest = get("/flights/1")
				.accept(MediaTypes.HAL_JSON)
				.header("Authorization", "Bearer " + accessToken);
		MvcResult flightsAction = this.mvc
				.perform(flightsRequest)
				.andExpect(header().string("Content-Type",
						MediaTypes.HAL_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andReturn();

//		Flight flight = this.objectMapper.readValue(
//				flightsAction.getResponse().getContentAsString(), Flight.class);
//
//		assertThat(flight.getOrigin()).isEqualTo("Nashville");
//		assertThat(flight.getDestination()).isEqualTo("Dallas");
//		assertThat(flight.getAirline()).isEqualTo("Spring Ways");
//		assertThat(flight.getFlightNumber()).isEqualTo("OAUTH2");
//		assertThat(flight.getTraveler()).isEqualTo("Greg Turnquist");
	}

}
