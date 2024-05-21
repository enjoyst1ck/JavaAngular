package EventHub.controllers;

import EventHub.dtos.IDto;
import EventHub.mappers.IMapper;
import EventHub.models.IModel;
import EventHub.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public abstract class GenericController<M extends IModel, D extends IDto, S extends GenericService> {
    @Autowired
    private S service;

    @GetMapping("/getall")
    public List<D> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public D getById(@PathVariable("id") Integer id) throws Exception {
        return (D) service.getById(id);
    }

    @PostMapping("/add")
    public D add(@RequestBody D objectDto) {
        return (D) service.insert(objectDto);
    }

    @PutMapping("/edit")
    public D edit(@RequestBody D objectDto) {
        return (D) service.update(objectDto);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
