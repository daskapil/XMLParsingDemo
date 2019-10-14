package com.kapil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.kapil.jdom.JDOMXMLReader;

public class Main {

	public static void main(String[] args) throws IOException {
		final String serviceListXML = "./ServiceListsXML/user1_servicelist.xml";
		final String changePasswordDir = "./change_password";

		Predicate<? super Path> predicate = path -> path.toString().endsWith(".trg");

		List<Path> fileNamesList = Files.list(Paths.get(changePasswordDir))
										.filter(Files::isRegularFile)
										.filter(predicate)
										.collect(Collectors.toList());

		
		fileNamesList.stream().forEach(System.out::println);
		
		Map<String, String> credentials = new HashMap<>();
		
		fileNamesList.forEach(path -> {
			credentials.put(path.getFileName().toString(), "Value");
//						Files.lines(path).map(s -> s.split(",")).findFirst().get());
		});
		
		credentials.forEach((k, v) -> System.out.println("Key & Value: " + k + ":" + v));
		new JDOMXMLReader().prepareServerList(serviceListXML);
	}

}
