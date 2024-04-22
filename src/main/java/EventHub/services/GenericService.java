package EventHub.services;

import EventHub.models.IModel;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public abstract class GenericService<M extends IModel, R extends JpaRepository<M, Integer>> {
    @Autowired
    private R repo;
    public List<M> getAll() {
        return repo.findAll();
    }

    public M getById(Integer id) throws Exception {
        Optional<M> object = repo.findById(id);

        if(object == null) {
            throw new Exception("Object not found");
        }

        return object.get();
    }
    public M insert(M object) {
        return repo.save(object);
    }

    public M update(M object) {
        return repo.save(object);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
