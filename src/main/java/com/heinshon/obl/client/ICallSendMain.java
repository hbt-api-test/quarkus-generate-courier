package com.heinshon.obl.client;

import com.heinshon.obl.model.DTO.MailParameterDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient
@Path("/send/mail")
public interface ICallSendMain {

    @POST
    @Path("/attachment")
    Response sendMail(MailParameterDTO mailParameterDTO);
}
