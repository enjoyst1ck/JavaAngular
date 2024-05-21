package EventHub.services;

import EventHub.dtos.StaffDto;
import EventHub.mappers.StaffMapper;
import EventHub.models.Staff;
import EventHub.repositories.StaffRepository;
import org.springframework.stereotype.Service;

@Service
public class StaffService extends GenericService<Staff, StaffDto, StaffRepository, StaffMapper> {
}
