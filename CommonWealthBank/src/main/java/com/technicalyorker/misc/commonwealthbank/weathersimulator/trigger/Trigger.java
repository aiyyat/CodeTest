package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

/**
 * Trigger Interface has to be started using the start operation. Once started
 * it repeatedly calls the notifyEngine method that indicates to the Engine to
 * sample and emit data.
 * 
 * @author achuth
 *
 */
public interface Trigger {
	public abstract void start();

	public void notifyEngine();
}
