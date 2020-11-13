import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';
import { IApiInvokeLog, ApiInvokeLog } from '@/shared/model/api-invoke-log.model';
import ApiInvokeLogService from './api-invoke-log.service';

const validations: any = {
  apiInvokeLog: {
    login: {},
    apiName: {},
    method: {},
    httpStatusCode: {},
    time: {},
    reqeustData: {},
    responseData: {},
  },
};

@Component({
  validations,
})
export default class ApiInvokeLogUpdate extends mixins(JhiDataUtils) {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('apiInvokeLogService') private apiInvokeLogService: () => ApiInvokeLogService;
  public apiInvokeLog: IApiInvokeLog = new ApiInvokeLog();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.apiInvokeLogId) {
        vm.retrieveApiInvokeLog(to.params.apiInvokeLogId);
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
    if (this.apiInvokeLog.id) {
      this.apiInvokeLogService()
        .update(this.apiInvokeLog)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.apiInvokeLog.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.apiInvokeLogService()
        .create(this.apiInvokeLog)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.apiInvokeLog.created', { param: param.id });
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
      this.apiInvokeLog[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.apiInvokeLog[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.apiInvokeLog[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.apiInvokeLog[field] = null;
    }
  }

  public retrieveApiInvokeLog(apiInvokeLogId): void {
    this.apiInvokeLogService()
      .find(apiInvokeLogId)
      .then(res => {
        res.time = new Date(res.time);
        this.apiInvokeLog = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
