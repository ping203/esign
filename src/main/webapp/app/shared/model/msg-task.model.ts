import { IMsgSubTask } from '@/shared/model/msg-sub-task.model';
import { IDdUser } from '@/shared/model/dd-user.model';
import { IPdfFile } from '@/shared/model/pdf-file.model';

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
  sender?: IDdUser;
  pdfFile?: IPdfFile;
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
    public sender?: IDdUser,
    public pdfFile?: IPdfFile
  ) {
    this.toAllUser = this.toAllUser || false;
    this.completeSharding = this.completeSharding || false;
  }
}
