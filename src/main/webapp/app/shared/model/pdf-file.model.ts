import { IPdfSign } from '@/shared/model/pdf-sign.model';

export interface IPdfFile {
  id?: number;
  name?: string;
  mediaType?: string;
  objName?: string;
  fileUrl?: string;
  pdfSigns?: IPdfSign[];
}

export class PdfFile implements IPdfFile {
  constructor(
    public id?: number,
    public name?: string,
    public mediaType?: string,
    public objName?: string,
    public fileUrl?: string,
    public pdfSigns?: IPdfSign[]
  ) {}
}
