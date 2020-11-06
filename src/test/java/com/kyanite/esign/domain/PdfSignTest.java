package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class PdfSignTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdfSign.class);
        PdfSign pdfSign1 = new PdfSign();
        pdfSign1.setId(1L);
        PdfSign pdfSign2 = new PdfSign();
        pdfSign2.setId(pdfSign1.getId());
        assertThat(pdfSign1).isEqualTo(pdfSign2);
        pdfSign2.setId(2L);
        assertThat(pdfSign1).isNotEqualTo(pdfSign2);
        pdfSign1.setId(null);
        assertThat(pdfSign1).isNotEqualTo(pdfSign2);
    }
}
