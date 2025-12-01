package za.ac.cput.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping
    public String test() {
        return "Backend is running on port 8080! - " + System.currentTimeMillis();
    }
}
