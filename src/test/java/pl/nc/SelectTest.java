package pl.nc;

import org.testng.annotations.Test;
import pl.nc.core.NativeCriteria;
import pl.nc.core.NativeExps;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:41
 */
public class SelectTest
{
	@Test
	public void selectTest()
	{
		NativeCriteria nc = new NativeCriteria(new NativeTestProvider(), "shipment", "s");
		nc.setProjection(NativeExps.projection().addProjection("columnName"));
		nc.add(NativeExps.eq("column_name", 15));
		System.out.println("piszu");
		System.out.println(nc.list().iterator().next());
	}
}
