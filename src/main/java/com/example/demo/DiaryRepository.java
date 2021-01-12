package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends
				JpaRepository<Diary,Long>{

public List<Diary> findByIdIsNotNullOrderByIdDesc();

public List<Diary> findByUserdata(UserData userId);




}