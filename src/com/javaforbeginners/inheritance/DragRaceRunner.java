package com.javaforbeginners.inheritance;

public class DragRaceRunner {

		public static void main(String[] args) {


			// Create single shared instance of Gas class with
			// synchronized addGas() method to simulate one pump
			Gas gas = new Gas();

			// Create objects that extend super class Vehicle
			SportsCar supra = new SportsCar("Supra", 0, 5, 30, gas);
			Truck tacoma = new LightPickup("Tacoma", 0, 3, 35, gas);
			SportsCar wrx = new SportsCar("WRX", 0, 4, 33, gas);
			Hybrid prius = new Hybrid("Prius", 0, 2, 45, gas);

			// Use those instances to create Threads
			Thread supraThread = new Thread(supra);
			Thread tacomaThread = new Thread(tacoma);
			Thread wrxThread = new Thread(wrx);
			Thread priusThread = new Thread(prius);
			
			// Set as "user" threads (as opposed to daemon threads)
			supraThread.setDaemon(false);
			tacomaThread.setDaemon(false);
			wrxThread.setDaemon(false);
			priusThread.setDaemon(false);
			
			// Start threads
			supraThread.start();
			tacomaThread.start();
			wrxThread.start();
			priusThread.start();

		}

	}
