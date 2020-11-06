/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import PdfFileUpdateComponent from '@/entities/pdf-file/pdf-file-update.vue';
import PdfFileClass from '@/entities/pdf-file/pdf-file-update.component';
import PdfFileService from '@/entities/pdf-file/pdf-file.service';

import PdfSignService from '@/entities/pdf-sign/pdf-sign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('PdfFile Management Update Component', () => {
    let wrapper: Wrapper<PdfFileClass>;
    let comp: PdfFileClass;
    let pdfFileServiceStub: SinonStubbedInstance<PdfFileService>;

    beforeEach(() => {
      pdfFileServiceStub = sinon.createStubInstance<PdfFileService>(PdfFileService);

      wrapper = shallowMount<PdfFileClass>(PdfFileUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          pdfFileService: () => pdfFileServiceStub,

          pdfSignService: () => new PdfSignService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.pdfFile = entity;
        pdfFileServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pdfFileServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.pdfFile = entity;
        pdfFileServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(pdfFileServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
