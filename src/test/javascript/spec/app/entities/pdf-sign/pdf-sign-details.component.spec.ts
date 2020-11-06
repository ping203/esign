/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PdfSignDetailComponent from '@/entities/pdf-sign/pdf-sign-details.vue';
import PdfSignClass from '@/entities/pdf-sign/pdf-sign-details.component';
import PdfSignService from '@/entities/pdf-sign/pdf-sign.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('PdfSign Management Detail Component', () => {
    let wrapper: Wrapper<PdfSignClass>;
    let comp: PdfSignClass;
    let pdfSignServiceStub: SinonStubbedInstance<PdfSignService>;

    beforeEach(() => {
      pdfSignServiceStub = sinon.createStubInstance<PdfSignService>(PdfSignService);

      wrapper = shallowMount<PdfSignClass>(PdfSignDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { pdfSignService: () => pdfSignServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPdfSign = { id: 123 };
        pdfSignServiceStub.find.resolves(foundPdfSign);

        // WHEN
        comp.retrievePdfSign(123);
        await comp.$nextTick();

        // THEN
        expect(comp.pdfSign).toBe(foundPdfSign);
      });
    });
  });
});
