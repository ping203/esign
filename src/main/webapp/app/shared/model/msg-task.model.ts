import { IMsgSubTask } from '@/shared/model/msg-sub-task.model';

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
}

export interface IMsgTask {
  id?: number;
  title?: string;
  deptIdList?: string;
  useridList?: string;
  toAllUser?: boolean;
  msg?: string;
  executeTime?: Date;
  agentId?: number;
  type?: DdMessageType;
  status?: MessageStatus;
  msgSubTasks?: IMsgSubTask[];
}

export class MsgTask implements IMsgTask {
  constructor(
    public id?: number,
    public title?: string,
    public deptIdList?: string,
    public useridList?: string,
    public toAllUser?: boolean,
    public msg?: string,
    public executeTime?: Date,
    public agentId?: number,
    public type?: DdMessageType,
    public status?: MessageStatus,
    public msgSubTasks?: IMsgSubTask[]
  ) {
    this.toAllUser = this.toAllUser || false;
  }
}