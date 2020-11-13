/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import ApiInvokeLogService from '@/entities/api-invoke-log/api-invoke-log.service';
import { ApiInvokeLog } from '@/shared/model/api-invoke-log.model';

const mockedAxios: any = axios;
const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

jest.mock('axios', () => ({
  get: jest.fn(),
  post: jest.fn(),
  put: jest.fn(),
  delete: jest.fn(),
}));

describe('Service Tests', () => {
  describe('ApiInvokeLog Service', () => {
    let service: ApiInvokeLogService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new ApiInvokeLogService();
      currentDate = new Date();

      elemDefault = new ApiInvokeLog(0, 'AAAAAAA', 'AAAAAAA', 0, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            time: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
        mockedAxios.get.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a ApiInvokeLog', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            time: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a ApiInvokeLog', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a ApiInvokeLog', async () => {
        const returnedFromService = Object.assign(
          {
            login: 'BBBBBB',
            apiName: 'BBBBBB',
            httpStatusCode: 1,
            time: format(currentDate, DATE_TIME_FORMAT),
            reqeustData: 'BBBBBB',
            responseData: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a ApiInvokeLog', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of ApiInvokeLog', async () => {
        const returnedFromService = Object.assign(
          {
            login: 'BBBBBB',
            apiName: 'BBBBBB',
            httpStatusCode: 1,
            time: format(currentDate, DATE_TIME_FORMAT),
            reqeustData: 'BBBBBB',
            responseData: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            time: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of ApiInvokeLog', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a ApiInvokeLog', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a ApiInvokeLog', async () => {
        mockedAxios.delete.mockReturnValue(Promise.reject(error));

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
