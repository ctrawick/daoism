package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleCreatorTest {

    @SuppressWarnings("unchecked")
    private final SingleCreator<Object> bean = mock(SingleCreator.class);
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.data);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.data);
        verify(this.bean).createOne(same(this.data));
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        this.bean.accept(this.data);
    }
}
