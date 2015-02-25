package com.pernix.services;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.pernix.pojos.FolderResponse;
import com.pernix.services.workers.FolderWorker;

@Path("/folder")
public class Folder {
	
    @GET
    @Produces(MediaType.APPLICATION_XHTML_XML)
    public FolderResponse getFolder(
    		@DefaultValue("folderName") @QueryParam("folderName") String folderName) {
    	FolderResponse response = new FolderResponse();
    	FolderWorker worker = new FolderWorker(folderName, response);
    	worker.execute();
        return response;
    }
}