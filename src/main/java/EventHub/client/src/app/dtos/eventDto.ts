
import { ArtistDto } from "./artistDto";
import { AttachmentDto } from "./attachmentDto";
import { StaffDto } from "./staffDto";
import { VenueDto } from "./venueDto";

export class EventDto {
  id: number = 0;
  name!: string;
  description!: string;
  startDate!: Date;
  endDate!: Date;
  venue?: VenueDto;
  artists: ArtistDto[] = [];
  attachments: AttachmentDto[] = [];
  staff: StaffDto[] = [];
  static CLASS_NAME: string = 'EventDto';
}
