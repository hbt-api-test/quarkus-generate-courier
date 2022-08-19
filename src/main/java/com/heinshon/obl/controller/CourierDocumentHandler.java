package com.heinshon.obl.controller;

import com.heinshon.obl.model.DTO.CourierResponseDTO;
import com.heinshon.obl.service.IGenerateCourier;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/send")
@Slf4j
@Singleton
@PermitAll
public class CourierDocumentHandler {

    @Inject
    IGenerateCourier igenerateCourier;


    @ConfigProperty(name = "send.courier.document.message.mail")
    private String mailUser;

    @POST
    @Path("/document")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGeneratorCourier(@Valid List<CourierResponseDTO> courierResponseDto) throws Exception {

        log.info("Generando el archivo courier");
        return Response.ok(igenerateCourier.getGenerateCourier(courierResponseDto)).build();

    }


    @POST
    @Path("/documentAuto")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getGeneratorCourierWithMailAuto(@Valid List<CourierResponseDTO> courierResponseDto) throws Exception {

        log.info("Generando el archivo courier");
        return Response.ok(igenerateCourier.getGenerateCourierWithMailAuto(courierResponseDto)).build();

    }


  /*  @GET
    @Path("/attachment")

    public void sendEmailWithAttachment() {
        String cuentaCorreo= "fraygiraldo.13@gmail.com";
        String subject="Archivo generado para envío a Courier";
        String texto= "Se ha generado exitosamente el archivo que relaciona las notificaciones para envio a Courier";
        //log.info("enviando el archivo csv a yherrera..........");
        mailer.send(Mail.withText(cuentaCorreo, subject,
                        texto)
              /*  .addAttachment("my-file-1.txt",
                        "content of my file".getBytes(), "text/plain")*/
   /*             .addAttachment("NotificacionesGestoraEntregaCourier20220817_134212.csv",
                        new File("NotificacionesGestoraEntregaCourier20220817_134212.csv"), "text/plain")
               //"D:\\test\\" + nombre
        );
    }*/





 /*   @GET
    @Path("/mail")
    public void sendASimpleEmail() {
        mailer.send(Mail.withText("fraygiraldo.13@gmail.com", "A simple email from quarkus", "This is my body"));
    }*/

 /*   @GET
    @Path("/html")
    public void sendingHTML() {
        String body = "<strong>Hello!</strong>" + "\n" +
                "<p>Here is an image for you: <img src=\"cid:my-image@quarkus.io\"/></p>" +
                "<p>Regards</p>";
        mailer.send(Mail.withHtml("fraygiraldo.13@gmail.com", "An email in HTML", body)
                .addInlineAttachment("quarkus-logo.png",
                        new File("quarkus-logo.png"),
                        "image/png", "<my-image@quarkus.io>"));
    }*/



}