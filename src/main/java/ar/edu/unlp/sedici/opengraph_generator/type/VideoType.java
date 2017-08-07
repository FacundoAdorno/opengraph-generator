/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class VideoType extends OpenGraphObjectType {

	public VideoType(String subtype) {
		super(subtype);
		this.typeNamespace = new OpenGraphNamespace("video", "http://ogp.me/ns/video#");
	}
}
