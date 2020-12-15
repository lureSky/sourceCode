package abstract_factory.product;

/**
 * @author caofeng
 * @date 2020/12/15 9:10
 */
public class SmallBananaIceCream extends SmallIceCream{
    @Override
    public void taste() {
        System.out.println("正在品尝香蕉味冰淇淋（小份）");
    }
}
