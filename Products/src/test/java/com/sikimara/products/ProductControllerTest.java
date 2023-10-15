package com.sikimara.products;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

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

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    /**
     * Method under test: {@link ProductController#createProduct(Products)}
     */
    @Test
    void testCreateProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        when(productRepository.save(Mockito.<Products>any())).thenReturn(products);

        Products products2 = new Products();
        products2.setBadge(true);
        products2.setBrand("Brand");
        products2.setCategory("Category");
        products2.setColor("Color");
        products2.setDes("Des");
        products2.setId(1);
        products2.setImg("Img");
        products2.setPrice(10.0f);
        products2.setProductName("Product Name");
        products2.setQuantity(1);
        products2.setReviews(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(products2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\",\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,"
                                        + "\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category\",\"reviews\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(int)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        Optional<Products> ofResult = Optional.of(products);
        doNothing().when(productRepository).deleteById(Mockito.<Integer>any());
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/products/delete/42");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product Deleted"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(int)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        doNothing().when(productRepository).deleteById(Mockito.<Integer>any());
        Optional<Products> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/products/delete/42");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product Not Found"));
    }

    /**
     * Method under test: {@link ProductController#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws Exception {
        when(productRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/all");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }

    /**
     * Method under test: {@link ProductController#getBestSellers()}
     */
    @Test
    void testGetBestSellers() throws Exception {
        when(productRepository.findTop4ByOrderByQuantityAsc()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/BestSellers");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#getNewArrivals()}
     */
    @Test
    void testGetNewArrivals() throws Exception {
        when(productRepository.findTop4ByOrderByQuantityDesc()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/NewArrivals");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#getPrice(int)}
     */
    @Test
    void testGetPrice() throws Exception {
        when(productRepository.findPriceById(Mockito.<Integer>any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/products/getPrice/42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link ProductController#getProduct(int)}
     */
    @Test
    void testGetProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        Optional<Products> ofResult = Optional.of(products);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/products/get/42");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\",\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,"
                                        + "\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category\",\"reviews\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#getQuantityProduct(Integer)}
     */
    @Test
    void testGetQuantityProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        Optional<Products> ofResult = Optional.of(products);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/getQuantity/{id}", 1);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    /**
     * Method under test: {@link ProductController#getQuantityProduct(Integer)}
     */
    @Test
    void testGetQuantityProduct2() throws Exception {
        Optional<Products> emptyResult = Optional.empty();
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/getQuantity/{id}", 1);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    /**
     * Method under test: {@link ProductController#getSpecialOffers()}
     */
    @Test
    void testGetSpecialOffers() throws Exception {
        when(productRepository.findTop4ByOrderByPriceAsc()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/SpecialOffers");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#test()}
     */
    @Test
    void testTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/test");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("test"));
    }

    /**
     * Method under test: {@link ProductController#test()}
     */
    @Test
    void testTest2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/test");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("test"));
    }

    /**
     * Method under test: {@link ProductController#updateProduct(Products, int)}
     */
    @Test
    void testUpdateProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        when(productRepository.save(Mockito.<Products>any())).thenReturn(products);

        Products products2 = new Products();
        products2.setBadge(true);
        products2.setBrand("Brand");
        products2.setCategory("Category");
        products2.setColor("Color");
        products2.setDes("Des");
        products2.setId(1);
        products2.setImg("Img");
        products2.setPrice(10.0f);
        products2.setProductName("Product Name");
        products2.setQuantity(1);
        products2.setReviews(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(products2);
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/products/update/42");
        MockHttpServletRequestBuilder requestBuilder = putResult.param("id", String.valueOf(1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productName\":\"Product Name\",\"des\":\"Des\",\"price\":10.0,\"quantity\":1,\"img\":\"Img\",\"badge\":true,"
                                        + "\"color\":\"Color\",\"brand\":\"Brand\",\"category\":\"Category\",\"reviews\":[]}"));
    }

    /**
     * Method under test: {@link ProductController#updateQuantityProduct(int, int)}
     */
    @Test
    void testUpdateQuantityProduct() throws Exception {
        Products products = new Products();
        products.setBadge(true);
        products.setBrand("Brand");
        products.setCategory("Category");
        products.setColor("Color");
        products.setDes("Des");
        products.setId(1);
        products.setImg("Img");
        products.setPrice(10.0f);
        products.setProductName("Product Name");
        products.setQuantity(1);
        products.setReviews(new ArrayList<>());
        Optional<Products> ofResult = Optional.of(products);

        Products products2 = new Products();
        products2.setBadge(true);
        products2.setBrand("Brand");
        products2.setCategory("Category");
        products2.setColor("Color");
        products2.setDes("Des");
        products2.setId(1);
        products2.setImg("Img");
        products2.setPrice(10.0f);
        products2.setProductName("Product Name");
        products2.setQuantity(1);
        products2.setReviews(new ArrayList<>());
        when(productRepository.save(Mockito.<Products>any())).thenReturn(products2);
        when(productRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/products/updateQuantity/{id}/quantity/{quantity}", 1, 1);
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Quantity Updated"));
    }
}

