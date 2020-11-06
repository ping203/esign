/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';

import AlertService from '@/shared/alert/alert.service';
import * as config from '@/shared/config/config';
import SealDataUpdateComponent from '@/entities/seal-data/seal-data-update.vue';
import SealDataClass from '@/entities/seal-data/seal-data-update.component';
import SealDataService from '@/entities/seal-data/seal-data.service';

import PdfSignService from '@/entities/pdf-sign/pdf-sign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.component('font-awesome-icon', {});

describe('Component Tests', () => {
  describe('SealData Management Update Component', () => {
    let wrapper: Wrapper<SealDataClass>;
    let comp: SealDataClass;
    let sealDataServiceStub: SinonStubbedInstance<SealDataService>;

    beforeEach(() => {
      sealDataServiceStub = sinon.createStubInstance<SealDataService>(SealDataService);

      wrapper = shallowMount<SealDataClass>(SealDataUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          alertService: () => new AlertService(store),
          sealDataService: () => sealDataServiceStub,

          pdfSignService: () => new PdfSignService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.sealData = entity;
        sealDataServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sealDataServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.sealData = entity;
        sealDataServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sealDataServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });
  });
});
