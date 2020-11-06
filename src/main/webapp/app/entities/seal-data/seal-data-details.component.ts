import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { ISealData } from '@/shared/model/seal-data.model';
import SealDataService from './seal-data.service';

@Component
export default class SealDataDetails extends mixins(JhiDataUtils) {
  @Inject('sealDataService') private sealDataService: () => SealDataService;
  public sealData: ISealData = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sealDataId) {
        vm.retrieveSealData(to.params.sealDataId);
      }
    });
  }

  public retrieveSealData(sealDataId) {
    this.sealDataService()
      .find(sealDataId)
      .then(res => {
        this.sealData = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
