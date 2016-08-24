/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aryosh.rest;

import com.aryosh.bl.GeographicInit;
import com.aryosh.var.DriverLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author AryoSH XLINK
 */
@Path("/service")
public class HandleService
{

    @GET
    @Path("/drivers")
    public Response getInfo(
            @QueryParam("latitude") String latitude,
            @QueryParam("longitude") String longitude,
            @QueryParam("radius") String radius,
            @QueryParam("limit") String limit)
    {
        try
        {
            String result = "";
            if (!validate(latitude, longitude, radius, limit))
            {
                result = "{'errors': ['Latitude should be between +/- 90']}";
                return Response.status(400).entity(result).build();
            }
            if (radius == null || "".equals(radius))
            {
                radius = "500";
            }

            if (limit == null || "".equals(limit))
            {
                limit = "10";
            }

            double currLatitude = Double.parseDouble(latitude);
            double currLongitude = Double.parseDouble(longitude);
            int currRadius = Integer.parseInt(radius);
            int currLimit = Integer.parseInt(limit);

            ArrayList<DriverLocation> driverLoc = new GeographicInit().compare(currLatitude, currLongitude, currRadius, currLimit);
            if (driverLoc.isEmpty())
            {
                return Response.status(200).entity("{\"errors\": [\"Data is empty\"]}").build();
            }
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(driverLoc);
            return Response.status(200).entity(jsonInString).build();
        } catch (NumberFormatException ex)
        {
            String result = "{'errors': ['Exception Error']}";
            return Response.status(400).entity(result).build();
        } catch (JsonProcessingException ex)
        {
            String result = "{'errors': ['Exception Error']}";
            return Response.status(400).entity(result).build();
        }

    }

    private boolean validate(String latitude, String longitude, String radius, String limit)
    {
        boolean ret = true;
        if (latitude == null || "".equals(latitude))
        {
            ret &= false;
        }

        if (longitude == null || "".equals(longitude))
        {
            ret &= false;
        }
        return ret;
    }
}
