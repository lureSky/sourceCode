package abstract_factory.factory;

import abstract_factory.product.BigIceCream;
import abstract_factory.product.SmallIceCream;

/**
 * @author caofeng
 * @date 2020/12/15 8:56
 */
public abstract class IceCreamFactory {

    /**
     * @Description: 可以生产大冰激凌，也可以生产小冰淇淋
     * @author: Caofeng
     * @mail: 867403822@qq.com
     * @date: 2020-12-15 9:05
    */
    public abstract SmallIceCream createSmallIceCream();

    public abstract BigIceCream createBigIceCream();
}
