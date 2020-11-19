/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import format from 'date-fns/format';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import PdfSignUpdateComponent from '@/entities/pdf-sign/pdf-sign-update.vue';
import PdfSignClass from '@/entities/pdf-sign/pdf-sign-update.component';
import PdfSignService from '@/entities/pdf-sign/pdf-sign.service';

import SealDataService from '@/entities/seal-data/seal-data.service';

import DdUserService from '@/entities/dd-user/dd-user.service';

import PdfFileService from '@/entities/pdf-file/pdf-file.service';

import MsgTaskService from '@/entities/msg-task/msg-task.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('PdfSign Management Update Component', () => {
    let wrapper: Wrapper<PdfSignClass>;
    let comp: PdfSignClass;
    let pdfSignServiceStub: SinonStubbedInstance<PdfSignService>;

    beforeEach(() => {
      pdfSignServiceStub = sinon.createStubInstance<PdfSignService>(PdfSignService);

      wrapper = shallowMount<PdfSignClass>(PdfSignUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          pdfSignService: () => pdfSignServiceStub,

          sealDataService: () => new SealDataService(),

          ddUserService: () => new DdUserService(),

          pdfFileService: () => new PdfFileService(),

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
        comp.pdfSign = entity;
        pdfSignServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pdfSignServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.pdfSign = entity;
        pdfSignServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pdfSignServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
