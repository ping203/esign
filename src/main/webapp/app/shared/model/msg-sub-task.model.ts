import { IMsgTask } from '@/shared/model/msg-task.model';

export const enum MessageStatus {
  SentSuccessfully = 'SentSuccessfully',
  Sending = 'Sending',
  NotSentYet = 'NotSentYet',
}

export interface IMsgSubTask {
  id?: number;
  useridList?: string;
  taskId?: number;
  time?: Date;
  rspMsg?: string;
  status?: number;
  progressInPercent?: number;
  subTaskStatus?: MessageStatus;
  invalidUserIdList?: string;
  forbiddenUserIdList?: string;
  failedUserIdList?: string;
  readUserIdList?: string;
  unreadUserIdList?: string;
  invalidDeptIdList?: string;
  withdraw?: boolean;
  msgTask?: IMsgTask;
}

export class MsgSubTask implements IMsgSubTask {
  constructor(
    public id?: number,
    public useridList?: string,
    public taskId?: number,
    public time?: Date,
    public rspMsg?: string,
    public status?: number,
    public progressInPercent?: number,
    public subTaskStatus?: MessageStatus,
    public invalidUserIdList?: string,
    public forbiddenUserIdList?: string,
    public failedUserIdList?: string,
    public readUserIdList?: string,
    public unreadUserIdList?: string,
    public invalidDeptIdList?: string,
    public withdraw?: boolean,
    public msgTask?: IMsgTask
  ) {
    this.withdraw = this.withdraw || false;
  }
}
