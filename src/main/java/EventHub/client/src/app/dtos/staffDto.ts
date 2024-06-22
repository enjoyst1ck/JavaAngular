import { AttachmentDto } from "./attachmentDto";

export class StaffDto {
  id!: number;
  name!: string;
  attachments: AttachmentDto[] = [];
  events: Event[] = [];
}