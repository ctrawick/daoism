package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeyedBatchUpdaterTest {

    @SuppressWarnings("unchecked")
    private final KeyedBatchUpdater<Object, Object> bean = mock(KeyedBatchUpdater.class);
    @SuppressWarnings("unchecked")
    private final Map<Object, Object> data = mock(Map.class);

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.data);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.data);
        verify(this.bean).updateManyByKey(same(this.data));
        verifyNoMoreInteractions(this.bean, this.data);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        this.bean.accept(this.data);
    }

}
