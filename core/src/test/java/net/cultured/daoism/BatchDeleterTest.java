package net.cultured.daoism;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BatchDeleterTest {

    @SuppressWarnings("unchecked")
    private final BatchDeleter<Object> bean = mock(BatchDeleter.class);
    @SuppressWarnings("unchecked")
    private final Collection<Object> keys = mock(Collection.class);

    @Before
    public void setUpInteractions() {
        doCallRealMethod().when(this.bean).accept(this.keys);
    }

    @After
    public void verifyInteractions() {
        verify(this.bean).accept(this.keys);
        verify(this.bean).deleteMany(same(this.keys));
        verifyNoMoreInteractions(this.bean, this.keys);
    }

    @Test
    public void testConsumerReturnsNormally() throws Exception {
        this.bean.accept(this.keys);
    }

}
