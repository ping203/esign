/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ESignConfigDetailComponent from '@/entities/e-sign-config/e-sign-config-details.vue';
import ESignConfigClass from '@/entities/e-sign-config/e-sign-config-details.component';
import ESignConfigService from '@/entities/e-sign-config/e-sign-config.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ESignConfig Management Detail Component', () => {
    let wrapper: Wrapper<ESignConfigClass>;
    let comp: ESignConfigClass;
    let eSignConfigServiceStub: SinonStubbedInstance<ESignConfigService>;

    beforeEach(() => {
      eSignConfigServiceStub = sinon.createStubInstance<ESignConfigService>(ESignConfigService);

      wrapper = shallowMount<ESignConfigClass>(ESignConfigDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { eSignConfigService: () => eSignConfigServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundESignConfig = { id: 123 };
        eSignConfigServiceStub.find.resolves(foundESignConfig);

        // WHEN
        comp.retrieveESignConfig(123);
        await comp.$nextTick();

        // THEN
        expect(comp.eSignConfig).toBe(foundESignConfig);
      });
    });
  });
});
