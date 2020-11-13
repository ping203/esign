export const enum HttpMethod {
  GET = 'GET',
  HEAD = 'HEAD',
  POST = 'POST',
  PUT = 'PUT',
  PATCH = 'PATCH',
  DELETE = 'DELETE',
  OPTIONS = 'OPTIONS',
  TRACE = 'TRACE',
}

export interface IApiInvokeLog {
  id?: number;
  login?: string;
  apiName?: string;
  method?: HttpMethod;
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
    public method?: HttpMethod,
    public httpStatusCode?: number,
    public time?: Date,
    public reqeustData?: any,
    public responseData?: any
  ) {}
}
