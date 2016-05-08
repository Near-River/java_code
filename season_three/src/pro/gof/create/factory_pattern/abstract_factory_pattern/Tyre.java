package pro.gof.create.factory_pattern.abstract_factory_pattern;

public interface Tyre {
	void revolve();
}

class LuxuryTyre implements Tyre {
	@Override
	public void revolve() {
		System.out.println("LuxuryTyre:revolve()");
	}
}

class LowerTyre implements Tyre {
	@Override
	public void revolve() {
		System.out.println("LowerTyre:revolve()");
	}
}