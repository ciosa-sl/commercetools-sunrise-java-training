package playbasics;

import org.junit.Test;
import play.mvc.Result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static play.test.Helpers.OK;
import static play.test.Helpers.contentAsString;

public class UnitTest {

    @Test
    public void showsHelloWorld() throws Exception {
        final Result result = myController().show1();
        assertThat(result.status()).isEqualTo(OK);
        assertThat(contentAsString(result)).isEqualTo("Hello World!");
    }

    @Test
    public void showsHelloName() throws Exception {
        final Result result = myController().show2("John");
        assertThat(result.status()).isEqualTo(OK);
        assertThat(contentAsString(result)).isEqualTo("Hello John!");
    }

    private static MyController myController() {
        return new MyController(null);
    }
}
