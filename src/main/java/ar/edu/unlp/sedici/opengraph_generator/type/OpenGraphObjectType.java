package ar.edu.unlp.sedici.opengraph_generator.type;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphMetadata;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;
import ar.edu.unlp.sedici.opengraph_generator.exceptions.InvalidMetadataBindingException;
import ar.edu.unlp.sedici.opengraph_generator.type.MusicType.MusicMetadata;
import ar.edu.unlp.sedici.opengraph_generator.utils.OpenGraphUtils;

/**
 * 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public abstract class OpenGraphObjectType {
	
	protected OpenGraphNamespace typeNamespace;
	
	protected ArrayList<OpenGraphMetadata> typeMetadata;
	
	protected String subtype;
	
	protected HashMap<String, ArrayList<String> > subtypeMetadataRestrictions;
	
	/**
	 * A "no-vertical" type has no subtype available. 
	 * http://ogp.me/#no_vertical
	 */
	protected static final String NO_VERTICAL_TYPE_SUBTYPE = "none";
	
	/**
	 * Use this constructor when an OpenGraph object has a "vertical type" ()
	 * @param subtype
	 */
	public OpenGraphObjectType(String subtype) {
		this.subtype = subtype;
		this.typeMetadata = new ArrayList<OpenGraphMetadata>();
		this.subtypeMetadataRestrictions = new HashMap<String, ArrayList<String>>();
		this.defineSubtypeMetadataRestrictions();
		this.defineNoVerticalRestrictions();
	}
	
	public OpenGraphObjectType() {
		this.subtype = OpenGraphObjectType.NO_VERTICAL_TYPE_SUBTYPE;
		this.typeMetadata = new ArrayList<OpenGraphMetadata>();
		this.subtypeMetadataRestrictions = new HashMap<String, ArrayList<String>>();
		this.defineSubtypeMetadataRestrictions();
		this.defineNoVerticalRestrictions();
	}

	public OpenGraphNamespace getTypeNamespace() {
		return typeNamespace;
	}

	public ArrayList<OpenGraphMetadata> getTypeMetadata() {
		//NOTE: we want to return an inmutableList to prevent wrong manipulations of the typeMetadataList? This is relevant only in case of Structured Properties at OGP.
		return typeMetadata;
	}
	
	public HashMap<String, ArrayList<String>> getSubtypeMetadataRestrictions() {
		return subtypeMetadataRestrictions;
	}
	
	protected void checkSubtypeRestriction(String subtype, String metadata) {
		
	}
	
	/**
	 * Here must define the specific metadata restriction/bindings for each subtype in a "vertical" type.
	 * This method is intended to use for those types that are "vertical" (such as Music, Video, etc) and have defined differents subtypes for its instances.
	 * http://ogp.me/#types
	 */
	protected void defineSubtypeMetadataRestrictions() {
	}
	
	/**
	 * Here must define the specific metadata restriction/bindings for a no-vertical type.
	 * This method is intended to use for those types that are "no-vertical" (such as Article, Book, etc) and don't have subtypes. 
	 * Besides, is intended too for those types that have metadata binding independently of its subtype.
	 * By default create an empty "none" metadata restriction list for this type of OpenGraph objects.
	 * http://ogp.me/#no_vertical
	 */
	protected void defineNoVerticalRestrictions() {
		//no vertical restriction list
		createSubtypeMetadataRestriction(OpenGraphObjectType.NO_VERTICAL_TYPE_SUBTYPE, "");
	}
	
	/**	
	 * Get the complete notation of the type of OpenGraph object.
	 * @return the concatenation 'type'.'subtype', if subtype was set.
	 */
	public String getFullSubtype() {
		if(this.subtype != null && this.subtype != NO_VERTICAL_TYPE_SUBTYPE) {
			return typeNamespace.getPrefix() + '.' + this.subtype;
		} else {
			return typeNamespace.getPrefix();
		}
	}
	/**
	 * Add a full list of metadata restrictions for a particular subtype.
	 * @param subtype is the subtype for create the restriction
	 * @param metadataList is the list of metadata related to that subtype (use "vargars" form)
	 */
	protected void createSubtypeMetadataRestriction(String subtype, String... metadataList) {
		ArrayList<String> mtdLst = new ArrayList<String>();
		for (String mtd : metadataList) {
			mtdLst.add(mtd);
		}
		//Check if the metadataList restrictions for that subtype already exists in the map. If exists, append new restrictions to the existing restrictions in map.
		if(subtypeMetadataRestrictions.containsKey(subtype)) {
			subtypeMetadataRestrictions.get(subtype).addAll(mtdLst);
		} else {
			subtypeMetadataRestrictions.put(subtype, mtdLst);			
		}
	}
	
	/**
	 * Add a full list of metadata restrictions for the current type, for vertical and no-vertical types.
	 * @param metadataList is the list of metadata related to that subtype (use "vargars" form)
	 */
	protected void createTypeMetadataRestriction(String... metadataList) {
		createSubtypeMetadataRestriction(OpenGraphObjectType.NO_VERTICAL_TYPE_SUBTYPE, metadataList);
	}
	
	/**
	 *  Given a metadataName for a subtype, add a new {@link OpenGraphMetadata } using the values passed as parameters. 
	 *  Besides, check if metadataName is valid for the configured subtype. If it is valid, add to the type metadata list; if not, raise an {@link InvalidMetadataBindingException} .
	 *  <p>
	 *  A 'simple metadata' in OpenGraph is a metadata that is not structured.
	 * 
	 * @param subtype 		Is the subtype name (without the type namespace prefix), i.e. 'album' for the 'music.album' subtype
	 * @param metadataName 	Is the metadata name without the type namespace prefix, i.e. 'song:disc' for the 'music.album' subtype
	 * @param metadataValue 		Is the content of the metadata
	 * 
	 * @throws InvalidMetadataBindingException if the metadataName doesn't is restricted by the subtype specified
	 */
	protected void addSimpleMetadataBySubtype(String subtype, String metadataName, String metadataValue) throws InvalidMetadataBindingException {
		OpenGraphMetadata ogmtd = getMetadataToAdd(subtype, metadataName, metadataValue);
		typeMetadata.add(ogmtd);
	}
	
	/**
	 * Given an array of metadata names for a subtype, add a set of new {@link OpenGraphMetadata } using the value list passed as parameter. 
	 *  Besides, check if every metadataName in the list is valid for the configured subtype. If all metadata are valid, add it to the type metadata list; 
	 *  if at least one metadata is not valid for the subtype specified, raise an {@link InvalidMetadataBindingException} .
	 *  <p>
	 *  This method is particularly favorable to use for structured properties in OpenGraph (http://ogp.me/#structured) where a set of metadata must be
	 *  specified in certain order in the HTML <meta> section as a way of represent it as a unique structured property.
	 * 
	 * @param subtype Is the subtype name (without the type namespace prefix), i.e. 'album' for the 'music.album' subtype
	 * @param metadataAndValues is a list of {@link SimpleEntry<K,V>} representing a set of K=metadataName and V=metadataValue.
	 * @throws InvalidMetadataBindingException
	 */
	protected void addStructuredPropertyBySubtype(String subtype, ArrayList<SimpleEntry<String, String>> metadataAndValues) throws InvalidMetadataBindingException {
		for (SimpleEntry<String, String> simpleEntry : metadataAndValues) {
			OpenGraphMetadata ogmtd = getMetadataToAdd(subtype, simpleEntry.getKey(), simpleEntry.getValue());
			typeMetadata.add(ogmtd);
		}
	}
	
	/**
	 * Create a {@link OpenGraphMetadata} with the specified metadata name and value. Besides, check if metadataName is valid for the configured subtype.
	 * 
	 * @param subtype		Is the subtype name (without the type namespace prefix), i.e. 'album' for the 'music.album' subtype
	 * @param metadataName 	Is the metadata name without the type namespace prefix, i.e. 'song:disc' for the 'music.album' subtype
	 * @param value 		Is the value for the metadata
	 * @return {@link OpenGraphMetadata} object configured with the parameters passed.
	 * @throws InvalidMetadataBindingException
	 */
	protected OpenGraphMetadata getMetadataToAdd(String subtype, String metadataName, String value) throws InvalidMetadataBindingException{
		if(subtypeMetadataRestrictions.containsKey(subtype)) {
			if( subtypeMetadataRestrictions.get(subtype).contains(metadataName) ) {
				return OpenGraphUtils.createMetadata(metadataName, typeNamespace, value);
			} 
			throw new InvalidMetadataBindingException("The metadata '" + metadataName + "' is not binding/restricted for the '" + typeNamespace.getPrefix() + "." + subtype + "' subtype.");
		} 
		throw new InvalidMetadataBindingException("The subtype '"  + typeNamespace.getPrefix() + "." + subtype + "' doesn't exists.");
	}
	
}
