package com.sunhaochen.demo.api;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Three demo endpoints:
 *
 * <ul>
 *   <li>{@code GET  /api/hello?name=xxx} — greeting</li>
 *   <li>{@code GET  /api/time}          — current server time</li>
 *   <li>{@code POST /api/echo}          — echo the request body back</li>
 * </ul>
 */
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    @GetMapping("/hello")
    public Map<String, Object> hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return Map.of(
                "message", "Hello, " + name + "!",
                "service", "spring-boot-demo",
                "timestamp", Instant.now().toString()
        );
    }

    @GetMapping("/time")
    public Map<String, Object> time() {
        Instant now = Instant.now();
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("iso", now.toString());
        body.put("epochMillis", now.toEpochMilli());
        body.put("zone", java.time.ZoneId.systemDefault().getId());
        return body;
    }

    @PostMapping(path = "/echo", consumes = MediaType.ALL_VALUE)
    public Map<String, Object> echo(@RequestBody(required = false) String body, HttpServletRequest request) {
        String payload = body == null ? "" : body;
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("received", payload);
        result.put("contentType", request.getContentType());
        result.put("length", payload.length());
        result.put("timestamp", Instant.now().toString());
        return result;
    }
}
