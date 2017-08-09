/**
 * 
 */
package ar.edu.unlp.sedici.opengraph_generator.exceptions;

/**
 * @author facundo@sedici.unlp.edu.ar
 *
 */
public class AbsentMandatoryMetadataException extends Exception {

	/**
	 * This exception is raised when there is absent some mandatory metadata for an OpenGraph object. For instance, if an object doesn't have the 'og:title' medatata, a 
	 * {@link AbsentMandatoryMetadataException} must be raised.
	 * @param message
	 */
	public AbsentMandatoryMetadataException(String message) {
		super(message);
	}
}
