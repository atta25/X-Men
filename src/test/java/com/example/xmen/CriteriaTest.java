package com.example.xmen;

import com.example.xmen.criterion.Diagonal;
import com.example.xmen.criterion.Horizontal;
import com.example.xmen.criterion.Vertical;
import com.example.xmen.detector.Detector;
import com.example.xmen.generator.Generator;
import org.junit.Assert;
import org.junit.Test;

public class CriteriaTest {
	@Test
	public void whenThereIsAHorizontalSequenceAndAHorizontalCriterionShouldReturnFalse() {
		Generator generator = new Generator();
		String[] dna = {"ATCCCC","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"};
		Horizontal horizontal = new Horizontal();
		generator.addCriterion(horizontal);
		Detector detector = new Detector(generator);
		Assert.assertFalse(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreTwoHorizontalSequencesAndAHorizontalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATCCCC","CAGTGC","TTATGT","AAAAGG","CCCTTA","TCACTG"};
		Horizontal horizontal = new Horizontal();
		generator.addCriterion(horizontal);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereIsAVerticalSequenceAndAVerticalCriterionShouldReturnFalse() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		Vertical vertical = new Vertical();
		generator.addCriterion(vertical);
		Detector detector = new Detector(generator);
		Assert.assertFalse(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreTwoVerticalSequencesAndAVerticalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","AAGTGC","ATATGT","AGAAGG","CCCCTA","TCACTG"};
		Vertical vertical = new Vertical();
		generator.addCriterion(vertical);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereIsADiagonalSequenceAndADiagonalCriterionShouldReturnFalse() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","AAGTGC","ATATGT","AGAAGG","CCCCTA","TCACTG"};
		Diagonal diagonal = new Diagonal();
		generator.addCriterion(diagonal);
		Detector detector = new Detector(generator);
		Assert.assertFalse(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreTwoDiagonalSequencesAndADiagonalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","CAGTGC","TCATGT","AGCAGG","CCCCTA","TCACTG"};
		Diagonal diagonal = new Diagonal();
		generator.addCriterion(diagonal);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreAHorizontalAndVerticalSequencesAndAHorizontalAndVerticalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","AAGTGC","ACATGT","AGCAGG","CCCCTA","TCACTG"};
		Horizontal horizontal = new Horizontal();
		Vertical vertical = new Vertical();
		generator.addCriterion(horizontal);
		generator.addCriterion(vertical);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreAHorizontalAndDiagonalSequencesAndAHorizontalAndDiagonalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","CAGTGC","TCATGT","AGCAGG","CCCCTA","TCACTG"};
		Horizontal horizontal = new Horizontal();
		Diagonal diagonal = new Diagonal();
		generator.addCriterion(horizontal);
		generator.addCriterion(diagonal);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreAVerticalAndDiagonalSequencesAndAVerticalAndDiagonalCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA","CAGTGC","TCATGT","AGCAGG","CCCCTA","TCACTG"};
		Vertical vertical = new Vertical();
		Diagonal diagonal = new Diagonal();
		generator.addCriterion(vertical);
		generator.addCriterion(diagonal);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}

	@Test
	public void whenThereAreAHVDSequencesAndAHVDCriterionShouldReturnTrue() {
		Generator generator = new Generator();
		String[] dna = {"ATGCGA", "CAGTGC", "TCATGT", "AGCAGG", "CCCCTA", "TCACTG"};
		Horizontal horizontal = new Horizontal();
		Vertical vertical = new Vertical();
		Diagonal diagonal = new Diagonal();
		generator.addCriterion(horizontal);
		generator.addCriterion(vertical);
		generator.addCriterion(diagonal);
		Detector detector = new Detector(generator);
		Assert.assertTrue(detector.isMutant(dna));
	}
}
