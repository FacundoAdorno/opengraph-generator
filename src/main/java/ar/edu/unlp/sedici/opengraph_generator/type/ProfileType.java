package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

public class ProfileType extends OpenGraphObjectType {

	public ProfileType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("profile", "http://ogp.me/ns/profile#");
	}
}
