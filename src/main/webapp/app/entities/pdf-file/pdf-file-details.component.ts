import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPdfFile } from '@/shared/model/pdf-file.model';
import PdfFileService from './pdf-file.service';

@Component
export default class PdfFileDetails extends Vue {
  @Inject('pdfFileService') private pdfFileService: () => PdfFileService;
  public pdfFile: IPdfFile = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pdfFileId) {
        vm.retrievePdfFile(to.params.pdfFileId);
      }
    });
  }

  public retrievePdfFile(pdfFileId) {
    this.pdfFileService()
      .find(pdfFileId)
      .then(res => {
        this.pdfFile = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
