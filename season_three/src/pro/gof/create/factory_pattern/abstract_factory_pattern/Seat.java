package pro.gof.create.factory_pattern.abstract_factory_pattern;

public interface Seat {
	void massage();
}

class LuxurySeat implements Seat {
	@Override
	public void massage() {
		System.out.println("LuxurySeat:message()");
	}
}

class LowerSeat implements Seat {
	@Override
	public void massage() {
		System.out.println("LowerSeat:message()");
	}
}
