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

/**
 * Verifies the implementation of {@link GeneratedKeySingleCreatorAdapter}.
 *
 * @author ctrawick
 */
public class GeneratedKeySingleCreatorTest {

    private final GeneratedKeySingleCreator<Object, Object> bean = mock(GeneratedKeySingleCreator.class);
    private final Object key = new Object();
    private final Object data = new Object();

    /**
     * Set up the test interactions. This test assures that the implementation
     * method is executed when the mock object method is called.
     */
    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).apply(this.data);
        when(this.bean.createOne(this.data)).thenReturn(this.key);
    }

    /**
     * Verify the test interactions. This test verifies that no unexpected
     * interactions occur on the mock data.
     */
    @After
    public void verifyInteractions() {
        verifyNoMoreInteractions(this.bean);
    }

    /**
     * Verify the implementation of
     * {@link GeneratedKeySingleCreator#apply(Object)}. The implementation is
     * required to call {@link GeneratedKeySingleCreator#createOne(Object)}.
     */
    @Test
    public void testApply() {
        // Execute the test
        assertSame(this.key, this.bean.apply(this.data));

        // Verify that the correct methods are called.
        verify(this.bean).apply(this.data);
        verify(this.bean).createOne(same(this.data));
    }
}
