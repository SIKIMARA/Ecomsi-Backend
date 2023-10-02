package org.sikimaraservices.clients.users;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name="users"

)
@Component
public interface UserClient {
    @GetMapping("/api/v1/auth/extract")
    public String ExtractUserFromJWT(@RequestBody String jwt);
    @PostMapping("/api/v1/auth/validate")
    public void validateUser(String user, String jwt, ServerHttpRequest request);
}
