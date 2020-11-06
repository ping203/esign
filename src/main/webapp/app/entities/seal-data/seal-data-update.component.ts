import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import PdfSignService from '../pdf-sign/pdf-sign.service';
import { IPdfSign } from '@/shared/model/pdf-sign.model';

import AlertService from '@/shared/alert/alert.service';
import { ISealData, SealData } from '@/shared/model/seal-data.model';
import SealDataService from './seal-data.service';

const validations: any = {
  sealData: {
    base64Str: {},
  },
};

@Component({
  validations,
})
export default class SealDataUpdate extends mixins(JhiDataUtils) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('sealDataService') private sealDataService: () => SealDataService;
  public sealData: ISealData = new SealData();

  @Inject('pdfSignService') private pdfSignService: () => PdfSignService;

  public pdfSigns: IPdfSign[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.sealDataId) {
        vm.retrieveSealData(to.params.sealDataId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.sealData.id) {
      this.sealDataService()
        .update(this.sealData)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.sealData.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.sealDataService()
        .create(this.sealData)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.sealData.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveSealData(sealDataId): void {
    this.sealDataService()
      .find(sealDataId)
      .then(res => {
        this.sealData = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.pdfSignService()
      .retrieve()
      .then(res => {
        this.pdfSigns = res.data;
      });
  }
}
