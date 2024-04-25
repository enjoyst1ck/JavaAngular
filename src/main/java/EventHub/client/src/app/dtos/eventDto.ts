import { AttachmentDto } from "./attachmentDto";
import { FileHandle } from "./fileHandle";

export class EventDto {
  id: number = 0;
  name!: string;
  description!: string;
  startDate!: Date;
  endDate!: Date;
  attachments: AttachmentDto[] = [];
  static CLASS_NAME: string = 'EventDto';
}