import { AttachmentDto } from "./attachmentDto";

export class EventDto {
  id: number = 0;
  startDate!: Date;
  endDate!: Date;
  description!: string;
  attachments: AttachmentDto[] = [];
}