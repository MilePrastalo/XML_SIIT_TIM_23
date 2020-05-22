package com.papershare.papershare.service;

import org.springframework.stereotype.Service;

import com.papershare.papershare.DTO.PaperUploadDTO;

@Service
public class PaperService {
	
	public void savePaper(PaperUploadDTO dto) {
		System.out.println(dto.getText());
	}

}
