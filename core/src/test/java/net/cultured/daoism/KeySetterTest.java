package net.cultured.daoism;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KeySetterTest {

    @SuppressWarnings("unchecked")
    private final KeySetter<Object, Object> bean = mock(KeySetter.class);
    private final Object key = new Object();
    private final Object data = new Object();

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.data, this.key);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.data, this.key);
        verify(this.bean).setKey(this.data, this.key);
        verifyNoMoreInteractions(this.bean);
    }

    @Test
    public void testFunctionReturnsNormally() throws Exception {
        this.bean.accept(this.data, this.key);
    }

}
