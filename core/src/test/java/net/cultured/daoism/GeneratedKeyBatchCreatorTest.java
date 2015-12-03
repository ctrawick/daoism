package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratedKeyBatchCreatorTest {

    @SuppressWarnings("unchecked")
    private final GeneratedKeyBatchCreator<Object, Object> bean = mock(GeneratedKeyBatchCreator.class);
    @SuppressWarnings("unchecked")
    private final Collection<Object> dataIn = mock(Collection.class);
    @SuppressWarnings("unchecked")
    private final Map<Object, Object> dataOut = mock(Map.class);

    @Before
    public void setUpInteractions() {
        when(this.bean.apply(this.dataIn)).thenCallRealMethod();
        when(this.bean.createMany(this.dataIn)).thenReturn(this.dataOut);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).apply(this.dataIn);
        verify(this.bean).createMany(same(this.dataIn));
        verifyNoMoreInteractions(this.bean, this.dataIn, this.dataOut);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        final Map<Object, Object> actual = this.bean.apply(this.dataIn);
        assertSame(this.dataOut, actual);
    }

}
