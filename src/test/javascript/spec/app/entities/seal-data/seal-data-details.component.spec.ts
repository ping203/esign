/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SealDataDetailComponent from '@/entities/seal-data/seal-data-details.vue';
import SealDataClass from '@/entities/seal-data/seal-data-details.component';
import SealDataService from '@/entities/seal-data/seal-data.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('SealData Management Detail Component', () => {
    let wrapper: Wrapper<SealDataClass>;
    let comp: SealDataClass;
    let sealDataServiceStub: SinonStubbedInstance<SealDataService>;

    beforeEach(() => {
      sealDataServiceStub = sinon.createStubInstance<SealDataService>(SealDataService);

      wrapper = shallowMount<SealDataClass>(SealDataDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { sealDataService: () => sealDataServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSealData = { id: 123 };
        sealDataServiceStub.find.resolves(foundSealData);

        // WHEN
        comp.retrieveSealData(123);
        await comp.$nextTick();

        // THEN
        expect(comp.sealData).toBe(foundSealData);
      });
    });
  });
});
