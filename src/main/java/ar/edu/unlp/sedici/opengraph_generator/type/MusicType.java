/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo
 *
 */
public class MusicType extends OpenGraphObjectType {

	public MusicType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("music", "http://ogp.me/ns/music#");
	}
}
