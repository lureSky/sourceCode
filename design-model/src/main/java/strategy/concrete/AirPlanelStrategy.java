package strategy.concrete;

import strategy.abs.TravelStrategy;

/***
 * 具体策略类(ConcreteStrategy)
 * @author think
 *
 */
public class AirPlanelStrategy implements TravelStrategy {

	@Override
	public void travelWay() {
		System.out.println("旅游方式选择：乘坐飞机");
	}
	
	@Override
	public boolean isOK(int type) {
		if (type <= 10000 && type >1000) {
			return true;
		}
		return false;
	}

}
