package org.sikimaraservices.clients.orders;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name="order"
)
public interface OrderClient {

}
