import { ISealData } from '@/shared/model/seal-data.model';
import { IDdUser } from '@/shared/model/dd-user.model';
import { IPdfFile } from '@/shared/model/pdf-file.model';

export interface IPdfSign {
  id?: number;
  channelNo?: string;
  accountId?: string;
  pdfUrl?: string;
  fileName?: string;
  key?: string;
  posType?: string;
  posX?: number;
  posY?: number;
  width?: number;
  signType?: string;
  requestNo?: string;
  requestTime?: Date;
  sealData?: ISealData[];
  ddUser?: IDdUser;
  pdfFile?: IPdfFile;
}

export class PdfSign implements IPdfSign {
  constructor(
    public id?: number,
    public channelNo?: string,
    public accountId?: string,
    public pdfUrl?: string,
    public fileName?: string,
    public key?: string,
    public posType?: string,
    public posX?: number,
    public posY?: number,
    public width?: number,
    public signType?: string,
    public requestNo?: string,
    public requestTime?: Date,
    public sealData?: ISealData[],
    public ddUser?: IDdUser,
    public pdfFile?: IPdfFile
  ) {}
}
