package t;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Service")
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Service> getAll##entityName##() {
        return service.getAll##entityName##();
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return service.getServiceById(id);
    }

    @PostMapping
    public Service createService(@RequestBody Service Service) {
        return service.createService(Service);
    }

    // ... d'autres méthodes pour les opérations CRUD

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        service.deleteService(id);
    }
}
