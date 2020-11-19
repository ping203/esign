/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import MsgTaskService from '@/entities/msg-task/msg-task.service';
import { MsgTask, DdMessageType, MessageStatus } from '@/shared/model/msg-task.model';

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
  describe('MsgTask Service', () => {
    let service: MsgTaskService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new MsgTaskService();
      currentDate = new Date();

      elemDefault = new MsgTask(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        false,
        'AAAAAAA',
        currentDate,
        0,
        DdMessageType.Voice,
        MessageStatus.SentSuccessfully,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            executeTime: format(currentDate, DATE_TIME_FORMAT),
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

      it('should create a MsgTask', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            executeTime: format(currentDate, DATE_TIME_FORMAT),
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            executeTime: currentDate,
          },
          returnedFromService
        );

        mockedAxios.post.mockReturnValue(Promise.resolve({ data: returnedFromService }));
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MsgTask', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MsgTask', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            deptIdList: 'BBBBBB',
            useridList: 'BBBBBB',
            toAllUser: true,
            markdown: 'BBBBBB',
            singleTitle: 'BBBBBB',
            singleUrl: 'BBBBBB',
            pdfUrl: 'BBBBBB',
            coverUrl: 'BBBBBB',
            numberOfShards: 1,
            completeSharding: true,
            msg: 'BBBBBB',
            executeTime: format(currentDate, DATE_TIME_FORMAT),
            agentId: 1,
            type: 'BBBBBB',
            status: 'BBBBBB',
            progressInPercent: 1,
            key: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            executeTime: currentDate,
          },
          returnedFromService
        );
        mockedAxios.put.mockReturnValue(Promise.resolve({ data: returnedFromService }));

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MsgTask', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MsgTask', async () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            deptIdList: 'BBBBBB',
            useridList: 'BBBBBB',
            toAllUser: true,
            markdown: 'BBBBBB',
            singleTitle: 'BBBBBB',
            singleUrl: 'BBBBBB',
            pdfUrl: 'BBBBBB',
            coverUrl: 'BBBBBB',
            numberOfShards: 1,
            completeSharding: true,
            msg: 'BBBBBB',
            executeTime: format(currentDate, DATE_TIME_FORMAT),
            agentId: 1,
            type: 'BBBBBB',
            status: 'BBBBBB',
            progressInPercent: 1,
            key: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            executeTime: currentDate,
          },
          returnedFromService
        );
        mockedAxios.get.mockReturnValue(Promise.resolve([returnedFromService]));
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MsgTask', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MsgTask', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MsgTask', async () => {
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
