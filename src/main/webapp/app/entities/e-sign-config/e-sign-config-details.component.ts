import { Component, Vue, Inject } from 'vue-property-decorator';

import { IESignConfig } from '@/shared/model/e-sign-config.model';
import ESignConfigService from './e-sign-config.service';

@Component
export default class ESignConfigDetails extends Vue {
  @Inject('eSignConfigService') private eSignConfigService: () => ESignConfigService;
  public eSignConfig: IESignConfig = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eSignConfigId) {
        vm.retrieveESignConfig(to.params.eSignConfigId);
      }
    });
  }

  public retrieveESignConfig(eSignConfigId) {
    this.eSignConfigService()
      .find(eSignConfigId)
      .then(res => {
        this.eSignConfig = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
