package ar.edu.unlp.sedici.opengraph_generator;

public class OpenGraphNamespace {
	
	private String prefix;
	private String schemaURI;
	
	private static String DEFAULT_PREFIX = "og";
	private static String DEFAULT_SCHEMA = "http://ogp.me/ns#";
	
	public OpenGraphNamespace(String prefix, String schemaURI) {
		this.prefix = prefix;
		this.schemaURI = schemaURI;
	}
	
	public static OpenGraphNamespace getDefaultNamespace() {
		return new OpenGraphNamespace(DEFAULT_PREFIX, DEFAULT_SCHEMA);
	}
}
