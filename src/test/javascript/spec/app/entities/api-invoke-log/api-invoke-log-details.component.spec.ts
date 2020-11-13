/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import ApiInvokeLogDetailComponent from '@/entities/api-invoke-log/api-invoke-log-details.vue';
import ApiInvokeLogClass from '@/entities/api-invoke-log/api-invoke-log-details.component';
import ApiInvokeLogService from '@/entities/api-invoke-log/api-invoke-log.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('ApiInvokeLog Management Detail Component', () => {
    let wrapper: Wrapper<ApiInvokeLogClass>;
    let comp: ApiInvokeLogClass;
    let apiInvokeLogServiceStub: SinonStubbedInstance<ApiInvokeLogService>;

    beforeEach(() => {
      apiInvokeLogServiceStub = sinon.createStubInstance<ApiInvokeLogService>(ApiInvokeLogService);

      wrapper = shallowMount<ApiInvokeLogClass>(ApiInvokeLogDetailComponent, {
        store,
        i18n,
        localVue,
        provide: { apiInvokeLogService: () => apiInvokeLogServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundApiInvokeLog = { id: 123 };
        apiInvokeLogServiceStub.find.resolves(foundApiInvokeLog);

        // WHEN
        comp.retrieveApiInvokeLog(123);
        await comp.$nextTick();

        // THEN
        expect(comp.apiInvokeLog).toBe(foundApiInvokeLog);
      });
    });
  });
});
