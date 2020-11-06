/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import MsgSubTaskDetailComponent from '@/entities/msg-sub-task/msg-sub-task-details.vue';
import MsgSubTaskClass from '@/entities/msg-sub-task/msg-sub-task-details.component';
import MsgSubTaskService from '@/entities/msg-sub-task/msg-sub-task.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('MsgSubTask Management Detail Component', () => {
    let wrapper: Wrapper<MsgSubTaskClass>;
    let comp: MsgSubTaskClass;
    let msgSubTaskServiceStub: SinonStubbedInstance<MsgSubTaskService>;

    beforeEach(() => {
      msgSubTaskServiceStub = sinon.createStubInstance<MsgSubTaskService>(MsgSubTaskService);

      wrapper = shallowMount<MsgSubTaskClass>(MsgSubTaskDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { msgSubTaskService: () => msgSubTaskServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundMsgSubTask = { id: 123 };
        msgSubTaskServiceStub.find.resolves(foundMsgSubTask);

        // WHEN
        comp.retrieveMsgSubTask(123);
        await comp.$nextTick();

        // THEN
        expect(comp.msgSubTask).toBe(foundMsgSubTask);
      });
    });
  });
});
