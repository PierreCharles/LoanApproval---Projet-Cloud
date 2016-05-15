package exceptions;

/**
 * Exception throws when there is a "not found" problem when you try to get some datas in the Datastore
 */
public class PersistanceNotFoundException extends Exception
{
	public PersistanceNotFoundException(String message)
	{
		super(message);
	}
}
