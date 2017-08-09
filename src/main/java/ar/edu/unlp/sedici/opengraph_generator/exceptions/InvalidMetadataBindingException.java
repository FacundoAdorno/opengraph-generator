package ar.edu.unlp.sedici.opengraph_generator.exceptions;

/** 
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class InvalidMetadataBindingException extends Exception {
	/**
	 * This exception is raised when there exists invalid metadata binding for certain namespace.
	 * For instance, when the metadata 'og:audio:height' is instantiated, an {@link InvalidMetadataBindingException} must be raised, because the qualifier 'height' is not valid for metadata 'og:audio'.
	 * 
	 * @param message
	 */
	public InvalidMetadataBindingException(String message) {
		super(message);
	}
}
