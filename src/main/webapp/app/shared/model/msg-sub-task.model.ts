import { IMsgTask } from '@/shared/model/msg-task.model';

export interface IMsgSubTask {
  id?: number;
  useridList?: string;
  taskId?: number;
  time?: Date;
  rsp?: string;
  msgTask?: IMsgTask;
}

export class MsgSubTask implements IMsgSubTask {
  constructor(
    public id?: number,
    public useridList?: string,
    public taskId?: number,
    public time?: Date,
    public rsp?: string,
    public msgTask?: IMsgTask
  ) {}
}
