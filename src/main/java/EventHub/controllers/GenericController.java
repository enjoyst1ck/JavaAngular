package EventHub.controllers;

import EventHub.models.IModel;
import EventHub.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericController<M extends IModel, S extends GenericService> {
    @Autowired
    private S service;

    @GetMapping("/getall")
    public List<M> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public M getById(@PathVariable("id") Integer id) throws Exception {
        return (M) service.getById(id);
    }

    @PostMapping("/add")
    public M add(@RequestBody M object) {
        return (M) service.insert(object);
    }

    @PutMapping("/edit")
    public M edit(@RequestBody M object) {
        return (M) service.update(object);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
