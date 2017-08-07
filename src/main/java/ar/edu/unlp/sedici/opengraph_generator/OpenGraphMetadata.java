/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator;

/**
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class OpenGraphMetadata {

	private static OpenGraphNamespace DEFAULT_NAMESPACE = OpenGraphNamespace.getDefaultNamespace();
	
	private OpenGraphNamespace namespace;
	
	private String element;
	
	//Optional. Only Structured Properties have qualifier. Read more at http://ogp.me/#structured
	private String qualifier;
	
	private String value;
	
	/**
	 * 
	 * @param element
	 * @param value
	 */
	public OpenGraphMetadata(String element, String value) {
		this.namespace = DEFAULT_NAMESPACE;
		this.element = element;
		this.value = value;
	}
	
	/**
	 * 
	 * @param element
	 * @param namespace
	 * @param value
	 */
	public OpenGraphMetadata(String element, OpenGraphNamespace namespace, String value) {
		this.namespace = namespace;
		this.element = element;
		this.value = value;
	}
	
	/**
	 * 
	 * @param element
	 * @param qualifier
	 * @param value
	 */
	public OpenGraphMetadata(String element, String qualifier, String value) {
		this.namespace = DEFAULT_NAMESPACE;
		this.element = element;
		this.qualifier = qualifier;
		this.value = value;
	}
	
	/**
	 * 
	 * @param element
	 * @param qualifier
	 * @param namespace
	 * @param value
	 */
	public OpenGraphMetadata(String element, String qualifier, OpenGraphNamespace namespace, String value) {
		this.namespace = namespace;
		this.element = element;
		this.qualifier = qualifier;
		this.value = value;
	}

	public OpenGraphNamespace getNamespace() {
		return namespace;
	}

	public String getElement() {
		return element;
	}

	public String getQualifier() {
		return qualifier;
	}
	
	public String getValue() {
		return value;
	}

	/**
	 * Get a value of 'name="{namespace}:{element}:{qualifier}" value="{value}" '
	 * @return String that represents de metadata
	 */
	public String print() {
		 StringBuilder sb = new StringBuilder();
		 sb.append("property = \"");
		 sb.append(namespace.getPrefix()); 
		 sb.append(":");
		 sb.append(element); 
		 if(qualifier != null) {
			 sb.append(":"); 
			 sb.append(qualifier); 
		 }
		 sb.append("\" content = \"");
		 sb.append(value);
		 sb.append("\" ");
		 return sb.toString();
	}
	
	public static void main(String args[]) {
		OpenGraphMetadata ogmdt = new OpenGraphMetadata("title", "Que tal Bob?");
		System.out.println(ogmdt.getNamespace().print());
		System.out.println("<meta "+ ogmdt.print() + " />");
	}

}
