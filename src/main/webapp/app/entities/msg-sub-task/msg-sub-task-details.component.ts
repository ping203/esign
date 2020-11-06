import { Component, Vue, Inject } from 'vue-property-decorator';

import { IMsgSubTask } from '@/shared/model/msg-sub-task.model';
import MsgSubTaskService from './msg-sub-task.service';

@Component
export default class MsgSubTaskDetails extends Vue {
  @Inject('msgSubTaskService') private msgSubTaskService: () => MsgSubTaskService;
  public msgSubTask: IMsgSubTask = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.msgSubTaskId) {
        vm.retrieveMsgSubTask(to.params.msgSubTaskId);
      }
    });
  }

  public retrieveMsgSubTask(msgSubTaskId) {
    this.msgSubTaskService()
      .find(msgSubTaskId)
      .then(res => {
        this.msgSubTask = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
