package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class DdUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DdUser.class);
        DdUser ddUser1 = new DdUser();
        ddUser1.setId(1L);
        DdUser ddUser2 = new DdUser();
        ddUser2.setId(ddUser1.getId());
        assertThat(ddUser1).isEqualTo(ddUser2);
        ddUser2.setId(2L);
        assertThat(ddUser1).isNotEqualTo(ddUser2);
        ddUser1.setId(null);
        assertThat(ddUser1).isNotEqualTo(ddUser2);
    }
}
