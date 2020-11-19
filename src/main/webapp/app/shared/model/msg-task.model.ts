import { IMsgSubTask } from '@/shared/model/msg-sub-task.model';
import { IPdfSign } from '@/shared/model/pdf-sign.model';
import { IDdUser } from '@/shared/model/dd-user.model';

export const enum DdMessageType {
  Voice = 'Voice',
  ActionCard = 'ActionCard',
  Markdown = 'Markdown',
  Oa = 'Oa',
  Link = 'Link',
  File = 'File',
  Text = 'Text',
  Image = 'Image',
}

export const enum MessageStatus {
  SentSuccessfully = 'SentSuccessfully',
  Sending = 'Sending',
  NotSentYet = 'NotSentYet',
  Withdraw = 'Withdraw',
}

export interface IMsgTask {
  id?: number;
  title?: string;
  deptIdList?: string;
  useridList?: string;
  toAllUser?: boolean;
  markdown?: string;
  singleTitle?: string;
  singleUrl?: string;
  pdfUrl?: string;
  coverUrl?: string;
  numberOfShards?: number;
  completeSharding?: boolean;
  msg?: string;
  executeTime?: Date;
  agentId?: number;
  type?: DdMessageType;
  status?: MessageStatus;
  progressInPercent?: number;
  msgSubTasks?: IMsgSubTask[];
  pdfSigns?: IPdfSign[];
  sender?: IDdUser;
}

export class MsgTask implements IMsgTask {
  constructor(
    public id?: number,
    public title?: string,
    public deptIdList?: string,
    public useridList?: string,
    public toAllUser?: boolean,
    public markdown?: string,
    public singleTitle?: string,
    public singleUrl?: string,
    public pdfUrl?: string,
    public coverUrl?: string,
    public numberOfShards?: number,
    public completeSharding?: boolean,
    public msg?: string,
    public executeTime?: Date,
    public agentId?: number,
    public type?: DdMessageType,
    public status?: MessageStatus,
    public progressInPercent?: number,
    public msgSubTasks?: IMsgSubTask[],
    public pdfSigns?: IPdfSign[],
    public sender?: IDdUser
  ) {
    this.toAllUser = this.toAllUser || false;
    this.completeSharding = this.completeSharding || false;
  }
}
