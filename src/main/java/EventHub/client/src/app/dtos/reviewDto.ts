import { UserDto } from "./userDto";

export class ReviewDto {
  id: number = 0;
  date!: Date;
  comment!: string;
  user!: UserDto;
  eventName!: string;
}