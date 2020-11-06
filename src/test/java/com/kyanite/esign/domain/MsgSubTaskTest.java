package com.kyanite.esign.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.kyanite.esign.web.rest.TestUtil;

public class MsgSubTaskTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MsgSubTask.class);
        MsgSubTask msgSubTask1 = new MsgSubTask();
        msgSubTask1.setId(1L);
        MsgSubTask msgSubTask2 = new MsgSubTask();
        msgSubTask2.setId(msgSubTask1.getId());
        assertThat(msgSubTask1).isEqualTo(msgSubTask2);
        msgSubTask2.setId(2L);
        assertThat(msgSubTask1).isNotEqualTo(msgSubTask2);
        msgSubTask1.setId(null);
        assertThat(msgSubTask1).isNotEqualTo(msgSubTask2);
    }
}
