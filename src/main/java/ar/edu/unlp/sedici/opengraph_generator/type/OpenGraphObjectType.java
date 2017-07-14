package ar.edu.unlp.sedici.opengraph_generator.type;

import java.util.ArrayList;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphMetadata;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;

public abstract class OpenGraphObjectType {
	
	protected OpenGraphNamespace typeNamespace;
	
	private ArrayList<OpenGraphMetadata> typeMetadata;
	
	public OpenGraphObjectType() {
		this.typeMetadata = new ArrayList<OpenGraphMetadata>();
		this.
	}

	public OpenGraphNamespace getTypeNamespace() {
		return typeNamespace;
	}

	public ArrayList<OpenGraphMetadata> getTypeMetadata() {
		return typeMetadata;
	}
	public void defineTypeMetadata() {
		
	}
	
	
	
	
}
