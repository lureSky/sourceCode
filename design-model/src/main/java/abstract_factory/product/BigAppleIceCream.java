package abstract_factory.product;

/**
 * @author caofeng
 * @date 2020/12/15 9:10
 */
public class BigAppleIceCream extends BigIceCream{
    @Override
    public void taste() {
        System.out.println("正在品尝苹果冰淇淋（大份）");
    }
}
