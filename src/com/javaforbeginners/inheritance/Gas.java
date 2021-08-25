package com.javaforbeginners.inheritance;

public class Gas {

	// constructor
	public Gas() {
		super();
	}

	// stopTime = ( 4 * Vehicle.coolDown)
	synchronized public void addGas(String model, int stopTime) throws InterruptedException {

		System.out.printf("\n%s began fueling.", model);

		Thread.sleep(stopTime);

		System.out.printf("\n%s finished fueling.", model);
	}

}
