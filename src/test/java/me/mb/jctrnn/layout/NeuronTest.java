package me.mb.jctrnn.layout;

import static org.junit.Assert.*;
import me.mb.jctrnn.layout.Neuron.ParameterType;
import mockit.Mocked;

import org.junit.Test;
import org.hamcrest.CoreMatchers.*;

public class NeuronTest {

	
	@Test
	public void testTwoNeuronsEqual() {		
		Neuron neuron1 = new Neuron(1);
		neuron1.setParameter(ParameterType.TAU, new NeuronParam.Gene(new Range(0,10)));
		neuron1.setParameter(ParameterType.BIAS, new NeuronParam.Gene(new Range(10,20)));
		neuron1.setParameter(ParameterType.GAIN, new NeuronParam.Fixed(2));
		neuron1.setWeight(0, new NeuronParam.Gene(new Range(30,40)));
		
		Neuron neuron2 = new Neuron(1);
		neuron2.setParameter(ParameterType.TAU, new NeuronParam.Gene(new Range(0,10)));
		neuron2.setParameter(ParameterType.BIAS, new NeuronParam.Gene(new Range(10,20)));
		neuron2.setParameter(ParameterType.GAIN, new NeuronParam.Fixed(2));
		neuron2.setWeight(0, new NeuronParam.Gene(new Range(30,40)));
		
		assertTrue(neuron1.equals( neuron2 ));
		
		
	}
	

	@Test
	public void testTwoNeuronsNotEqual() {		
		Neuron neuron1 = new Neuron(1);
		neuron1.setParameter(ParameterType.TAU, new NeuronParam.Gene(new Range(0,10)));
		neuron1.setParameter(ParameterType.BIAS, new NeuronParam.Gene(new Range(10,20)));
		neuron1.setParameter(ParameterType.GAIN, new NeuronParam.Fixed(2));
		neuron1.setWeight(0, new NeuronParam.Gene(new Range(30,40)));
		
		Neuron neuron2 = new Neuron(1);
		neuron2.setParameter(ParameterType.TAU, new NeuronParam.Gene(new Range(0,10)));
		neuron2.setParameter(ParameterType.BIAS, new NeuronParam.Gene(new Range(10,20)));
		neuron2.setParameter(ParameterType.GAIN, new NeuronParam.Fixed(2));
		neuron2.setWeight(0, new NeuronParam.Gene(new Range(25,40)));
		
		assertFalse(neuron1.equals( neuron2 ));
		
		
	}

}
