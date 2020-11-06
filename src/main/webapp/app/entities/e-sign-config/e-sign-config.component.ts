import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IESignConfig } from '@/shared/model/e-sign-config.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import ESignConfigService from './e-sign-config.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ESignConfig extends mixins(AlertMixin) {
  @Inject('eSignConfigService') private eSignConfigService: () => ESignConfigService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public eSignConfigs: IESignConfig[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllESignConfigs();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllESignConfigs();
  }

  public retrieveAllESignConfigs(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.eSignConfigService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.eSignConfigs = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IESignConfig): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeESignConfig(): void {
    this.eSignConfigService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('esignApp.eSignConfig.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllESignConfigs();
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
    this.retrieveAllESignConfigs();
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
