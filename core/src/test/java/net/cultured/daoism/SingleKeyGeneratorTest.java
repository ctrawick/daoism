package net.cultured.daoism;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Verify the implementation of {@link SingleKeyGenerator}.
 *
 * @author ctrawick
 */
public class SingleKeyGeneratorTest {

    private final SingleKeyGenerator<Object> bean = mock(SingleKeyGenerator.class);
    private final Object key = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        when(this.bean.get()).thenCallRealMethod();
        when(this.bean.generateKey()).thenReturn(this.key);
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
     * Verify the implementation of {@link SingleKeyGenerator#get()}. The
     * implementation is required to call
     * {@link SingleKeyGenerator#generateKey()}.
     */
    @Test
    public void testFunctionReturnsNormally() {
        // Execute the test
        final Object actual = this.bean.get();

        // Verify the result and that the correct methods are called.
        assertSame(this.key, actual);
        verify(this.bean).get();
        verify(this.bean).generateKey();
    }

}
