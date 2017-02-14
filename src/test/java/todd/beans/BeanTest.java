package todd.beans;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class BeanTest {

	@Test
	public void testBeanWithJenerateEqualsAndHashcode() {
		Assertions.assertThat(new Foo("bar", "baz")).isEqualTo(new Foo("bar", "baz"));
	}

}
