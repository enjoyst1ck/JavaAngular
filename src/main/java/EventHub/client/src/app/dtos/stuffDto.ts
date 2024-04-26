import { AttachmentDto } from "./attachmentDto";

export class StuffDto {
  id!: number;
  name!: string;
  attachments: AttachmentDto[] = [];
  events: Event[] = [];
}