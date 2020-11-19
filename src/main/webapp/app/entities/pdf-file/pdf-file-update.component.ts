import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue } from 'vuelidate/lib/validators';

import PdfSignService from '../pdf-sign/pdf-sign.service';
import { IPdfSign } from '@/shared/model/pdf-sign.model';

import AlertService from '@/shared/alert/alert.service';
import { IPdfFile, PdfFile } from '@/shared/model/pdf-file.model';
import PdfFileService from './pdf-file.service';

const validations: any = {
  pdfFile: {
    name: {},
    mediaType: {},
    objName: {},
    fileUrl: {},
  },
};

@Component({
  validations,
})
export default class PdfFileUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('pdfFileService') private pdfFileService: () => PdfFileService;
  public pdfFile: IPdfFile = new PdfFile();

  @Inject('pdfSignService') private pdfSignService: () => PdfSignService;

  public pdfSigns: IPdfSign[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pdfFileId) {
        vm.retrievePdfFile(to.params.pdfFileId);
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
    if (this.pdfFile.id) {
      this.pdfFileService()
        .update(this.pdfFile)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.pdfFile.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
        });
    } else {
      this.pdfFileService()
        .create(this.pdfFile)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('esignApp.pdfFile.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
        });
    }
  }

  public retrievePdfFile(pdfFileId): void {
    this.pdfFileService()
      .find(pdfFileId)
      .then(res => {
        this.pdfFile = res;
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
