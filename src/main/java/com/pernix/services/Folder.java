package com.pernix.services;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/folder")
public class Folder {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHtmlHello(
    		@DefaultValue("folderName") @QueryParam("folderName") String folderName) {
        return "Folder Name: " + folderName + "";
    }
    
    
}