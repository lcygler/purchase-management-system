package com.asj.api.controllers.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asj.api.models.user.GenreModel;
import com.asj.api.services.user.GenreService;
import com.asj.api.utils.ValidationUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/genres")
public class GenreController {

	@Autowired
	GenreService genreService;

	@GetMapping("/test")
	public String test() {
		return "Pass!";
	}

	@GetMapping
	public ResponseEntity<List<GenreModel>> getAllGenres() {
		List<GenreModel> genres = genreService.getAllGenres();
		return ResponseEntity.ok(genres);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GenreModel> getGenreById(@PathVariable Integer id) {
		GenreModel genre = genreService.getGenreById(id);
		return ResponseEntity.ok(genre);
	}

	@PostMapping
	public ResponseEntity<GenreModel> createGenre(@Valid @RequestBody GenreModel genre, BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		GenreModel createdGenre = genreService.createGenre(genre);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GenreModel> updateGenre(@PathVariable Integer id, @Valid @RequestBody GenreModel genre,
			BindingResult bindingResult) {
		ValidationUtils.handleErrors(bindingResult);
		GenreModel updatedGenre = genreService.updateGenre(id, genre);
		return ResponseEntity.ok(updatedGenre);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<GenreModel> patchGenre(@PathVariable Integer id, @Valid @RequestBody GenreModel genre,
			BindingResult bindingResult) {
		ValidationUtils.handlePartialErrors(bindingResult, genre);
		GenreModel patchedGenre = genreService.patchGenre(id, genre);
		return ResponseEntity.ok(patchedGenre);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<GenreModel> deleteGenre(@PathVariable Integer id) {
		GenreModel deletedGenre = genreService.deleteGenre(id);
		return ResponseEntity.ok(deletedGenre);
	}
}