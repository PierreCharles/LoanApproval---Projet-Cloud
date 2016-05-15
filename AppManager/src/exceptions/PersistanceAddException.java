package exceptions;

/**
 * Exception which is throws when there is a problem with an add in the Datastore
 */
public class PersistanceAddException extends Exception
{
	public PersistanceAddException(String message) 
	{
		super(message);
	}
}