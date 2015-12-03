package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratedKeySingleCreatorTest {

    @SuppressWarnings("unchecked")
    private final GeneratedKeySingleCreator<Object, Object> bean = mock(GeneratedKeySingleCreator.class);
    private final Object key = new Object();
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).apply(this.data);
        when(this.bean.createOne(this.data)).thenReturn(this.key);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).apply(this.data);
        verify(this.bean).createOne(same(this.data));
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        assertSame(this.key, this.bean.apply(this.data));
    }
}
