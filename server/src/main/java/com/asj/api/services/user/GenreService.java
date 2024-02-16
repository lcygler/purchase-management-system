package com.asj.api.services.user;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asj.api.exceptions.AssociatedEntitiesException;
import com.asj.api.exceptions.EntityNotFoundException;
import com.asj.api.exceptions.UniqueViolationException;
import com.asj.api.models.user.GenreModel;
import com.asj.api.repositories.user.GenreRepository;
import com.asj.api.repositories.user.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	public List<GenreModel> getAllGenres() {
		return genreRepository.findAll();
	}

	public GenreModel getGenreById(Integer id) {
		return genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre not found"));
	}

	@Transactional
	public GenreModel createGenre(GenreModel genre) {
		if (!isNameUnique(genre.getName())) {
			throw new UniqueViolationException("Name must be unique");
		}

		genre.setCreatedAt(LocalDateTime.now());
		genre.setUpdatedAt(LocalDateTime.now());
		genre.setIsDeleted(false);
		
		GenreModel createdGenre = genreRepository.save(genre);
		entityManager.refresh(createdGenre);

		return createdGenre;
	}

	@Transactional
	public GenreModel updateGenre(Integer id, GenreModel genre) {
		if (!genreRepository.existsById(id)) {
			throw new EntityNotFoundException("Genre not found");
		}

		if (!isNameUniqueAndIdNot(genre.getName(), id)) {
			throw new UniqueViolationException("Name must be unique");
		}

		genre.setUpdatedAt(LocalDateTime.now());

		GenreModel updatedGenre = genreRepository.save(genre);
		entityManager.flush();
		entityManager.refresh(updatedGenre);

		return updatedGenre;
	}

	@Transactional
	public GenreModel patchGenre(Integer id, GenreModel genre) {
		GenreModel existingGenre = getGenreById(id);

		if (genre.getName() != null) {
			if (!isNameUniqueAndIdNot(genre.getName(), id)) {
				throw new UniqueViolationException("Name must be unique");
			}
			existingGenre.setName(genre.getName());
		}

		if (genre.getIsDeleted() != null) {
			if (userRepository.countByGenre(id) > 0) {
				throw new AssociatedEntitiesException();
			}
			existingGenre.setIsDeleted(genre.getIsDeleted());
		}

		existingGenre.setUpdatedAt(LocalDateTime.now());

		genreRepository.save(existingGenre);
		entityManager.flush();
		entityManager.refresh(existingGenre);

		return existingGenre;
	}

	@Transactional
	public GenreModel deleteGenre(Integer id) {
		GenreModel deletedGenre = getGenreById(id);

		if (userRepository.countByGenre(id) > 0) {
			throw new AssociatedEntitiesException();
		}

		genreRepository.deleteById(id);

		return deletedGenre;
	}

	private boolean isNameUnique(String name) {
		return !genreRepository.existsByName(name);
	}

	private boolean isNameUniqueAndIdNot(String name, Integer id) {
		return !genreRepository.existsByNameAndIdNot(name, id);
	}

	public boolean isIdValid(Integer id) {
		return genreRepository.existsById(id);
	}

}
