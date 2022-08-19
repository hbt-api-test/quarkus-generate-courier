package com.heinshon.obl.service;

import com.heinshon.obl.model.DTO.CourierResponseDTO;

import javax.ws.rs.core.Response;
import java.util.List;

public interface IGenerateCourier {


    Response getGenerateCourier (List<CourierResponseDTO> courierResponseDto) throws Exception;
/*
    Response getGenerateCourierWithMail (List<CourierResponseDTO> courierResponseDto, String userMail, String subjet, String texto, String namefile) throws Exception;
*/

    Response getGenerateCourierWithMailAuto (List<CourierResponseDTO> courierResponseDto) throws Exception;
}
