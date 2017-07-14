/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator;

/**
 * @author facundo
 *
 */
public class OpenGraphMetadata {

	private static OpenGraphNamespace DEFAULT_NAMESPACE = OpenGraphNamespace.getDefaultNamespace();
	
	private OpenGraphNamespace namespace;
	
	private String element;
	
	//Optional. Only Structured Properties have qualifier. Read more at http://ogp.me/#structured
	private String qualifier;
	
	public OpenGraphMetadata(String element) {
		this.namespace = DEFAULT_NAMESPACE;
		this.element = element;
	}
	
	public OpenGraphMetadata(String element, OpenGraphNamespace namespace) {
		this.namespace = namespace;
		this.element = element;
	}
	
	public OpenGraphMetadata(String element, String qualifier) {
		this.namespace = DEFAULT_NAMESPACE;
		this.element = element;
		this.qualifier = qualifier;
	}
	
	public OpenGraphMetadata(String element, String qualifier, OpenGraphNamespace namespace) {
		this.namespace = namespace;
		this.element = element;
		this.qualifier = qualifier;
	}

}
