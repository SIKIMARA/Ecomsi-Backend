package org.sikimaraservices.clients.orders;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name="order",
        url="${clients.orders.url}"
)
public interface OrderClient {

}
