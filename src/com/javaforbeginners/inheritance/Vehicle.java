package com.javaforbeginners.inheritance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public abstract class Vehicle implements VehicleInterface, Runnable {

	// Initialize instance variables
	private String model = null;
	private int position = 0;
	private int speed;
	private int coolDown;
	private int gasRange;
	private int gasGuage;
	private Gas gas;
	private static boolean winner = false;
	
	// Constructor
	public Vehicle(String model, int position, int speed, int gasRange, Gas gas) {
		super();
		this.model = model;
		this.position = position;
		this.speed = speed;
		this.coolDown = calculateCoolDown();
		this.gasRange = gasRange;
		this.gasGuage = gasRange;
		this.gas = gas;
	}
	

	@Override
	public void run() {

		startEngine();
		try {
			race();
		} catch (FileNotFoundException fnfe) {
			System.out.println("FileNotFoundException at race()");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("IOException at race()");
			ioe.printStackTrace();
		}

	}

	@Override
	public void race() throws IOException {

		// Create local variable for position
		// in case a car gets a head start
		int localPosition = this.position;

		// Race length is 100 miles
		while (localPosition < 100) {

			// loop 5 times, cool down, repeat
			for (int i = 0; i <= 5; i++) {

				// Report name and race position
				System.out.printf("\n%s is racing at position %d", model, localPosition);

				// Increment position with speed variable
				localPosition += this.speed;
				
				// Decrement gasGuage with speed variable
				gasGuage -= this.speed;

				// If gas is empty, fill it up!
				if (gasGuage <= 0) {
					System.out.printf("\n%s needs fuel! Pulling into pit lane.", model);
					fillGas();
					System.out.printf("\n%s is pulling out of the pit lane and back in the race!", model);
				}

				// If position > 100 miles, declare a winner, stop race
				if (localPosition >= 100) {

					// Set winner
					Vehicle.setWinner(true);

					// Print race results to file
					resultToFile();

					// Print race results to console
					System.out.printf("\n%s has won the race!", model);

					// Inherited Method from VehicleInterface
					stopEngine();

					// Prevents any other threads
					System.exit(0);

				}

			}

			// Cool down!
			engineCool();

		}
	}

	// Inherited Method from VehicleInterface
	@Override
	public void startEngine() {

		System.out.printf("\n%S: VROOM", model);

		// Allow all cars to start before race begins
		Thread.yield();
	}

	// Inherited Method from VehicleInterface
	@Override
	public void stopEngine() {

		System.out.printf("\n%S: Skrrrp", model);

	}

	// Method to print race result to a file ( extracted from run() )
	private void resultToFile() throws FileNotFoundException, IOException {

		// Create a text file inside the project directory
		File fout = new File("raceResult.txt");

		// Declare instance of FileOutputStream. Pass in new File
		FileOutputStream fos = new FileOutputStream(fout);

		// Declare instance of output. Pass in FileOutputStream
		OutputStreamWriter osw = new OutputStreamWriter(fos);

		// Write to file
		osw.write(model + " has won the race! \n\nThe crowd goes WILD!");

		osw.close();
	}

	@Override
	public int calculateCoolDown() { // fast cars take longer to cool
		int result = this.getSpeed();
		setCoolDown(result);
		return result;
	}

	private void fillGas() { // large fuel tanks take longer to fill
		int stopTime = gasRange;
		try {
			gas.addGas(model, stopTime);
			gasGuage = gasRange;
			System.out.printf("\n%s has %d fresh gallons of gas", model, gasGuage);
		} catch (InterruptedException ie) {
			System.out.printf("\nInterruptedException in fillGas() for %s", this.model);
			ie.printStackTrace();
		}
	}

	private void engineCool() { // unsynchronized
		try {
			System.out.printf("\n%s's engine overheated. Stopping to COOL DOWN.", model);
			Thread.sleep(this.coolDown);
			System.out.printf("\n%s's engine cooled down and is BACK TO RACING!", this.model);
		} catch (InterruptedException ie) {
			System.out.printf("\nInterruptedException in engineCool() for %s", this.model);
			ie.printStackTrace();
		}
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public Gas getGas() {
		return gas;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
	}

	public static boolean isWinner() {
		return winner;
	}

	public static void setWinner(boolean winner) {
		Vehicle.winner = winner;
	}

}