import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import PdfSignService from '../pdf-sign/pdf-sign.service';
import { IPdfSign } from '@/shared/model/pdf-sign.model';

import AlertService from '@/shared/alert/alert.service';
import { IDdUser, DdUser } from '@/shared/model/dd-user.model';
import DdUserService from './dd-user.service';

const validations: any = {
  ddUser: {
    unionid: {},
    remark: {},
    userid: {},
    isLeaderInDepts: {},
    isBoss: {},
    hiredDate: {},
    isSenior: {},
    tel: {},
    department: {},
    workPlace: {},
    orderInDepts: {},
    mobile: {},
    errmsg: {},
    active: {},
    avatar: {},
    isAdmin: {},
    isHide: {},
    jobnumber: {},
    name: {},
    extattr: {},
    stateCode: {},
    position: {},
    roles: {},
    accountId: {},
    idNumber: {},
  },
};

@Component({
  validations,
})
export default class DdUserUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('ddUserService') private ddUserService: () => DdUserService;
  public ddUser: IDdUser = new DdUser();

  @Inject('pdfSignService') private pdfSignService: () => PdfSignService;

  public pdfSigns: IPdfSign[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.ddUserId) {
        vm.retrieveDdUser(to.params.ddUserId);
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
    if (this.ddUser.id) {
      this.ddUserService()
        .update(this.ddUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.ddUser.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.ddUserService()
        .create(this.ddUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.ddUser.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrieveDdUser(ddUserId): void {
    this.ddUserService()
      .find(ddUserId)
      .then(res => {
        this.ddUser = res;
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
