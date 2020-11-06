import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { IESignConfig, ESignConfig } from '@/shared/model/e-sign-config.model';
import ESignConfigService from './e-sign-config.service';

const validations: any = {
  eSignConfig: {
    name: {},
    time: {},
    channelNo: {},
    accessId: {},
    accessKeySecret: {},
  },
};

@Component({
  validations,
})
export default class ESignConfigUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('eSignConfigService') private eSignConfigService: () => ESignConfigService;
  public eSignConfig: IESignConfig = new ESignConfig();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.eSignConfigId) {
        vm.retrieveESignConfig(to.params.eSignConfigId);
      }
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
    if (this.eSignConfig.id) {
      this.eSignConfigService()
        .update(this.eSignConfig)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.eSignConfig.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.eSignConfigService()
        .create(this.eSignConfig)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.eSignConfig.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.eSignConfig[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.eSignConfig[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.eSignConfig[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.eSignConfig[field] = null;
    }
  }

  public retrieveESignConfig(eSignConfigId): void {
    this.eSignConfigService()
      .find(eSignConfigId)
      .then(res => {
        res.time = new Date(res.time);
        this.eSignConfig = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
