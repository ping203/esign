/* tslint:disable max-line-length */
import axios from 'axios';
import { format } from 'date-fns';

import * as config from '@/shared/config/config';
import { DATE_TIME_FORMAT } from '@/shared/date/filters';
import MsgSubTaskService from '@/entities/msg-sub-task/msg-sub-task.service';
import { MsgSubTask, MessageStatus } from '@/shared/model/msg-sub-task.model';

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
  describe('MsgSubTask Service', () => {
    let service: MsgSubTaskService;
    let elemDefault;
    let currentDate: Date;
    beforeEach(() => {
      service = new MsgSubTaskService();
      currentDate = new Date();

      elemDefault = new MsgSubTask(
        0,
        'AAAAAAA',
        0,
        currentDate,
        'AAAAAAA',
        0,
        0,
        MessageStatus.SentSuccessfully,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false
      );
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

      it('should create a MsgSubTask', async () => {
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

      it('should not create a MsgSubTask', async () => {
        mockedAxios.post.mockReturnValue(Promise.reject(error));

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MsgSubTask', async () => {
        const returnedFromService = Object.assign(
          {
            useridList: 'BBBBBB',
            taskId: 1,
            time: format(currentDate, DATE_TIME_FORMAT),
            rspMsg: 'BBBBBB',
            status: 1,
            progressInPercent: 1,
            subTaskStatus: 'BBBBBB',
            invalidUserIdList: 'BBBBBB',
            forbiddenUserIdList: 'BBBBBB',
            failedUserIdList: 'BBBBBB',
            readUserIdList: 'BBBBBB',
            unreadUserIdList: 'BBBBBB',
            invalidDeptIdList: 'BBBBBB',
            withdraw: true,
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

      it('should not update a MsgSubTask', async () => {
        mockedAxios.put.mockReturnValue(Promise.reject(error));

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MsgSubTask', async () => {
        const returnedFromService = Object.assign(
          {
            useridList: 'BBBBBB',
            taskId: 1,
            time: format(currentDate, DATE_TIME_FORMAT),
            rspMsg: 'BBBBBB',
            status: 1,
            progressInPercent: 1,
            subTaskStatus: 'BBBBBB',
            invalidUserIdList: 'BBBBBB',
            forbiddenUserIdList: 'BBBBBB',
            failedUserIdList: 'BBBBBB',
            readUserIdList: 'BBBBBB',
            unreadUserIdList: 'BBBBBB',
            invalidDeptIdList: 'BBBBBB',
            withdraw: true,
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

      it('should not return a list of MsgSubTask', async () => {
        mockedAxios.get.mockReturnValue(Promise.reject(error));

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MsgSubTask', async () => {
        mockedAxios.delete.mockReturnValue(Promise.resolve({ ok: true }));
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MsgSubTask', async () => {
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
