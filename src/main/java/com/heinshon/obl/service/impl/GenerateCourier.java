package com.heinshon.obl.service.impl;

import com.heinshon.obl.model.DTO.CourierResponseDTO;
import com.heinshon.obl.client.ICallSendMain;
import com.heinshon.obl.model.DTO.MailParameterDTO;
import com.heinshon.obl.service.IGenerateCourier;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.client.exception.ResteasyWebApplicationException;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ApplicationScoped
@Slf4j
@PermitAll
public class GenerateCourier implements IGenerateCourier {

    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    @Inject
    @RestClient
    ICallSendMain callSendMain;

    @Inject
    Mailer mailer;

    @ConfigProperty(name = "send.courier.document.message.nameCourier")
    private String nameCourier;

    @Override
    public Response getGenerateCourier(List<CourierResponseDTO> courierResponseDtoList) throws Exception {

        log.info("fecha del sistema formateada " + LocalDateTime.now().format(dateFormat));

        Writer writer = Files.newBufferedWriter(Paths.get(nameCourier + LocalDateTime.now().format(dateFormat) + ".csv"));

        CSVPrinter printer = CSVFormat.DEFAULT
                .withDelimiter('|')
                .print(writer);

         List<Object[]> data = new ArrayList<>();

        if (!courierResponseDtoList.isEmpty()) {

            log.info("Recorriendo el contenido de la lista para armar el archivo csv");

            courierResponseDtoList.stream().forEach(n -> {
                try {
                    data.add(new Object[] {n.getTipoIdentificación(),n.getNumeroIdentificacion(),
                            n.getComplementoIdentificacion(),n.getRazonSocial(),
                            n.getPrimerNombre(),n.getSegundoNombre(),n.getPrimerApellido(),n.getSegundoApellido(),
                            n.getApellidoCasada(),n.getNumeroNotificacion(),n.getCiteNotificacion(),n.getFechaNotificacion(),
                            n.getTipoNotificado(),n.getTipoNotificacion(), n.getReferenciaNotificacion(),n.getRegional(),
                            n.getContactoDestinatario()});


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            printer.printRecords(data);


            // flush the stream
            printer.flush();

            // close the writer
            writer.close();
           // sizeFile();


        }
   return null;
    }
/*

    @Override
    public Response getGenerateCourierWithMail(List<CourierResponseDTO> courierResponseDtoListMail, String userMail, String subjet, String texto, String namefile) throws Exception {

        log.info("fecha del sistema formateada " + LocalDateTime.now().format(dateFormat));

       Writer writer = Files.newBufferedWriter(Paths.get(nameCourier + LocalDateTime.now().format(dateFormat) + ".csv"));
      //  InputStream writer = Files.newInputStream(Path.of(nameCourier + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".csv"));

        CSVPrinter printer = CSVFormat.DEFAULT
                .withDelimiter('|')
                .print(writer);//((Appendable) writer);

        List<Object[]> data = new ArrayList<>();

        if (!courierResponseDtoListMail.isEmpty()) {

            log.info("Recorriendo el contenido de la lista para armar el archivo csv");

            courierResponseDtoListMail.stream().forEach(n -> {
                try {
                    data.add(new Object[] {n.getTipoIdentificación(),n.getNumeroIdentificacion(),
                            n.getComplementoIdentificacion(),n.getRazonSocial(),
                            n.getPrimerNombre(),n.getSegundoNombre(),n.getPrimerApellido(),n.getSegundoApellido(),
                            n.getApellidoCasada(),n.getNumeroNotificacion(),n.getCiteNotificacion(),n.getFechaNotificacion(),
                            n.getTipoNotificado(),n.getTipoNotificacion(), n.getReferenciaNotificacion(),n.getRegional(),
                            n.getContactoDestinatario()});


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            printer.printRecords(data);


            // flush the stream
            printer.flush();

            // close the writer
            writer.close();
          //  sizeFile();


            File pathfile = new File((nameCourier + LocalDateTime.now().format(dateFormat) + ".csv"));
            namefile= pathfile.getName();

            sendMailCourier(userMail, subjet, texto, namefile);

         */
/*   byte[] document= writer.readAllBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(document);

            log.info("el csv en byte" + document);*//*




        }



        return null;
    }
*/

/*    private void sendMailCourier(String userMail, String subjet, String texto, String file) throws Exception {

                callSendMain.getCallSendMail(userMail,subjet,texto,file);

    }*/


    @Override
    public Response getGenerateCourierWithMailAuto(List<CourierResponseDTO> courierResponseDtoListMail) throws Exception {
        sizeFile();
        log.info("fecha del sistema formateada " + LocalDateTime.now().format(dateFormat));

        String nameCsvFile = nameCourier + LocalDateTime.now().format(dateFormat) + ".csv";

        Writer writer = Files.newBufferedWriter(Paths.get(nameCsvFile));

        CSVPrinter printer = CSVFormat.DEFAULT
                .withDelimiter('|')
                .print(writer);

        List<Object[]> data = new ArrayList<>();

        if (!courierResponseDtoListMail.isEmpty()) {

            log.info("Recorriendo el contenido de la lista para armar el archivo csv");

            courierResponseDtoListMail.stream().forEach(n -> {
                try {
                    data.add(new Object[] {n.getTipoIdentificación(),n.getNumeroIdentificacion(),
                            n.getComplementoIdentificacion(),n.getRazonSocial(),
                            n.getPrimerNombre(),n.getSegundoNombre(),n.getPrimerApellido(),n.getSegundoApellido(),
                            n.getApellidoCasada(),n.getNumeroNotificacion(),n.getCiteNotificacion(),n.getFechaNotificacion(),
                            n.getTipoNotificado(),n.getTipoNotificacion(), n.getReferenciaNotificacion(),n.getRegional(),
                            n.getContactoDestinatario()});


                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            printer.printRecords(data);


            // flush the stream
            printer.flush();

            // close the writer
            writer.close();

            File pathfile = new File(nameCsvFile);
            String namefile= pathfile.getName();

            //sendEmailWithAttachment(namefile);
            //No se conoce cual el es el destinatario del correo
            MailParameterDTO mailParameterDTO = MailParameterDTO
                    .builder()
                    .from(Arrays.asList("yherrera@heinsohn.com.co","jsolis@heinsohn.com.co"))
                    .subject("Archivo generado para envío a Courier")
                    .mailText("Se ha generado exitosamente el archivo que relaciona las notificaciones para envio a Courier")
                    .file(pathfile)
                    .build();


            try {
                callSendMain.sendMail(mailParameterDTO);
            }catch (ResteasyWebApplicationException re){
                log.error("Error: " + re.getMessage());
                throw new Exception(re.getMessage());
            }

        }

        return null;
    }



    public void sizeFile(){
        File directorio = new File("C://Heinsohn");
        File[] ficheros = directorio.listFiles();

        log.info("directorio del fichero  " +  directorio);
        log.info("ficheros del directorio  " +  ficheros);

        long size = 1;

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss");
        formatter.format(date);


        for (int x = 0; x < ficheros.length; x++){

            if (ficheros[x].getName().contains("NotificacionesGestoraEntregaCourier")) {

                ficheros[x].getName();

                size = ficheros[x].length();

                Date fechaFichero = new Date(ficheros[x].lastModified());
                SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss");
                formatear.format(fechaFichero);

                String fecha1 = formatter.format(date);
                String fecha2 = formatear.format(fechaFichero);

                log.info("fecha actual del sistema formateada          " + formatter.format(date));
                log.info("fecha de la ultima modificacion formateada          " + formatear.format(fechaFichero));

                if(date.after(fechaFichero)){

                    log.info("fecha de fichero antes q la actual" + fechaFichero + " " + date);
                    log.info("nombre del fichero  " + ficheros[x].getName());
                    log.info("tamaño del fichero  " + size);
                    log.info("fecha de la ultima modificacion del fichero  " + formatear.format(fechaFichero));
                    ficheros[x].delete();

                }



            }

        }

    }

}
