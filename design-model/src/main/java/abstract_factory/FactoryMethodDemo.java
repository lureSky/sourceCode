package abstract_factory;

import abstract_factory.factory.AppIeIceCreamFactory;
import abstract_factory.factory.BananaIceCreamFactory;
import abstract_factory.factory.IceCreamFactory;
import abstract_factory.product.BigIceCream;
import abstract_factory.product.SmallIceCream;


/**
 * @Description: 总结：抽象工厂方法和普通工厂方法的区别
 * 		抽象工厂方法可以在工厂抽象类中定义多种产品，可以理解为多条流水线
 * 		普通工厂方法只能在工厂抽象类中定义一类产品，只有一条流水线
 * @author: Caofeng
 * @mail: 867403822@qq.com
 * @date: 2020-12-15 9:22
*/
public class FactoryMethodDemo {

	public static void main(String[] args) {

		//生产苹果味冰淇淋
		IceCreamFactory appleIceCreamFactory = new AppIeIceCreamFactory();
		SmallIceCream appleSmallIceCream = appleIceCreamFactory.createSmallIceCream();
		BigIceCream appleBigIceCream = appleIceCreamFactory.createBigIceCream();

		appleSmallIceCream.taste();
		appleBigIceCream.taste();

		//生产香蕉味冰淇淋
		IceCreamFactory bananaIceCreamFactory = new BananaIceCreamFactory();
		BigIceCream bigIceCream = bananaIceCreamFactory.createBigIceCream();
		SmallIceCream smallIceCream = bananaIceCreamFactory.createSmallIceCream();
		bigIceCream.taste();
		smallIceCream.taste();
	}

}
