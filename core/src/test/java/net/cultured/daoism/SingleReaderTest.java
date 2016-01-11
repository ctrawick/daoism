package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Verify the implementation of {@link SingleReader}.
 *
 * @author ctrawick
 */
public class SingleReaderTest {

    @Mock
    private SingleReader<Object, Object> bean;
    private final Object key = new Object();
    private final Object data = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        MockitoAnnotations.initMocks(this);
        when(this.bean.apply(this.key)).thenCallRealMethod();
        when(this.bean.readOne(this.key)).thenReturn(this.data);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock delegate.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean);
    }

    /**
     * Verify the implementation of {@link SingleReader#apply(Object)}. The
     * implementation is required to call {@link SingleReader#readOne(Object)}.
     */
    @Test
    public void testFunctionReturnsNormally() {
        // Execute the test
        final Object actual = this.bean.apply(this.key);

        // Verify the result and that the correct methods are called.
        assertSame(this.data, actual);
        verify(this.bean).apply(this.key);
        verify(this.bean).readOne(same(this.key));
    }

}
