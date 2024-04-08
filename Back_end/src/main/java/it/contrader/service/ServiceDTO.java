package it.contrader.service;

public interface ServiceDTO<DTO> {
	
	public Iterable<DTO> getAll();

	public DTO read(Long id);
	
	public DTO insert (DTO dto);
	
	public DTO update (DTO dto);
	
	public void delete (long id);
}