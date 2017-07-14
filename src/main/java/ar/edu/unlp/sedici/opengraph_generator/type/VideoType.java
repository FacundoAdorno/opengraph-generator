/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

/**
 * @author facundo
 *
 */
public class VideoType extends OpenGraphObjectType {

	public VideoType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("video", "http://ogp.me/ns/video#");
	}
}
