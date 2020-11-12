import { IPdfSign } from '@/shared/model/pdf-sign.model';
import { IMsgTask } from '@/shared/model/msg-task.model';

export interface IPdfFile {
  id?: number;
  name?: string;
  mediaType?: string;
  objName?: string;
  fileUrl?: string;
  pdfSigns?: IPdfSign[];
  msgTasks?: IMsgTask[];
}

export class PdfFile implements IPdfFile {
  constructor(
    public id?: number,
    public name?: string,
    public mediaType?: string,
    public objName?: string,
    public fileUrl?: string,
    public pdfSigns?: IPdfSign[],
    public msgTasks?: IMsgTask[]
  ) {}
}
