package com.javaforbeginners.inheritance;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface VehicleInterface {

	public void startEngine() throws FileNotFoundException;

	public void race() throws FileNotFoundException, IOException;

	public int calculateCoolDown();

	public void stopEngine();

}
