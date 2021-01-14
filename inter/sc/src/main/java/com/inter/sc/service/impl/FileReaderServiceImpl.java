package com.inter.sc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.inter.sc.service.FileReaderService;

public class FileReaderServiceImpl implements FileReaderService {
     Logger logger=Logger.getLogger(FileReaderServiceImpl.class.getName());
	@Override
	public InputStream readFile(String fileName) {
		Resource resource = new ClassPathResource(fileName + ".txt");
		InputStream in = null;
		try {
			in = resource.getInputStream();
		} catch (IOException io) {
			logger.info("reading exception ..."+io.getMessage());
		}
		return in;
	}

}
