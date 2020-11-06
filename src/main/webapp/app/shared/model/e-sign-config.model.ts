export interface IESignConfig {
  id?: number;
  name?: string;
  time?: Date;
  channelNo?: string;
  accessId?: string;
  accessKeySecret?: string;
}

export class ESignConfig implements IESignConfig {
  constructor(
    public id?: number,
    public name?: string,
    public time?: Date,
    public channelNo?: string,
    public accessId?: string,
    public accessKeySecret?: string
  ) {}
}
