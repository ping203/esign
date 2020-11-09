import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import MsgSubTaskService from '../msg-sub-task/msg-sub-task.service';
import { IMsgSubTask } from '@/shared/model/msg-sub-task.model';

import DdUserService from '../dd-user/dd-user.service';
import { IDdUser } from '@/shared/model/dd-user.model';

import AlertService from '@/shared/alert/alert.service';
import { IMsgTask, MsgTask } from '@/shared/model/msg-task.model';
import MsgTaskService from './msg-task.service';

const validations: any = {
  msgTask: {
    title: {},
    deptIdList: {},
    useridList: {},
    toAllUser: {},
    markdown: {},
    singleTitle: {},
    singleUrl: {},
    coverUrl: {},
    numberOfShards: {},
    completeSharding: {},
    msg: {},
    executeTime: {},
    agentId: {},
    type: {},
    status: {},
    progressInPercent: {},
  },
};

@Component({
  validations,
})
export default class MsgTaskUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('msgTaskService') private msgTaskService: () => MsgTaskService;
  public msgTask: IMsgTask = new MsgTask();

  @Inject('msgSubTaskService') private msgSubTaskService: () => MsgSubTaskService;

  public msgSubTasks: IMsgSubTask[] = [];

  @Inject('ddUserService') private ddUserService: () => DdUserService;

  public ddUsers: IDdUser[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.msgTaskId) {
        vm.retrieveMsgTask(to.params.msgTaskId);
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
    if (this.msgTask.id) {
      this.msgTaskService()
        .update(this.msgTask)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.msgTask.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.msgTaskService()
        .create(this.msgTask)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.msgTask.created', { param: param.id });
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
      this.msgTask[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.msgTask[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.msgTask[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.msgTask[field] = null;
    }
  }

  public retrieveMsgTask(msgTaskId): void {
    this.msgTaskService()
      .find(msgTaskId)
      .then(res => {
        res.executeTime = new Date(res.executeTime);
        this.msgTask = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.msgSubTaskService()
      .retrieve()
      .then(res => {
        this.msgSubTasks = res.data;
      });
    this.ddUserService()
      .retrieve()
      .then(res => {
        this.ddUsers = res.data;
      });
  }
}
