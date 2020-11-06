import { Component, Vue, Inject } from 'vue-property-decorator';

import { IPdfSign } from '@/shared/model/pdf-sign.model';
import PdfSignService from './pdf-sign.service';

@Component
export default class PdfSignDetails extends Vue {
  @Inject('pdfSignService') private pdfSignService: () => PdfSignService;
  public pdfSign: IPdfSign = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.pdfSignId) {
        vm.retrievePdfSign(to.params.pdfSignId);
      }
    });
  }

  public retrievePdfSign(pdfSignId) {
    this.pdfSignService()
      .find(pdfSignId)
      .then(res => {
        this.pdfSign = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
