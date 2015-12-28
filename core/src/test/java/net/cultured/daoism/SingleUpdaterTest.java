package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleUpdaterTest {

    private final SingleUpdater<Object> bean = mock(SingleUpdater.class);
    private final Object key = new Object();

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.key);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.key);
        verify(this.bean).updateOne(same(this.key));
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        this.bean.accept(this.key);
    }

}
