/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo
 *
 */
public class WebsiteType extends OpenGraphObjectType {
	
	public WebsiteType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("website", "http://ogp.me/ns/website#");
	}
}
