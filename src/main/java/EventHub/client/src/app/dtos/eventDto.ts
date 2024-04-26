
import { ArtistDto } from "./artistDto";
import { AttachmentDto } from "./attachmentDto";
import { StuffDto } from "./stuffDto";

export class EventDto {
  id: number = 0;
  name!: string;
  description!: string;
  startDate!: Date;
  endDate!: Date;
  artists: ArtistDto[] = [];
  attachments: AttachmentDto[] = [];
  stuff: StuffDto[] = [];
  static CLASS_NAME: string = 'EventDto';
}