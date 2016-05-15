package exceptions;

/**
 * Exception throws when there is a delete probleme in the datastore
 */
public class PersistanceDeleteException extends Exception
{
	public PersistanceDeleteException(String message)
	{
		super(message);
	}
}
