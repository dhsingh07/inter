package com.inter.sc.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.inter.sc.service.FileReaderService;


public class DigitalScanningService {
    Logger logger=Logger.getLogger(DigitalScanningService.class.getName());
	private static Map<String, Integer> cachedData = new LinkedHashMap<>();

	public static void main(String args[]) {
		String fileName = "singleChunk";
		new DigitalScanningService().digitalTextScan(fileName);
	}

	static {
		Resource metaData = new ClassPathResource("referenceData.txt");
		try {
			InputStream metaStream = metaData.getInputStream();
			BufferedReader metaDataReader = new BufferedReader(new InputStreamReader(metaStream));
			// prepare metaData cache....
			prepareMetaDataCache(metaDataReader);
		} catch (IOException e) {
		}
	}

	/**
	 * processing input files...
	 * 
	 * @param fileName
	 */
	public void digitalTextScan(String fileName) {
		// Resource resource = new ClassPathResource("singleChunk.txt");
		//Resource resource = new ClassPathResource(fileName + ".txt");
		try {
			String val;
			FileReaderService fileReaderService=new FileReaderServiceImpl();
			// processing input files....
			//InputStream in = resource.getInputStream();
			InputStream in=fileReaderService.readFile(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			int count1 = 0;
			int outerCount = 0;
			String[] digitalValue = new String[9];
			while ((val = reader.readLine()) != null) {
				if (!val.isEmpty() && val.trim().length() > 0) {
					for (int j = 0; j < val.length() - 2; j = j + 3) {
						String value = val.substring(j, j + 3);
						if (digitalValue[count1] == null) {
							digitalValue[count1] = value;
						} else {
							digitalValue[count1] = digitalValue[count1] + value;
						}
						count1++;
						if (count1 == 9) {
							count1 = 0;
						}
					}
				} else {
					logger.info("blank line......");
				}

				if (outerCount == 2) {
					outerCount = 0;
					printDigitalInput(digitalValue);
					digitalValue = new String[9];

				} else {
					if (!val.isEmpty() && val.trim().length() > 0) {
						outerCount++;
					}
				}
			}
		} catch (IOException e) {
		}
	}
	
	public List<String> getDigitalTextScan(String fileName) {
		// Resource resource = new ClassPathResource("singleChunk.txt");
		//Resource resource = new ClassPathResource(fileName + ".txt");
		List<String> result=new ArrayList<>();;
		try {
			
			String val;
			// processing input files....
			FileReaderService fileReaderService=new FileReaderServiceImpl();
			// processing input files....
			//InputStream in = resource.getInputStream();
			InputStream in=fileReaderService.readFile(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			int count1 = 0;
			int outerCount = 0;
			String[] digitalValue = new String[9];
			while ((val = reader.readLine()) != null) {
				if (!val.isEmpty() && val.trim().length() > 0) {
					for (int j = 0; j < val.length() - 2; j = j + 3) {
						String value = val.substring(j, j + 3);
						if (digitalValue[count1] == null) {
							digitalValue[count1] = value;
						} else {
							digitalValue[count1] = digitalValue[count1] + value;
						}
						count1++;
						if (count1 == 9) {
							count1 = 0;
						}
					}
				} else {
					logger.info("blank line......");
				}

				if (outerCount == 2) {
					outerCount = 0;
					String res=getResultPrintDigitalInput(digitalValue);
					result.add(res);
					digitalValue = new String[9];
				} else {
					if (!val.isEmpty() && val.trim().length() > 0) {
						outerCount++;
					}
				}
			}
		} catch (IOException e) {
		}
		return result;
	}
	

	// prepare metaData cache....
	private static void prepareMetaDataCache(BufferedReader metaReader) throws IOException {
		String data;
		// created referenced data to use for matching to input.
		String[] key = new String[10];
		int count = 0;
		while ((data = metaReader.readLine()) != null) {
			for (int i = 0; i < data.length() - 2; i = i + 3) {
				String value = data.substring(i, i + 3);
				if (key[count] == null) {
					key[count] = value;
				} else {
					key[count] = key[count] + value;
				}
				count++;
				if (count == 10) {
					count = 0;
				}
			}
		}
		for (int i = 0; i < key.length; i++) {
			cachedData.put(key[i], i);
		}
	}

	private static void printDigitalInput(String[] digitalValue) {
		String result = "";
		if (digitalValue != null && digitalValue.length != 0) {
			for (int i = 0; i < digitalValue.length; i++) {
				if (cachedData.get(digitalValue[i]) != null) {
					result = result + cachedData.get(digitalValue[i]);
				} else {
					result = result + "?ILL";
				}
			}
		}
		 System.out.println(result);
	}
	
	
	private static String getResultPrintDigitalInput(String[] digitalValue) {
		String result = "";
		if (digitalValue != null && digitalValue.length != 0) {
			for (int i = 0; i < digitalValue.length; i++) {
				if (cachedData.get(digitalValue[i]) != null) {
					result = result + cachedData.get(digitalValue[i]);
				} else {
					result = result + "?ILL";
				}
			}
		}
		 System.out.println(result);
		 return result;
	}
}
