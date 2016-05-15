package exceptions;

/**
 * Exception throws when there is a problem with a get in the Datastore
 */
public class PersistanceSelectException extends Exception
{
	public PersistanceSelectException(String message)
	{
		super(message);
	}
}
