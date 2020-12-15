package abstract_factory.factory;

import abstract_factory.product.BigAppleIceCream;
import abstract_factory.product.BigIceCream;
import abstract_factory.product.SmallAppleIceCream;
import abstract_factory.product.SmallIceCream;

/**
 * @author caofeng
 * @date 2020/12/15 9:07
 */
public class AppIeIceCreamFactory extends IceCreamFactory{
    @Override
    public SmallIceCream createSmallIceCream() {
        return new SmallAppleIceCream();
    }

    @Override
    public BigIceCream createBigIceCream() {
        return new BigAppleIceCream();
    }
}
