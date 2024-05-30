package com.kazurayam.ks

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kazurayam.ks.KeywordUtils as KU

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords

@RunWith(JUnit4.class)
public class KeywordBookTest {

	@Test
	public void test_constructor() {
		KeywordBook kb = new KeywordBook()
		assertNotNull(kb)
	}

	@Test
	public void test_set_getKeywordMethods() {
		// given
		List<KeywordMethod> list1 = KU.getKeywordMethods(AUTType.WebUI.getKeywordsClass())
		KeywordBook kb = new KeywordBook()
		kb.setKeywordMethods(AUTType.WebUI, list)
		// when
		List<KeywordMethod> list2 = kb.getKeywordMethods(AUTType.WebUI)
		// then
		assertNotNull(list2)
		assertEquals(list1.size(), list2.size())
	}

	@Test
	public void test_getKeywordMethods() {
		throw new RuntimeException("TODO")
	}

	@Test
	public void test_serialize() {
		throw new RuntimeException("TODO")
	}

	@Test
	public void test_deserialize() {
		throw new RuntimeException("TODO")
	}
}
