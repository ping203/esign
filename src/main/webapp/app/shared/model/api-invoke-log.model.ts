export interface IApiInvokeLog {
  id?: number;
  login?: string;
  apiName?: string;
  httpStatusCode?: number;
  time?: Date;
  reqeustData?: any;
  responseData?: any;
}

export class ApiInvokeLog implements IApiInvokeLog {
  constructor(
    public id?: number,
    public login?: string,
    public apiName?: string,
    public httpStatusCode?: number,
    public time?: Date,
    public reqeustData?: any,
    public responseData?: any
  ) {}
}
