import { UserDto } from "./userDto";

export class LoggedUserDto {
  token!: string;
  userDto!: UserDto;
}