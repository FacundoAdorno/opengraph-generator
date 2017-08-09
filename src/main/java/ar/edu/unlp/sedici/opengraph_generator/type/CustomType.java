package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;
import ar.edu.unlp.sedici.opengraph_generator.exceptions.InvalidMetadataBindingException;

/**
 * This class was thinking to create a on-the-fly custom OpenGraph object types.
 * 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class CustomType extends OpenGraphObjectType {
	
	/**
	 * Instantiate the custom type specifing a custom Namespace.
	 * @param namespace		is the custom namespace
	 */
	public CustomType(OpenGraphNamespace namespace) {
		super();
		this.typeNamespace = namespace;
	}
	
	/**
	 * Specify the subtype of this type only if exists restrictions for it. It is not manadatory. If this CustomType doesnt have subtype, {@link OpenGraphObjectType#NO_VERTICAL_TYPE_SUBTYPE}
	 * is the default.
	 * 
	 * @param subtype is the subtype to set for this Type. The subtype restrictions for the subtype specified must exists to set it sucessfully. If the restrictions doesn't exists, you must
	 * add it using {@link #addSubtype(String, String...)} method.
	 */
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	
	/**
	 * Specify the metadata names that are supported/valid for this cutom object type.
	 * @param typeRestricions
	 */
	public void addTypeRestrictions(String... typeRestricions){
		this.createTypeMetadataRestriction(typeRestricions);
	}
	
	/**
	 * Specify the subtype for this custom type and a list with the restrictions for this type.
	 * @param subtypeName
	 * @param subtypeRestricions
	 */
	public void addSubtype(String subtypeName, String... subtypeRestricions) {
		this.createSubtypeMetadataRestriction(subtypeName, subtypeRestricions);
	}
	
	/**
	 * Create a new metadata for this type. 
	 * @param metadataName
	 * @param metadataValue
	 */
	public void addMetadata(String metadataName, String metadataValue) {
		try {
			this.addSimpleMetadataBySubtype(subtype, metadataName, metadataValue);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
}
