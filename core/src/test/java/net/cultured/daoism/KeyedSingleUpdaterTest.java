package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeyedSingleUpdaterTest {

    private final KeyedSingleUpdater<Object, Object> bean = mock(KeyedSingleUpdater.class);
    private final Object key = new Object();
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.key, this.data);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.key, this.data);
        verify(this.bean).updateOneByKey(same(this.key), same(this.data));
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        this.bean.accept(this.key, this.data);
    }

}
