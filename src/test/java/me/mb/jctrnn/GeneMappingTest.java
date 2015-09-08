package me.mb.jctrnn;

import static org.junit.Assert.*;
import me.mb.jctrnn.layout.GeneMapping;
import me.mb.jctrnn.layout.NeuronParam;
import me.mb.jctrnn.layout.Range;

import org.junit.Test;

public class GeneMappingTest {

	@Test
	public void settingGeneLocationAddsParameterToThatLocation() {
		GeneMapping mapping = new GeneMapping();
		
		NeuronParam.Gene gene = new NeuronParam.Gene(new Range(0,1));
		gene.setGeneValue(1f);
		
		mapping.setNewGeneMapping(3,gene);
		
		float expected = 1f,actual = mapping.getValueAtGene(3);
		
		assertEquals(expected,actual, 0.00001f);
	}

}
