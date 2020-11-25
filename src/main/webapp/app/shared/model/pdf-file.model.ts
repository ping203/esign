import { IPdfSign } from '@/shared/model/pdf-sign.model';
import { IMsgTask } from '@/shared/model/msg-task.model';
import { IDdUser } from '@/shared/model/dd-user.model';

export interface IPdfFile {
  id?: number;
  name?: string;
  mediaType?: string;
  objName?: string;
  fileUrl?: string;
  key?: string;
  pdfSigns?: IPdfSign[];
  msgTasks?: IMsgTask[];
  creator?: IDdUser;
}

export class PdfFile implements IPdfFile {
  constructor(
    public id?: number,
    public name?: string,
    public mediaType?: string,
    public objName?: string,
    public fileUrl?: string,
    public key?: string,
    public pdfSigns?: IPdfSign[],
    public msgTasks?: IMsgTask[],
    public creator?: IDdUser
  ) {}
}
