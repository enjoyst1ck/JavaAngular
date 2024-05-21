package EventHub.mappers;

import EventHub.dtos.AttachmentDto;
import EventHub.models.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper implements IMapper<Attachment, AttachmentDto> {
    @Override
    public Attachment toModel(AttachmentDto attachmentDto) {
        Attachment attachment = new Attachment();
        attachment.setId(attachmentDto.getId());
        attachment.setFileName(attachmentDto.getFileName());
        attachment.setImage(attachmentDto.getImage());

        return attachment;
    }

    @Override
    public AttachmentDto toDto(Attachment attachment) {
        AttachmentDto attachmentDto = new AttachmentDto();
        attachmentDto.setId(attachment.getId());
        attachmentDto.setFileName(attachment.getFileName());
        attachmentDto.setImage(attachment.getImage());

        return attachmentDto;

    }
}
