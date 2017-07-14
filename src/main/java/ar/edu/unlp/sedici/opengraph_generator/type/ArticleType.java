/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo
 *
 */
public class ArticleType extends OpenGraphObjectType {
	
	public ArticleType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("article", "http://ogp.me/ns/article#");
	}
		
}
