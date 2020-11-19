/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import DdUserUpdateComponent from '@/entities/dd-user/dd-user-update.vue';
import DdUserClass from '@/entities/dd-user/dd-user-update.component';
import DdUserService from '@/entities/dd-user/dd-user.service';

import PdfSignService from '@/entities/pdf-sign/pdf-sign.service';

import MsgTaskService from '@/entities/msg-task/msg-task.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('DdUser Management Update Component', () => {
    let wrapper: Wrapper<DdUserClass>;
    let comp: DdUserClass;
    let ddUserServiceStub: SinonStubbedInstance<DdUserService>;

    beforeEach(() => {
      ddUserServiceStub = sinon.createStubInstance<DdUserService>(DdUserService);

      wrapper = shallowMount<DdUserClass>(DdUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          ddUserService: () => ddUserServiceStub,

          pdfSignService: () => new PdfSignService(),

          msgTaskService: () => new MsgTaskService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.ddUser = entity;
        ddUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.ddUser = entity;
        ddUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(ddUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
