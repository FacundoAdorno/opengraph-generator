package ar.edu.unlp.sedici.opengraph_generator;

import java.util.ArrayList;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace.Default_OG_Metadata;
import ar.edu.unlp.sedici.opengraph_generator.type.ArticleType;
import ar.edu.unlp.sedici.opengraph_generator.type.BookType;
import ar.edu.unlp.sedici.opengraph_generator.type.MusicType;
import ar.edu.unlp.sedici.opengraph_generator.type.OpenGraphObjectType;
import ar.edu.unlp.sedici.opengraph_generator.type.ProfileType;
import ar.edu.unlp.sedici.opengraph_generator.type.VideoType;
import ar.edu.unlp.sedici.opengraph_generator.type.WebsiteType;

/**
 *  Main class used to generate a set of <meta> fields  to represent an OpenGraph Object.
 *  
 *  For more details of Open Graph Protocol go to http://ogp.me
 */

/**
 * @author Facundo Adorno (facundo@sedici.unlp.edu.ar)
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
	//An array of other locales this page is available in.
	private ArrayList<OpenGraphMetadata> localeAlternates;
	// Represent  an audio file to accompany this object. It's a structured property and can exists multiple instances.
	private ArrayList<ArrayList<OpenGraphMetadata>> audios;
	// Represent  a video file to accompany this object. It's a structured property and can exists multiple instances.
	private ArrayList<ArrayList<OpenGraphMetadata>> videos;
	
	//Holds the list of simple metadata set for the object
	private ArrayList<OpenGraphMetadata> metadata;
	
	
	
	public void setTitle(String title) {
		this.title = new OpenGraphMetadata(Default_OG_Metadata.TITLE.text(), title);
	}

	public void setUrl(String url) {
		this.url =  new OpenGraphMetadata(Default_OG_Metadata.URL.text(), url);
	}

	public void setSiteName(String siteName) {
		this.siteName = new OpenGraphMetadata(Default_OG_Metadata.SITENAME.text(),siteName);
	}

	public void setDescription(String description) {
		this.description = new OpenGraphMetadata(Default_OG_Metadata.DESCRIPTION.text(),description);
	}

	public void setDeterminer(String determiner) {
		this.determiner = new OpenGraphMetadata(Default_OG_Metadata.DETERMINER.text(), determiner);
	}

	public void setLocale(String locale) {
		this.locale = new OpenGraphMetadata(Default_OG_Metadata.LOCALE.text(), locale);
	}
	
	public void addAlternativeLocale(String alt_locale) {
		this.metadata.add(new OpenGraphMetadata(Default_OG_Metadata.LOCALE_ALTERNATE.text(), alt_locale));
	}
	
	public void addAudio(String url, String secureUrl, String mimetype, String width, String height, String alt){
		ArrayList<OpenGraphMetadata> audio = new ArrayList<OpenGraphMetadata>();
		audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_URL.text(), url));
		if(secureUrl != null && secureUrl.length() > 0) {
			audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_SECURE_URL.text(), secureUrl));
		}
		if(mimetype != null && mimetype.length() > 0) {
			audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_TYPE.text(), mimetype));
		}
		if(width != null && width.length() > 0) {
			audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_WIDTH.text(), width));
		}
		if(height != null & height.length() > 0) {
			audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_HEIGHT.text(), height));
		}
		if(alt != null & alt.length() > 0) {
			audio.add(new OpenGraphMetadata(Default_OG_Metadata.IMAGE_ALT.text(), alt));
		}
	}
	
	////////////////////////////////////////////////////////////////
	/////			VERTICAL TYPES
	///////////////////////////////////////////////////////////////
	public void setMusicType(String subtype) {
		this.type = new MusicType(subtype);
	}
	
	public void setVideoType(String subtype) {
		this.type = new VideoType(subtype);
	}
	
	////////////////////////////////////////////////////////////////
	/////			NO-VERTICAL TYPES
	///////////////////////////////////////////////////////////////
	public void setArticleType() {
		this.type = new ArticleType();
	}
	
	public void setBookType() {
		this.type = new BookType();
	}
	
	public void setProfileType() {
		this.type = new ProfileType();
	}
	
	public void setWebsiteType() {
		this.type = new ProfileType();
	}
	
	public void setCustomType(OpenGraphObjectType customType) {
		this.type = customType;
	}
		
}
