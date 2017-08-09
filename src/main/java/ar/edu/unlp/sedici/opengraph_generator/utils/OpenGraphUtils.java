package ar.edu.unlp.sedici.opengraph_generator.utils;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphGenerator;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphMetadata;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.sound.sampled.Line;

/**
 * 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class OpenGraphUtils {
	
	private static InstanceCounter counter = new OpenGraphUtils.InstanceCounter();
	
	public static String generateMetaHTML(OpenGraphGenerator og) {
		//TODO a partir de los datos en el og, generar un string con todos los campos <meta>
		return " ";
	}
	
	/**
	 * Given a metadataName string, this method parse this name by the colons (':') to get the element and (optionally) the qualifier of the metadata, and creates a OpenGraphMetadata 
	 * using those parsed values.
	 * 
	 * @param metadataName is the metadata name to parse, i.e. 'title' for og:title metadata, 'song:disc' for music:song:disc metadata, etc
	 * @param namespace		Is the namespace used for the metadata. This parameter must have a value.
	 * @param value		Is the value for the metadata
	 * @return
	 */
	//TODO complete
	public static OpenGraphMetadata createMetadata(String metadataName, OpenGraphNamespace namespace, String value) {
		List<String> metadataParts = Arrays.asList(metadataName.split( ":" ));
		//metadataName is formed at least by a 'element' fields and (optionally) a 'qualifier' field...
		String element = metadataParts.get(0);
		if(metadataParts.size() >= 2) {
			String qualifier = metadataParts.get(1);
			return new OpenGraphMetadata(element, qualifier, namespace, value);
		} else {
			return new OpenGraphMetadata(element, namespace, value);
		}
	}
		
	/**
	  * Create a new metadata considering that the 'value' passed as parameter can be null or empty. This method is useful in case of structured properties, where not all of its components has necessary value.
	  * <p>
	  * Given a metadataName string, this method parse this name by the colons (':') to get the element and (optionally) the qualifier of the metadata, and creates a OpenGraphMetadata 
	 * using those parsed values.
	 * 
	 * @param metadataName is the metadata name to parse, i.e. 'title' for og:title metadata, 'song:disc' for music:song:disc metadata, etc
	 * @param namespace		Is the namespace used for the metadata
	 * @param value		Is the value for the metadata. Can be NULL or empty.
	 * @return If 'value' is null or empty, return {@code NULL}, else return a {@link OpenGraphMetadata}.
	 */
	public static OpenGraphMetadata createOptionalMetadata(String metadataName, OpenGraphNamespace namespace, String value){
		if(value != null || value.length() > 0) {
			return createMetadata(metadataName, namespace, value);
		}
		return null;
	}
	
	/**
	 * Add a new metadata to a structured property. This method is useful in case of structured properties, where the component added must necessary have a value.
	 * <p>
	 * Given a metadataName string, this method parse this name by the colons (':') to get the element and (optionally) the qualifier of the metadata, and creates a OpenGraphMetadata 
	 * using those parsed values.
	 * 
	 * @param metadataName is the metadata name to parse, i.e. 'title' for og:title metadata, 'song:disc' for music:song:disc metadata, etc
	 * @param namespace		Is the namespace used for the metadata
	 * @param value		Is the value for the metadata. Must have a value.
	 * @param structuredProperty	Is a list representing a structured property. If the metadata can be created, then add it to this structured property.
	 */
	public static void createMetadata(String metadataName, OpenGraphNamespace namespace, String value, ArrayList<OpenGraphMetadata> structuredProperty){
		structuredProperty.add(createMetadata(metadataName, namespace, value));
	}
	
	/**
	  * Add a new metadata to a structured property considering that the 'value' passed as parameter can be null or empty. This method is useful in case of structured properties, where not all of its components has necessary value.
	  * <p>
	  * Given a metadataName string, this method parse this name by the colons (':') to get the element and (optionally) the qualifier of the metadata, and creates a OpenGraphMetadata 
	 * using those parsed values.
	 * 
	 * @param metadataName is the metadata name to parse, i.e. 'title' for og:title metadata, 'song:disc' for music:song:disc metadata, etc
	 * @param namespace		Is the namespace used for the metadata
	 * @param value		Is the value for the metadata. Can be NULL or empty.
	 * @param structuredProperty	Is a list representing a structured property. If the metadata can be created, then add it to this structured property.
	 */
	public static void createOptionalMetadata(String metadataName, OpenGraphNamespace namespace, String value, ArrayList<OpenGraphMetadata> structuredProperty){
		OpenGraphMetadata mtd = createOptionalMetadata(metadataName, namespace, value);
		if(mtd != null) {
			structuredProperty.add(mtd);
		}
	}
	
	/**
	 * Check if a passed metadata is well configured or not. The validation is based on the following consideration:
	 * <ul>
	 * 	<li>have a namespace with a prefix and schemaURI configured</li>
	 * 	<li>have at least the 'element' field configured (considerate that a metadata is conformed by schema:element:qualifier)</li>
	 * 	<li>have a value</li>
	 * </ul>
	 * @param ogm the metadata to validate if is well configured
	 * @return true if it is well configured.
	 */
	public static boolean isWellConfigured(OpenGraphMetadata ogm) {
		return (!(ogm.getNamespace() != null) && !ogm.getNamespace().getPrefix().isEmpty() && !ogm.getNamespace().getSchemaURI().isEmpty() && 
				!ogm.getElement().isEmpty() && !ogm.getValue().isEmpty());
	}
	
	/**
	 * Create an inmutable list.
	 * @param list
	 * @return
	 */
	public static List<Object> getInmutableList(ArrayList<?> list) {
		return Collections.unmodifiableList(list);
	}
	
	public static int getNextCounterValue(String counterKey) {
		return counter.getNextValueInstance(counterKey);
	}
	
	/**
	 * Inner class used for count instances of metadata
	 * @author facundo@sedici.unlp.edu.ar
	 *
	 */
	static class InstanceCounter{
		private HashMap<String,Integer> counter;
		
		public InstanceCounter() {
			this.counter = new HashMap<String, Integer>();
		}
		
		public int getNextValueInstance(String instance) {
			if(!this.counter.containsKey(instance)) {
				this.counter.put(instance, 0);
			}
			//increment counter for the passed instance
			this.counter.put(instance, this.counter.get(instance) + 1);
			return this.counter.get(instance);
		}
	}
	
	
}
