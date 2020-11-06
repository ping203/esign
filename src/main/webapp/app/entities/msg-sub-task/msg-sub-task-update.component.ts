import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import MsgTaskService from '../msg-task/msg-task.service';
import { IMsgTask } from '@/shared/model/msg-task.model';

import AlertService from '@/shared/alert/alert.service';
import { IMsgSubTask, MsgSubTask } from '@/shared/model/msg-sub-task.model';
import MsgSubTaskService from './msg-sub-task.service';

const validations: any = {
  msgSubTask: {
    useridList: {},
    taskId: {},
    time: {},
    rspMsg: {},
    status: {},
    progressInPercent: {},
    subTaskStatus: {},
    invalidUserIdList: {},
    forbiddenUserIdList: {},
    failedUserIdList: {},
    readUserIdList: {},
    unreadUserIdList: {},
    invalidDeptIdList: {},
    withdraw: {},
  },
};

@Component({
  validations,
})
export default class MsgSubTaskUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('msgSubTaskService') private msgSubTaskService: () => MsgSubTaskService;
  public msgSubTask: IMsgSubTask = new MsgSubTask();

  @Inject('msgTaskService') private msgTaskService: () => MsgTaskService;

  public msgTasks: IMsgTask[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.msgSubTaskId) {
        vm.retrieveMsgSubTask(to.params.msgSubTaskId);
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
    if (this.msgSubTask.id) {
      this.msgSubTaskService()
        .update(this.msgSubTask)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.msgSubTask.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.msgSubTaskService()
        .create(this.msgSubTask)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.msgSubTask.created', { param: param.id });
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
      this.msgSubTask[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.msgSubTask[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.msgSubTask[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.msgSubTask[field] = null;
    }
  }

  public retrieveMsgSubTask(msgSubTaskId): void {
    this.msgSubTaskService()
      .find(msgSubTaskId)
      .then(res => {
        res.time = new Date(res.time);
        this.msgSubTask = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.msgTaskService()
      .retrieve()
      .then(res => {
        this.msgTasks = res.data;
      });
  }
}
