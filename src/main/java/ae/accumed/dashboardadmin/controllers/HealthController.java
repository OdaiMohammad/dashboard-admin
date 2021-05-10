package ae.accumed.dashboardadmin.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    ResponseEntity<Object> getHealth() {
        return new ResponseEntity<>("I'm healthy, I guess.", HttpStatus.OK);
    }
}
