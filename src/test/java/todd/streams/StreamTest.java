package todd.streams;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.junit.Test;

import todd.beans.Foo;

public class StreamTest {

	@Test
	public void buildMapFromListOfValueObjects() throws Exception {
		
		List<Foo> myList = Arrays.asList(
				new Foo("bar1", "baz1"),
				new Foo("bar2", "baz2")
			);
		
			Map<String, String> kv = myList.stream()
					.collect(groupingBy(
							foo -> foo.getBar(), 
							reducing("", 
									(Function<Foo, String>) foo -> foo.getBaz(), 
									(ignored, passThrough) -> passThrough)));
						
			assertThat(kv).containsOnlyKeys("bar1", "bar2");
			assertThat(kv.get("bar1")).isEqualTo("baz1");
			assertThat(kv.get("bar2")).isEqualTo("baz2");
	}
}
