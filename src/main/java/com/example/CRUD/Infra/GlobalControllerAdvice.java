package com.example.CRUD.Infra;

import com.example.CRUD.Exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ResponseEntity <?> handleNotFoundException ( Exception e) {
      Map<String, Object> body = new HashMap<>();
      body.put("message", e.getMessage());
      return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }
}
