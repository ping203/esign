/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import PdfFileDetailComponent from '@/entities/pdf-file/pdf-file-details.vue';
import PdfFileClass from '@/entities/pdf-file/pdf-file-details.component';
import PdfFileService from '@/entities/pdf-file/pdf-file.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('PdfFile Management Detail Component', () => {
    let wrapper: Wrapper<PdfFileClass>;
    let comp: PdfFileClass;
    let pdfFileServiceStub: SinonStubbedInstance<PdfFileService>;

    beforeEach(() => {
      pdfFileServiceStub = sinon.createStubInstance<PdfFileService>(PdfFileService);

      wrapper = shallowMount<PdfFileClass>(PdfFileDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { pdfFileService: () => pdfFileServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundPdfFile = { id: 123 };
        pdfFileServiceStub.find.resolves(foundPdfFile);

        // WHEN
        comp.retrievePdfFile(123);
        await comp.$nextTick();

        // THEN
        expect(comp.pdfFile).toBe(foundPdfFile);
      });
    });
  });
});
