package com.sikimara.products.Reviews;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sikimara.products.Products;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ReviewsController.class})
@ExtendWith(SpringExtension.class)
class ReviewsControllerTest {
    @Autowired
    private ReviewsController reviewsController;

    @MockBean
    private ReviewsService reviewsService;

    /**
     * Method under test: {@link ReviewsController#createReview(Reviews)}
     */
    @Test
    void testCreateReview() throws Exception {
        Products product = new Products();
        product.setBadge(true);
        product.setBrand("Brand");
        product.setCategory("Category");
        product.setColor("Color");
        product.setDes("Des");
        product.setId(1);
        product.setImg("Img");
        product.setPrice(10.0f);
        product.setProductName("Product Name");
        product.setQuantity(1);
        product.setReviews(new ArrayList<>());

        Reviews reviews = new Reviews();
        reviews.setComment("Comment");
        reviews.setDate("2020-03-01");
        reviews.setId(1);
        reviews.setProduct(product);
        reviews.setRating(1);
        reviews.setUser_email("jane.doe@example.org");
        reviews.setUser_id("User id");
        reviews.setUser_name("User name");
        when(reviewsService.saveReview(Mockito.<Reviews>any())).thenReturn(reviews);

        Products product2 = new Products();
        product2.setBadge(true);
        product2.setBrand("Brand");
        product2.setCategory("Category");
        product2.setColor("Color");
        product2.setDes("Des");
        product2.setId(1);
        product2.setImg("Img");
        product2.setPrice(10.0f);
        product2.setProductName("Product Name");
        product2.setQuantity(1);
        product2.setReviews(new ArrayList<>());

        Reviews reviews2 = new Reviews();
        reviews2.setComment("Comment");
        reviews2.setDate("2020-03-01");
        reviews2.setId(1);
        reviews2.setProduct(product2);
        reviews2.setRating(1);
        reviews2.setUser_email("jane.doe@example.org");
        reviews2.setUser_id("User id");
        reviews2.setUser_name("User name");
        String content = (new ObjectMapper()).writeValueAsString(reviews2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/reviews/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"rating\":1,\"comment\":\"Comment\",\"product\":{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\","
                                        + "\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category"
                                        + "\",\"reviews\":[]},\"user_name\":\"User name\",\"user_email\":\"jane.doe@example.org\",\"date\":\"2020-03-01\",\"user"
                                        + "_id\":\"User id\"}"));
    }

    /**
     * Method under test: {@link ReviewsController#getAllReviews()}
     */
    @Test
    void testGetAllReviews() throws Exception {
        when(reviewsService.getAllReviews()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/reviews/all");
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ReviewsController#updateReview(Reviews)}
     */
    @Test
    void testUpdateReview() throws Exception {
        Products product = new Products();
        product.setBadge(true);
        product.setBrand("Brand");
        product.setCategory("Category");
        product.setColor("Color");
        product.setDes("Des");
        product.setId(1);
        product.setImg("Img");
        product.setPrice(10.0f);
        product.setProductName("Product Name");
        product.setQuantity(1);
        product.setReviews(new ArrayList<>());

        Reviews reviews = new Reviews();
        reviews.setComment("Comment");
        reviews.setDate("2020-03-01");
        reviews.setId(1);
        reviews.setProduct(product);
        reviews.setRating(1);
        reviews.setUser_email("jane.doe@example.org");
        reviews.setUser_id("User id");
        reviews.setUser_name("User name");
        when(reviewsService.updateReview(Mockito.<Reviews>any())).thenReturn(reviews);

        Products product2 = new Products();
        product2.setBadge(true);
        product2.setBrand("Brand");
        product2.setCategory("Category");
        product2.setColor("Color");
        product2.setDes("Des");
        product2.setId(1);
        product2.setImg("Img");
        product2.setPrice(10.0f);
        product2.setProductName("Product Name");
        product2.setQuantity(1);
        product2.setReviews(new ArrayList<>());

        Reviews reviews2 = new Reviews();
        reviews2.setComment("Comment");
        reviews2.setDate("2020-03-01");
        reviews2.setId(1);
        reviews2.setProduct(product2);
        reviews2.setRating(1);
        reviews2.setUser_email("jane.doe@example.org");
        reviews2.setUser_id("User id");
        reviews2.setUser_name("User name");
        String content = (new ObjectMapper()).writeValueAsString(reviews2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/reviews/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"rating\":1,\"comment\":\"Comment\",\"product\":{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\","
                                        + "\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category"
                                        + "\",\"reviews\":[]},\"user_name\":\"User name\",\"user_email\":\"jane.doe@example.org\",\"date\":\"2020-03-01\",\"user"
                                        + "_id\":\"User id\"}"));
    }

    /**
     * Method under test: {@link ReviewsController#getReviewById(int)}
     */
    @Test
    void testGetReviewById() throws Exception {
        Products product = new Products();
        product.setBadge(true);
        product.setBrand("Brand");
        product.setCategory("Category");
        product.setColor("Color");
        product.setDes("Des");
        product.setId(1);
        product.setImg("Img");
        product.setPrice(10.0f);
        product.setProductName("Product Name");
        product.setQuantity(1);
        product.setReviews(new ArrayList<>());

        Reviews reviews = new Reviews();
        reviews.setComment("Comment");
        reviews.setDate("2020-03-01");
        reviews.setId(1);
        reviews.setProduct(product);
        reviews.setRating(1);
        reviews.setUser_email("jane.doe@example.org");
        reviews.setUser_id("User id");
        reviews.setUser_name("User name");
        when(reviewsService.getReviewById(anyInt())).thenReturn(reviews);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/reviews/get/42");
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"rating\":1,\"comment\":\"Comment\",\"product\":{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\","
                                        + "\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category"
                                        + "\",\"reviews\":[]},\"user_name\":\"User name\",\"user_email\":\"jane.doe@example.org\",\"date\":\"2020-03-01\",\"user"
                                        + "_id\":\"User id\"}"));
    }

    /**
     * Method under test: {@link ReviewsController#deleteReview(int)}
     */
    @Test
    void testDeleteReview() throws Exception {
        when(reviewsService.deleteReviewById(anyInt())).thenReturn("42");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/reviews/delete/42");
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("42"));
    }

    /**
     * Method under test: {@link ReviewsController#getReviewByProductId(int)}
     */
    @Test
    void testGetReviewByProductId() throws Exception {
        when(reviewsService.getReviewsByProductId(anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/reviews/product/42");
        MockMvcBuilders.standaloneSetup(reviewsController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

