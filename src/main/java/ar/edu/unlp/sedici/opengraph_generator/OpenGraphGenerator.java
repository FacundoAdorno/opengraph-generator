package ar.edu.unlp.sedici.opengraph_generator;

import java.util.ArrayList;
import java.util.HashMap;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace.Default_OG_Metadata;
import ar.edu.unlp.sedici.opengraph_generator.type.ArticleType;
import ar.edu.unlp.sedici.opengraph_generator.type.BookType;
import ar.edu.unlp.sedici.opengraph_generator.type.CustomType;
import ar.edu.unlp.sedici.opengraph_generator.type.MusicType;
import ar.edu.unlp.sedici.opengraph_generator.type.OpenGraphObjectType;
import ar.edu.unlp.sedici.opengraph_generator.type.ProfileType;
import ar.edu.unlp.sedici.opengraph_generator.type.VideoType;
import ar.edu.unlp.sedici.opengraph_generator.type.WebsiteType;
import ar.edu.unlp.sedici.opengraph_generator.utils.OpenGraphUtils;

/**
 * @author Facundo Adorno (facundo@sedici.unlp.edu.ar)
 */

/**
 *  Main class of the OpenGraph Generator for Java. It is used to generate a set of metadata fields  that represent an OpenGraph Object.
 *  <p>
 *  For more details about "Open Graph Protocol" go to http://ogp.me
 *  
 *  @see <a href="http://ogp.me">Open Graph Protocol</a>
 */
public class OpenGraphGenerator {
	/**
	 * Minimum set of mandatory metadata for an Object:
	 * 	- title, url, type, image
	 */
	//The title of your object as it should appear within the graph, e.g., "The Rock".
	private OpenGraphMetadata title;
	//The canonical URL of your object that will be used as its permanent ID in the graph, e.g., "http://www.imdb.com/title/tt0117500/".
	private OpenGraphMetadata url;
	//An image (URL as mandatory) which should represent your object within the graph. It's a structured property and can exists multiple instances.
	private ArrayList<ArrayList<OpenGraphMetadata>> images;
	//The type of your object, e.g., "video.movie". Depending on the type you specify, other properties may also be required.
	private OpenGraphObjectType type;
	//This is the default Object Type is no one is specified. Any non-marked up webpage should be treated as og:type website.
	public static final OpenGraphObjectType DEFAULT_OBJECT_TYPE = new WebsiteType();
	
	/**
	 * Optional metadata
	 */
	//If your object is part of a larger web site, the name which should be displayed for the overall site. e.g., "IMDb".
	private OpenGraphMetadata siteName;
	//A one to two sentence description of your object.
	private OpenGraphMetadata description;
	//The word that appears before this object's title in a sentence. An enum of (a, an, the, "", auto). If auto is chosen, the consumer of your data should chose between "a" or "an". Default is "" (blank).
	private OpenGraphMetadata determiner;
	//The locale these tags are marked up in. Of the format language_TERRITORY. Default is en_US.
	private OpenGraphMetadata locale;
	// Represent  an audio file to accompany this object. It's a structured property and can exists multiple instances.
	private ArrayList<ArrayList<OpenGraphMetadata>> audios;
	// Represent  a video file to accompany this object. It's a structured property and can exists multiple instances.
	private ArrayList<ArrayList<OpenGraphMetadata>> videos;
	
	//Holds the list of simple metadata set for the object
	private HashMap<String, OpenGraphMetadata> metadata;
	
	
	/**
	 * Constructor
	 */
	public OpenGraphGenerator() {
		//by default set the object type as website
		this.metadata = new HashMap<String, OpenGraphMetadata>();
		this.images = new ArrayList<ArrayList<OpenGraphMetadata>>();
		this.videos = new ArrayList<ArrayList<OpenGraphMetadata>>();
		this.audios = new ArrayList<ArrayList<OpenGraphMetadata>>();
		this.setWebsiteType();
	}
	
	public void setTitle(String title) {
		this.title = new OpenGraphMetadata(Default_OG_Metadata.TITLE.text(), title);
		this.metadata.put(Default_OG_Metadata.TITLE.text(), this.title);
	}

	public void setUrl(String url) {
		this.url =  new OpenGraphMetadata(Default_OG_Metadata.URL.text(), url);
		this.metadata.put(Default_OG_Metadata.URL.text(), this.url);
	}

	public void setSiteName(String siteName) {
		this.siteName = new OpenGraphMetadata(Default_OG_Metadata.SITENAME.text(),siteName);
		this.metadata.put(Default_OG_Metadata.SITENAME.text(), this.siteName);
	}

	public void setDescription(String description) {
		this.description = new OpenGraphMetadata(Default_OG_Metadata.DESCRIPTION.text(),description);
		this.metadata.put(Default_OG_Metadata.DESCRIPTION.text(), this.description);
	}

	public void setDeterminer(String determiner) {
		this.determiner = new OpenGraphMetadata(Default_OG_Metadata.DETERMINER.text(), determiner);
		this.metadata.put(Default_OG_Metadata.DETERMINER.text(), this.determiner);
	}

	public void setLocale(String locale) {
		this.locale = new OpenGraphMetadata(Default_OG_Metadata.LOCALE.text(), locale);
		this.metadata.put(Default_OG_Metadata.LOCALE.text(), this.locale);
	}
	
	public void addAlternativeLocale(String alt_locale) {
		//TODO manejar multiples instancias, cambiando definición de variable 'metadata' o de alguna otra forma.
		this.metadata.put(
				Default_OG_Metadata.LOCALE_ALTERNATE.text() + "_" + OpenGraphUtils.getNextCounterValue(Default_OG_Metadata.LOCALE_ALTERNATE.text()), 
				OpenGraphUtils.createMetadata(Default_OG_Metadata.LOCALE_ALTERNATE.text(), OpenGraphNamespace.getDefaultNamespace(), alt_locale)
		);
	}
	
	/**
	 * Internal method to set the object type. To set the object type from outside object, use any of the public methods available (i.e. {@link #setArticleType()}, {@link #setWebsiteType()}, etc.).
	 * @param type		The type of your object, e.g., "video.movie".
	 */
	private void setType(String type) {
		OpenGraphMetadata typeMdt = new OpenGraphMetadata(Default_OG_Metadata.TYPE.text(), type);
		this.metadata.put(Default_OG_Metadata.TYPE.text(), typeMdt);
	}
	
	/**
	 * Add an optional og:image structured property.
	 * @param url
	 * @param secureUrl (optional)
	 * @param mimetype (optional)
	 * @param width (optional)
	 * @param height (optional)
	 * @param alt (optional)
	 */
	public void addImage(String url, String secureUrl, String mimetype, String width, String height, String alt){
		ArrayList<OpenGraphMetadata> image = new ArrayList<OpenGraphMetadata>();
		OpenGraphNamespace namespace = OpenGraphNamespace.getDefaultNamespace();
		OpenGraphUtils.createMetadata(Default_OG_Metadata.IMAGE_URL.text(), namespace, url, image);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.IMAGE_SECURE_URL.text(), namespace, secureUrl, image);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.IMAGE_TYPE.text(), namespace, mimetype, image);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.IMAGE_WIDTH.text(), namespace, width, image);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.IMAGE_HEIGHT.text(), namespace, height, image);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.IMAGE_ALT.text(), namespace, alt, image);
		this.audios.add(image);
	}
	
	/**
	 * Add an optional og:video structured property.
	 * @param url
	 * @param secureUrl (optional)
	 * @param mimetype (optional)
	 * @param width (optional)
	 * @param height (optional)
	 * @param alt (optional)
	 */
	public void addVideo(String url, String secureUrl, String mimetype, String width, String height, String alt) {
		ArrayList<OpenGraphMetadata> video = new ArrayList<OpenGraphMetadata>();
		OpenGraphNamespace namespace = OpenGraphNamespace.getDefaultNamespace();
		OpenGraphUtils.createMetadata(Default_OG_Metadata.VIDEO_URL.text(), namespace, url, video);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.VIDEO_SECURE_URL.text(), namespace, secureUrl, video);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.VIDEO_TYPE.text(), namespace, mimetype, video);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.VIDEO_WIDTH.text(), namespace, width, video);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.VIDEO_HEIGHT.text(), namespace, height, video);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.VIDEO_ALT.text(), namespace, alt, video);
		this.videos.add(video);
	}

	/**
	 * Add an optional og:audio structured property.
	 * @param url
	 * @param secureUrl (optional)
	 * @param mimetype (optional)
	 */
	public void addAudio(String url, String secureUrl, String mimetype) {
		ArrayList<OpenGraphMetadata> audio = new ArrayList<OpenGraphMetadata>();
		OpenGraphNamespace namespace = OpenGraphNamespace.getDefaultNamespace();
		OpenGraphUtils.createMetadata(Default_OG_Metadata.AUDIO_URL.text(), namespace, url, audio);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.AUDIO_SECURE_URL.text(), namespace, secureUrl, audio);
		OpenGraphUtils.createOptionalMetadata(Default_OG_Metadata.AUDIO_TYPE.text(), namespace, mimetype, audio);
		this.audios.add(audio);
	}
	
	/**
	 * If wants to add a custom or arbitrary metadata, use this method. i.e. for the Facebook stories that use the 'fb' namespace, among others.
	 * If you want to add customs metadata but binding to a certain new/custom OpenGraphType, see {@link CustomType}.
	 *<p>
	 * @param ogm
	 * @see <a href="https://developers.facebook.com/docs/sharing/opengraph">Open Graph Stories for reference</a>
	 */
	public void addCustomOGMetadata(OpenGraphMetadata ogm) {
		if(OpenGraphUtils.isWellConfigured(ogm)) {
			//save the custom metadata using the "metadata full name" as key
			this.metadata.put(ogm.getMetadataName(true),ogm);
		}
	}
	
	////////////////////////////////////////////////////////////////
	/////			VERTICAL TYPES
	///////////////////////////////////////////////////////////////
	public void setMusicType(String subtype) {
		this.type = new MusicType(subtype);
		setType(this.type.getFullSubtype());
	}
	
	public void setVideoType(String subtype) {
		this.type = new VideoType(subtype);
		setType(this.type.getFullSubtype());
	}
	
	////////////////////////////////////////////////////////////////
	/////			NO-VERTICAL TYPES
	///////////////////////////////////////////////////////////////
	public void setArticleType() {
		this.type = new ArticleType();
		setType(this.type.getFullSubtype());
	}
	
	public void setBookType() {
		this.type = new BookType();
		setType(this.type.getFullSubtype());
	}
	
	public void setProfileType() {
		this.type = new ProfileType();
		setType(this.type.getFullSubtype());
	}
	
	public void setWebsiteType() {
		this.type = new WebsiteType();
		setType(this.type.getFullSubtype());
	}
	
	public void setCustomType(OpenGraphObjectType customType) {
		this.type = customType;
		setType(this.type.getFullSubtype());
	}
	
	/**
	 * Return the object type for manipulation
	 * @return the type {@link OpenGraphObjectType} of this object.	
	 */
	public OpenGraphObjectType getType() {
		return this.type;
	}
	
	/**
	 * Get all the metadata that represents this OpenGraph object. Returns all the common metadata (in the 'og' namespace) and the type metadata (i.e. metadata for the 'music' namespace).
	 * @return a list of {@link OpenGraphMetadata} metadata.
	 */
	public ArrayList<OpenGraphMetadata> getAllObjectMetadata(){
		ArrayList<OpenGraphMetadata> allMtd = new ArrayList<OpenGraphMetadata>();
		allMtd.addAll(metadata.values());
		allMtd.addAll(type.getTypeMetadata());
		return allMtd;
	}
	
	public static void main(String[] args) {
		OpenGraphGenerator ogg = new OpenGraphGenerator();
		ogg.setArticleType();
		ogg.setTitle("Repositorios DSpace con múltiples contextos OAI-PMH");
		ogg.setLocale("es");
		ogg.addAlternativeLocale("en");
		ogg.addAlternativeLocale("pt");
		ogg.addAlternativeLocale("fr");
		ogg.addAlternativeLocale("jp");
		ArticleType article = (ArticleType) ogg.getType();
		article.addAuthor("https://www.researchgate.net/profile/Facundo_Adorno");
		article.addAuthor("https://www.researchgate.net/profile/Ariel_Lira");
		article.addAuthor("https://www.researchgate.net/profile/Marisa_De_Giusti");
		article.setPublishedTime("2014-11");
		article.addTag("DSpace"); article.addTag("xoai"); article.addTag("SEDICI"); article.addTag("data provider");
		for (OpenGraphMetadata ogm : ogg.getAllObjectMetadata()) {
			System.out.println(ogm.print());
		}
	}
		
}
