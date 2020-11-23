import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import SealDataService from '../seal-data/seal-data.service';
import { ISealData } from '@/shared/model/seal-data.model';

import DdUserService from '../dd-user/dd-user.service';
import { IDdUser } from '@/shared/model/dd-user.model';

import PdfFileService from '../pdf-file/pdf-file.service';
import { IPdfFile } from '@/shared/model/pdf-file.model';

import MsgTaskService from '../msg-task/msg-task.service';
import { IMsgTask } from '@/shared/model/msg-task.model';

import AlertService from '@/shared/alert/alert.service';
import { IPdfSign, PdfSign } from '@/shared/model/pdf-sign.model';
import PdfSignService from './pdf-sign.service';

const validations: any = {
  pdfSign: {
    channelNo: {},
    accountId: {},
    pdfUrl: {},
    fileName: {},
    key: {},
    posType: {},
    posX: {},
    posY: {},
    width: {},
    signType: {},
    requestNo: {},
    requestTime: {},
    status: {},
    cycle: {},
    cycleUnit: {},
    retry: {},
    retrySwitch: {},
    retryCount: {},
    retryTime: {},
  },
};

@Component({
  validations,
})
export default class PdfSignUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('pdfSignService') private pdfSignService: () => PdfSignService;
  public pdfSign: IPdfSign = new PdfSign();

  @Inject('sealDataService') private sealDataService: () => SealDataService;

  public sealData: ISealData[] = [];

  @Inject('ddUserService') private ddUserService: () => DdUserService;

  public ddUsers: IDdUser[] = [];

  @Inject('pdfFileService') private pdfFileService: () => PdfFileService;

  public pdfFiles: IPdfFile[] = [];

  @Inject('msgTaskService') private msgTaskService: () => MsgTaskService;

  public msgTasks: IMsgTask[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pdfSignId) {
        vm.retrievePdfSign(to.params.pdfSignId);
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
    if (this.pdfSign.id) {
      this.pdfSignService()
        .update(this.pdfSign)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.pdfSign.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.pdfSignService()
        .create(this.pdfSign)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.pdfSign.created', { param: param.id });
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
      this.pdfSign[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.pdfSign[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.pdfSign[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.pdfSign[field] = null;
    }
  }

  public retrievePdfSign(pdfSignId): void {
    this.pdfSignService()
      .find(pdfSignId)
      .then(res => {
        res.requestTime = new Date(res.requestTime);
        res.retryTime = new Date(res.retryTime);
        this.pdfSign = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.sealDataService()
      .retrieve()
      .then(res => {
        this.sealData = res.data;
      });
    this.ddUserService()
      .retrieve()
      .then(res => {
        this.ddUsers = res.data;
      });
    this.pdfFileService()
      .retrieve()
      .then(res => {
        this.pdfFiles = res.data;
      });
    this.msgTaskService()
      .retrieve()
      .then(res => {
        this.msgTasks = res.data;
      });
  }
}
