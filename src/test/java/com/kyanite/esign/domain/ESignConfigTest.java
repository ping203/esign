package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class ESignConfigTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ESignConfig.class);
        ESignConfig eSignConfig1 = new ESignConfig();
        eSignConfig1.setId(1L);
        ESignConfig eSignConfig2 = new ESignConfig();
        eSignConfig2.setId(eSignConfig1.getId());
        assertThat(eSignConfig1).isEqualTo(eSignConfig2);
        eSignConfig2.setId(2L);
        assertThat(eSignConfig1).isNotEqualTo(eSignConfig2);
        eSignConfig1.setId(null);
        assertThat(eSignConfig1).isNotEqualTo(eSignConfig2);
    }
}
