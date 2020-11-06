/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MsgTaskDetailComponent from '@/entities/msg-task/msg-task-details.vue';
import MsgTaskClass from '@/entities/msg-task/msg-task-details.component';
import MsgTaskService from '@/entities/msg-task/msg-task.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MsgTask Management Detail Component', () => {
    let wrapper: Wrapper<MsgTaskClass>;
    let comp: MsgTaskClass;
    let msgTaskServiceStub: SinonStubbedInstance<MsgTaskService>;

    beforeEach(() => {
      msgTaskServiceStub = sinon.createStubInstance<MsgTaskService>(MsgTaskService);

      wrapper = shallowMount<MsgTaskClass>(MsgTaskDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { msgTaskService: () => msgTaskServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMsgTask = { id: 123 };
        msgTaskServiceStub.find.resolves(foundMsgTask);

        // WHEN
        comp.retrieveMsgTask(123);
        await comp.$nextTick();

        // THEN
        expect(comp.msgTask).toBe(foundMsgTask);
      });
    });
  });
});
