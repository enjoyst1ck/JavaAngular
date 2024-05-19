package EventHub.services;

import EventHub.models.Staff;
import EventHub.repositories.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService extends GenericService<Staff, StaffRepository> {
}
