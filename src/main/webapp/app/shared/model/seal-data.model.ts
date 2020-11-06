import { IPdfSign } from '@/shared/model/pdf-sign.model';

export interface ISealData {
  id?: number;
  base64StrContentType?: string;
  base64Str?: any;
  pdfSign?: IPdfSign;
}

export class SealData implements ISealData {
  constructor(public id?: number, public base64StrContentType?: string, public base64Str?: any, public pdfSign?: IPdfSign) {}
}
