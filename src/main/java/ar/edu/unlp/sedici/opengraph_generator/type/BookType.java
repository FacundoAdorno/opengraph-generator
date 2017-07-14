/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo
 *
 */
public class BookType extends OpenGraphObjectType {

	public BookType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("book", "http://ogp.me/ns/book#");
	}
}
