package com.inter.sc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.inter.sc.service.impl.DigitalScanningService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ScApplicationTests {

	private DigitalScanningService digitalScanningService;

	public ScApplicationTests() {
		super();
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testSingleChunkFile() {
		String fileName = "singleChunk";
		digitalScanningService = new DigitalScanningService();
		List<String> actual = digitalScanningService.getDigitalTextScan(fileName);
		List<String> expectedOut = getMockDataTest(fileName);
		assertEquals(expectedOut, actual);

	}

	@Test
	public void testMultipleChunksWithIllegalRowFile() {
		String fileName = "multipleChunksWithIllegalRow";
		digitalScanningService = new DigitalScanningService();
		List<String> actual = digitalScanningService.getDigitalTextScan(fileName);
		List<String> expectedOut = getMockDataTest(fileName);
		assertEquals(expectedOut, actual);
	}

	@Test
	public void testMultipleChunksFile() {
		String fileName = "multipleChunks";
		digitalScanningService = new DigitalScanningService();
		List<String> actual = digitalScanningService.getDigitalTextScan(fileName);
		List<String> expectedOut = getMockDataTest(fileName);
		assertEquals(expectedOut, actual);
	}

	public List<String> getMockDataTest(String fileName) {
		List<String> list = new ArrayList<>();
		if ("singleChunk".equals(fileName)) {
			list.add("000000000");
		} else if ("multipleChunks".equals(fileName)) {
			list.add("123456789");
			list.add("123456789");
			list.add("123456789");
		} else if ("multipleChunksWithIllegalRow".equals(fileName)) {
			list.add("123456789");
			list.add("123456?ILL89");
			list.add("123456789");
		}
		return list;

	}
}
