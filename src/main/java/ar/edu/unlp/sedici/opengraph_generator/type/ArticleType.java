/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;
import ar.edu.unlp.sedici.opengraph_generator.exceptions.InvalidMetadataBindingException;
import ar.edu.unlp.sedici.opengraph_generator.type.MusicType.MusicMetadata;

/**
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class ArticleType extends OpenGraphObjectType {
	
	public static enum ArticleMetadata{
		PUBLISHED_TIME("published_time"), MODIFIED_TIME("modified_time"), EXPIRATION_TIME("expiration_time"), AUTHOR("author"), SECTION("section"), TAG("tag");
		
		private String metadataName;
		
		ArticleMetadata(String metadataName){
			this.metadataName = metadataName;
		}
		
		public String text() {
			return this.metadataName;
		}
		
		/**
		 * Concatenate the enum text + prefix passed as parameter
		 * @param prefix		String to concatenate as prefix.
		 * @return concatenation prefix + enum value
		 */
		public String text(String prefix) {
			return prefix + ":" + this.metadataName;
		}
	}
	
	public ArticleType() {
		super();
		this.typeNamespace = new OpenGraphNamespace("article", "http://ogp.me/ns/article#");
	}
	
	protected void defineNoVerticalRestrictions() {
		super.defineNoVerticalRestrictions();
		createTypeMetadataRestriction(ArticleMetadata.AUTHOR.text(), ArticleMetadata.EXPIRATION_TIME.text(), ArticleMetadata.MODIFIED_TIME.text(), ArticleMetadata.PUBLISHED_TIME.text(), ArticleMetadata.SECTION.text(), ArticleMetadata.TAG.text());
	}
	
	public void addAuthor(String authorProfileUrl) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.AUTHOR.text(), authorProfileUrl);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public void setPublishedTime(String publishedTime) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.PUBLISHED_TIME.text(), publishedTime);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public void setModifiedTime(String modifiedTime) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.MODIFIED_TIME.text(), modifiedTime);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public void setExpirationTime(String expirationTime) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.EXPIRATION_TIME.text(), expirationTime);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public void setSection(String section) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.SECTION.text(), section);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public void addTag(String tag) {
		try {
			addSimpleMetadataBySubtype(subtype, ArticleMetadata.TAG.text(), tag);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
		
}
