import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class LIstMockTest {

    @Mock
    List<Integer> list;

    @Test
    void testList() {
        given(list.size()).willReturn(5);

        assertEquals(5, list.size());
    }

    @Test
    void testListDefault() {
        assertEquals(0, list.size());
    }

    @Test
    void testListVerification() {
        list.size();
        list.size();
        verify(list, times(2)).size();
    }
}
