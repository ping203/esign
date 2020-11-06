import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IDdUser } from '@/shared/model/dd-user.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import DdUserService from './dd-user.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class DdUser extends mixins(AlertMixin) {
  @Inject('ddUserService') private ddUserService: () => DdUserService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public ddUsers: IDdUser[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllDdUsers();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllDdUsers();
  }

  public retrieveAllDdUsers(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.ddUserService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.ddUsers = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IDdUser): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeDdUser(): void {
    this.ddUserService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('esignApp.ddUser.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllDdUsers();
        this.closeDialog();
      });
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllDdUsers();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
