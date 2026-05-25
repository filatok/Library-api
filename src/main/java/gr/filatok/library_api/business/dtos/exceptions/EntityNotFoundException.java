package gr.filatok.library_api.business.dtos.exceptions;

public class EntityNotFoundException extends RuntimeException{

	public EntityNotFoundException(String entityName, int id)
	{
		super(String.format("%s was not found with ID: %d", entityName, id));
	}
}
