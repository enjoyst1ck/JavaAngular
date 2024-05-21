package EventHub.mappers;

import EventHub.dtos.StaffDto;
import EventHub.models.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper implements IMapper<Staff, StaffDto> {
    @Override
    public Staff toModel(StaffDto staffDto) {
        Staff staff = new Staff();
        staff.setId(staffDto.getId());
        staff.setName(staffDto.getName());
        staff.setAttachments(staffDto.getAttachments());
        staff.setEvents(staffDto.getEvents());

        return staff;
    }

    @Override
    public StaffDto toDto(Staff staff) {
        StaffDto staffDto = new StaffDto();
        staffDto.setId(staff.getId());
        staffDto.setName(staff.getName());
        staffDto.setAttachments(staff.getAttachments());
        staffDto.setEvents(staff.getEvents());

        return staffDto;
    }
}
