package com.project.Controller;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.project.Model.Client;
import com.project.Model.Personnel;
import com.project.Model.Service;
import com.project.Model.ServiceWrapper;
import com.project.Model.TimeTable;
import com.project.Model.Rest.FreeTimeResponse;
import com.project.Model.Rest.ReservationRequest;
import com.project.Model.Rest.TimeTableRequest;
import com.project.Service.AdministratorService;
import com.project.Service.ClientService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/reg")
@Api(value = "Main microservice", description = "Operations for booking services")
public class RestController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Registation user")
    @PostMapping(value = "/addClient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPerson(@RequestBody Client client) {
        try {
            administratorService.createClient(client);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Customer deactivation")
    @PutMapping("/disableClient/{id}")
    public ResponseEntity<Void> disableClient(@PathVariable("id") Long id) {
        try {
            administratorService.disableClient(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addService", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addService(@RequestBody ServiceWrapper serviceWrapper) {
        try {
            administratorService.addPersonnelAndService(serviceWrapper);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addTimeTable", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addPersonnel(@RequestBody TimeTableRequest timeTableRequest) {
        try {
            administratorService.addTimeTableToPerson(timeTableRequest);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findServiceByLastName")
    public ResponseEntity<List<Service>> findServiceByLastName(@RequestParam String lastName) {
        try {
            return new ResponseEntity<List<Service>>(clientService.findServices(lastName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Service>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findPersonnelByServiceName")
    public ResponseEntity<List<Personnel>> selectPersonnelByServiceName(@RequestParam String descr) {
        try {
            return new ResponseEntity<List<Personnel>>(clientService.findPersonnels(descr), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<Personnel>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findTime")
    public ResponseEntity<TimeTable> checkTimeTable(@RequestParam String lastName,
                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            return new ResponseEntity<TimeTable>(clientService.findTimeTable(lastName, date), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<TimeTable>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/addReservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReservation(@RequestBody ReservationRequest reservationRequest) {
        try {
            if (clientService.addReservation(reservationRequest))
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            else
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // to jest do poprawy -> dodanie id klienta
    @PutMapping(value = "/disableReservation/{id}")
    public ResponseEntity<Void> disableReservation(@RequestParam String serviceName, @PathVariable Long id,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            clientService.disableReservation(serviceName, id, date);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/checkFreeTime")
    public ResponseEntity<List<FreeTimeResponse>> checkFreeTime(@RequestParam String lastName,
                                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        try {
            return new ResponseEntity<List<FreeTimeResponse>>(clientService.checkFreeTime(date, lastName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<FreeTimeResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
