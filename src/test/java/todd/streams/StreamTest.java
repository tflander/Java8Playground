package todd.streams;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {

	@Test
	public void convertListToMapofLists() {
		List<Foo> myList = Arrays.asList(
			new Foo("v1", "v2"),
			new Foo("v3", "v4")
		);
		
		Map<String, List<Foo>> toMap = myList.stream().collect(Collectors.groupingBy(Foo::getBar));
		
		assertThat(toMap).containsOnlyKeys("v1", "v3");
		assertThat(toMap).containsValues(
			Arrays.asList(new Foo("v1", "v2")),
			Arrays.asList(new Foo("v3", "v4"))
			);
	}
	
	class Foo {
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((bar == null) ? 0 : bar.hashCode());
			result = prime * result + ((baz == null) ? 0 : baz.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Foo other = (Foo) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (bar == null) {
				if (other.bar != null)
					return false;
			} else if (!bar.equals(other.bar))
				return false;
			if (baz == null) {
				if (other.baz != null)
					return false;
			} else if (!baz.equals(other.baz))
				return false;
			return true;
		}

		public String getBar() {
			return bar;
		}

		public String getBaz() {
			return baz;
		}

		private final String bar;
		private final String baz;
		
		public Foo(String bar, String baz) {
			this.bar = bar;
			this.baz = baz;
		}
		
		@Override
		public String toString() {
			return "Foo [bar=" + bar + ", baz=" + baz + "]";
		}

		private StreamTest getOuterType() {
			return StreamTest.this;
		}
		
	}
	

}
