package ar.edu.unlp.sedici.opengraph_generator.utils;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphGenerator;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphMetadata;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class OpenGraphUtils {
	
	public static String generateMetaHTML(OpenGraphGenerator og) {
		//TODO a partir de los datos en el og, generar un string con todos los campos <meta>
		return " ";
	}
	
	/**
	 * Given a fullMetadataName string, this method parse this name by the colons (':') to get the schema, element and (optional) qualifier and returns a OpenGraphMetadata using
	 * those values parsed.
	 * 
	 * @param fullMetadataName is the metadata name to parse, i.e. 'og:title', 'music:song:disc', etc.
	 * @return
	 */
	//TODO complete
	public static OpenGraphMetadata createMetadata(String fullMetadataName, OpenGraphNamespace namespace, String value) {
		List<String> metadataParts = Arrays.asList(fullMetadataName.split( ":" ));
		//A fullMetadataName is well formed, it must have schema and element fields, with an optional qualifier field...
		String schema = metadataParts.get(0);
		String element = metadataParts.get(1);
		if(metadataParts.size() >= 3) {
			String qualifier = metadataParts.get(2);
			return new OpenGraphMetadata(element, qualifier, namespace, value);
		} else {
			return new OpenGraphMetadata(element, namespace, value);
		}
	}
	
	public static List<Object> getInmutableList(ArrayList<?> list) {
		return Collections.unmodifiableList(list);
	}
	
	
}
