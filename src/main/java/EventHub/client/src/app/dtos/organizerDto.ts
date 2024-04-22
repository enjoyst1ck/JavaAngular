import { AttachmentDto } from "./attachmentDto";
import { EventDto } from "./eventDto";

export class OrganizerDto {
  id: number = 0;
  name!: string;
  attachments: AttachmentDto[] = [];
  events: EventDto[] = [];
}