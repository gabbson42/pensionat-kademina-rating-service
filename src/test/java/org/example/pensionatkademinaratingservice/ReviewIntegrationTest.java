package org.example.pensionatkademinaratingservice;


import org.example.pensionatkademinaratingservice.entity.Review;
import org.example.pensionatkademinaratingservice.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WireMockTest(httpPort = 8090)
public class ReviewIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void returnBadRequest() throws Exception {

        String json = """
            {
              "customerId": 1,
              "roomId": 1,
              "rating": 6,
              "comment": "super upplevelse"
            }
            """;

        mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))

                .andExpect(status().isBadRequest());

    }

    @Test
    void createReview() throws Exception{

        stubFor(get(urlPathEqualTo("/api/bookings/check"))
                .withQueryParam("customerId", equalTo("1"))
                .withQueryParam("roomId", equalTo("1"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                            {
                              "booked": true
                            }
                            """)
                        .withStatus(200)));

        String json = """
            {
              "customerId": 1,
              "roomId": 1,
              "rating": 5,
              "comment": "Bra rum"
            }
            """;


        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        List<Review> reviews = reviewRepository.findAll();
        assertFalse(reviews.isEmpty());


    }






}
