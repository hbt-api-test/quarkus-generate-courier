package com.heinshon.obl.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierResponseDTO implements Serializable {

    private String tipoIdentificación;
    private Long numeroIdentificacion;
    private String complementoIdentificacion;
    private String razonSocial;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String apellidoCasada;
    private Long numeroNotificacion;
    private String citeNotificacion;
    private String fechaNotificacion;
    private String tipoNotificado;
    private String tipoNotificacion;
    private String referenciaNotificacion;
    private Long regional;
    private String contactoDestinatario;


}
