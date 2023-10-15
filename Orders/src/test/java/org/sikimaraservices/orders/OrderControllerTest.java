package org.sikimaraservices.orders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test: {@link OrderController#createOrder(Orders)}
     */
    @Test
    void testCreateOrder() throws Exception {
        Orders orders = new Orders();
        orders.setAddress("42 Main St");
        orders.setCity("Oxford");
        orders.setCountry("GB");
        orders.setEmail("jane.doe@example.org");
        orders.setFullName("Dr Jane Doe");
        orders.setId(1L);
        orders.setOrderDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        orders.setOrderItems(new ArrayList<>());
        orders.setPhone("6625550144");
        orders.setPostalCode("Postal Code");
        orders.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(orders);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link OrderController#getAllOrders()}
     */
    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/all");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#getAllOrders()}
     */
    @Test
    void testGetAllOrders2() throws Exception {
        ArrayList<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO.OrderDTOBuilder idResult = OrderDTO.builder()
                .City("Oxford")
                .Country("GB")
                .Email("jane.doe@example.org")
                .FullName("Dr Jane Doe")
                .Phone("6625550144")
                .PostalCode("Postal Code")
                .UserId(1)
                .id(1L);
        OrderDTO buildResult = idResult
                .orderDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()))
                .build();
        orderDTOList.add(buildResult);
        when(orderService.getAllOrders()).thenReturn(orderDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/all");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"orderDate\":0,\"address\":null,\"country\":\"GB\",\"fullName\":\"Dr Jane Doe\",\"email\":\"jane.doe@example"
                                        + ".org\",\"phone\":\"6625550144\",\"city\":\"Oxford\",\"postalCode\":\"Postal Code\",\"userId\":1}]"));
    }

    /**
     * Method under test: {@link OrderController#createOrder(Orders)}
     */
    @Test
    void testCreateOrder2() throws Exception {
        java.sql.Date orderDate = mock(java.sql.Date.class);
        when(orderDate.getTime()).thenReturn(10L);

        Orders orders = new Orders();
        orders.setAddress("42 Main St");
        orders.setCity("Oxford");
        orders.setCountry("GB");
        orders.setEmail("jane.doe@example.org");
        orders.setFullName("Dr Jane Doe");
        orders.setId(1L);
        orders.setOrderDate(orderDate);
        orders.setOrderItems(new ArrayList<>());
        orders.setPhone("6625550144");
        orders.setPostalCode("Postal Code");
        orders.setUserId(1);
        String content = (new ObjectMapper()).writeValueAsString(orders);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link OrderController#getOrder(Long)}
     */
    @Test
    void testGetOrder() throws Exception {
        Orders orders = new Orders();
        orders.setAddress("42 Main St");
        orders.setCity("Oxford");
        orders.setCountry("GB");
        orders.setEmail("jane.doe@example.org");
        orders.setFullName("Dr Jane Doe");
        orders.setId(1L);
        orders.setOrderDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        orders.setOrderItems(new ArrayList<>());
        orders.setPhone("6625550144");
        orders.setPostalCode("Postal Code");
        orders.setUserId(1);
        when(orderService.getOrderById(Mockito.<Long>any())).thenReturn(orders);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/42");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"orderDate\":0,\"orderItems\":[],\"address\":\"42 Main St\",\"country\":\"GB\",\"fullName\":\"Dr Jane"
                                        + " Doe\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"city\":\"Oxford\",\"postalCode\":\"Postal"
                                        + " Code\",\"userId\":1}"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersByUser(Long)}
     */
    @Test
    void testGetOrdersByUser() throws Exception {
        when(orderService.getOrdersByUserId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/user/42");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#test()}
     */
    @Test
    void testTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/test");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("test"));
    }

    /**
     * Method under test: {@link OrderController#test()}
     */
    @Test
    void testTest2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/test");
        requestBuilder.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("test"));
    }
}

