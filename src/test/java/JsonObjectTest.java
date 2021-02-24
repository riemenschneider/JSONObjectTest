import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import io.vertx.core.json.JsonObject;

@RunWith(Parameterized.class)
public class JsonObjectTest {
	
	private Object value;
	
	public JsonObjectTest(Object value) {
		this.value = value;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> values() {
		return Arrays.asList(new Object[][] {
			{ null },
			{ 42 },
			{ new Date() }
		});
	}
	
	@Test
	public void testFailsafePutAndCopy() {
		JsonObject jsonObject = new JsonObject();
		failsafePut(jsonObject, "data", value);
		JsonObject copy = jsonObject.copy();
		assertEquals(jsonObject, copy);
	}
	
	public void failsafePut(JsonObject jsonObject, String key, Object value) {
		try {
			jsonObject.put(key, value);
		}
		catch (IllegalStateException e) {
			jsonObject.put(key, value.toString());
		}
	}

}
