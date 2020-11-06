/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import MsgSubTaskUpdateComponent from '@/entities/msg-sub-task/msg-sub-task-update.vue';
import MsgSubTaskClass from '@/entities/msg-sub-task/msg-sub-task-update.component';
import MsgSubTaskService from '@/entities/msg-sub-task/msg-sub-task.service';

import MsgTaskService from '@/entities/msg-task/msg-task.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('MsgSubTask Management Update Component', () => {
    let wrapper: Wrapper<MsgSubTaskClass>;
    let comp: MsgSubTaskClass;
    let msgSubTaskServiceStub: SinonStubbedInstance<MsgSubTaskService>;

    beforeEach(() => {
      msgSubTaskServiceStub = sinon.createStubInstance<MsgSubTaskService>(MsgSubTaskService);

      wrapper = shallowMount<MsgSubTaskClass>(MsgSubTaskUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          msgSubTaskService: () => msgSubTaskServiceStub,

          msgTaskService: () => new MsgTaskService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(format(date, DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.msgSubTask = entity;
        msgSubTaskServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(msgSubTaskServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.msgSubTask = entity;
        msgSubTaskServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(msgSubTaskServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
