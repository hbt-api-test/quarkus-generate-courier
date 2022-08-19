package com.heinshon.obl.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailParameterDTO {

    List<String> from;
    List<String> cc;
    List<String> bcc;
    String subject;
    String mailText;
    File file;

}
