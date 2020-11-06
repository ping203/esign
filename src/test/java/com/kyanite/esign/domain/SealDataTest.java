package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class SealDataTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SealData.class);
        SealData sealData1 = new SealData();
        sealData1.setId(1L);
        SealData sealData2 = new SealData();
        sealData2.setId(sealData1.getId());
        assertThat(sealData1).isEqualTo(sealData2);
        sealData2.setId(2L);
        assertThat(sealData1).isNotEqualTo(sealData2);
        sealData1.setId(null);
        assertThat(sealData1).isNotEqualTo(sealData2);
    }
}
