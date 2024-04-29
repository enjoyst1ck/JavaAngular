import { AttachmentDto } from "./attachmentDto";
import { EventDto } from "./eventDto";

export class ArtistDto {
  id: number = 0;
  name!: string;
  attachments: AttachmentDto[] = [];
}