package EventHub.services;

import EventHub.dtos.IDto;
import EventHub.mappers.IMapper;
import EventHub.models.IModel;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class GenericService<M extends IModel, D extends IDto, R extends JpaRepository<M, Integer>, Map extends IMapper<M, D>> {
    @Autowired
    private R repo;
    @Autowired
    private Map mapper;

    public List<D> getAll() {
        List<M> data = repo.findAll();

        return data.stream().map(mapper::toDto).toList();
    }

    public D getById(Integer id) throws Exception {
        Optional<M> object = repo.findById(id);

        if(object == null) {
            throw new Exception("Object not found");
        }

        return mapper.toDto(object.get());
    }
    public D insert(D objectDto) {
        var entity = mapper.toModel(objectDto);
        var savedEntity = repo.save(entity);
        return mapper.toDto(savedEntity);
    }

    public D update(D objectDto) {
        var entity = mapper.toModel(objectDto);
        var savedEntity = repo.save(entity);
        return mapper.toDto(savedEntity);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
