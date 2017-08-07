/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.type;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import ar.edu.unlp.sedici.opengraph_generator.OpenGraphMetadata;
import ar.edu.unlp.sedici.opengraph_generator.OpenGraphNamespace;
import ar.edu.unlp.sedici.opengraph_generator.exceptions.InvalidMetadataBindingException;

/**
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class MusicType extends OpenGraphObjectType {
	
	/**
	 * Set the subtype as a 'music.song'. See at http://ogp.me/#type_music.song.
	 */
	public static final String SONG = "song";
	
	/**
	 * Set the subtype as a 'music.playlist'. See at http://ogp.me/#type_music.song.
	 */
	public static final String ALBUM = "album";
	
	/**
	 * Set the subtype as a 'music.album'. See at http://ogp.me/#type_music.song.
	 */
	public static final String PLAYLIST = "playlist";
	
	/**
	 * Set the subtype as a 'music.radio_station'. See at {@link http://ogp.me/#type_music.song}.
	 */
	public static final String RADIOSTATION = "radio_station";
	
	public static enum MusicMetadata{
		DURATION("duration"), 	ALBUM("album"), ALBUM_DISC("album:disc"), ALBUM_TRACK("album:track"), MUSICIAN("musician"), SONG("song"), SONG_DISC("song:disc"),
		SONG_TRACK("song:track"), RELEASE_DATE("release_date"), CREATOR("creator");
		
		private String metadataName;
		
		MusicMetadata(String metadataName){
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
	
	/**
	 * Create a MusicType using a specific subtype. 
	 * @param subtype		Use the MusicType constants to create a MusicType, i.e. {@code MusicType mt = new MusicType( MusicType.SONG );}
	 */
	public MusicType(String subtype) {
		super(subtype);
		this.typeNamespace = new OpenGraphNamespace("music", "http://ogp.me/ns/music#");
	}
	
	@Override
	protected void defineSubtypeMetadataRestrictions() {
		//music.song - http://ogp.me/#type_music.song
		createSubtypeMetadataRestriction(MusicType.SONG, MusicMetadata.DURATION.text(), MusicMetadata.ALBUM.text(), MusicMetadata.ALBUM_DISC.text(), MusicMetadata.ALBUM_TRACK.text(), MusicMetadata.MUSICIAN.text());
		
		//music.album - http://ogp.me/#type_music.album
		createSubtypeMetadataRestriction(MusicType.ALBUM, MusicMetadata.SONG.text(), MusicMetadata.SONG_DISC.text(), MusicMetadata.SONG_TRACK.text(), MusicMetadata.MUSICIAN.text(), MusicMetadata.RELEASE_DATE.text());
		
		//music.playlist - http://ogp.me/#type_music.playlist
		createSubtypeMetadataRestriction(MusicType.PLAYLIST, MusicMetadata.SONG.text(), MusicMetadata.SONG_DISC.text(), MusicMetadata.SONG_TRACK.text(), MusicMetadata.CREATOR.text());
		
		//music.radio_station - http://ogp.me/#type_music.radio_station
		createSubtypeMetadataRestriction(MusicType.RADIOSTATION, MusicMetadata.CREATOR.text());
	}
	
	/**
	 * Set duration ('music:duration') metadata
	 * @param duration		The song's length in seconds.
	 */
	public void setDuration(String duration) {
		try {
			addSimpleMetadataBySubtype(subtype, MusicMetadata.DURATION.text(), duration);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a song ('music:song:*') metadata
	 * @param songURL 	The song on an album. It is an URL to another OpenGraph object, related in some form to the current OpenGraph object.
	 * @param disc		(optional) 
	 * @param track	(optional)
	 */
	public void addSong(String songURL, String disc, String track ) {
		ArrayList<SimpleEntry<String, String>> song =  new ArrayList<SimpleEntry<String,String>>();
		song.add(new SimpleEntry<String, String>(MusicMetadata.SONG.text(), songURL));
		if(disc != null) {
			song.add(new SimpleEntry<String, String>(MusicMetadata.SONG_DISC.text(), disc));
		}
		if(track != null) {
			song.add(new SimpleEntry<String, String>(MusicMetadata.SONG_TRACK.text(), track));
		}
		try {
			addStructuredPropertyBySubtype(subtype, song);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add an album ('music:album:*') metadata
	 */
	public void addAlbum(String albumURL, String disc, String track ) {
		ArrayList<SimpleEntry<String, String>> album =  new ArrayList<SimpleEntry<String,String>>();
		album.add(new SimpleEntry<String, String>(MusicMetadata.ALBUM.text(), albumURL));
		if(disc != null) {
			album.add(new SimpleEntry<String, String>(MusicMetadata.ALBUM_DISC.text(), disc));
		}
		if(track != null) {
			album.add(new SimpleEntry<String, String>(MusicMetadata.ALBUM_TRACK.text(), track));
		}
		try {
			addStructuredPropertyBySubtype(subtype, album);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a musician ('music:musician') metadata
	 */
	public void addMusician(String musicianProfileURL) {
		try {
			addSimpleMetadataBySubtype(subtype,  MusicMetadata.MUSICIAN.text(), musicianProfileURL);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the release date ('music:release_date') metadata
	 */
	public void setReleaseDate(String releaseDate) {
		try {
			addSimpleMetadataBySubtype(subtype,  MusicMetadata.RELEASE_DATE.text(), releaseDate);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a creator ('music:creator') metadata
	 */
	public void addCreator(String creatorProfileURL) {
		try {
			addSimpleMetadataBySubtype(subtype,  MusicMetadata.CREATOR.text(), creatorProfileURL);
		} catch (InvalidMetadataBindingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MusicType music = new MusicType(MusicType.ALBUM);
		music.addSong("http://songs.com/theSong", "1", "2");
		music.addMusician("https://the-musician.bandcamp.com/");
		for (OpenGraphMetadata mtd : music.getTypeMetadata()) {
			System.out.println(mtd.print());
		}
		System.out.println();
	}
	
	
}
