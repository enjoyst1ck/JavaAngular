export class AttachmentDto {
  id: number = 0;
  fileName!: string;
  image!: Uint8Array;
  CLASS_NAME: string = 'AttachmentDto';
}