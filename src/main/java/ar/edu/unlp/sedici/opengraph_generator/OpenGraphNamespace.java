package ar.edu.unlp.sedici.opengraph_generator;

import java.util.ArrayList;

/**
 * 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class OpenGraphNamespace {
	
	private String prefix;
	private String schemaURI;
	
	private static String DEFAULT_PREFIX = "og";
	private static String DEFAULT_SCHEMA = "http://ogp.me/ns#";
	
	public static enum Default_OG_Metadata{
		TITLE("title"), TYPE("type"), URL("url"), IMAGE("image"), IMAGE_URL("image:url"), IMAGE_SECURE_URL("image:secure_url"), IMAGE_TYPE("image:type"), IMAGE_WIDTH("image:width"),
		IMAGE_HEIGHT("image:height"), IMAGE_ALT("image:alt"), VIDEO("video"), VIDEO_URL("video:url"), VIDEO_SECURE_URL("video:secure_url"), VIDEO_TYPE("video:type"), VIDEO_WIDTH("video:width"), 
		VIDEO_HEIGHT("video:height"), VIDEO_ALT("video:alt"), AUDIO("audio"), AUDIO_URL("audio:url"), AUDIO_SECURE_URL("audio:secure_url"), AUDIO_TYPE("audio:type")	,
		DESCRIPTION("description"), DETERMINER("determiner"), LOCALE("locale"), LOCALE_ALTERNATE("locale:alternate"), SITENAME("site_name");
		
		private String metadataName;
		
		Default_OG_Metadata(String metadataName){
			this.metadataName = metadataName;
		}
		
		public String text() {
			return this.metadataName;
		}
		
		/**
		 * Get the mandatory metadata for the "og" metadata schema namespace
		 * @return a list with of mandatory metadata for "og" namespace.
		 */
		public ArrayList<String> getMandatoryMetadata() {
			ArrayList<String> mandatorymdt = new ArrayList<String>();
			mandatorymdt.add("music");
			mandatorymdt.add("type");
			mandatorymdt.add("url");
			mandatorymdt.add("image");
			return mandatorymdt;
		}
	}
	public OpenGraphNamespace(String prefix, String schemaURI) {
		this.prefix = prefix;
		this.schemaURI = schemaURI;
	}
	
	public static OpenGraphNamespace getDefaultNamespace() {
		return new OpenGraphNamespace(DEFAULT_PREFIX, DEFAULT_SCHEMA);
	}

	public String getPrefix() {
		return prefix;
	}

	public String getSchemaURI() {
		return schemaURI;
	}
	
	public String print() {
		return prefix + ": " +  schemaURI; 
	}
}
