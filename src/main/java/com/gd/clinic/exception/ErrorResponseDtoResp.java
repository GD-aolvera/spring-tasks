package com.gd.clinic.exception;

import com.gd.clinic.model.ErrorResponseDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponseDtoResp implements Serializable {
    List<ErrorResponseDto> listDto;
    public ErrorResponseDtoResp(){
        super();
        listDto=new ArrayList<ErrorResponseDto>();
    }

    public ErrorResponseDtoResp(List<ErrorResponseDto> listDto) {
        this.listDto = listDto;
    }

    public List<ErrorResponseDto> getListDto() {
        return listDto;
    }

    public void setListDto(List<ErrorResponseDto> listDto) {
        this.listDto = listDto;
    }
}
