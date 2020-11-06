package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class MsgTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MsgTask.class);
        MsgTask msgTask1 = new MsgTask();
        msgTask1.setId(1L);
        MsgTask msgTask2 = new MsgTask();
        msgTask2.setId(msgTask1.getId());
        assertThat(msgTask1).isEqualTo(msgTask2);
        msgTask2.setId(2L);
        assertThat(msgTask1).isNotEqualTo(msgTask2);
        msgTask1.setId(null);
        assertThat(msgTask1).isNotEqualTo(msgTask2);
    }
}
