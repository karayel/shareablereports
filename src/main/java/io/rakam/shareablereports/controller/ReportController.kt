package io.rakam.shareablereports.controller

import io.rakam.shareablereports.dto.ReportRequestDTO
import io.rakam.shareablereports.dto.ResponseDTO
import io.rakam.shareablereports.entity.Report
import io.rakam.shareablereports.service.ReportService
import org.rakam.server.http.HttpService
import org.rakam.server.http.annotations.*
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.PUT
import javax.ws.rs.Path
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


@Path("/rest/v1")
class ReportController : HttpService {

    private val service: ReportService

    constructor(service: ReportService) : super() {
        this.service = service
    }

    @JsonRequest
    @Path("/report")
    @ApiOperation(value = "Create report")
    fun createReport(@BodyParam reportRequestDTO: ReportRequestDTO): ResponseDTO {
        return service.add(reportRequestDTO)
    }

    @GET
    @JsonRequest
    @Path("/report")
    @ApiOperation(value = "Read report")
    fun readReport(@QueryParam(value = "reportId", required = true) reportId: Long): ResponseDTO {
        return service.get(reportId);
    }

    @PUT
    @JsonRequest
    @Path("/report")
    @ApiOperation(value = "Edit report")
    fun editReport(@QueryParam(value = "reportId", required = true) reportId: Long, @BodyParam report: ReportRequestDTO): ResponseDTO {
        return service.update(reportId, report)
    }

    @DELETE
    @Path("/report")
    @ApiOperation(value = "Delete report")
    fun deleteReport(@QueryParam(value = "reportId", required = true) reportId: Long): ResponseDTO {
        return service.delete(reportId)
    }
}