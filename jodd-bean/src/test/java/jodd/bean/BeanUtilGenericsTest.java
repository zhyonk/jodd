// Copyright (c) 2003-2013, Jodd Team (jodd.org). All Rights Reserved.

package jodd.bean;

import jodd.bean.data.Woof;
import jodd.introspector.ClassDescriptor;
import jodd.introspector.ClassIntrospector;
import jodd.introspector.PropertyDescriptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanUtilGenericsTest {

	@Test
	public void testAllBeanSetters() {
		Woof woof = new Woof();
		Class type = woof.getClass();
		ClassDescriptor cd = ClassIntrospector.lookup(type);
		PropertyDescriptor[] properties = cd.getAllPropertyDescriptors();
		assertNotNull(properties);
		assertEquals(7, properties.length);
	}

	// ---------------------------------------------------------------- fields

	public static class BaseClass<A, B> {
		public A f1;		// String
		public B f2;		// Integer


		public void setFoo1(A a) {
			f1 = a;
		}
		public A getFoo1() {return f1;}
		public void setFoo2(B b) {
			f2 = b;
		}
		public B getFoo2() {return f2;}
	}

	public static class ConcreteClass extends BaseClass<String, Integer> {
	}

	public static class BaseClass2<X> extends BaseClass<X, Integer> {
	}

	public static class ConcreteClass2 extends BaseClass2<String> {
	}

	@Test
	public void testSetField() {
		BaseClass base = new BaseClass();

		BeanUtil.setProperty(base, "f1", Integer.valueOf(173));
		assertEquals(Integer.valueOf(173), base.f1);
		assertEquals(Integer.valueOf(173), BeanUtil.getProperty(base, "f1"));
		assertEquals(Integer.class, base.f1.getClass());

		BeanUtil.setProperty(base, "f2", "123");
		assertEquals("123", base.f2);
		assertEquals("123", BeanUtil.getProperty(base, "f2"));
		assertEquals(String.class, base.f2.getClass());

		// concrete implementation #1

		ConcreteClass impl1 = new ConcreteClass();

		BeanUtil.setProperty(impl1, "f1", Integer.valueOf(173));
		assertEquals("173", impl1.f1);
		assertEquals("173", BeanUtil.getProperty(impl1, "f1"));
		assertEquals(String.class, impl1.f1.getClass());

		BeanUtil.setProperty(impl1, "f2", "123");
		assertEquals(Integer.valueOf(123), impl1.f2);
		assertEquals(Integer.valueOf(123), BeanUtil.getProperty(impl1, "f2"));
		assertEquals(Integer.class, impl1.f2.getClass());

		// concrete implementation #2

		ConcreteClass2 impl2 = new ConcreteClass2();

		BeanUtil.setProperty(impl2, "f1", Integer.valueOf(173));
		assertEquals("173", impl2.f1);
		assertEquals("173", BeanUtil.getProperty(impl2, "f1"));
		assertEquals(String.class, impl2.f1.getClass());

		BeanUtil.setProperty(impl2, "f2", "123");
		assertEquals(Integer.valueOf(123), impl2.f2);
		assertEquals(Integer.valueOf(123), BeanUtil.getProperty(impl2, "f2"));
		assertEquals(Integer.class, impl2.f2.getClass());
	}

	@Test
	public void testSetProperty() {
		BaseClass base = new BaseClass();

		BeanUtil.setProperty(base, "foo1", Integer.valueOf(173));
		assertEquals(Integer.valueOf(173), base.getFoo1());
		assertEquals(Integer.valueOf(173), BeanUtil.getProperty(base, "foo1"));
		assertEquals(Integer.class, base.getFoo1().getClass());

		BeanUtil.setProperty(base, "foo2", "123");
		assertEquals("123", base.getFoo2());
		assertEquals("123", BeanUtil.getProperty(base, "foo2"));
		assertEquals(String.class, base.getFoo2().getClass());

		// concrete implementation #1

		ConcreteClass impl1 = new ConcreteClass();

		BeanUtil.setProperty(impl1, "foo1", Integer.valueOf(173));
		assertEquals("173", impl1.getFoo1());
		assertEquals("173", BeanUtil.getProperty(impl1, "foo1"));
		assertEquals(String.class, impl1.getFoo1().getClass());

		BeanUtil.setProperty(impl1, "foo2", "123");
		assertEquals(Integer.valueOf(123), impl1.getFoo2());
		assertEquals(Integer.valueOf(123), BeanUtil.getProperty(impl1, "foo2"));
		assertEquals(Integer.class, impl1.getFoo2().getClass());

		// concrete implementation #2

		ConcreteClass2 impl2 = new ConcreteClass2();

		BeanUtil.setProperty(impl2, "foo1", Integer.valueOf(173));
		assertEquals("173", impl2.getFoo1());
		assertEquals("173", BeanUtil.getProperty(impl2, "foo1"));
		assertEquals(String.class, impl2.getFoo1().getClass());

		BeanUtil.setProperty(impl2, "f2", "123");
		assertEquals(Integer.valueOf(123), impl2.getFoo2());
		assertEquals(Integer.valueOf(123), BeanUtil.getProperty(impl2, "foo2"));
		assertEquals(Integer.class, impl2.getFoo2().getClass());
	}

}