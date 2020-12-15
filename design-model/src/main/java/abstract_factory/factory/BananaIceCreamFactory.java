package abstract_factory.factory;

import abstract_factory.product.BigBananaIceCream;
import abstract_factory.product.BigIceCream;
import abstract_factory.product.SmallBananaIceCream;
import abstract_factory.product.SmallIceCream;

/**
 * @author caofeng
 * @date 2020/12/15 9:07
 */
public class BananaIceCreamFactory extends IceCreamFactory{
    @Override
    public SmallIceCream createSmallIceCream() {
        return new SmallBananaIceCream();
    }

    @Override
    public BigIceCream createBigIceCream() {
        return new BigBananaIceCream();
    }
}
